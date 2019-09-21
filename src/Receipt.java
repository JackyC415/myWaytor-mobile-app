package com.example.jchen415.mywaytormobileapplication;

public class Receipt {

    private int Transaction_ID;
    private String Order_Names;
    private double Order_Prices;
    private int Order_Quantity;
    private String MenuCategory;

    public Receipt() {

    }

    public Receipt(int t_id, String o_name, double o_price, int o_quantity, String m_category) {
        this.Transaction_ID = t_id;
        this.Order_Names = o_name;
        this.Order_Prices = o_price;
        this.Order_Quantity = o_quantity;
        this.MenuCategory = m_category;
    }

    public int getTransaction_ID() {
        return Transaction_ID;
    }

    public void setTransaction_ID(int transaction_ID) {
        Transaction_ID = transaction_ID;
    }

    public String getOrder_Names() {
        return Order_Names;
    }

    public void setOrder_Names(String order_Names) {
        Order_Names = order_Names;
    }

    public double getOrder_Prices() {
        return Order_Prices;
    }

    public void setOrder_Prices(double order_Prices) {
        Order_Prices = order_Prices;
    }

    public int getOrder_Quantity() {
        return Order_Quantity;
    }

    public void setOrder_Quantity(int order_Quantity) {
        Order_Quantity = order_Quantity;
    }

    public String getMenuCategory() {
        return MenuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        MenuCategory = menuCategory;
    }
}
