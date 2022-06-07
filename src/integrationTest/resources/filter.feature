Feature: Given a list of letters to include and exclude, return the possible words
  Scenario Outline: client sends a two strings containing the letters to include and exclude from the word list
    Given the client calls the filter with <included> and <excluded> letters
    Then the client receives status code of <statusCode>
    And the client receives response list <response>
    Examples:
      | included | excluded | statusCode | response |
      | "de"       | ""         | 200        | "abcde defgh"  |
      | "de"       | "a"        | 200        | "defgh"  |
      | ""         | "de"       | 200        | "ijklm"  |

