package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

public class DemoShopCartPage extends PageObject {
int expectedTotal;
int ActualTotal;
String Id;
    @Step
    public void ClickPlaceOrder()
    {
        expectedTotal= Integer.parseInt($(By.xpath("//h3[@id='totalp']")).getText());
        $(By.xpath("//button[contains(text(),'Place Order')]")).click();
    }
    @Step
    public void FillPurchaseForm()
    {
        $(By.xpath("//input[@id='name']")).type("Ketan");
        $(By.xpath("//input[@id='country']")).type("India");
        $(By.xpath("//input[@id='city']")).type("New Delhi");
        $(By.xpath("//input[@id='card']")).type("100110011001");
        $(By.xpath("//input[@id='month']")).type("June");
        $(By.xpath("//input[@id='year']")).type("2020");
    }
    @Step
    public void clickPurchase()
    {
        $(By.xpath("//button[contains(text(),'Purchase')]")).click();
    }

    @Step
    public void RecordReceipt()
    {
        String receipt =$(By.xpath("//body/div[10]/p[1]")).getText();
        Id =receipt.split("Id: ")[1].split("\n")[0];
        ActualTotal=Integer.parseInt(receipt.split("Amount: ")[1].split(" USD")[0]);
    }

    @Step
    public void VerifyPurchaseAmount()
    {
        System.out.println("Purchase Id is "+Id);
        Assert.assertEquals(expectedTotal, ActualTotal);
        $(By.xpath("//button[contains(text(),'OK')]")).click();
    }
}
