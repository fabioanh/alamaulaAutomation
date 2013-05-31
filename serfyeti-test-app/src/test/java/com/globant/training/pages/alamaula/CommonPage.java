package com.globant.training.pages.alamaula;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globant.serfyeti.core.SYDriver;
import com.globant.serfyeti.core.SYPage;

public class CommonPage extends SYPage {
	@FindBy(id = "search")
	protected WebElement searchField;

	@FindBy(xpath = "//li[@class='static_ar']/span")
	protected WebElement argentinaLi;

	@FindBy(xpath = "//li[@class='static_uy']/span")
	protected WebElement uruguayLi;

	public ResultsPage search(String query) {
		this.searchField.sendKeys(query);
		this.searchField.submit();
		return PageFactory.initElements(SYDriver.getWebDriver(),
				ResultsPage.class);
	}

	public AlamaulaHomePage goToSiteArgentina() {
		argentinaLi.click();
		return PageFactory.initElements(SYDriver.getWebDriver(),
				AlamaulaHomePage.class);
	}

	public AlamaulaUruguayHomePage goToSiteUruguay() {
		uruguayLi.click();
		return PageFactory.initElements(SYDriver.getWebDriver(),
				AlamaulaUruguayHomePage.class);
	}

}
