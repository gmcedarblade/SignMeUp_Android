package edu.cvtc.android.signmeup;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Greg on 5/17/2017.
 */

public class UserCursorAdapter extends CursorAdapter {


    public UserCursorAdapter(Context context, Cursor cursor, int flags) {

        super(context, cursor, flags);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        final User user = new User(
                cursor.getString(UserTable.INDEX_FIRST_NAME),
                cursor.getString(UserTable.INDEX_LAST_NAME),
                cursor.getString(UserTable.INDEX_PHONE),
                cursor.getString(UserTable.INDEX_EMAIL),
                cursor.getLong(UserTable.INDEX_ID));

        //final UserInfo userInfo = new UserInfo(context, user);

        //return userInfo;



        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final User user = new User(
                cursor.getString(UserTable.INDEX_FIRST_NAME),
                cursor.getString(UserTable.INDEX_LAST_NAME),
                cursor.getString(UserTable.INDEX_PHONE),
                cursor.getString(UserTable.INDEX_EMAIL),
                cursor.getLong(UserTable.INDEX_ID));

    }
}
