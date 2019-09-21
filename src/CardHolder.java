package com.example.jchen415.mywaytormobileapplication;

public class CardHolder {

    private int primaryID;
    private String card_registrationUser;
    private String cardHolderName;
    private long cardNumber;
    private String expirationDate;
    private int cvvNumber;
    private String userAddress;
    private int userZipCode;
    private String userCity;
    private String userState;
    private String userCountry;

    public CardHolder(int primaryID,String card_registrationUser, String cardHolderName, long cardNumber, String expirationDate,
                      int cvvNumber, String userAddress, int userZipCode,
                      String userCity, String userState, String userCountry) {

        this.primaryID = primaryID;
        this.card_registrationUser = card_registrationUser;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.userAddress = userAddress;
        this.userZipCode = userZipCode;
        this.userCity = userCity;
        this.userState = userState;
        this.userCountry = userCountry;
    }

    public int getPrimaryID() {
        return primaryID;
    }

    public void setPrimaryID(int primaryID) {
        this.primaryID = primaryID;
    }

    public String getCard_registrationUser() {
        return card_registrationUser;
    }

    public void setCard_registrationUser(String card_registrationUser) {
        this.card_registrationUser = card_registrationUser;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserZipCode() {
        return userZipCode;
    }

    public void setUserZipCode(Integer userZipCode) {
        this.userZipCode = userZipCode;
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

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    }
