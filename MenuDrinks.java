package com.example.jchen415.mywaytormobileapplication;

public class MenuDrinks {

    private int drinks_ID;
    private String customer_Name;
    private String drinks_Name;
    private double drinks_Price;
    private int drinks_Quantity;

    public MenuDrinks() {

    }
    public MenuDrinks (int drinks_ID, String customer_Name, String drinks_Name, double drinks_Price, int drinks_Quantity) {
        this.drinks_ID = drinks_ID;
        this.customer_Name = customer_Name;
        this.drinks_Name = drinks_Name;
        this.drinks_Price = drinks_Price;
        this.drinks_Quantity = drinks_Quantity;
    }

    public int getDrinks_ID() {
        return drinks_ID;
    }

    public void setDrinks_ID(int drinks_ID) {
        this.drinks_ID = drinks_ID;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
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

}
