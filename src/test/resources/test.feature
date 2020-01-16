Feature: Cats endpoint

  Scenario: client makes call to GET /cats
    Given a client with the following roles : 'ROLE_cats-reader, ROLE_cats-writer'
    When the client calls the '/cats' endpoint
    Then the client receives status code of 200
    And the client receives a cat name 'Joe'

  Scenario: Unauthorized client makes call to GET /cats
    Given a client with the following roles : ''
    When the client calls the '/cats' endpoint
    Then the client receives status code of 403
