package com.globant.training.scripts;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.globant.serfyeti.core.SYDriver;
import com.globant.serfyeti.dataprovider.factory.DataFactory;
import com.globant.serfyeti.dataprovider.interfaces.IDataLoader;
import com.globant.serfyeti.dataprovider.model.DataLoaderType;
import com.globant.serfyeti.tests.SYTest;
import com.globant.training.pages.alamaula.AlamaulaHomePage;
import com.globant.training.pages.alamaula.AlamaulaUruguayHomePage;
import com.globant.training.pages.alamaula.ItemDetailsPage;
import com.globant.training.pages.alamaula.NewItem1Page;
import com.globant.training.pages.alamaula.NewItem2Page;
import com.globant.training.pages.alamaula.ResultsPage;

public class Alamaula extends SYTest {

	@DataProvider(name = "searchCriterias")
	public Object[][] searchCriterias() throws Exception {
		IDataLoader dl = DataFactory.getInstance().getDataLoader(
				DataLoaderType.XML);
		dl.loadFromPath("./src/test/resources/data-alamaula/");
		return dl.getArrayFromKey("alamaulaSearch");
	}

	@DataProvider(name = "messageFormInput")
	public Object[][] messageFormInput() throws Exception {
		IDataLoader dl = DataFactory.getInstance().getDataLoader(
				DataLoaderType.XML);
		dl.loadFromPath("./src/test/resources/data-alamaula/");
		return dl.getArrayFromKey("messageForm");
	}

	@DataProvider(name = "createItemDataProvider")
	public Object[][] createItemDataProvider() throws Exception {
		IDataLoader dl = DataFactory.getInstance().getDataLoader(
				DataLoaderType.XML);
		dl.loadFromPath("./src/test/resources/data-alamaula/");
		return dl.getArrayFromKey("createItem");
	}

	// groups = "test1",
	@Test(dataProvider = "searchCriterias")
	public void search(String criteria) {
		AlamaulaHomePage home = PageFactory.initElements(SYDriver.getInstance()
				.getDriver(), AlamaulaHomePage.class);
		home.go();
		ResultsPage result = home.search(criteria);
		Assert.assertTrue(result.isDescriptionPresent(0),
				"Description should contain some text");
		Assert.assertTrue(result.isPricePresent(0), "Price should have a value");
		Assert.assertTrue(result.isUpdateInfoPresent(0),
				"Update info should contain some text");
	}

	@Test
	public void checkTitles() {
		AlamaulaHomePage home = PageFactory.initElements(SYDriver.getInstance()
				.getDriver(), AlamaulaHomePage.class);
		home.go();
		ResultsPage result = home.goToCompraVenta();
		Assert.assertTrue(result.areTitlesShown(), "Error with the titles");
	}

	@Test(dataProvider = "messageFormInput")
	public void messageForm(String searchQuery, String name, String message,
			String email, String privateMessage, String invalidMessage) {
		AlamaulaHomePage home = PageFactory.initElements(SYDriver.getInstance()
				.getDriver(), AlamaulaHomePage.class);
		home.go();
		ResultsPage result = home.search(searchQuery);
		ItemDetailsPage itemDetails = result.goToResult(0);
		itemDetails.fillMessageForm(name, message, email, privateMessage);
		itemDetails.submitMessageForm();
		Assert.assertTrue(itemDetails.isMessagePresent(invalidMessage),
				"Invalid mail message should be present");
	}

	@Test
	public void validateCountries() {
		AlamaulaHomePage home = PageFactory.initElements(SYDriver.getInstance()
				.getDriver(), AlamaulaHomePage.class);
		home.go();
		// considering that there is only one tab opened in that point.
		WebDriver driver = SYDriver.getInstance().getDriver();
		String oldTab = driver.getWindowHandle();

		AlamaulaHomePage siteArgentina = home.goToSiteArgentina();

		ArrayList<String> newTab = new ArrayList<String>(
				driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		// the new tab
		Assert.assertTrue(siteArgentina.isCorrectPage(),
				"Wrong page Argentina Site");
		driver.close();
		// change focus back to old tab
		driver.switchTo().window(oldTab);

		AlamaulaUruguayHomePage siteUruguay = home.goToSiteUruguay();

		newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		// the new tab
		Assert.assertTrue(siteUruguay.isCorrectPage(),
				"Wrong page Uruguay Site");
		driver.close();
		// change focus back to old tab
		driver.switchTo().window(oldTab);
	}

	@Test(dataProvider = "createItemDataProvider")
	public void createItem(String stateName, String cityName,
			String categoryName, String subCategoryName, String title,
			String price, String description, String email, String phone,
			String descriptionErrorMessage, String emailErrorMessage) {
		AlamaulaHomePage home = PageFactory.initElements(SYDriver.getInstance()
				.getDriver(), AlamaulaHomePage.class);
		home.go();
		NewItem1Page newItem1 = home.createNewItem();
		newItem1.selectState(stateName);
		newItem1.selectCity(cityName);
		newItem1.selectCategory(categoryName);
		newItem1.selectSubCategory(subCategoryName);

		NewItem2Page newItem2 = newItem1.continueNextPage();
		newItem2.setTitle(title);
		newItem2.setPrice(price);
		newItem2.setDescription(description);
		newItem2.setMail(email);
		newItem2.setPhone(phone);

		newItem2.submitForm();

//		Assert.assertTrue(newItem2.isMessagePresent(descriptionErrorMessage),
//				"Description error message should be present");

		Assert.assertTrue(newItem2.isMessagePresent(emailErrorMessage),
				"Description error message should be present");
	}
}
