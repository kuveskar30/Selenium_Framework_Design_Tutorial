
@tag
Feature: Error validation
  I want to use this template for my feature file

  @LoginErrorValidationTest
  Scenario Outline: test case for error validation 
    Given I landed on ecommerce page
    When I logged in with user email <email> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | email 									 | password 	| desired_product_name  |
      | pratikkuveskar@gmail.com | kPratik@123 | ZARA COAT 3 					|
