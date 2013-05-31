package com.globant.training.pages.alamaula;

import com.globant.serfyeti.core.SYDriver;

public class AlamaulaUruguayHomePage extends CommonPage {

	private static final String URL = "http://www.alamaula.com.uy/";

	public void go() {
		SYDriver.getWebDriver().get(URL);
	}

	public boolean isCorrectPage() {
		return this.getCurrentUrl().equals(URL);
	}
}
