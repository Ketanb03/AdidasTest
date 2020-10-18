package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.DemoShopCartPage;
import starter.pages.DemoShopHomePage;

public class DemoShopStepDefinitions {

    @Before
    public void setUp(){
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
        }
    }

    @Steps
    DemoShopHomePage homepage;
    @Steps
    DemoShopCartPage cartPage;

    @Given("Customer is on the Demo online shop home page")
    public void customerIsOnTheDemoOnlineShopHomePage() {
        homepage.open();
    }

    @When("Customer chooses the category as {string}")
    public void customerChoosesTheCategoryAs(String category) {
        homepage.SelectGivenCategory(category);
    }

    @And("Customer adds following products to Cart")
    public void customerAddsFollowingProductsToCart(DataTable table) {
        homepage.AddProduct(table);
    }

    @And("Customer deletes following products from Cart")
    public void customerDeletesFollowingProductsFromCart(DataTable table) {
        homepage.DeleteProduct(table);
    }

    @And("Customer clicks on Place order button")
    public void customerClicksOnPlaceOrderButton() {
        cartPage.ClickPlaceOrder();
    }

    @And("Customer fills in purchase form")
    public void customerFillsInPurchaseForm() {
        cartPage.FillPurchaseForm();
    }

    @And("Customer clicks on Purchase button")
    public void customerClicksOnPurchaseButton() {
        cartPage.clickPurchase();
    }

    @Then("I capture and log purchase Id and Amount")
    public void iCaptureAndLogPurchaseIdAndAmount() {
        cartPage.RecordReceipt();
    }

    @And("I verify purchase amount equals expected and click Ok")
    public void iVerifyPurchaseAmountEqualsExpectedAndClickOk() {
    }
}
