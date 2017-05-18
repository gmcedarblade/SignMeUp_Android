package edu.cvtc.android.signmeup;

/**
 * Created by Greg on 5/17/2017.
 */

public class UserTable {

    public static final String TABLE_NAME = "user_table";

    public static final String KEY_ID = "_id";
    public static final int INDEX_ID = 0;

    public static final String KEY_FIRST_NAME = "firstName";
    public static final int INDEX_FIRST_NAME = 1;

    public static final String KEY_LAST_NAME = "lastName";
    public static final int INDEX_LAST_NAME = 2;

    public static final String KEY_PHONE = "phone";
    public static final int INDEX_PHONE = 3;

    public static final String KEY_EMAIL = "emailAddress";
    public static final int INDEX_EMAIL = 4;

    public static final String DATABASE_CREATE = "create table " + TABLE_NAME + " (" +
            KEY_ID + " integer primary key autoincrement, " +
            KEY_FIRST_NAME + " text not null, " +
            KEY_LAST_NAME + " text not null, " +
            KEY_PHONE + " text not null, "

}
