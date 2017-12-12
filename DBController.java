package com.example.jchen415.mywaytormobileapplication;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.*;
import android.util.Log;
import android.widget.Toast;

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
    private static final String TABLE_MENU1 = "Menu1";
    private static final String TABLE_MENU2 = "Menu2";
    private static final String TABLE_HISTORY = "History";

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
    private static final String KEY_CARD_REGISTRATION_USER = "CARD_REGISTRATION_USER";
    private static final String KEY_CARD_NAME = "CARD_NAME";
    private static final String KEY_CARD_NUMBER = "CARD_NUMBER";
    private static final String KEY_CARD_EXPIRATIONDATE = "CARD_EXPIRATION";
    private static final String KEY_CARD_CVV = "CARD_CVV";
    private static final String KEY_CARD_USERADDRESS = "CARD_ADDRESS";
    private static final String KEY_CARD_USERZIPCODE = "CARD_ZIPCODE";
    private static final String KEY_CARD_USERCITY = "CARD_CITY";
    private static final String KEY_CARD_USERSTATE = "CARD_STATE";
    private static final String KEY_CARD_USERCOUNTRY = "CARD_COUNTRY";

    //Menu Table Column Names
    private static final String KEY_MENU_NAME = "MENU_NAME";
    private static final String KEY_MENU_VALUE = "MENU_VALUE";
    private static final String KEY_MENU_NUM = "MENU_NUMBER";

    //Default constructor which generates the database
    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints upon database create to ensure enforcement
            db.execSQL("PRAGMA foreign_keys=1;");
            Log.d("TAG", "Foreign Keys Constraint Enabled!!!");
        }
    }

    //Generating Database Tables for Registration, CardHolder and Four Menu Categories
    //Foreign Keys referencing Registration PK
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Tables
        try {
            db.execSQL("create table " + TABLE_REGISTRATIONS + " (REGISTRATION_pID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, REGISTRATION_FIRST TEXT NOT NULL, REGISTRATION_LAST TEXT NOT NULL, REGISTRATION_USER TEXT UNIQUE, REGISTRATION_PASS TEXT NOT NULL, REGISTRATION_AGE INTEGER NOT NULL, REGISTRATION_GENDER TEXT NOT NULL, REGISTRATION_EMAIL TEXT UNIQUE)");
            db.execSQL("create table " + TABLE_CARDHOLDER + " (CARD_pID INTEGER PRIMARY KEY NOT NULL, CARD_REGISTRATION_USER TEXT NOT NULL, CARD_NAME TEXT NOT NULL, CARD_NUMBER INTEGER UNIQUE, CARD_EXPIRATION TEXT NOT NULL, CARD_CVV INTEGER NOT NULL, CARD_ADDRESS TEXT NOT NULL, CARD_ZIPCODE INTEGER NOT NULL, CARD_CITY TEXT NOT NULL, CARD_STATE TEXT NOT NULL, CARD_COUNTRY TEXT NOT NULL, FOREIGN KEY(CARD_REGISTRATION_USER) REFERENCES Registration(REGISTRATION_USER))");
            db.execSQL("create table " + TABLE_MENU1 + "(MENU_NAME TEXT NOT NULL, MENU_VALUE TEXT NOT NULL, MENU_NUMBER NOT NULL)");
            db.execSQL("create table " + TABLE_MENU2 + "(MENU_NAME TEXT NOT NULL, MENU_VALUE TEXT NOT NULL, MENU_NUMBER NOT NULL)");
            db.execSQL("create table " + TABLE_HISTORY + "(MENU_NAME TEXT NOT NULL, MENU_VALUE TEXT NOT NULL, MENU_NUMBER NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "Tables Created Successfully!!!");
    }

    //Database upgrade routine
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop previous table if exists on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDHOLDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        //Regenerate Tables
        onCreate(db);
        Log.d("TAG", "Tables Dropped Successfully!!!");
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

    //Database login authentication routine
    public String loginAuth(String username) {
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

    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT REGISTRATION_USER FROM " + TABLE_REGISTRATIONS, null);
        String user;
        if (cursor.moveToFirst()) {
            do {
                user = cursor.getString(0);
                if (user.equals(username)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }

    public boolean checkEmailExists(String emails) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT REGISTRATION_EMAIL FROM " + TABLE_REGISTRATIONS, null);
        String email;
        if (cursor.moveToFirst()) {
            do {
                email = cursor.getString(0);
                if (email.equals(emails)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }

    public boolean checkCardExists(String cards) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CARD_NUMBER FROM " + TABLE_CARDHOLDER, null);
        String card;
        //Loop through the CardHolder table database for CARD_NUMBER column and if card matches the input parameter
        //return true if found, else return false
        if (cursor.moveToFirst()) {
            do {
                card = cursor.getString(0);
                if (card.equals(cards)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }


    //Database match foreign key routine
    public String getFK() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT REGISTRATION_USER FROM " + TABLE_REGISTRATIONS, null);
        //+ " R JOIN " + TABLE_CARDHOLDER + " C ON R.REGISTRATION_pID = C.CARD_pID"
        String FK = "";
        while (c.moveToNext()) {
            FK = c.getString(0);
        }
        c.close();
        return FK;
    }

    //Database get card number
    public String getCardNumber() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT CARD_NUMBER FROM " + TABLE_CARDHOLDER, null);
        String cardNumber = "";
        while (c.moveToNext()) {
           cardNumber = c.getString(0);
        }
        c.close();
        return cardNumber;
    }

    //Database acquire cardholder primary ID for delete card
    public String getCardpID() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT CARD_pID FROM " + TABLE_CARDHOLDER, null);
        String cardNumber = "";
        while (c.moveToNext()) {
            cardNumber = c.getString(0);
        }
        c.close();
        return cardNumber;
    }

    //Database validate card routine
    public boolean checkCard() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CARDHOLDER, null);

        if (c != null && (c.getCount() > 0)) {
            c.close();
            return true;
        }
        return false;
    }

    public boolean checkSummary1() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MENU1, null);

        if (c != null && (c.getCount() > 0)) {
            c.close();
            return true;
        }
        return false;
    }

    public boolean checkSummary2() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MENU2, null);

        if (c != null && (c.getCount() > 0)) {
            c.close();
            return true;
        }
        return false;
    }

    //Database insert user card data routine
    public void insertCardData(CardHolder cardholder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CARD_REGISTRATION_USER, cardholder.getCard_registrationUser());
        values.put(KEY_CARD_NAME, cardholder.getCardHolderName());
        values.put(KEY_CARD_NUMBER, cardholder.getCardNumber());
        values.put(KEY_CARD_EXPIRATIONDATE, cardholder.getExpirationDate());
        values.put(KEY_CARD_CVV, cardholder.getCvvNumber());
        values.put(KEY_CARD_USERADDRESS, cardholder.getUserAddress());
        values.put(KEY_CARD_USERZIPCODE, cardholder.getUserZipCode());
        values.put(KEY_CARD_USERCITY, cardholder.getUserCity());
        values.put(KEY_CARD_USERSTATE, cardholder.getUserState());
        values.put(KEY_CARD_USERCOUNTRY, cardholder.getUserState());

        db.insert(TABLE_CARDHOLDER, null, values);
        Log.d("TAG", "Cardholder Data Inserted Successfully!!!");
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
        values.put(KEY_CARD_REGISTRATION_USER, cardholder.getCard_registrationUser());
        values.put(KEY_CARD_NAME, cardholder.getCardHolderName());
        values.put(KEY_CARD_NUMBER, cardholder.getCardNumber());
        values.put(KEY_CARD_EXPIRATIONDATE, cardholder.getExpirationDate());
        values.put(KEY_CARD_CVV, cardholder.getCvvNumber());
        values.put(KEY_CARD_USERADDRESS, cardholder.getUserAddress());
        values.put(KEY_CARD_USERZIPCODE, cardholder.getUserZipCode());
        values.put(KEY_CARD_USERCITY, cardholder.getUserCity());
        values.put(KEY_CARD_USERSTATE, cardholder.getUserState());
        values.put(KEY_CARD_USERCOUNTRY, cardholder.getUserState());

        return db.update(TABLE_CARDHOLDER, values, KEY_CARD_pID + "=?",
                new String[]{String.valueOf(cardholder.getPrimaryID())});
    }

    //Database delete user card data routine
    public void deleteCard(String pID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CARDHOLDER, KEY_CARD_pID + "=?", new String[]{pID});
        db.close();
    }

    //insert data for menu 1
    public void insert(order tes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_MENU_NAME, tes.getFoodName());
        values.put(KEY_MENU_VALUE, tes.getFoodPrice());
        values.put(KEY_MENU_NUM, tes.getAmount());

        db.insert(TABLE_MENU1, null, values);
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }


    //insert data for menu2
    public void insert2(order tes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_NAME, tes.getFoodName());
        values.put(KEY_MENU_VALUE, tes.getFoodPrice());
        values.put(KEY_MENU_NUM, tes.getAmount());

        db.insert(TABLE_MENU2, null, values);
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }


    //get data from menu 1
    public Cursor getAllData() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_MENU1, null);

        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    //get data from menu 2
    public Cursor getAllData2() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_MENU2, null);

        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public void deleteData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MENU1, null, null);
        db.delete(TABLE_MENU2, null, null);

    }

    //Database connection terminator
    public void DBConnectionTerminator() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
