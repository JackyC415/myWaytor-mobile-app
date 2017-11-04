package com.example.lap.mywaytor;
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

    //Database Name
    private static final String DATABASE_NAME = "myWaytor2.db";

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

    private static final String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN
            + "(" +  KEY_LOGIN_pID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_LOGIN_USERNAME + " TEXT,"
            + KEY_LOGIN_PASSWORD + " TEXT" + ");";

    private static final String CREATE_REGISTRATION_TABLE = "CREATE TABLE " + TABLE_REGISTRATIONS
            + "(" + KEY_REGISTRATION_pID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_REGISTRATION_USERFIRSTNAME + " TEXT," + KEY_REGISTRATION_USERLASTNAME + " TEXT,"
            + KEY_REGISTRATION_USERNAME + " TEXT," + KEY_REGISTRATION_USERPASSWORD + " TEXT,"
            + KEY_REGISTRATION_USERAGE + " INTEGER," + KEY_REGISTRATION_USERGENDER + " TEXT,"
            + KEY_REGISTRATION_USEREMAIL + " TEXT" + ");";

    private static final String CREATE_CARDHOLDER_TABLE = "CREATE TABLE " + TABLE_CARDHOLDER
            + "(" + KEY_CARD_pID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_CARD_NUMBER + " INTEGER,"
            + KEY_CARD_EXPIRATIONDATE + " INTEGER,"
            + KEY_CARD_CVV + " INTEGER,"
            + KEY_CARD_USERADDRESS + " TEXT,"
            + KEY_CARD_USERCITY + " TEXT,"
            + KEY_CARD_USERSTATE + " TEXT,"
            + KEY_CARD_USERZIPCODE + " INTEGER,"
            + KEY_CARD_USERCOUNTRY + " TEXT" + ");";
    //Default constructor which generates the database
    public DBController(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //Generating Database Tables for Login, Registration and CardHolder
    @Override
    public void onCreate(SQLiteDatabase db) {
        //execute SQL commands
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

    //Database insert login data routine
    public void insertLoginData(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN_USERNAME, login.getLoginUsername());
        values.put(KEY_LOGIN_PASSWORD, login.getLoginPassword());

        db.insert(TABLE_REGISTRATIONS, null, values);
        db.close();
    }

    //Database insert user registration data routine
    public void insertRegistrationData(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
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

    //Database insert user card data routine
    public void insertCardData(CardHolder cardholder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REGISTRATION_USERFIRSTNAME, cardholder.getCardHolderName());
        values.put(KEY_REGISTRATION_USERLASTNAME, cardholder.getCardNumber());
        values.put(KEY_REGISTRATION_USERNAME, cardholder.getCvvNumber());
        values.put(KEY_REGISTRATION_USERPASSWORD, cardholder.getExpirationDate());
        values.put(KEY_REGISTRATION_USERAGE, cardholder.getUserAddress());
        values.put(KEY_REGISTRATION_USERGENDER, cardholder.getUserCity());
        values.put(KEY_REGISTRATION_USEREMAIL, cardholder.getUserCountry());
        values.put(KEY_REGISTRATION_USEREMAIL, cardholder.getUserState());
        values.put(KEY_REGISTRATION_USEREMAIL, cardholder.getUserZipCode());

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
}