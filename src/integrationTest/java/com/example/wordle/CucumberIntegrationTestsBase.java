package com.example.wordle;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(classes = WordleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberIntegrationTestsBase {
    protected ResponseEntity<String> response;

    protected void executeGet(String path) {
        WebClient client = WebClient.create("http://localhost:8080");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(path)
                .retrieve();
        this.response = responseSpec.toEntity(String.class).block();
    }
}
