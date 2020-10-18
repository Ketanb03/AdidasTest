Feature: Demo Online Shop

  Scenario Outline: Purchase products from Demo online shop
    Given Customer is on the Demo online shop home page
    When Customer chooses the category as "<Category>"
    And Customer adds following products to Cart
      | Sony vaio i5 |
      | Dell i7 8gb  |
    And Customer deletes following products from Cart
      | Dell i7 8gb |
    And Customer clicks on Place order button
    And Customer fills in purchase form
    And Customer clicks on Purchase button
    Then I capture and log purchase Id and Amount
    And I verify purchase amount equals expected and click Ok
    Examples:
      | Category |
      | Laptops  |