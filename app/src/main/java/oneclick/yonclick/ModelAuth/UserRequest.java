package oneclick.yonclick.ModelAuth;

public class UserRequest {

    public String name;
    public String email;
    public int userId;
    public String password;
    public String deviceId;
    public String deviceType;
    private String accessToken;


    public UserRequest(String name, String email, int userId, String password, String deviceId, String deviceType, String accessToken) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

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

    public UserRequest() {
    }
}
