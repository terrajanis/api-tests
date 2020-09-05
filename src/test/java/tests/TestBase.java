package tests;

import helpers.JsonHelper;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Listeners(MyTestListener.class)
public class TestBase {

    Properties properties = new Properties();
    JsonHelper jsonHelper = new JsonHelper();
    final String PATH_TO_USER = "src/test/resources/user.json";

    {
        try {
            properties.load(new FileInputStream("src/test/resources/local.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     String baseUrl = properties.getProperty("baseUrl");

}
