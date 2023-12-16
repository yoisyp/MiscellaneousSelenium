import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		//Identificar todos los links que no funcionan en una pagina, its broken. Util cuando hay paginas ecomerce con miles de links
		//Paso 1. Obtener todas las urls vinculadas a un link usando selenium.
		//Paso 2Java methods will call URL's and gets you the status code
		//Paso 3if status code>400 then that url is not working -> link tied to url is broken
		//a[href*="soapui"]
		
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Calidad de Software\\Automation\\FilesDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		
		
		//VARIANTE  1 para un link. 
		//Obtener la URL vinculada al enlace que contiene el termino "soapui"
		//String url = driver.findElement(By.cssSelector("a[href*=\"soapui\"]")).getAttribute("href");
		
		//Para muchos links
		//Scan todos los links en la seccion footer de la pagina
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		SoftAssert objectSoftAssert = new SoftAssert(); //SoftAssert permite ejecutar todo el ciclo, almacenando las fallas, sin interrumpir el script.
		
		for (WebElement link : links)
		{
			String url = link.getAttribute("href"); //Obtener la URL vinculada a uno de los enlaces que se tienen en links
			
			//Creando un objeto de la clase URL de java para acceder al metodo openConnection, el cual retorna un tipo HttpURLConnection 
			HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect(); //llamando a conexion
			int respondCode = connection.getResponseCode();//Obteniendo el codigo de respuesta de la URL, 200 conection Ok, 401 Unauthorized
			System.out.println(respondCode);
			 
			//Uso de SoftAssert. La condicion hard del if de abajo comentado se invirtio para hacerla soft (verdadera cuando no haya error)
			objectSoftAssert.assertTrue(respondCode<400, "The link with Text"+link.getText()+"is broken with code"+respondCode);
		}

		//Cualquier falla que se haya almacenado durante el ciclo anterior se aplica assertAll y falla el test.
		//Si no se encontro ninguna falla entonces el script pasa.
		objectSoftAssert.assertAll();	}

}

/*
if (respondCode>400) //Esto hace que el ciclo se ejecuta hasta que encuentra un link con error
{
	System.out.println("The link with Text"+link.getText()+"is broken with code"+respondCode);
	Assert.assertTrue(false); //Argumento es false porque el valor >400 es error
}
*/
