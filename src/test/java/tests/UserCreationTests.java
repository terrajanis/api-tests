package tests;

import io.restassured.response.ValidatableResponse;
import models.UserInfo;
import org.testng.annotations.Test;
import providers.UserInfoProvider;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserCreationTests extends TestBase {


    @Test(dataProvider = "generateInfo", dataProviderClass = UserInfoProvider.class, priority = 1)
    public void createUser(String jsonUser) {

        ValidatableResponse response = given().
                contentType("application/json").
                body(jsonUser).
                when().
                post(baseUrl + "users").
                then();

        response.statusCode(201);

        UserInfo user = jsonHelper.getFromJson(jsonUser);

        response.body("name", equalTo(user.getName())).
                body("job", equalTo(user.getJob())).
                body("$", hasKey("createdAt"));

        UserInfo userInfo2 = new UserInfo().
                withId(response.extract().jsonPath().get("id")).
                withName(response.extract().jsonPath().get("name")).
                withJob(response.extract().jsonPath().get("job"));

        jsonHelper.writeAsJson(userInfo2, new File(PATH_TO_USER));

    }

    @Test(dataProvider = "generateEmptyInfo", dataProviderClass = UserInfoProvider.class)
    public void createEmptyUser(String jsonUser) {

        ValidatableResponse response = given().
                contentType("application/json").
                body(jsonUser).
                when().
                post(baseUrl + "users").
                then();

        response.statusCode(201).
                body("$", hasKey("id")).
                body("$", hasKey("createdAt"));

    }
}
