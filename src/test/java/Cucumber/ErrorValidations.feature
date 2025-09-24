
Feature: Error validation
  @ErrorValidation
  Scenario Outline:
    Given I landed on Ecommerce Page
    When Login with the username <name> and password <password>
    Then "Incorrect email or password." message is displayed on Login Page

    Examples:
      | name                | password         |  |
      | alex_jely@yahoo.com | LearnrtSelenium6 |  |
