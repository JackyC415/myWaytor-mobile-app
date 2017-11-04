package com.example.lap.mywaytor;

public class CardHolder {

    private String cardHolderName;
    private String cardNumber;
    private Integer expirationDate;
    private Integer cvvNumber;
    private String userAddress;
    private String userCity;
    private String userState;
    private Integer userZipCode;
    private String userCountry;

    public CardHolder(String cardHolderName, String cardNumber, Integer expirationDate,
                            Integer cvvNumber, String userAddress, String userCity, String userState,
                            Integer userZipCode, String userCountry) {
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

    public Integer getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Integer expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCvvNumber() {
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

    public Integer getUserZipCode() {
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
