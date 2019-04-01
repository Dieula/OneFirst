package oneclick.yonclick.Model.ModelAuth;

public class RequestLogin {
    private String password;
    private String email;
    private String accessToken;

    public RequestLogin() {
    }

    public RequestLogin(String password, String email, String accessToken) {
        this.password = password;
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public RequestLogin(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
