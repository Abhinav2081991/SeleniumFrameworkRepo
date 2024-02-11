@tag
Feature: Error Handling

  Background:
    Given I landed on Ecommerce Page

  @ErrorHandling
  Scenario Outline: Positive Test of Submitting the order

    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." is displayed

    Examples:
      | name                  | password        | productName     |
      | abhinavtest@gmail.com | Newchandela47@1 | ADIDAS ORIGINAL |