Feature: Filter products by category

  Scenario Outline: Filter products by category
    Given user clicks on Menu item shop initially
    When the user clicks on the filter button for <category>
    Then the number of products should be <number>

    Examples:
      | category           | number |
      | "Men's clothing"   | 4      |
      | "Women's clothing" | 6      |
      | "Jewelery"         | 4      |
      | "Electronics"      | 6      |
      | "All"              | 20     |