package com.example.jchen415.mywaytormobileapplication;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.*;

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

    //Default constructor which generates the database
    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Generating Database Tables for Registration, CardHolder and Four Menu Categories
    //Foreign keys: CardHolder and all four menu categories reference registration username
    //Foreign keys for customer_name & card_name reference registration_user (unique key)

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Enable foreign key constraints
        db.execSQL("PRAGMA foreign_keys=1;");
        //create tables
        db.execSQL("create table " + TABLE_REGISTRATIONS + " (REGISTRATION_pID INTEGER PRIMARY KEY AUTOINCREMENT, REGISTRATION_FIRST TEXT NOT NULL, REGISTRATION_LAST TEXT NOT NULL, REGISTRATION_USER TEXT UNIQUE, REGISTRATION_PASS TEXT NOT NULL, REGISTRATION_AGE INTEGER NOT NULL, REGISTRATION_GENDER TEXT NOT NULL, REGISTRATION_EMAIL TEXT UNIQUE)");
        db.execSQL("create table " + TABLE_CARDHOLDER + " (CARD_pID INTEGER PRIMARY KEY AUTOINCREMENT, CARD_NAME TEXT NOT NULL, CARD_NUMBER INTEGER UNIQUE, CARD_EXPIRATION TEXT NOT NULL, CARD_CVV INTEGER NOT NULL, CARD_ADDRESS TEXT NOT NULL, CARD_ZIPCODE INTEGER NOT NULL, CARD_CITY TEXT NOT NULL, CARD_STATE TEXT NOT NULL, CARD_COUNTRY TEXT NOT NULL, CARD_REGISTRATION_pID TEXT NOT NULL, FOREIGN KEY (CARD_REGISTRATION_pID) REFERENCES Registration (REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_MENU_DISH + "(DISH_pID INTEGER PRIMARY KEY AUTOINCREMENT, DISH_NAME TEXT NOT NULL, DISH_PRICE DOUBLE NOT NULL, DISH_QUANTITY INTEGER NOT NULL, DISH_REGISTRATION_pID NOT NULL, FOREIGN KEY (DISH_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_MENU_APPETIZER + "(APPETIZER_pID INTEGER PRIMARY KEY AUTOINCREMENT, APPETIZER_NAME TEXT NOT NULL, APPETIZER_PRICE DOUBLE NOT NULL, APPETIZER_QUANTITY INTEGER NOT NULL, APPETIZER_REGISTRATION_pID NOT NULL, FOREIGN KEY (APPETIZER_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_MENU_DRINKS + "(DRINKS_pID INTEGER PRIMARY KEY AUTOINCREMENT, DRINKS_NAME TEXT NOT NULL, DRINKS_PRICE DOUBLE NOT NULL, DRINKS_QUANTITY INTEGER NOT NULL, DRINKS_REGISTRATION_pID NOT NULL, FOREIGN KEY (DRINKS_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
        db.execSQL("create table " + TABLE_MENU_DESSERTS + "(DESSERTS_pID INTEGER PRIMARY KEY AUTOINCREMENT, DESSERTS_NAME TEXT NOT NULL, DESSERTS_PRICE DOUBLE NOT NULL, DESSERTS_QUANTITY INTEGER NOT NULL, DESSERTS_REGISTRATION_pID NOT NULL, FOREIGN KEY (DESSERTS_REGISTRATION_pID) REFERENCES Registration(REGISTRATION_pID))");
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
        db.close();
    }

    public int getFK() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT REGISTRATION_pID FROM Registration WHERE REGISTRATION_USER = '?'",null);
        int FK = 0;
        while(c.moveToNext()) {
            FK = c.getInt(0);
        }
        c.close();
        return FK;
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
}
