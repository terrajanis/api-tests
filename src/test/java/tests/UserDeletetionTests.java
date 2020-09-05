package tests;

import models.UserInfo;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserDeletetionTests extends TestBase {

    @Test(priority = 4)
    public void deleteCreatedUser() throws IOException {

        UserInfo user = jsonHelper.readFromJson(new File(PATH_TO_USER));

        given().
                contentType("application/json").
                when().
                delete(baseUrl + "users/" + user.getId()).
                then().
                statusCode(204);


    }
}
