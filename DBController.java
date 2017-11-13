package com.example.jchen415.mywaytor_mobile_application;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.*;
import java.util.*;

//Database controller; responsible for manipulating all database interaction activities
//Functions include, but not limited to, insert, update, retrieve, edit and delete
//Future implementations will include authentications and verifications as well as additional new features
public class DBController extends SQLiteOpenHelper {

    //Database Name
    private static final String DATABASE_NAME = "myWaytor.db";

    //Database Table names
    private static final String TABLE_REGISTRATIONS = "Registration";
    private static final String TABLE_CARDHOLDER = "Cardholder";

    //Registration Table Column Names
    private static final String KEY_REGISTRATION_pID = "R_ID";
    private static final String KEY_REGISTRATION_USERFIRSTNAME = "R_FIRST";
    private static final String KEY_REGISTRATION_USERLASTNAME = "R_LAST";
    private static final String KEY_REGISTRATION_USERNAME = "R_USER";
    private static final String KEY_REGISTRATION_USERPASSWORD = "R_PASS";
    private static final String KEY_REGISTRATION_USERAGE = "R_AGE";
    private static final String KEY_REGISTRATION_USERGENDER = "R_GENDER";
    private static final String KEY_REGISTRATION_USEREMAIL = "R_EMAIL";

    //CardHolder Table Column Names
    private static final String KEY_CARD_pID = "C_ID";
    private static final String KEY_CARD_NUMBER = "C_NUMBER";
    private static final String KEY_CARD_EXPIRATIONDATE = "C_EXPIRATION";
    private static final String KEY_CARD_CVV = "C_CVV";
    private static final String KEY_CARD_USERADDRESS = "C_ADDRESS";
    private static final String KEY_CARD_USERCITY = "C_CITY";
    private static final String KEY_CARD_USERSTATE = "C_STATE";
    private static final String KEY_CARD_USERZIPCODE = "C_ZIPCODE";
    private static final String KEY_CARD_USERCOUNTRY = "C_COUNTRY";

    //Default constructor which generates the database
    public DBController(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Generating Database Tables for Registration and CardHolder
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_REGISTRATIONS + " (R_ID INTEGER PRIMARY KEY AUTOINCREMENT, R_FIRST TEXT NOT NULL, R_LAST TEXT NOT NULL, R_USER TEXT NOT NULL, R_PASS TEXT NOT NULL, R_AGE INTEGER NOT NULL, R_GENDER TEXT NOT NULL, R_EMAIL TEXT NOT NULL)");
        db.execSQL("create table " + TABLE_CARDHOLDER + " (C_ID INTEGER PRIMARY KEY AUTOINCREMENT, C_NUMBER TEXT NOT NULL, C_EXPIRATION INTEGER NOT NULL, C_CVV INTEGER NOT NULL, C_ADDRESS TEXT NOT NULL, C_CITY TEXT NOT NULL, C_STATE TEXT NOT NULL, C_ZIPCODE INTEGER NOT NULL, C_COUNTRY TEXT NOT NULL)");
    }

    //Database upgrade routine
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop previous table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDHOLDER);
        //Regenerate table
        onCreate(db);
    }

    //Database insert user registration data routine
    public boolean insertRegistrationData(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REGISTRATION_USERFIRSTNAME, registration.getUserFirstName());
        values.put(KEY_REGISTRATION_USERLASTNAME, registration.getUserLastName());
        values.put(KEY_REGISTRATION_USERNAME, registration.getUserName());
        values.put(KEY_REGISTRATION_USERPASSWORD, registration.getUserPassword());
        values.put(KEY_REGISTRATION_USERAGE, registration.getUserAge());
        values.put(KEY_REGISTRATION_USERGENDER, registration.getUserGender());
        values.put(KEY_REGISTRATION_USEREMAIL, registration.getUserEmail());
        long inserted = db.insert(TABLE_REGISTRATIONS, null, values);

        if (inserted == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //Database insert user card data routine
    public void insertCardData(CardHolder cardholder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CARD_NUMBER, cardholder.getCardNumber());
        values.put(KEY_CARD_CVV, cardholder.getCvvNumber());
        values.put(KEY_CARD_EXPIRATIONDATE, cardholder.getExpirationDate());
        values.put(KEY_CARD_USERADDRESS, cardholder.getUserAddress());
        values.put(KEY_CARD_USERCITY, cardholder.getUserCity());
        values.put(KEY_CARD_USERCITY, cardholder.getUserCountry());
        values.put(KEY_CARD_USERCOUNTRY, cardholder.getUserState());
        values.put(KEY_CARD_USERZIPCODE, cardholder.getUserZipCode());

        db.insert(TABLE_REGISTRATIONS, null, values);
        db.close();
    }

    //Database retrieve user registration data routine
    public List<Registration> getAllRegistrations() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Registration> registrationList = new ArrayList<>();

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
        close();
        return registrationList;
    }

    //Database update user registration data routine
    public int updateRegistration(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REGISTRATION_USERFIRSTNAME, registration.getUserFirstName());
        values.put(KEY_REGISTRATION_USERLASTNAME, registration.getUserLastName());
        values.put(KEY_REGISTRATION_USERNAME, registration.getUserName());
        values.put(KEY_REGISTRATION_USERPASSWORD, registration.getUserPassword());
        values.put(KEY_REGISTRATION_USERAGE, registration.getUserAge());
        values.put(KEY_REGISTRATION_USERGENDER, registration.getUserGender());
        values.put(KEY_REGISTRATION_USEREMAIL, registration.getUserEmail());

        return db.update(TABLE_REGISTRATIONS, values, KEY_REGISTRATION_pID + "=?",
                new String[]{String.valueOf(registration.getPrimaryID())});
    }

    //Database delete user registration data routine
    public void deleteRegistration(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REGISTRATIONS, KEY_REGISTRATION_pID + "=?", new String[]{String.valueOf(registration.getPrimaryID())});
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
