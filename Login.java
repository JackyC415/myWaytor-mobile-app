package com.example.jchen415.mywaytor_mobile_application;

public class Login {

    String loginID;
    String loginUsername;
    String loginPassword;

    public Login() {

    }

    public Login (String loginID, String loginUsername, String loginPassword) {
        this.loginID = loginID;
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
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