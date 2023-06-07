@tag1
  Feature: Purchase the order from Ecommerce website

    Background:
      Given I landed on Ecommerce page
    @Regression
    Scenario Outline:
      Given Logged in with <username> and <password>
      When I add the <productName> from cart
      And Checkout<productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
      Examples:

        | username            | password    | productName |
        | ts7837212@gmail.com | Welcome@123 | ZARA COAT 3 |
