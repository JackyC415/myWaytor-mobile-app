package com.example.jchen415.mywaytormobileapplication;

public class MenuDish {

    private int dish_ID;
    private String dish_Name;
    private double dish_Price;
    private int dish_Quantity;
    private int dish_Registration_pID;

    public MenuDish() {

    }
    public MenuDish(int dish_ID, String dish_Name, double dish_Price, int dish_Quantity, int dish_Registration_pID) {

        this.dish_ID = dish_ID;
        this.dish_Name = dish_Name;
        this.dish_Price = dish_Price;
        this.dish_Quantity= dish_Quantity;
        this.dish_Registration_pID = dish_Registration_pID;
    }

    public int getDish_ID() {
        return dish_ID;
    }

    public void setDish_ID(int dish_ID) {
        this.dish_ID = dish_ID;
    }

    public String getDish_Name() {
        return dish_Name;
    }

    public void setDish_Name(String dish_Name) {
        this.dish_Name = dish_Name;
    }

    public double getDish_Price() {
        return dish_Price;
    }

    public void setDish_Price(double dish_Price) {
        this.dish_Price = dish_Price;
    }

    public int getDish_Quantity() {
        return dish_Quantity;
    }

    public void setDish_Quantity(int dish_Quantity) {
        this.dish_Quantity = dish_Quantity;
    }

    public int getDish_Registration_pID() {
        return dish_Registration_pID;
    }

    public void setDish_Registration_pID(int dish_Registration_pID) {
        this.dish_Registration_pID = dish_Registration_pID;
    }
}
