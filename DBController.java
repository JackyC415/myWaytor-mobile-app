package com.example.jchen415.mywaytor_mobile_application;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.*;
import java.util.*;

//Database controller; responsible for manipulating all database interaction activities.
//Functions include, but not limited to, insert, update, retrieve, edit and delete.
//Future implementations will include authentications and verifications.

public class DBController extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "myWaytor.DB";

    //Database Table names
    private static final String TABLE_LOGIN = "Login";
    private static final String TABLE_REGISTRATIONS = "Registration";
    private static final String TABLE_CARDHOLDER = "Cardholder";

    //Login Table Column Names
    private static final String KEY_LOGIN_pID = "Login primaryID";
    private static final String KEY_LOGIN_USERNAME = "Login userName";
    private static final String KEY_LOGIN_PASSWORD = "Login passWord";

    //Registration Table Column Names
    private static final String KEY_REGISTRATION_pID = "Registration primaryID";
    private static final String KEY_REGISTRATION_USERFIRSTNAME = "Registration firstName";
    private static final String KEY_REGISTRATION_USERLASTNAME = "Registration lastName";
    private static final String KEY_REGISTRATION_USERNAME = "Registration userName";
    private static final String KEY_REGISTRATION_USERPASSWORD = "Registration passWord";
    private static final String KEY_REGISTRATION_USERAGE = "Registration age";
    private static final String KEY_REGISTRATION_USERGENDER = "Registration gender";
    private static final String KEY_REGISTRATION_USEREMAIL = "Registration email";

    //CardHolder Table Column Names
    private static final String KEY_CARD_pID = "Card primaryID";
    private static final String KEY_CARD_NUMBER = "cardNumber";
    private static final String KEY_CARD_EXPIRATIONDATE = "cardExpirationDate";
    private static final String KEY_CARD_CVV = "cardCVV";
    private static final String KEY_CARD_USERADDRESS = "cardAddress";
    private static final String KEY_CARD_USERCITY = "cardCity";
    private static final String KEY_CARD_USERSTATE = "cardState";
    private static final String KEY_CARD_USERZIPCODE = "cardZipCode";
    private static final String KEY_CARD_USERCOUNTRY = "cardCountry";


    //Default constructor which generates the database
    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Generating Database Tables for Login, Registration and CardHolder
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN_TABLE = "Create Login Table" + TABLE_LOGIN + "("
                + KEY_LOGIN_pID + "LOGIN PRIMARY KEY ID," + "(" + KEY_LOGIN_USERNAME + "TEXT" +
                KEY_LOGIN_PASSWORD + "TEXT" + ");";

        String CREATE_REGISTRATION_TABLE = "Create Registration Table" + TABLE_REGISTRATIONS + "("
                + KEY_REGISTRATION_pID + "REGISTRATION PRIMARY KEY ID," + KEY_REGISTRATION_USERFIRSTNAME +
                "TEXT" + KEY_REGISTRATION_USERLASTNAME + "TEXT" + KEY_REGISTRATION_USERNAME +
                "TEXT," + KEY_REGISTRATION_USERPASSWORD + "TEXT," + KEY_REGISTRATION_USERAGE + "TEXT,"
                + KEY_REGISTRATION_USERGENDER + "TEXT," + KEY_REGISTRATION_USEREMAIL + " TEXT" + ");";

        String CREATE_CARDHOLDER_TABLE = "Create CardHolder Table" + TABLE_CARDHOLDER + "(" + KEY_CARD_pID
                + "PRIMARY KEY ID," + KEY_CARD_NUMBER + "TEXT" + KEY_CARD_EXPIRATIONDATE + "TEXT" +
                KEY_CARD_CVV + "TEXT" + KEY_CARD_USERADDRESS + "TEXT" + KEY_CARD_USERCITY + "TEXT"
                + KEY_CARD_USERSTATE + "TEXT" + KEY_CARD_USERZIPCODE + "TEXT" + KEY_CARD_USERCOUNTRY
                + "TEXT" + ");";

        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_REGISTRATION_TABLE);
        db.execSQL(CREATE_CARDHOLDER_TABLE);
    }

    //Database upgrade routine
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop previous table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDHOLDER);

        //Regenerate table
        onCreate(db);
    }

    public void insertLoginData(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN_pID, login.getLoginID());
        values.put(KEY_LOGIN_USERNAME, login.getLoginUsername());
        values.put(KEY_LOGIN_PASSWORD, login.getLoginPassword());

        db.insert(TABLE_REGISTRATIONS, null, values);
        db.close();
    }

    //Database insert user registration data routine
    public void insertRegistrationData(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REGISTRATION_pID, registration.getPrimaryID());
        values.put(KEY_REGISTRATION_USERFIRSTNAME, registration.getUserFirstName());
        values.put(KEY_REGISTRATION_USERLASTNAME, registration.getUserLastName());
        values.put(KEY_REGISTRATION_USERNAME, registration.getUserName());
        values.put(KEY_REGISTRATION_USERPASSWORD, registration.getUserPassword());
        values.put(KEY_REGISTRATION_USERAGE, registration.getUserAge());
        values.put(KEY_REGISTRATION_USERGENDER, registration.getUserGender());
        values.put(KEY_REGISTRATION_USEREMAIL, registration.getUserEmail());

        db.insert(TABLE_REGISTRATIONS, null, values);
        db.close();
    }


/*
    Registration getRegistrationData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REGISTRATIONS,new String[] {KEY_ID,KEY_USERNAME,
                KEY_USERPASSWORD,KEY_USERAGE,KEY_USERGENDER,KEY_USEREMAIL}, KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        Registration registration = new Registration(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5));
        close();
        return registration;
    } */

    //Database retrieve user registration data routine
    public List<Registration> getAllRegistrations() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Registration> registrationList = new ArrayList<>();

        String ListQuery = "SELECT * FROM " + TABLE_REGISTRATIONS;
        Cursor cursor = db.rawQuery(ListQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Registration registration = new Registration();
                registration.setPrimaryID(Integer.parseInt(cursor.getString(0)));
                registration.setUserFirstName(cursor.getString(1));
                registration.setUserLastName(cursor.getString(2));
                registration.setUserName((cursor.getString(3)));
                registration.setUserPassword(cursor.getString(4));
                registration.setUserAge(Integer.parseInt(cursor.getString(5)));
                registration.setUserGender((cursor.getString(6)));
                registration.setUserEmail(cursor.getString(7));

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
        values.put(KEY_REGISTRATION_pID, registration.getPrimaryID());
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
}
