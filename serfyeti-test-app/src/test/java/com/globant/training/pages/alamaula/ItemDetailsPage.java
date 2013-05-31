package com.globant.training.pages.alamaula;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globant.serfyeti.core.SYDriver;

public class ItemDetailsPage extends CommonPage {

	private static final String trueString = "true";
	private static final String falseString = "false";

	@FindBy(id = "MessageAddForm")
	WebElement messageForm;

	@FindBy(id = "btnComment")
	WebElement commentButton;

	public void fillMessageForm(String name, String message, String email,
			String privateMessage) {
		messageForm.findElement(By.xpath(".//input[@id='MessageFromName']"))
				.sendKeys(name);
		messageForm.findElement(By.xpath(".//textarea[@id='MessageBody']"))
				.sendKeys(message);
		messageForm.findElement(By.xpath(".//input[@id='MessageFromEmail']"))
				.sendKeys(email);
		if ((messageForm
				.findElement(By.xpath(".//input[@id='MessagePrivate']"))
				.isSelected() && privateMessage.equals(falseString))
				|| (!messageForm.findElement(
						By.xpath(".//input[@id='MessagePrivate']"))
						.isSelected() && privateMessage.equals(trueString))) {
			messageForm.findElement(By.xpath(".//input[@id='MessagePrivate']"))
					.click();
		}
	}

	public void submitMessageForm() {
		commentButton.click();
	}

	public boolean isMessagePresent(String message) {
		boolean messagePresent = false;
		Alert alert = SYDriver.getWebDriver().switchTo().alert();
		messagePresent = alert.getText().contains(message);
		return messagePresent;
	}
}
