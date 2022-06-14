Feature: Given a word, returns true if it's valid, false otherwise
  Scenario Outline: client sends a word to validate
    Given the client calls GET <word> /validate
    Then the client receives status code of <statusCode>
    And the client receives response <response>
    Examples:
      | word  | statusCode | response |
      | irate | 200        | "true"   |
      | abcd  | 200        | "false"  |
