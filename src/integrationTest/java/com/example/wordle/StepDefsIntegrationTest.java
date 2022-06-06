package com.example.wordle;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;

@CucumberContextConfiguration
@SpringBootTest(classes = WordleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefsIntegrationTest {
    private ResponseEntity<String> response;

    @When("the client calls \\/word")
    public void theClientCallsWord() {
        this.executeGet("/word");
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int statusCode) {
        var currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect", currentStatusCode.value(), is(statusCode));
    }

    @And("the client receives a random word")
    public void theClientReceivesARandomWord() {
        var word = response.getBody();
        assertThat("Response is not a valid word", word, hasLength(5));
    }

    private void executeGet(String path) {
        WebClient client = WebClient.create("http://localhost:8080");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(path)
                .retrieve();
        this.response = responseSpec.toEntity(String.class).block();
    }
}
