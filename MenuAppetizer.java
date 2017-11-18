package com.example.jchen415.mywaytormobileapplication;

public class MenuAppetizer {

    private int appetizer_ID;
    private String appetizer_Name;
    private double appetizer_Price;
    private int appetizer_Quantity;
    private int appetizer_Registration_pID;

    public MenuAppetizer() {

    }

    public MenuAppetizer(int appetizer_ID, String appetizer_Name, double appetizer_Price, int appetizer_Quantity, int appetizer_Registration_pID) {
        this.appetizer_ID = appetizer_ID;
        this.appetizer_Name = appetizer_Name;
        this.appetizer_Price = appetizer_Price;
        this.appetizer_Quantity = appetizer_Quantity;
        this.appetizer_Registration_pID = appetizer_Registration_pID;
    }

    public int getAppetizer_ID() {
        return appetizer_ID;
    }

    public void setAppetizer_ID(int appetizer_ID) {
        this.appetizer_ID = appetizer_ID;
    }


    public String getAppetizer_Name() {
        return appetizer_Name;
    }

    public void setAppetizer_Name(String appetizer_Name) {
        this.appetizer_Name = appetizer_Name;
    }

    public double getAppetizer_Price() {
        return appetizer_Price;
    }

    public void setAppetizer_Price(double appetizer_Price) {
        this.appetizer_Price = appetizer_Price;
    }

    public int getAppetizer_Quantity() {
        return appetizer_Quantity;
    }

    public void setAppetizer_Quantity(int appetizer_Quantity) {
        this.appetizer_Quantity = appetizer_Quantity;
    }
    public int getAppetizer_Registration_pID() {
        return appetizer_Registration_pID;
    }

    public void setAppetizer_Registration_pID(int appetizer_Registration_pID) {
        this.appetizer_Registration_pID = appetizer_Registration_pID;
    }
}
