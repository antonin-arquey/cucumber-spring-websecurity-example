Feature: the version can be retrieved
  @WithAdminUser
  Scenario: client makes call to GET /cats
    When the client calls /cats
    Then the client receives status code of 200
    And the client receives a cat name 'Joe'
