package com.example.produktapi.cucumber;
import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ClickOnCategories {

    SeleniumConfig seleniumConfig = new SeleniumConfig();

    @When("the user clicks on the filter button for {string}")
    public void the_user_clicks_on_the_filter_button_for(String category) {
        // Map category names to the corresponding HTML onclick values
        System.out.printf("Category from feature file: %s", category);
        try {
            WebElement filterButton = seleniumConfig.getDriver().findElement(By.linkText(category));
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            filterButton.click();

            // Logging
            System.out.println("Clicked on the filter button for category: " + category);

            // Logging
            System.out.println("Products should be rendered for category: " + category);
        } catch (Exception e) {
            // Print the page source for debugging
            System.out.println("Element not found. Printing page source:");
            System.out.println(e.getMessage());
            throw e; // Re-throw the exception after logging
        }

    }

    @Then("the number of products should be {int}")
    public void the_number_of_products_should_be(int expectedNumber) {

       try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Count the number of product items displayed
        List<WebElement> productItems = seleniumConfig.getDriver().findElements(By.xpath("//main/div"));
        System.out.println("Number of product items found: " + productItems.size());
        for (WebElement item : productItems) {
            System.out.println("Product item: " + item.getText());
        }
        int actualNumber = productItems.size();

        // Assert the number of products
        Assert.assertEquals(expectedNumber, actualNumber);
    }
}