@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background: 
    Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Postive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANK YOU FOR THE ORDER." message is displayed on Confirmationpage

    Examples: 
      | name                        | password   | productName     |
      | surabathulagirija@gmail.com | Dajgar@123 | ADIDAS ORIGINAL |
