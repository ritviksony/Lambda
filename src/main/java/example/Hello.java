package example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Hello implements RequestHandler<Object, String> {

	@Override
    public String handleRequest(Object input, Context context) {
        //context.getLogger().log("Input: " + input);
		WebDriver driver;

		
		WebDriverManager.chromedriver()
		.cachePath("target")
		.setup(); //create folder
	
		ChromeOptions options = new ChromeOptions();
		//options.setBinary("usr/bin/chromium-browser");
		options.addArguments("--headless", "--disable-dev-shm-usage" , "--no-sandbox" );
		context.getLogger().log("Headless Chrome");
		options.addArguments("incognito");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(cap);	
        context.getLogger().log("Chrome successfully launched");
	    driver.manage().window().maximize();
	    context.getLogger().log("Maximize Window");
	    
	    driver.get("http://google.com");
	    //System.out.println("Google open in browser");
	    System.out.println(driver.getTitle());
	    driver.close();
	    //return true;
        // TODO: implement your handler
	    return "Hello from Lambda!";
    }

}
