package com.example.jchen415.mywaytormobileapplication;

public class MenuDrinks {

    private int drinks_ID;
    private String drinks_Name;
    private double drinks_Price;
    private int drinks_Quantity;

    private int drinks_Registration_pID;

    public MenuDrinks() {

    }
    public MenuDrinks (int drinks_ID, String drinks_Name, double drinks_Price, int drinks_Quantity, int drinks_Registration_pID) {
        this.drinks_ID = drinks_ID;
        this.drinks_Name = drinks_Name;
        this.drinks_Price = drinks_Price;
        this.drinks_Quantity = drinks_Quantity;
        this.drinks_Registration_pID = drinks_Registration_pID;
    }

    public int getDrinks_ID() {
        return drinks_ID;
    }

    public void setDrinks_ID(int drinks_ID) {
        this.drinks_ID = drinks_ID;
    }

    public String getDrinks_Name() {
        return drinks_Name;
    }

    public void setDrinks_Name(String drinks_Name) {
        this.drinks_Name = drinks_Name;
    }

    public double getDrinks_Price() {
        return drinks_Price;
    }

    public void setDrinks_Price(double drinks_Price) {
        this.drinks_Price = drinks_Price;
    }

    public int getDrinks_Quantity() {
        return drinks_Quantity;
    }

    public void setDrinks_Quantity(int drinks_Quantity) {
        this.drinks_Quantity = drinks_Quantity;
    }

    public int getDrinks_Registration_pID() {
        return drinks_Registration_pID;
    }

    public void setDrinks_Registration_pID(int drinks_Registration_pID) {
        this.drinks_Registration_pID = drinks_Registration_pID;
    }

}
