
package com.example.produktapi.stepDefinition;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AllProductsButtonSteps {
    SeleniumConfig seleniumConfig = new SeleniumConfig();

    @When("user clicks the AllProductButton")
    public void user_clicks_the_all_product_button() {
        WebElement allProductButton = seleniumConfig.getDriver().findElement(By.className("mb-lg-3"));
        allProductButton.findElement(By.tagName("button")).click();
    }

    @Then("user should be redirected to shop page")
    public void user_should_be_redirected_to_shop_page() {
        String expectedPage = seleniumConfig.getDriver().getCurrentUrl();
        String actualPage = "https://webshop-agil-testautomatiserare.netlify.app/products.html";
        Assertions.assertEquals(expectedPage, actualPage);
    }

}
