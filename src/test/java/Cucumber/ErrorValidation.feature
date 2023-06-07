@tag1
Feature: Purchase the order from Ecommerce website



  @ErrorValidation
  Scenario Outline:
    Given I landed on Ecommerce page
    When Logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples:

      | username            | password  |  |
      | ts7837212@gmail.com | Welcome@1 |  |
