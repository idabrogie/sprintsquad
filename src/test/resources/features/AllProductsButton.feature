# Fatima
Feature: Testing the AllProductsButton

  Scenario: Navigate to shopPage on button click
    Given User visiting webshop
    When user clicks the AllProductButton
    Then user should be redirected to shop page
