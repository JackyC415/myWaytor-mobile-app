package com.example.lap.mywaytor;
public class Registration {

    private int primaryID;
    private String userFirstName;
    private String userLastName;
    private String userName;
    private String userPassword;
    private int userAge;
    private String userGender;
    private String userEmail;

    public Registration() {

    }

    public Registration(int primaryID, String userFirstName, String userLastName, String userName, String userPassword, int userAge, String userGender, String userEmail) {
        this.primaryID = primaryID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userEmail = userEmail;
    }

    public int getPrimaryID() {
        return primaryID;
    }

    public void setPrimaryID(int primaryID) {
        this.primaryID = primaryID;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
