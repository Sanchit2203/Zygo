package com.example.zygo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    private static final String DATABASE_NAME = "zygo.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_CLIENT = "client";
    private static final String TABLE_DRIVER = "driver";

    // Common Column Names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";

    // Client Table Specific Columns
    private static final String COLUMN_CLIENT_EXTRA = "client_extra"; // Example: special client info

    // Driver Table Specific Columns
    private static final String COLUMN_DRIVER_LICENSE = "license"; // Example: driver license info

    // Create Table Queries
    private static final String CREATE_TABLE_CLIENT =
            "CREATE TABLE " + TABLE_CLIENT + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_EMAIL + " TEXT, "
                    + COLUMN_PHONE + " TEXT, "
                    + COLUMN_CLIENT_EXTRA + " TEXT);";

    private static final String CREATE_TABLE_DRIVER =
            "CREATE TABLE " + TABLE_DRIVER + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_EMAIL + " TEXT, "
                    + COLUMN_PHONE + " TEXT, "
                    + COLUMN_DRIVER_LICENSE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_DRIVER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVER);
        onCreate(db);
    }

    public long insertClient(String name, String email, String phone, String clientExtra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_CLIENT_EXTRA, clientExtra);
        return db.insert(TABLE_CLIENT, null, values);
    }

    public long insertDriver(String name, String email, String phone, String license) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_DRIVER_LICENSE, license);
        return db.insert(TABLE_DRIVER, null, values);
    }
    public boolean isUserExists(String email, String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CLIENT + " WHERE " + COLUMN_EMAIL + " = ? OR " + COLUMN_PHONE + " = ? " +
                "UNION " +
                "SELECT * FROM " + TABLE_DRIVER + " WHERE " + COLUMN_EMAIL + " = ? OR " + COLUMN_PHONE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, phone, email, phone});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public String getUserRoleByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String role = null;

        // Check in client table
        Cursor clientCursor = db.rawQuery("SELECT email FROM client WHERE email = ?", new String[]{email});
        if (clientCursor.moveToFirst()) {
            role = "client";
        }
        clientCursor.close();

        // Check in driver table
        if (role == null) {
            Cursor driverCursor = db.rawQuery("SELECT email FROM driver WHERE email = ?", new String[]{email});
            if (driverCursor.moveToFirst()) {
                role = "driver";
            }
            driverCursor.close();
        }

        return role;

    }
    public boolean updateDriverPhone(String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PHONE, phoneNumber);

        int result = db.update(TABLE_DRIVER, contentValues, null, null);
        return result > 0;
    }

    // Fetch driver's phone number
    public String getDriverPhone() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT phone FROM " + TABLE_DRIVER + " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            String phone = cursor.getString(0);
            cursor.close();
            return phone;
        } else {
            cursor.close();
            return null;
        }
    }

}
