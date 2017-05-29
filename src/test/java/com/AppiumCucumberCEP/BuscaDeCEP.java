package com.AppiumCucumberCEP;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BuscaDeCEP {
	AppiumDriver driver;

	String cepConsultado = "04578907";
	String retornoDaConsulta; 
	String cepRetornado;

	@Dado("^que eu estou na home de BuscaCEP$")
	public void que_eu_estou_na_home_de_BuscaCEP() throws Throwable {
		File appDir = new File("/Users/carlosaraujo/Documents/workspace/AppiumCucumberCEP");
		File app = new File(appDir, "app-debug.apk");

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");
		cap.setCapability(MobileCapabilityType.VERSION, "7.1.1");

		cap.setCapability("plataformName", "Android");
		cap.setCapability("appPackage", "com.example.heitorcolangelo.buscacep");
		cap.setCapability("appActivity", "com.example.heitorcolangelo.buscacep.MainActivity");

		cap.setCapability("app", app.getAbsolutePath());
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	}

	@Dado("^informo o CEP que vai ser consultado$")
	public void informo_o_CEP_que_vai_ser_consultadoz() throws Throwable {
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/cep")).sendKeys(cepConsultado);
	}

	@Quando("^clico no icone de busca$")
	public void clico_no_icone_de_busca() throws Throwable {
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/search")).click();
	}

	@Então("^vejo o endereço completo do CEP consultado\\.$")
	public void vejo_o_endereço_completo_do_CEP_consultado() throws Throwable {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		retornoDaConsulta = driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/address")).getText();
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/cep")).clear();

		cepRetornado = retornoDaConsulta.substring(0, 8);
		assertEquals(cepConsultado, cepRetornado);
	}
}
