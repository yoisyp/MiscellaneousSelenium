import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SSLCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions(); //Creando objeto de la clase ChromeOptions para acceder a sus metodos
		options.setAcceptInsecureCerts(true); //este metodo con argumento true ejecuta el proceso de aceptar el certificado en el browser
		//options.addEncodedExtensions(""); //Permite adicionar una extension al navegador

		//Mismo mecanismo anterior para Firefox
		//FirefoxOptions options1 = new FirefoxOptions();
		//options1.setAcceptInsecureCerts(true); 
			
		//Mismo mecanismo anterior para M.Edge
		//EdgeOptions options2 = new EdgeOptions();
		//options2.setAcceptInsecureCerts(true); 
				
		
		//Permite configurar un proxi, util cuando se necesita un proxi especial para acceder a una web
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("ipadress:4444");//permite indicar el proxy que se usará para la conexión, Direccion IP
		options.setCapability("proxy", proxy); 		
		
		//Download file
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "/directory/path");//Definiendo directorio y ruta de descarga de file
		options.setExperimentalOption("prefs", prefs);// segundo argumento prefs se debe indicar la ruta donde se desea descargar ficheros de chrome por defecto
		
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Calidad de Software\\Automation\\FilesDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options); //En este caso se maneja el argumento options para indicar al driver el comportamiento de options. 
		driver.get("https://expired.badssl.com/");
		
		System.out.println(driver.getTitle());
		

	}

}
