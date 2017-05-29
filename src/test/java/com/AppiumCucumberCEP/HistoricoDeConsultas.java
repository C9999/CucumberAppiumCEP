package com.AppiumCucumberCEP;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class HistoricoDeConsultas {
	AppiumDriver driver;
	
	String cep1 = "06773090";
	String cep2 = "04505000";
	String cep3 = "05690000";
	
	private String retornoDaConsulta;
	private String cepRetornado;
	private Object cepConsultado;
	
	@Dado("^que eu estou na tela de BuscaCEP$")
	public void que_eu_estou_na_tela_de_BuscaCEP() throws Throwable {
		File appDir = new File("/Users/carlosaraujo/Documents/workspace/AppiumCucumberCEP");
		File app = new File(appDir, "app-debug.apk");

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");
		cap.setCapability(CapabilityType.VERSION, "7.1.1");

		cap.setCapability("plataformName", "Android");
		cap.setCapability("appPackage", "com.example.heitorcolangelo.buscacep");
		cap.setCapability("appActivity", "com.example.heitorcolangelo.buscacep.MainActivity");

		cap.setCapability("app", app.getAbsolutePath());
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	}

	@Dado("^já busquei por alguns CEPs$")
	public void já_busquei_por_alguns_CEPs() throws Throwable {
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/cep")).sendKeys(cep1);
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/search")).click();
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/cep")).clear();

		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/cep")).sendKeys(cep2);
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/search")).click();
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/cep")).clear();

		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/cep")).sendKeys(cep3);
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/search")).click();
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/cep")).clear();
	}

	@Quando("^clico em Consultas Anteriores$")
	public void clico_em_Consultas_Anteriores() throws Throwable {
		driver.navigate().back();
		driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/history")).click();
	}

	@Então("^vejo todos os endereços já consultados$")
	public void vejo_todos_os_endereços_já_consultados() throws Throwable {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		retornoDaConsulta = driver.findElement(By.id("com.example.heitorcolangelo.buscacep:id/address")).getText();
		
		cepRetornado = retornoDaConsulta.substring(0, 8);
		assertEquals(cep1, cepRetornado);
	}

}
