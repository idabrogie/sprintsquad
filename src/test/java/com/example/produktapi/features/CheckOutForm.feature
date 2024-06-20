Feature: User wants to checkout shopcart

  Background:
    Given User is on checkout page

  Scenario: Form is missing data
    When user clicks the continue to checkout
    Then the user gets validation errors and cant continue

  Scenario: When user fills out the Form with wrongdata
    When User fillout the form with invalid emailadress
    And user clicks the continue to checkout
    Then the user gets validation errors on email and cant continue

  Scenario: when user fills out the form correct with depit card
    When the user fill out the form with "debit" as payment method
    And user clicks the continue to checkout
    Then the user should be redirected

  Scenario: when user fills out the form correct with paypal
    When User change payment method to PayPal
    Then creditcard information should be hidden and paypal information is visible