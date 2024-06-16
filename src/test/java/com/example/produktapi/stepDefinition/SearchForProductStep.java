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
import java.util.stream.Collectors;


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
    public void userCanSeeTheSearchProductAndExpectProducts(int numberOfProduct) {
        WebDriverWait wait = new WebDriverWait(seleniumConfig.getDriver(), Duration.ofSeconds(20));

        // Wait for the main element with class 'my-5' to be visible
        WebElement mainElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("my-5")));

        // Find all 'div' elements with class 'col' within the main element
        List<WebElement> colDivs = mainElement.findElements(By.cssSelector("div.col"));


        // Debug: Print the number of found 'col' div elements
        System.out.println("Total number of <div> elements with class 'col': " + colDivs.size());

        // Filter out only visible 'col' div elements using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) seleniumConfig.getDriver();
        List<WebElement> visibleColDivs = colDivs.stream()
                .filter(div -> (Boolean) js.executeScript("return arguments[0].offsetParent !== null;", div))
                .collect(Collectors.toList());

        // Count the number of visible 'col' div elements
        int numberOfVisibleColDivs = visibleColDivs.size();

        // Print the number of visible 'col' div elements
        System.out.println("Total number of visible <div> elements with class 'col' within 'my-5' class: " + numberOfVisibleColDivs);



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
