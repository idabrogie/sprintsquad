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

  #cenario: when user fills out the form correct with depit card
  #  When the user fill out the form with "Debit card" as payment method
    #And clicks on the continue button
    #Then the user should be redirected to https://webshop-agil-testautomatiserare.netlify.app/checkout?paymentMethod=on

  #Scenario: when user fills out the form correct with paypal
  #  When the user fill out the form with "PayPal" as payment method
    #And clicks on the continue button
    #Then the user should be redirected to https://webshop-agil-testautomatiserare.netlify.app/checkout?paymentMethod=on