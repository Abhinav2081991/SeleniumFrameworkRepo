@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
#    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order

    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
      | name                  | password       | productName |
      | abhinavtest@gmail.com | Newchandela47@ | ADIDAS ORIGINAL |

  @Datatable
  Scenario: Test the Datatable as Maps

   Given I have below items in the shopping cart
     | Item    | Quantity |
     | Laptop  | 2        |
     | IPhone  | 3        |
     | IPad    | 4        |
     | Oneplus | 6        |


  @Datatable1
  Scenario: Test the Datatable as List
    Given I have below items in the shopping cart list
      | Laptop  | 2 |
      | IPhone  | 3 |
      | IPad    | 4 |
      | Oneplus | 6 |
