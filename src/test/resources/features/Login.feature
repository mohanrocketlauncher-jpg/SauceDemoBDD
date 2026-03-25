Feature: Login Functionality

  Scenario: Successful login
    Given I open Chrome and navigate to "https://www.saucedemo.com/"
    When I enter "standard_user" and "secret_sauce"
    And I click login
    And I add the "Sauce Labs Backpack" to the cart
    Then the cart badge should show "1"