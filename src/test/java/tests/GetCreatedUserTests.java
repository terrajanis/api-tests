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

        //На самом деле это апи не сохраняет созданных юзеров (т.к. тестовое). Однако тест специально реализован так, будто сохраняет. Поэтому в данном случае будет фактически 404.

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
    public void getNonExistUser() throws IOException {

        given().
                contentType("application/json").
                when().
                get(baseUrl + "users/12345").
                then().
                statusCode(404);

    }
}
