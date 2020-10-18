Feature: Pet API

  Scenario: GET available Pets
    Given Pet store API is running
    When I request GET with resource as "findByStatus" and status as "available"
    Then I get status code as "200"
    And I verify response contains only available pets

  Scenario Outline: Create, Update and Delete a pet
    Given Pet store API is running
    When I request POST with request body as '<RequestBody>'
    Then I get status code as "200"
    And I Assert on request and response content
    And I update pet status to "sold"
    And I get status code as "200"
    And I verify pet status is updated to "sold"
    And I Delete this pet
    And I get status code as "200"
    And I verify pet is deleted successfully
    Examples:
      | RequestBody                                                                                                                                                                                                  |
      | {"id":23131,"category":{"id":100,"name":"German Shepherd"},"name":"Oggy","photoUrls":["https://www.pexels.com/photo/animal-canine-cute-dog-236622/"],"tags":[{"id":0,"name":"myPet1"}],"status":"available"} |
