package starter.pages;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

@DefaultUrl("https://www.demoblaze.com")
public class DemoShopHomePage extends PageObject {

    @Step
    public void SelectGivenCategory(String category) {
        $(By.xpath("//a[contains(text(),'" + category + "')]")).click();

    }

    @Step
    public void AddProduct(DataTable table) {

            List<String> products = table.asList(String.class);
            for (int row = 0; row < products.size(); row++) {
                getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                int attempts=0;
                while (attempts<3) {
                 try {
                     $(By.xpath("//a[contains(text(),'" + products.get(row) + "')]")).click();
                     break;
                 }
                 catch (StaleElementReferenceException e)
                 {}
                }
                waitForCondition().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add to cart')]")));
                $(By.xpath("//a[contains(text(),'Add to cart')]")).click();
                waitForCondition().until(ExpectedConditions.alertIsPresent());
                getDriver().switchTo().alert().accept();
                $(By.xpath("//a[@id='nava']")).click();
                $(By.xpath("//a[contains(text(),'Laptops')]")).click();
            }

    }

    @Step
    public void DeleteProduct(DataTable table) {
        List<String> products = table.asList(String.class);
        try {
            for (int row = 0; row < products.size(); row++) {
                $(By.xpath("//a[@id='cartur']")).click();
                waitForCondition().until(ExpectedConditions.visibilityOf($(By.xpath("//table/tbody/tr[1]/td[last()]"))));
                $(By.xpath("//tbody/tr/td[contains(text(),'" + products.get(row) + "')]/following-sibling::td[last()]/a")).click();
                Thread.sleep(4000);
            }
        }
        catch (Exception e)
        {
          System.out.println(e);
        }
    }

}
