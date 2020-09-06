package tests;

import io.restassured.response.ValidatableResponse;
import models.UserInfo;
import org.testng.annotations.Test;
import providers.UserInfoProvider;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateUserTests extends TestBase {

    @Test (dataProvider = "generateInfo", dataProviderClass = UserInfoProvider.class, priority = 3)
    public void changeUser(String jsonUser) throws IOException {

        UserInfo user = jsonHelper.readFromJson(new File(PATH_TO_USER));

        ValidatableResponse response =   given().
                contentType("application/json").
                body(jsonUser).
                when().
                put(baseUrl + "users/" + user.getId()).
                then();

        UserInfo user2 = jsonHelper.getFromJson(jsonUser);

        response.statusCode(200).
                body("name", equalTo(user2.getName())).
                body("job", equalTo(user2.getJob())).
                body("$", hasKey("updatedAt"));
    }

    @Test (dataProvider = "generateEmptyInfo", dataProviderClass = UserInfoProvider.class)
    public void changeEmptyUser(String jsonUser) throws IOException {

        UserInfo user = jsonHelper.readFromJson(new File(PATH_TO_USER));

        ValidatableResponse response =   given().
                contentType("application/json").
                body(jsonUser).
                when().
                put(baseUrl + "users/" + user.getId()).
                then();

        response.statusCode(200).
                body("name", equalTo("")).
                body("job", equalTo("")).
                body("$", hasKey("updatedAt"));
    }
}
