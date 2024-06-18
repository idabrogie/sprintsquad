/*
Pär Hinds
Ida Brogie
Fathima Grahn
 */
package com.example.produktapi.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CheckoutFormSteps {
//Fatima och Ida

    SeleniumConfig seleniumConfig = new SeleniumConfig();

    @Given("User is on checkout page")
    public void userIsOnCheckoutPage() {
        seleniumConfig.getDriver().get("https://webshop-agil-testautomatiserare.netlify.app/checkout");
    }

    @When("User fillout the form with invalid emailadress")
    public void userFilloutTheFormWithInvalidEmailadress() {
        // Map<String, String> validCustomerData = validCustomer();
        for (Map.Entry<String, String> set : invalidCustomer().entrySet()) {
            WebElement formElement = seleniumConfig.getDriver().findElement(By.id(set.getKey()));
            new Actions(seleniumConfig.getDriver())
                    .sendKeys(formElement, set.getValue())
                    .perform();
            System.out.println(set.getKey() + " " + set.getValue());

        }
    }

    @When("user clicks the continue to checkout")
    public void userClicksTheContinueToCheckout() {
        WebDriverWait wait = new WebDriverWait(seleniumConfig.getDriver(), Duration.ofSeconds(10));
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Continue to checkout')]")));

        // Scroll into view
        ((JavascriptExecutor) seleniumConfig.getDriver()).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);

        // Click using JavaScript executor
        ((JavascriptExecutor) seleniumConfig.getDriver()).executeScript("arguments[0].click();", checkoutButton);
        // checkoutButton.click();
    }

    @Then("the user gets validation errors and cant continue")
    public void theUserCannotContinueToCheckout() {
        WebElement formElement = seleniumConfig.getDriver().findElement(By.className("needs-validation"));
        String classesForElement = formElement.getAttribute("class");
        boolean hasValidateClass = classesForElement.contains("was-validated");
        Assertions.assertTrue(hasValidateClass);
    }

    @Then("the user gets validation errors on email and cant continue")
    public void theUserGetsValidationErrorsOnEmailAndCantContinue() {
        WebElement emailInputtxtErrorMessage = seleniumConfig.getDriver().findElement(By.xpath("//input[@id='email']/following-sibling::*[1]"));
        Assertions.assertTrue(emailInputtxtErrorMessage.isDisplayed());
    }

    @Then("the user is still on checkout page")
    public void theUserIsStillOnCheckoutPage() {
        String expectedUrl = "https://webshop-agil-testautomatiserare.netlify.app/checkout";
        String actualUrl = seleniumConfig.getDriver().getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @When("the user fill out the form with {string} as payment method")
    public void theUserFillOutTheFormWithAsPaymentMethod(String payMethod) {
        Map<String, String> validCustomerData = validCustomer();
        for (Map.Entry<String, String> set : validCustomerData.entrySet()) {
            WebElement formElement = seleniumConfig.getDriver().findElement(By.id(set.getKey()));
            new Actions(seleniumConfig.getDriver())
                    .sendKeys(formElement, set.getValue())
                    .perform();
            System.out.println(set.getKey() + " " + set.getValue());

        }
        WebElement radioButtonPayment = seleniumConfig.getDriver().findElement(By.id(payMethod));
        radioButtonPayment.click();
    }

    public Map<String, String> validCustomer() {
        Map<String, String> validCustomerMap = new HashMap<>();
        validCustomerMap.put("firstName", "Ada");
        validCustomerMap.put("lastName", "Svensen");
        validCustomerMap.put("email", "ada.svensen@epost.com");
        validCustomerMap.put("address", "Bogatan 1");
        validCustomerMap.put("country", "Sverige");
        validCustomerMap.put("city", "Köping");
        validCustomerMap.put("zip", "48700");
        validCustomerMap.put("cc-name", "Ada Svensen");
        validCustomerMap.put("cc-number", "12356");
        validCustomerMap.put("cc-expiration", "24/03");
        validCustomerMap.put("cc-cvv", "123");
        return validCustomerMap;
    }

    public Map<String, String> invalidCustomer() {
        Map<String, String> validCustomerMap = new HashMap<>();
        validCustomerMap.put("firstName", "Ada");
        validCustomerMap.put("lastName", "Svensen");
        validCustomerMap.put("email", "ada.svensen");
        validCustomerMap.put("address", "Bogatan 1");
        validCustomerMap.put("country", "Sverige");
        validCustomerMap.put("city", "Köping");
        validCustomerMap.put("zip", "48700");
        validCustomerMap.put("cc-name", "Ada Svensen");
        validCustomerMap.put("cc-number", "12356");
        validCustomerMap.put("cc-expiration", "24/03");
        validCustomerMap.put("cc-cvv", "123");
        return validCustomerMap;
    }

    @Then("the user should be redirected")
    public void theUserShouldBeRedirected() {

        String expectedURL = "https://webshop-agil-testautomatiserare.netlify.app/checkout?paymentMethod=on";
        String actualURL = seleniumConfig.getDriver().getCurrentUrl();
        Assertions.assertEquals(expectedURL, actualURL);
    }

    @When("User change payment method to PayPal")
    public void userChangePaymentMethodToPayPal() {
        WebElement radioButtonPayment = seleniumConfig.getDriver().findElement(By.id("paypal"));
        radioButtonPayment.click();
    }

    @Then("creditcard information should be hidden and paypal information is visible")
    public void creditcardInformationShouldBeHiddenAndPaypalInformationIsVisible() {
        WebElement cardElement = seleniumConfig.getDriver().findElement(By.id("card"));
        WebElement paypalInfo = seleniumConfig.getDriver().findElement(By.id("paypalInfo"));
        Assertions.assertAll(
                () -> Assertions.assertFalse(cardElement.isDisplayed()),
                () -> Assertions.assertTrue(paypalInfo.isDisplayed())
        );
    }
}

