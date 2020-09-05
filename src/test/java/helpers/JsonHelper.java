package helpers;

import com.google.gson.Gson;
import models.UserInfo;

import java.io.*;

public class JsonHelper {

    public UserInfo getFromJson(String jsonUser) {
        Gson gson = new Gson();
        UserInfo user = gson.fromJson(jsonUser, UserInfo.class);
        return user;
    }

    public void writeAsJson(UserInfo user, File file) {
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);

        try(Writer writer = new FileWriter(file)) {
            writer.write(jsonUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserInfo readFromJson(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String userFromJson = "";
        String line = reader.readLine();
        while (line != null) {
            userFromJson += line;
            line = reader.readLine();
        }
        reader.close();

        Gson gson = new Gson();
        UserInfo user = gson.fromJson(userFromJson, UserInfo.class);

        return user;
    }

}
