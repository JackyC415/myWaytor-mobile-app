package com.example.jchen415.mywaytormobileapplication;

public class CardHolder {

    private int primaryID;
    private String cardHolderName;
    private String cardNumber;
    private int expirationDate;
    private int cvvNumber;
    private String userAddress;
    private String userCity;
    private String userState;
    private int userZipCode;
    private String userCountry;

    public CardHolder(int primaryID, String cardHolderName, String cardNumber, int expirationDate,
                            int cvvNumber, String userAddress, String userCity, String userState,
                            int userZipCode, String userCountry) {
        this.primaryID = primaryID;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.userAddress = userAddress;
        this.userCity = userCity;
        this.userState = userState;
        this.userZipCode = userZipCode;
        this.userCountry = userCountry;
    }

    public int getPrimaryID() {
        return primaryID;
    }

    public void setPrimaryID(int primaryID) {
        this.primaryID = primaryID;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Integer expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(Integer cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public int getUserZipCode() {
        return userZipCode;
    }

    public void setUserZipCode(Integer userZipCode) {
        this.userZipCode = userZipCode;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }
}
