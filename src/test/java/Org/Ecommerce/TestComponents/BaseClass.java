package Org.Ecommerce.TestComponents;

import Org.Ecommerce.Pageobject.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public  LandingPage landing;
    public WebDriver intializeDriver() throws IOException {
        //Properties class
        Properties prop=new Properties();
        //conver the properties into file foramat
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\GlobalData\\GlobalDate.properties");
        //load the properties
        prop.load(fis);
        //Get the properties from GlobalDataFile
        String browserName=prop.getProperty("browser");
        if(browserName.contains("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            this.driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;

    }

    public List<HashMap<String, String>> getJsonDataMap(String filePath) throws IOException {
        //Read Json to string
        String jsonContent= FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //String to HashMap JacksonDatabind
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
        return data;
    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        File file=new File(System.getProperty("usr.dir")+"\\src\\main\\reports" +testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("usr.dir")+"\\src\\main\\reports" +testCaseName + ".png";
    }
@BeforeTest(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver=intializeDriver();
         landing=new LandingPage(driver);
        landing.goTo();
        return landing;
    }
    @AfterTest(alwaysRun = true)
    public void tearDown() throws InterruptedException {

        driver.close();
    }

}
