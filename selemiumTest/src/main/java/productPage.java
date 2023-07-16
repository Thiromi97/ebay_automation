import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class productPage {
    @FindBy(xpath = "//*[@id=\"LeftSummaryPanel\"]/div[1]/div[1]/div/h1/span")
    private WebElement itemTitle;

    @FindBy(xpath = "//*[@id=\"mainContent\"]/form/div[2]/div/div[1]/div[1]/div/div[2]/div/span")
    private WebElement itemPrice;

    @FindBy(xpath = "//*[@id=\"mainContent\"]/form/div[1]/div[1]/div/div[2]/div/div/span/span[1]/span")
    private WebElement itemCondition;

    @FindBy(xpath = "//*[@id=\"qtyTextBox\"]")
    private WebElement itemQuantity;

    @FindBy(xpath = "//*[@id=\"mainContent\"]/form/div[2]/div/div[1]/div[2]/ul/li[2]/div/a")
    WebElement cartButton;

    @FindBy(xpath = "//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[2]/div/ul/li/div/div/div/div[1]/div/div[2]/div/h3/span/a")
    private WebElement cartName;

    @FindBy(xpath = "//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[2]/div/ul/li/div/div/div/div[1]/div/div[3]/div/div[1]/div[2]/div/div")
    private WebElement cartPrice;

    @FindBy(xpath = "//*[@id=\"mainContent\"]/div/div[3]/div[2]/div/div[2]/div[1]/div[1]/span/span/span")
    private WebElement cartQuantity;
    @FindBy (xpath = "//*[@id=\"mainContent\"]/div/div[3]/div[2]/div/div[2]/div[4]/div[2]")
    private WebElement cartSubTotal;

    @FindBy(xpath="//*[@id=\"mainContent\"]/div/div[3]/div[2]/div/div[2]/div[2]/div[2]")
    private WebElement shippingCost;

    public productPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void getDetails() {
// Get item Name and print in console
        String itemName = itemTitle.getText();
        System.out.println("Item Name: " + itemName);

        String itemCost = itemPrice.getText();
        System.out.println("Item Price: " + itemCost);

        String itemQuality = itemCondition.getText();
        System.out.println("Item Condition: " + itemQuality);

        String itemAmount = itemQuantity.getAttribute("value");
        System.out.println("Item Quantity: " + itemAmount);

        try {

            cartButton.click();
            System.out.println("Item Name in cart:" + cartName.getText());
            Assert.assertEquals(itemName, cartName.getText());
            System.out.println("Product Name is equal to the Purchased Product Name in the cart");

            // Remove "/ea" from itemCost
            String itemCostClean = itemCost.replace("/ea", "").trim();
            System.out.println("Item Price in cart:" + cartPrice.getText());
            Assert.assertEquals(itemCostClean, cartPrice.getText());
            System.out.println("Product Price is equal to the Purchased Product Price in the cart");

            String cartQty=cartQuantity.getText();
            String cartQtyClean = cartQty.replace("Item (", "").trim();
            String cartRealQty = cartQtyClean.replace(")","").trim();
            System.out.println("Item Quantity in cart:" + cartRealQty);
            Assert.assertEquals(itemAmount,cartRealQty);
            System.out.println("Product Quantity is equal to the Purchased Product Quantity in the cart");

            System.out.println("Sub Total in cart:" + cartSubTotal.getText());
            Assert.assertEquals(itemCostClean, cartSubTotal.getText());
            System.out.println("Cart Sub Total is equal to the Item Cost ");

            System.out.println("Shipping Cost:" + shippingCost.getText());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
