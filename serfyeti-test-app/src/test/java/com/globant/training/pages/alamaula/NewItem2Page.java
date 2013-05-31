package com.globant.training.pages.alamaula;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globant.serfyeti.core.SYDriver;

public class NewItem2Page extends CommonPage {
	@FindBy(id = "ClassifiedTitle")
	private WebElement titleInput;

	@FindBy(id = "ClassifiedPrice")
	private WebElement priceInput;

	@FindBy(id = "ClassifiedEmail")
	private WebElement emailInput;

	@FindBy(id = "ClassifiedPhone")
	private WebElement phoneInput;
	
	@FindBy(xpath="//input[@value='Publicar']")
	private WebElement publishButton;

	public void setDescription(String description) {
		WebDriver driver = SYDriver.getInstance().getDriver();
		driver.switchTo()
				.frame(driver.findElement(By.id("ClassifiedBody_ifr")));
		WebElement descriptionText = driver.findElement(By
				.xpath("//body[@id='tinymce']/p"));
		descriptionText.sendKeys(description);
		driver.switchTo().defaultContent();
	}

	public void setTitle(String title) {
		titleInput.sendKeys(title);
	}

	public void setPrice(String price) {
		priceInput.sendKeys(price);
	}

	public void setMail(String email) {
		emailInput.sendKeys(email);
	}

	public void setPhone(String phone) {
		phoneInput.sendKeys(phone);
	}
	
	public void submitForm(){
		publishButton.click();
	}

	public boolean isMessagePresent(String message) {
		try {
			SYDriver.getInstance()
					.getDriver()
					.findElement(By.xpath("//*[contains(.,'" + message + "')]"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
