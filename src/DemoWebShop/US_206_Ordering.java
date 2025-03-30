package DemoWebShop;

import Utility.BaseDriver;
import Utility.MyFunc;
import Utility.UserInformation;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class US_206_Ordering extends BaseDriver {
    @Test
    public void Test_US_206() throws AWTException {
            MyFunc.loginTest();
            WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            Actions actions = new Actions(driver);
            MyFunc.Bekle(2);

            if (!driver.findElement(By.xpath("//span[@class='cart-qty']")).getText().equals("(0)")) {
                actions.moveToElement(driver.findElement(By.xpath("//span[@class='cart-qty']"))).click().build().perform();
                driver.findElement(By.xpath("//*[contains(@name,'itemquantity')]")).clear();
                driver.findElement(By.xpath("//*[contains(@name,'itemquantity')]")).sendKeys("0");
                actions.sendKeys(Keys.ENTER);
                myWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@alt='Tricentis Demo Web Shop']"))));
                actions.moveToElement(driver.findElement(By.xpath("//*[@alt='Tricentis Demo Web Shop']"))).click().build().perform();
            }

            WebElement computerBtn = driver.findElement(By.xpath("//*[contains(text(),'Computers')]"));

            actions.moveToElement(computerBtn).build().perform();
            MyFunc.Bekle(1);

            WebElement noteBooks = driver.findElement(By.xpath("//*[@class='top-menu']//a[@href='/notebooks']"));
            myWait.until(ExpectedConditions.visibilityOf(noteBooks));
            noteBooks.click();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0,200)");
            MyFunc.Bekle(1);

            WebElement link = driver.findElement(By.xpath("//h2[@class='product-title']/a"));
            myWait.until(ExpectedConditions.visibilityOf(link));
            link.click();

            WebElement addToCart2 = driver.findElement(By.xpath("//input[@id='add-to-cart-button-31']"));
            myWait.until(ExpectedConditions.visibilityOf(addToCart2));
            myWait.until(ExpectedConditions.elementToBeClickable(addToCart2));
            addToCart2.click();

            WebElement shoppingCart = driver.findElement(By.xpath("(//*[@class='cart-label'])[1]"));
            shoppingCart.click();
            MyFunc.Bekle(1);

            WebElement selectCountry = driver.findElement(By.xpath("//*[@id='CountryId']"));
            MyFunc.Bekle(1);
            Select select = new Select(selectCountry);
            select.selectByValue("2");

            WebElement selectCountry2 = driver.findElement(By.xpath("//*[@id='StateProvinceId']"));
            MyFunc.Bekle(1);
            Select select2 = new Select(selectCountry2);
            select2.selectByValue("71");
            MyFunc.Bekle(1);

            myWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='termsofservice']"))));
            WebElement checkBox = driver.findElement(By.xpath("//*[@id='termsofservice']"));
            checkBox.click();
            MyFunc.Bekle(1);

            WebElement checkOut = driver.findElement(By.xpath("//*[@id='checkout']"));
            checkOut.click();
            MyFunc.Bekle(1);

            WebElement continue1 = driver.findElement(By.xpath("//input[@class='button-1 new-address-next-step-button']"));
            continue1.click();
            MyFunc.Bekle(1);

            WebElement ShippingAddress = driver.findElement(By.xpath("//*[@id='shipping-address-select']"));
            WebElement inStorePickUp = driver.findElement(By.xpath("//*[@id='PickUpInStore']"));

            Assert.assertTrue("Wrong adress information !", ShippingAddress.getText().contains("Team5"));

            if (!inStorePickUp.isSelected())
                inStorePickUp.click();

            MyFunc.Bekle(1);
            WebElement continue2 = driver.findElement(By.xpath("//input[@onclick='Shipping.save()']"));
            continue2.click();
            MyFunc.Bekle(1);

            myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='paymentmethod_2']")));
            Assert.assertTrue("Payment method button isn't enabled", driver.findElement(By.xpath("//*[@id='paymentmethod_2']")).isEnabled());
            actions.moveToElement(driver.findElement(By.xpath("//*[@id='paymentmethod_2']"))).click().build().perform();
            Assert.assertTrue("Payment method isn't selected", driver.findElement(By.xpath("//*[@id='paymentmethod_2']")).isSelected());

            myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@value='Continue'])[4]")));
            Assert.assertTrue("Shipping Address Continue button isn't displayed", driver.findElement(By.xpath("(//*[@value='Continue'])[4]")).isDisplayed());
            actions.moveToElement(driver.findElement(By.xpath("(//*[@value='Continue'])[4]"))).click().build().perform();


            Select ccSelect = new Select(myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CreditCardType']"))));
            ccSelect.selectByIndex(0);

            myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CardholderName']")));
            myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CardholderName']")));
            actions.moveToElement(driver.findElement(By.xpath("//*[@id='CardholderName']"))).click().sendKeys(UserInformation.getCcHolderName()).perform();
            Assert.assertTrue("Cardholder name does not match", driver.findElement(By.xpath("//*[@id='CardholderName']")).getAttribute("value").contains(UserInformation.getCcHolderName()));

            myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CardNumber']")));
            actions.moveToElement(driver.findElement(By.xpath("//*[@id='CardNumber']"))).click().sendKeys(UserInformation.getCcNumber()).perform();
            Assert.assertTrue("Card number does not match", driver.findElement(By.xpath("//*[@id='CardNumber']")).getAttribute("value").contains(UserInformation.getCcNumber()));

            new Select(driver.findElement(By.xpath("//*[@id='ExpireMonth']")))
                    .selectByVisibleText(UserInformation.getCcExpirationMonth());
            Assert.assertEquals(UserInformation.getCcExpirationMonth(),
                    new Select(driver.findElement(By.xpath("//*[@id='ExpireMonth']"))).getFirstSelectedOption().getText());

            new Select(driver.findElement(By.xpath("//*[@id='ExpireYear']")))
                    .selectByVisibleText(UserInformation.getCcExpirationYear());
            Assert.assertEquals(UserInformation.getCcExpirationYear(),
                    new Select(driver.findElement(By.xpath("//*[@id='ExpireYear']"))).getFirstSelectedOption().getText());

            new Actions(driver).moveToElement(myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CardCode']"))))
                    .click().sendKeys(UserInformation.getCcCode()).perform();

            Assert.assertTrue(driver.findElement(By.xpath("//*[@id='CardCode']"))
                    .getAttribute("value").contains(UserInformation.getCcCode()));

            myWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@value='Continue'])[5]")));
            actions.moveToElement(driver.findElement(By.xpath("(//*[@value='Continue'])[5]"))).click().build().perform();
            Assert.assertTrue("Shipping Address Continue button isn't displayed", driver.findElement(By.xpath("(//*[@value='Continue'])[5]")).isDisplayed());

            MyFunc.Bekle(2);
            Robot robot1 = new Robot();
            for (int i = 0; i < 3; i++) {
                robot1.keyPress(KeyEvent.VK_TAB);
                robot1.keyRelease(KeyEvent.VK_TAB);
                MyFunc.Bekle(2);

            }

            robot1.keyPress(KeyEvent.VK_ENTER);
            robot1.keyRelease(KeyEvent.VK_ENTER);

            List<WebElement> urunler = driver.findElements(By.xpath("(//span[text()='1590.00'])[2]"));

            double urunUcretToplam = 0;

            for (WebElement e : urunler) {
                String StrUrunFiyat = e.getText().replaceAll("[^0-9,.]", "");
                double urunFiyat = Double.parseDouble(StrUrunFiyat);
                urunUcretToplam = urunUcretToplam + urunFiyat;
            }

            double ItemTotal = Double.parseDouble(driver.findElement(By.cssSelector("td.cart-total-right span.product-price")).getText().replaceAll("[^0-9,.]", ""));

            Assert.assertTrue("Rakamlar eşit değil", urunUcretToplam == ItemTotal);

            Robot robot2 = new Robot();
            for (int i = 0; i < 49; i++) {
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.keyRelease(KeyEvent.VK_TAB);
                MyFunc.Bekle(2);
            }
            robot2.keyPress(KeyEvent.VK_ENTER);
            robot2.keyRelease(KeyEvent.VK_ENTER);

            myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'successfully ')]")));
            Assert.assertTrue("Your order could not be processed!", driver.findElement(By.xpath("//*[contains(text(),'successfully ')]")).getText().contains("successfully"));

            Assert.assertTrue(driver.getCurrentUrl().contains("completed"));
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Order number')]")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Order number')]")).getText().contains("Order number"));

            actions.moveToElement(driver.findElement(By.xpath("//*[@value='Continue']"))).click().build().perform();


            BekleKapat();
        }
    }