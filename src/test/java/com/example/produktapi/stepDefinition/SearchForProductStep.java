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
        // For input fields, get the value attribute
        String inputValue = searchTxtField.getAttribute("value");


        System.out.println("Value inside the search input field: " + inputValue);

    }

    @Then("User can see the search product and expect {int} products")
    public void userCanSeeTheSearchProductAndExpectProducts(int numberOfProduct) throws InterruptedException {
       // WebDriverWait wait = new WebDriverWait(seleniumConfig.getDriver(), Duration.ofSeconds(20));
        Thread.sleep(20000);
        // Wait for the main element with class 'my-5' to be visible
       // List<WebElement> mainElement = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//main/div"),2));

        // Use XPath to find <h3> elements containing specific text "WD"
        List<WebElement> matchingElements = seleniumConfig.getDriver().findElements(By.xpath("//main/div"));
        // Print the number of matching elements found
        System.out.println("Number of div elements in main: " + matchingElements.size());

        // iterate over list
        for (WebElement element : matchingElements) {
            System.out.println("Text content: " + element.getText());
        }
        Assertions.assertEquals(numberOfProduct, matchingElements.size());
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
