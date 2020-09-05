package tests;

import models.UserInfo;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetCreatedUserTests extends TestBase{

    @Test(priority = 2)
    public void getCreatedUser() throws IOException {

        UserInfo user = jsonHelper.readFromJson(new File(PATH_TO_USER));

        given().
                contentType("application/json").
                when().
                get(baseUrl + "users/" + user.getId()).
                then().
                statusCode(200).
                body("data.id", equalTo(user.getId())).
                body("ad.company", equalTo(user.getJob()));

    }

    @Test
    public void getNonExistCreatedUser() throws IOException {

        given().
                contentType("application/json").
                when().
                get(baseUrl + "users/12345").
                then().
                statusCode(404);

    }
}
