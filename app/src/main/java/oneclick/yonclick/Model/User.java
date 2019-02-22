package oneclick.yonclick.Model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    public String name;
    public String email;
    public int userId;
    public String password;
    public String deviceId;
    public String deviceType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

   /* public User(JSONObject jsonObject) throws JSONException
    {
        this.idcl = jsonObject.getString("id_client");
        this.nomCl = jsonObject.getString("nom_client");
        this.telephone= jsonObject.getString("telephone_client");
        this.username = jsonObject.getString("username_client");
        this.code = jsonObject.getString("password_client");
        this.email = jsonObject.getString("email_client");


    }
    public static ArrayList<Services> fromJSONArray (JSONArray array)
    {
        ArrayList<Services> results = new ArrayList<>();
        for (int x= 0; x < array.length(); x++)
        {
            try
            {
                results.add(new Services(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }*/

}
