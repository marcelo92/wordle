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

    @When("the client calls \\/words\\/new")
    public void theClientCallsWord() {
        this.executeGet("/words/new");
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
    public void theClientCallsGET(String word) {
        this.executeGet("/words/"+word+"/validate");
    }


    @And("the client receives response {string}")
    public void theClientReceivesResponseResponse(String response) {
        var result = this.response.getBody();
        assertEquals(result, response);
    }

    @Given("the client calls the filter with {string} and {string} letters")
    public void theClientCallsTheFilterWithIncludedAndExcludedLetters(String included, String excluded) {
        this.executeGet("/words/filter?included="+included+"&excluded="+excluded);
    }

    @And("the client receives response list {string}")
    public void theClientReceivesResponseListResponse(String response) {
        var result = this.response.getBody();
        assert result != null;
        var strippedResult = result.replaceAll(",", " ").replaceAll("\"|\\[|\\]", "");
        assertEquals(response, strippedResult);
    }
}
