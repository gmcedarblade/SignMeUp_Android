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


    private UserView.OnUserChangeListener onUserChangeListener;

    public UserCursorAdapter(Context context, Cursor cursor, int flags) {

        super(context, cursor, flags);

    }

    public void setOnUserChangeListener(final UserView.OnUserChangeListener onUserChangeListener) {

        this.onUserChangeListener = onUserChangeListener;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        final User user = new User(
                cursor.getString(UserTable.INDEX_FIRST_NAME),
                cursor.getString(UserTable.INDEX_LAST_NAME),
                cursor.getString(UserTable.INDEX_PHONE),
                cursor.getString(UserTable.INDEX_EMAIL),
                cursor.getLong(UserTable.INDEX_ID));

        final UserView userView = new UserView(context, user);

        userView.setOnUserChangeListener(onUserChangeListener);

        return userView;


    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final User user = new User(
                cursor.getString(UserTable.INDEX_FIRST_NAME),
                cursor.getString(UserTable.INDEX_LAST_NAME),
                cursor.getString(UserTable.INDEX_PHONE),
                cursor.getString(UserTable.INDEX_EMAIL),
                cursor.getLong(UserTable.INDEX_ID));

        ((UserView) view).setOnUserChangeListener(null);
        ((UserView) view).setUser(user);
        ((UserView) view).setOnUserChangeListener(onUserChangeListener);

    }
}
