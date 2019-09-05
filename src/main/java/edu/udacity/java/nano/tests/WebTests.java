package edu.udacity.java.nano.tests;

import edu.udacity.java.nano.SeleniumTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebTests extends SeleniumTests {
    @Test
    public void login_to_chat() {
        this.driver.get("http://localhost:8080");
        WebElement inputElement = this.driver.findElement(By.id("username"));
        WebElement submitElement = this.driver.findElement(By.className("submit"));
        inputElement.sendKeys("mary");
        submitElement.click();
        Assert.assertNotNull(inputElement);
        //Chat page
        System.out.println(this.driver.getCurrentUrl().toString());
        //Verify Online Count
        WebElement countElement = this.driver.findElement(By.className("chat-num"));
        Assert.assertEquals("1", countElement.getText());

    }

    @Test
    public void send_msg() {
        this.driver.get("http://localhost:8080/index?username=mary");
        WebElement inputElement = this.driver.findElement(By.id("msg"));
        WebElement submitElement = this.driver.findElement(By.id("sendMsg"));
        inputElement.sendKeys("Hello");
        submitElement.click();
        Assert.assertNotNull(inputElement);
    }

    @Test
    public void leave_chat() {
        this.driver.get("http://localhost:8080/index?username=mary");
        WebElement submitElement = this.driver.findElement(By.className("mdui-btn-icon"));
        submitElement.click();
        //Verify if it is Login screen
        Assert.assertEquals("Chat Room Login", this.driver.getTitle());
    }
}
