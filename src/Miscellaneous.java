import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//Java no reconoce esta dependencia, por tanto hay que escribirla y luego descargarla manualmente de esta url: https://commons.apache.org/proper/commons-io/ y anadirla ocmo externa en el classpath
import org.apache.commons.io.FileUtils;


public class Miscellaneous {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Calidad de Software\\Automation\\FilesDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//Maximizando browser
		driver.manage().window().maximize();
		
		//Borrar todas las cookies antes de la ejecucion del test
		//Cuando se borran las coockies el browser obligara a autenticarte a traves de una pagina de login
		driver.manage().deleteAllCookies();
		
		//Borrar solo una cookie, por su name
		driver.manage().deleteCookieNamed("name");
		
		driver.get("https://www.google.com/");
		
		//Take Screenshots
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //(TakesScreenshot)driver) Casting our driver object to can use the method getScreenshotAs
		FileUtils.copyFile(src,new File("C:\\Users\\yoisy\\screenshot.png")); //Copiando la imagen capturada anteriormente en la URL local que se indica .
	}

}
