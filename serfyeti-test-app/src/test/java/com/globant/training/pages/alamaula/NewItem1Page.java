package com.globant.training.pages.alamaula;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.globant.serfyeti.core.SYDriver;

public class NewItem1Page extends CommonPage {

	@FindBy(id = "ClassifiedStateId")
	private WebElement selectState;

	@FindBy(id = "ClassifiedCityId")
	private WebElement selectCity;

	@FindBy(className = "categories")
	private WebElement categoriesContainer;

	@FindBy(className = "list-container")
	private WebElement subCategoriesContainer;

	@FindBy(xpath = "//input[@value='Continuar']")
	private WebElement continueButton;

	public void selectState(String stateName) {
		Select stateDropDown = new Select(selectState);
		stateDropDown.selectByVisibleText(stateName);
	}

	public void selectCity(String cityName) {
		Select cityDropDown = new Select(selectCity);
		cityDropDown.selectByVisibleText(cityName);
	}

	public void selectCategory(String categoryName) {
		categoriesContainer.findElement(
				By.xpath(".//a[contains(text(),'" + categoryName + "')]"))
				.click();
	}

	public void selectSubCategory(String subCategoryName) {
		subCategoriesContainer.findElement(
				By.xpath(".//a[contains(text(),'" + subCategoryName + "')]"))
				.click();
	}

	public NewItem2Page continueNextPage() {
		continueButton.click();
		return PageFactory.initElements(SYDriver.getWebDriver(),
				NewItem2Page.class);
	}
}
