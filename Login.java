package com.example.jchen415.mywaytormobileapplication;

public class Login {

    private String loginUsername;
    private String loginPassword;

    public Login() {

    }

    public void Login (String loginUsername, String loginPassword) {
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
    }

    public int getLoginID(int loginID) {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
