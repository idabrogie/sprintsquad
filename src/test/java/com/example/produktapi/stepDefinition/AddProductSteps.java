package com.example.produktapi.stepDefinition;
import io.cucumber.java.en.Then;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class AddProductSteps {

    SeleniumConfig seleniumConfig = new SeleniumConfig();
    @Then("a product should be added to the cart")
    public void a_product_should_be_added_to_the_cart() {
        WebDriverWait wait = new WebDriverWait(seleniumConfig.getDriver(), Duration.ofSeconds(10));

        // Wait for the cart badge to be updated
        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSize")));

        // Get the text of the cart badge
        String badgeText = cartBadge.getText();

        // Verify that the cart badge text has been updated to reflect the addition of the product
        Assertions.assertEquals(badgeText, "1", "Cart badge has been updated to indicate the addition of the product.");

    }



}