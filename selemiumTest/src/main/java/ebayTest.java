
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.NoSuchElementException;



public class ebayTest {
    WebDriver driver;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browser) {

        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver\",\"P:\\\\selenium\\\\selemiumTest\\\\resources\\\\chromedriver.exe", "chromedriver");
            driver = new ChromeDriver();
        }

        if(browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver\",\"P:\\\\selenium\\\\selemiumTest\\\\resources\\\\msedgedriver.exe", "msedgedriver");
            driver = new EdgeDriver();
        }

    }

    @Parameters({"url"})
    @Test(priority=0)
    public void searchEbay(String url){
//        String url="https://www.ebay.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        String title = driver.getTitle();
        System.out.println("Title of the page is : " + title);
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals(url)) {
            System.out.println("Verification Successful - The correct Url is opened.");
        } else {
            System.out.println("Verification Failed - An incorrect Url is opened.");
            System.out.println("Actual URL is : " + actualUrl);
            System.out.println("Expected URL is : " + url);
        }
    }

    @Test(priority = 1)
    public void selectCellPhoneCategory(){
        homePage home = new homePage(driver);
        home.selectCategory();
    }

    @Test(priority = 2)
    public void searchMobilePhone(){
        homePage home = new homePage(driver);
        home.searchMainProduct("Mobile phone");
    }

    @Test(priority = 3)
    public void selectAppleBrand(){
        searchResultPage resultPage=new searchResultPage(driver);
        resultPage.selectBrand();
    }

    @Test(priority = 4)
    public void selectFirstSearchItem(){
        searchResultPage resultPage=new searchResultPage(driver);
        resultPage.selectFirstOption();
    }

    @Test(priority = 5)
    public void navigateToNewWindow() {
        // Navigate to a new window
        String mainWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    @Test(priority = 6)
    public void selectDropOption() {
        for (int i = 0; i <6; i++) {
            String selectId = "x-msku__select-box-100";
            String num = String.valueOf(i);
            String dropId = selectId + num;

            try {
                WebElement dropdown = driver.findElement(By.id(dropId));
                Duration timeout = Duration.ofSeconds(10);

                WebDriverWait wait = new WebDriverWait(driver, timeout);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(dropId)));
                if (dropdown.isDisplayed()) {
                    Select select = new Select(dropdown);
                    try {
                        select.selectByIndex(1);
                    } catch (Exception e) {
                        select.selectByIndex(4);
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Dropdown with ID " + dropId + " does not exist, skipping...");
                continue;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    @Test(priority = 7)
    public void checkProductDetails(){
        productPage product=new productPage(driver);
        product.getDetails();
    }


    @AfterTest
    public void afterTest(){
        driver.close();
        driver.quit();
    }
}
