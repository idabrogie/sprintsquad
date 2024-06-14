package com.example.produktapi.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SearchForProductStep {
    SeleniumConfig seleniumConfig = new SeleniumConfig();

    @And("User search for product {string}")
    public void userSearchForProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(seleniumConfig.getDriver(), Duration.ofSeconds(10));
        WebElement searchTxtField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));

        searchTxtField.clear();
        searchTxtField.click();

        new Actions(seleniumConfig.getDriver())
                .sendKeys(searchTxtField, productName)
                .perform();


    }

    @Then("User can see the search product and expect {int} products")
    public void userCanSeeTheSearchProductAndExpectProducts(int numberOfProduct) {
        WebDriverWait wait = new WebDriverWait(seleniumConfig.getDriver(), Duration.ofSeconds(20));

        // Wait for the products container to be visible
        WebElement mainElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("my-5")));

        // Find all 'col' div elements within the main element
        List<WebElement> colDivs = mainElement.findElements(By.xpath(".//div[contains(@class, 'col')]"));

        // Initialize the count for <div class="card h-100 p-3"> elements
        //int totalCount = 0;

        // Iterate through each 'col' div
//        for (WebElement colDiv : colDivs) {
//            // Find all 'card' divs within this 'col' div
//            List<WebElement> cardDivs = colDiv.findElements(By.xpath(".//div[contains(@class, 'card') and contains(@class, 'h-100') and contains(@class, 'p-3')]"));
//            // Increment the total count by the number of found 'card' divs
//            totalCount += cardDivs.size();
//        }
        int productSize = colDivs.size();
        Assertions.assertEquals(numberOfProduct, productSize);
        // Print the total count
       // System.out.println("Total number of <div class=\"card h-100 p-3\"> elements in <div class=\"col\">: " + totalCount);

    }

    @Then("Result should be an empty main")
    public void resultShouldBeAnEmptyMain() {
        // Find the main element by class name
        WebElement mainElement = seleniumConfig.getDriver().findElement(By.className("my-5"));
        // Get the inner HTML content of the main element
        String innerHTML = mainElement.getAttribute("innerHTML");
        // Assert that the inner HTML content is empty
        Assertions.assertTrue(innerHTML.trim().isEmpty(), "The inner HTML content is empty");
    }


}
