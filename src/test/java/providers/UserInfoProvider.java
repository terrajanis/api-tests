package providers;

import com.google.gson.Gson;
import models.UserInfo;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class UserInfoProvider {


    @DataProvider(name = "generateInfo")
    public Object[] generateInfo() throws IOException {

        UserInfo user = new UserInfo()
                .withName("Name" + (int) (Math.random() * 10000))
                .withJob("Job" + (int) (Math.random() * 10000));


        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);

        return new Object[]{jsonUser};
    }

    @DataProvider(name = "generateEmptyInfo")
    public Object[] generateEmptyInfo() throws IOException {

        UserInfo user = new UserInfo()
                .withName("")
                .withJob("");


        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);

        return new Object[]{jsonUser};
    }
}
