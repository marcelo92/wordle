Feature: a random word is returned
  Scenario: client makes call to GET /word
    When the client calls /word
    Then the client receives status code of 200
    And the client receives a random word