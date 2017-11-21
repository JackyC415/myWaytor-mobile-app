package com.example.jchen415.mywaytormobileapplication;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.*;
import android.util.Log;

import java.util.*;

//Database controller; responsible for manipulating all database interaction activities
//Functions include, but not limited to, insert, update, retrieve, edit and delete
//SQL queries include: login authentication,retrieve all registration info, total order payment receipt
//info linked to menu items from all categories: dish, appetizer, drinks & desserts orders, searching
//for a specific menu item based on its category, filtering based on price range, etc...
public class DBController extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "myWaytor.db";

    //Database Table Names
    private static final String TABLE_REGISTRATIONS = "Registration";
    private static final String TABLE_CARDHOLDER = "Cardholder";
    private static final String TABLE_MENU_DISH = "Dish";
    private static final String TABLE_MENU_APPETIZER = "Appetizer";
    private static final String TABLE_MENU_DRINKS = "Drinks";
    private static final String TABLE_MENU_DESSERTS = "Desserts";
    private static final String TABLE_RECEIPT = "Receipt";

    //Registration Table Column Names
    private static final String KEY_REGISTRATION_pID = "REGISTRATION_pID";
    private static final String KEY_REGISTRATION_USERFIRSTNAME = "REGISTRATION_FIRST";
    private static final String KEY_REGISTRATION_USERLASTNAME = "REGISTRATION_LAST";
    private static final String KEY_REGISTRATION_USERNAME = "REGISTRATION_USER";
    private static final String KEY_REGISTRATION_USERPASSWORD = "REGISTRATION_PASS";
    private static final String KEY_REGISTRATION_USERAGE = "REGISTRATION_AGE";
    private static final String KEY_REGISTRATION_USERGENDER = "REGISTRATION_GENDER";
    private static final String KEY_REGISTRATION_USEREMAIL = "REGISTRATION_EMAIL";

    //CardHolder Table Column Names
    private static final String KEY_CARD_pID = "CARD_pID";
    private static final String KEY_CARD_REGISTRATION_pID = "CARD_REGISTRATION_pID";
    private static final String KEY_CARD_NAME = "CARD_NAME";
    private static final String KEY_CARD_NUMBER = "CARD_NUMBER";
    private static final String KEY_CARD_EXPIRATIONDATE = "CARD_EXPIRATION";
    private static final String KEY_CARD_CVV = "CARD_CVV";
    private static final String KEY_CARD_USERADDRESS = "CARD_ADDRESS";
    private static final String KEY_CARD_USERZIPCODE = "CARD_ZIPCODE";
    private static final String KEY_CARD_USERCITY = "CARD_CITY";
    private static final String KEY_CARD_USERSTATE = "CARD_STATE";
    private static final String KEY_CARD_USERCOUNTRY = "CARD_COUNTRY";

    //Menu Dish Table Column Names
    private static final String KEY_MENU_DISH_pID = "DISH_pID";
    private static final String KEY_MENU_DISH_NAME = "DISH_NAME";
    private static final String KEY_MENU_DISH_PRICE = "DISH_PRICE";
    private static final String KEY_MENU_DISH_QUANTITY = "DISH_QUANTITY";
    private static final String KEY_MENU_DISH_REGISTRATION_pID = "DISH_REGISTRATION_pID";

    //Menu Appetizer Table Column Names
    private static final String KEY_MENU_APPETIZER_pID = "APPETIZER_pID";
    private static final String KEY_MENU_APPETIZER_NAME = "APPETIZER_NAME";
    private static final String KEY_MENU_APPETIZER_PRICE = "APPETIZER_PRICE";
    private static final String KEY_MENU_APPETIZER_QUANTITY = "APPETIZER_QUANTITY";
    private static final String KEY_MENU_APPETIZER_REGISTRATION_pID = "APPETIZER_REGISTRATION_pID";

    //Menu Drinks Table Column Names
    private static final String KEY_MENU_DRINKS_pID = "DRINKS_pID";
    private static final String KEY_MENU_DRINKS_NAME = "DRINKS_NAME";
    private static final String KEY_MENU_DRINKS_PRICE = "DRINKS_PRICE";
    private static final String KEY_MENU_DRINKS_QUANTITY = "DRINKS_QUANTITY";
    private static final String KEY_MENU_DRINKS_REGISTRATION_pID = "DRINKS_REGISTRATION_pID";

    //Menu Desserts Table Column Names
    private static final String KEY_MENU_DESSERTS_pID = "DESSERTS_pID";
    private static final String KEY_MENU_DESSERTS_NAME = "DESSERTS_NAME";
    private static final String KEY_MENU_DESSERTS_PRICE = "DESSERTS_PRICE";
    private static final String KEY_MENU_DESSERTS_QUANTITY = "DESSERTS_QUANTITY";
    private static final String KEY_MENU_DESSERTS_REGISTRATION_pID = "DESSERTS_REGISTRATION_pID";

    //Receipt Table Column Names
    private static final String KEY_RECEIPT_pID = "RECEIPT_pID";
    private static final String KEY_RECEIPT_TRANSACTION_ID = "RECEIPT_TRANSACTION_ID";
    private static final String KEY_RECEIPT_ORDERS = "RECEIPT_ORDERS";
    private static final String KEY_RECEIPT_CHECK = "RECEIPT_CHECK";
    private static final String KEY_RECEIPT_QUANTITY = "RECEIPT_QUANTITY";
    private static final String KEY_RECEIPT_MENU_CATEGORY = "RECEIPT_MENU_CATEGORY";
    private static final String KEY_RECEIPT_REGISTRATION_pID = "RECEIPT_REGISTRATION_pID";

    //Default constructor which generates the database
    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Generating Database Tables for Registration, CardHolder and Four Menu Categories
    //Foreign Keys referencing Registration PK
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Enable foreign key constraints upon database create to ensure enforcement
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys = 1;");
            Log.d("TAG", "Foreign Keys Constraint Enabled!!!");
        }
        //Create Tables
        db.execSQL("create table " + TABLE_REGISTRATIONS + " (REGISTRATION_pID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, REGISTRATION_FIRST TEXT NOT NULL, REGISTRATION_LAST TEXT NOT NULL, REGISTRATION_USER TEXT UNIQUE, REGISTRATION_PASS TEXT NOT NULL, REGISTRATION_AGE INTEGER NOT NULL, REGISTRATION_GENDER TEXT NOT NULL, REGISTRATION_EMAIL TEXT UNIQUE)");
        db.execSQL("create table " + TABLE_CARDHOLDER + " (CARD_pID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, CARD_NAME TEXT NOT NULL, CARD_NUMBER INTEGER UNIQUE, CARD_EXPIRATION TEXT NOT NULL, CARD_CVV INTEGER NOT NULL, CARD_ADDRESS TEXT NOT NULL, CARD_ZIPCODE INTEGER NOT NULL, CARD_CITY TEXT NOT NULL, CARD_STATE TEXT NOT NULL, CARD_COUNTRY TEXT NOT NULL, CARD_REGISTRATION_pID TEXT NOT NULL, FOREIGN KEY (CARD_REGISTRATION_pID) REFERENCES Registration (REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_MENU_DISH + "(DISH_pID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, DISH_NAME TEXT NOT NULL, DISH_PRICE NUM NOT NULL, DISH_QUANTITY INTEGER NOT NULL, DISH_REGISTRATION_pID NOT NULL, FOREIGN KEY (DISH_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_MENU_APPETIZER + "(APPETIZER_pID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, APPETIZER_NAME TEXT NOT NULL, APPETIZER_PRICE NUM NOT NULL, APPETIZER_QUANTITY INTEGER NOT NULL, APPETIZER_REGISTRATION_pID NOT NULL, FOREIGN KEY (APPETIZER_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_MENU_DRINKS + "(DRINKS_pID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, DRINKS_NAME TEXT NOT NULL, DRINKS_PRICE NUM NOT NULL, DRINKS_QUANTITY INTEGER NOT NULL, DRINKS_REGISTRATION_pID NOT NULL, FOREIGN KEY (DRINKS_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_MENU_DESSERTS + "(DESSERTS_pID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, DESSERTS_NAME TEXT NOT NULL, DESSERTS_PRICE NUM NOT NULL, DESSERTS_QUANTITY INTEGER NOT NULL, DESSERTS_REGISTRATION_pID NOT NULL, FOREIGN KEY (DESSERTS_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_RECEIPT + "(RECEIPT_pID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, RECEIPT_TRANSACTION_ID INTEGER NOT NULL, RECEIPT_ORDERS TEXT NOT NULL, RECEIPT_CHECK NUM NOT NULL, RECEIPT_QUANTITY INTEGER NOT NULL, RECEIPT_MENU_CATEGORY TEXT NOT NULL, RECEIPT_REGISTRATION_pID INTEGER NOT NULL, FOREIGN KEY (RECEIPT_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
        Log.d("TAG", "Tables Created Successfully!!!");
    }

    //Database upgrade routine
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop previous table if exists on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDHOLDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_DISH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_APPETIZER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_DRINKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_DESSERTS);
        //Regenerate Tables
        onCreate(db);
    }

    //Database insert user registration data routine
    public void insertRegistrationData(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REGISTRATION_USERFIRSTNAME, registration.getUserFirstName());
        values.put(KEY_REGISTRATION_USERLASTNAME, registration.getUserLastName());
        values.put(KEY_REGISTRATION_USERAGE, registration.getUserAge());
        values.put(KEY_REGISTRATION_USERGENDER, registration.getUserGender());
        values.put(KEY_REGISTRATION_USEREMAIL, registration.getUserEmail());
        values.put(KEY_REGISTRATION_USERNAME, registration.getUserName());
        values.put(KEY_REGISTRATION_USERPASSWORD, registration.getUserPassword());

        db.insert(TABLE_REGISTRATIONS, null, values);
        Log.d("TAG", "Registration Data Inserted Successfully!!!");
        db.close();
    }

    //Database login authentication routine matches username with password in db
    public String LoginAuth(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select REGISTRATION_USER, REGISTRATION_PASS from " + TABLE_REGISTRATIONS;
        Cursor cursor = db.rawQuery(query, null);
        String user, pass;
        pass = "try again";
        if (cursor.moveToFirst()) {
            do {
                user = cursor.getString(0);
                if (user.equals(username)) {
                    pass = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return pass;
    }

    //Database match foreign key routine
    public int getFK() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT REGISTRATION_pID FROM " + TABLE_REGISTRATIONS, null);
        int FK = 0;
        while (c.moveToNext()) {
            FK = c.getInt(0);
        }
        c.close();
        return FK;
    }

    //Database validate card routine
    public boolean CheckCard() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CARDHOLDER, null);

        if (c != null && c.getCount() > 0) {
            c.close();
            return true;
        }
        return false;
    }

    //Database insert user card data routine
    public void insertCardData(CardHolder cardholder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CARD_NAME, cardholder.getCardHolderName());
        values.put(KEY_CARD_NUMBER, cardholder.getCardNumber());
        values.put(KEY_CARD_EXPIRATIONDATE, cardholder.getExpirationDate());
        values.put(KEY_CARD_CVV, cardholder.getCvvNumber());
        values.put(KEY_CARD_USERADDRESS, cardholder.getUserAddress());
        values.put(KEY_CARD_USERZIPCODE, cardholder.getUserZipCode());
        values.put(KEY_CARD_USERCITY, cardholder.getUserCity());
        values.put(KEY_CARD_USERSTATE, cardholder.getUserState());
        values.put(KEY_CARD_USERCOUNTRY, cardholder.getUserState());
        values.put(KEY_CARD_REGISTRATION_pID, cardholder.getCard_registrationID());

        db.insert(TABLE_CARDHOLDER, null, values);
        Log.d("TAG", "Cardholder Data Inserted Successfully!!!");
        db.close();
    }

    //Database insert dish menu data routine
    public void insertMenuDish(MenuDish menuDish) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_DISH_NAME, menuDish.getDish_Name());
        values.put(KEY_MENU_DISH_PRICE, menuDish.getDish_Price());
        values.put(KEY_MENU_DISH_QUANTITY, menuDish.getDish_Quantity());
        values.put(KEY_MENU_DISH_REGISTRATION_pID, menuDish.getDish_Registration_pID());

        db.insert(TABLE_MENU_DISH, null, values);
        Log.d("TAG", "Dish Menu Data Inserted Successfully!!!");
        db.close();
    }

    //Database insert appetizer menu data routine
    public void insertMenuAppetizer(MenuAppetizer menuAppetizer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_APPETIZER_NAME, menuAppetizer.getAppetizer_Name());
        values.put(KEY_MENU_APPETIZER_PRICE, menuAppetizer.getAppetizer_Price());
        values.put(KEY_MENU_APPETIZER_QUANTITY, menuAppetizer.getAppetizer_Quantity());
        values.put(KEY_MENU_APPETIZER_REGISTRATION_pID, menuAppetizer.getAppetizer_Registration_pID());

        db.insert(TABLE_MENU_APPETIZER, null, values);
        Log.d("TAG", "Appetizer Menu Data Inserted Successfully!!!");
        db.close();
    }

    //Database insert drinks menu data routine
    public void insertMenuDrinks(MenuDrinks menuDrinks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_DRINKS_NAME, menuDrinks.getDrinks_Name());
        values.put(KEY_MENU_DRINKS_PRICE, menuDrinks.getDrinks_Price());
        values.put(KEY_MENU_DRINKS_QUANTITY, menuDrinks.getDrinks_Quantity());
        values.put(KEY_MENU_DRINKS_REGISTRATION_pID, menuDrinks.getDrinks_Registration_pID());

        db.insert(TABLE_MENU_DRINKS, null, values);
        Log.d("TAG", "Drinks Menu Data Inserted Successfully!!!");
        db.close();
    }

    //Database insert desserts menu data routine
    public void insertMenuDesserts(MenuDesserts menuDesserts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_DESSERTS_NAME, menuDesserts.getDesserts_Name());
        values.put(KEY_MENU_DESSERTS_PRICE, menuDesserts.getDesserts_Price());
        values.put(KEY_MENU_DESSERTS_QUANTITY, menuDesserts.getDesserts_Quantity());
        values.put(KEY_MENU_DESSERTS_REGISTRATION_pID, menuDesserts.getDesserts_Registration_pID());

        db.insert(TABLE_MENU_DESSERTS, null, values);
        Log.d("TAG", "Desserts Menu Data Inserted Successfully!!!");
        db.close();
    }

    //Database retrieve user registration data routine
    public List<Registration> getAllRegistrations() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Registration> registrationList = new ArrayList<Registration>();
        String ListQuery = "SELECT * FROM " + TABLE_REGISTRATIONS;
        Cursor cursor = db.rawQuery(ListQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Registration registration = new Registration();
                registration.setUserFirstName(cursor.getString(0));
                registration.setUserLastName(cursor.getString(1));
                registration.setUserName((cursor.getString(2)));
                registration.setUserPassword(cursor.getString(3));
                registration.setUserAge(Integer.parseInt(cursor.getString(4)));
                registration.setUserGender((cursor.getString(5)));
                registration.setUserEmail(cursor.getString(6));

                registrationList.add(registration);

            } while (cursor.moveToNext());
        }
        return registrationList;
    }

    //Database insert receipt data routine
    public void insertReceipt(Receipt receipt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECEIPT_TRANSACTION_ID, receipt.getTransaction_ID());
        values.put(KEY_RECEIPT_MENU_CATEGORY, receipt.getMenuCategory()); 
        values.put(KEY_RECEIPT_ORDERS, receipt.getOrder_Names());
        values.put(KEY_RECEIPT_QUANTITY, receipt.getOrder_Quantity());
        values.put(KEY_RECEIPT_CHECK, receipt.getOrder_Prices());

        db.insert(TABLE_RECEIPT, null, values);
        Log.d("TAG", "Receipt Data Inserted Successfully!!!");
        db.close();
    }

    //Database Menu categories routine responsible for retrieving all relevant order history
    public Cursor getMenuDishData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor dish = db.rawQuery("SELECT * FROM " + TABLE_MENU_DISH, null);
        return dish;
    }

    public Cursor getMenuAppetizerData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor appetizer = db.rawQuery("SELECT * FROM " + TABLE_MENU_APPETIZER, null);
        return appetizer;
    }

    public Cursor getMenuDrinksData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor drinks = db.rawQuery("SELECT * FROM " + TABLE_MENU_DRINKS, null);
        return drinks;
    }

    public Cursor getMenuDessertsData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor desserts = db.rawQuery("SELECT * FROM " + TABLE_MENU_DESSERTS, null);
        return desserts;
    }

    public Cursor getReceiptData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor receipt = db.rawQuery("SELECT * FROM " + TABLE_RECEIPT, null);
        return receipt;
    }

    //Database update user registration data routine
    public int updateRegistration(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REGISTRATION_USERFIRSTNAME, registration.getUserFirstName());
        values.put(KEY_REGISTRATION_USERLASTNAME, registration.getUserLastName());
        values.put(KEY_REGISTRATION_USERAGE, registration.getUserAge());
        values.put(KEY_REGISTRATION_USERGENDER, registration.getUserGender());
        values.put(KEY_REGISTRATION_USEREMAIL, registration.getUserEmail());
        values.put(KEY_REGISTRATION_USERNAME, registration.getUserName());
        values.put(KEY_REGISTRATION_USERPASSWORD, registration.getUserPassword());

        return db.update(TABLE_REGISTRATIONS, values, KEY_REGISTRATION_pID + "=?",
                new String[]{String.valueOf(registration.getPrimaryID())});
    }

    //Database update user card data routine
    public int updateCard(CardHolder cardholder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CARD_NAME, cardholder.getCardHolderName());
        values.put(KEY_CARD_NUMBER, cardholder.getCardNumber());
        values.put(KEY_CARD_EXPIRATIONDATE, cardholder.getExpirationDate());
        values.put(KEY_CARD_CVV, cardholder.getCvvNumber());
        values.put(KEY_CARD_USERADDRESS, cardholder.getUserAddress());
        values.put(KEY_CARD_USERZIPCODE, cardholder.getUserZipCode());
        values.put(KEY_CARD_USERCITY, cardholder.getUserCity());
        values.put(KEY_CARD_USERSTATE, cardholder.getUserState());
        values.put(KEY_CARD_USERCOUNTRY, cardholder.getUserState());
        values.put(KEY_CARD_REGISTRATION_pID, cardholder.getCard_registrationID());

        return db.update(TABLE_CARDHOLDER, values, KEY_CARD_pID + "=?",
                new String[]{String.valueOf(cardholder.getPrimaryID())});
    }

    //Database delete user registration data routine
    public void deleteRegistration(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REGISTRATIONS, KEY_REGISTRATION_pID + "=?", new String[]{String.valueOf(registration.getPrimaryID())});
        db.close();
    }

    //Database delete user card data routine
    public void deleteCard(CardHolder cardholder) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REGISTRATIONS, KEY_REGISTRATION_pID + "=?", new String[]{String.valueOf(cardholder.getPrimaryID())});
        db.close();
    }

    //Database connection terminator
    public void DBConnectionTerminator() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
