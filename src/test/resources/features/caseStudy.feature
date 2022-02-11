@kloia
Feature: Test Automation Case Study-2 (API)

  Scenario Outline: Task-1
    * Finds pets with using path "pet/findByStatus" and query params "<statusQuery>"
    * Verify status code should be 200
    * Verify "Content-Type" should be "application/json"
    * Verify status should be equal to "<statusQuery>"
    * Verify each item in response should have id not null

    Examples:
      | statusQuery |
      | available   |
      | pending     |
      | sold        |

  Scenario: Task-3
    * Create user with using path "user" and set the "Content-Type" to "application/json"
    * Verify status code should be 200
    * Verify message should be equal to posted id

  Scenario: Task-4
    * Create user with using path "user" and set the Json body with desired parameters
      | username | lastname  |
      | Adem     | KloiaCase |
    * Verify status code should be 200
    # As I searched Calling Scenario from an other one is not possible at Gherkin. That is why ı only modified it as stated at Task-4