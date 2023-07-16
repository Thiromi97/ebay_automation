import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class searchResultPage {
    @FindBy(xpath = "//*[@id=\"x-refine__group_1__0\"]/ul/li[1]/div/a/div/span/input")
    WebElement selectedBrand;

    @FindBy(xpath = "//*[@id=\"item3dd83400fe\"]/div/div[2]/a")
    WebElement firstResult;
    public searchResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void selectBrand(){
        try {
            selectedBrand.click();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void selectFirstOption(){
        try {
            firstResult.click();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
