
Feature: Purchase the order from Ecommerce Website
  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the Order
    Given Login with the username <name> and password <password>
    When I add <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on Confirmation Page

    Examples:
      | name                | password       | productName |
      | alex_jely@yahoo.com | LearnSelenium1 | ZARA COAT 3 |
