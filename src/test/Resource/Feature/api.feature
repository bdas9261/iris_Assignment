Feature: Verify USD API
  Scenario: Verify API call is successful and returns valid price
    Given the API "https://open.er-api.com/v6/latest/USD"
    When a GET request is made
    Then the response status code should be 200
    And the response should contain a valid price

  Scenario: Fetch USD price against AED
    Given the API "https://open.er-api.com/v6/latest/USD"
    When a GET request is made
    Then the price should be in the range 3.6-3.7

  Scenario: Verify response time
    Given the API "https://open.er-api.com/v6/latest/USD"
    When a GET request is made
    Then the response time should not be less than 3 seconds

  Scenario: Verify 162 currency coin pair returned by the API
    Given the API "https://open.er-api.com/v6/latest/USD"
    When a GET request is made
    Then the response should contain 162 currency coin pairs

  Scenario: Verify API response matches the JSON schema
    Given the API "https://open.er-api.com/v6/latest/USD"
    When a GET request is made
    Then the response should match the JSON schema "path/to/schema.json"
