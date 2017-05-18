package edu.cvtc.android.signmeup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Greg on 5/17/2017.
 */

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";

    public static final int DATABASE_VERSION = 1;

    public UserDatabaseHelper(final Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        UserTable.onCreate(database);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        UserTable.onUpgrade(database, oldVersion, newVersion);

    }

}


