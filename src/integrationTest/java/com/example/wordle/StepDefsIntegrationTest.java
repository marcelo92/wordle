package com.example.wordle;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
public class StepDefsIntegrationTest extends CucumberIntegrationTestsBase {

    @When("the client calls \\/word")
    public void theClientCallsWord() {
        this.executeGet("/word");
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int statusCode) {
        var currentStatusCode = this.response.getStatusCode();
        assertThat("status code is incorrect", currentStatusCode.value(), is(statusCode));
    }

    @And("the client receives a random word")
    public void theClientReceivesARandomWord() {
        var word = response.getBody();
        assertThat("Response is not a valid word", word, hasLength(5));
    }

    @Given("the client calls GET {word}")
    public void theClientCallsGET(String endpoint) {
        this.executeGet("/word/"+endpoint+"/validate");
    }


    @And("the client receives response {string}")
    public void theClientReceivesResponseResponse(String response) {
        var result = this.response.getBody();
        assertEquals(result, response);
    }
}
