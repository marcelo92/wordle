Feature: get a random word
  Scenario: client makes call to GET /words/new
    When the client calls /words/new
    Then the client receives status code of 200
    And the client receives a random word