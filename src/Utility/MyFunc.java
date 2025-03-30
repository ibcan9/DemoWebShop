package Utility;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyFunc {
    public static void Bekle(int saniye){
        try{
            Thread.sleep(saniye*1000);
        }catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }
    public static int RandomSayiVer(int min, int max)
    {
        return  (int)(Math.random()* (max-min) )+ min;
    }

    public static int RandomSayiVer(int max)
    {
        return  (int)(Math.random()* max);
    }


    public static void loginTest(){
        BaseDriver.driver.get("https://demowebshop.tricentis.com");


        WebElement logInButton = BaseDriver.driver.findElement(By.xpath("//*[text()='Log in']"));
        Assert.assertTrue("Login button is not displayed",logInButton.isDisplayed());
        logInButton.click();

        WebElement emailInputField = BaseDriver.driver.findElement(By.xpath("//*[@id='Email']"));
        emailInputField.sendKeys("tteam.5.techno@gmail.com");

        WebElement passwordInputField = BaseDriver.driver.findElement(By.xpath("//*[@id='Password']"));
        passwordInputField.sendKeys("Team5.123");

        WebElement logInBtn = BaseDriver.driver.findElement(By.xpath("//*[@value='Log in']"));
        logInBtn.click();

        WebElement loggedText = BaseDriver.driver.findElement(By.xpath("//*[text()='tteam.5.techno@gmail.com']"));
        Assert.assertTrue("Log in unsuccessful",loggedText.isDisplayed());
        Assert.assertTrue("Log in unsuccessful",loggedText.getText().equals("tteam.5.techno@gmail.com"));
    }
}