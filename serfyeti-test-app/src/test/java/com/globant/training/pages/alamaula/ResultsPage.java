package com.globant.training.pages.alamaula;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globant.serfyeti.core.SYDriver;

public class ResultsPage extends CommonPage {

	@FindBy(className = "classified-summary")
	List<WebElement> resultItems;

	@FindBy(xpath = "//div[@class='h4-filters']/h4")
	List<WebElement> filterTitles;

	public boolean isDescriptionPresent(int itemIndex) {
		WebElement resultItem = resultItems.get(itemIndex);
		WebElement description = resultItem.findElement(By.xpath(".//p"));
		return description.getText() != null
				&& !description.getText().isEmpty();
	}

	public boolean isPricePresent(int itemIndex) {
		WebElement resultItem = resultItems.get(itemIndex);
		WebElement price = resultItem.findElement(By.className("price-list"));
		return price.getText() != null && !price.getText().isEmpty();
	}

	public boolean isUpdateInfoPresent(int itemIndex) {
		WebElement resultItem = resultItems.get(itemIndex);
		WebElement updateInfo = resultItem.findElement(By.id("interval"));
		return updateInfo.getText() != null && !updateInfo.getText().isEmpty();
	}

	public boolean areTitlesShown() {
		for (WebElement we : filterTitles) {
			if (we.getText() == null || we.getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public ItemDetailsPage goToResult(int itemIndex) {
		WebElement resultItem = resultItems.get(itemIndex);
		WebElement resultLink = resultItem.findElement(By
				.xpath(".//div[@class='image-list']/a"));
		resultLink.click();
		return PageFactory.initElements(SYDriver.getWebDriver(),
				ItemDetailsPage.class);
	}

}
