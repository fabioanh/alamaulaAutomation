package com.globant.training.pages.alamaula;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globant.serfyeti.core.SYDriver;

public class AlamaulaHomePage extends CommonPage {

	private static final String URL = "http://www.alamaula.com/";

	@FindBy(xpath = "//a[contains(text(), 'Compra - Venta')]")
	WebElement compraVentaLink;

	@FindBy(linkText = "Publicar tu Clasificado")
	WebElement createItemLink;

	public void go() {
		SYDriver.getWebDriver().get(URL);
	}

	public ResultsPage goToCompraVenta() {
		compraVentaLink.click();
		return PageFactory.initElements(SYDriver.getWebDriver(),
				ResultsPage.class);
	}

	public boolean isCorrectPage() {
		return this.getCurrentUrl().equals(URL);
	}

	public NewItem1Page createNewItem() {
		createItemLink.click();
		return PageFactory.initElements(SYDriver.getWebDriver(),
				NewItem1Page.class);
	}
}
