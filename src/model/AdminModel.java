package model;

public class AdminModel {
    private int id;
    private String username;
    private String password;
    private String token;

    public AdminModel(String token, String password, String username) {
        this.token = token;
        this.password = password;
        this.username = username;
    }

    public AdminModel(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
