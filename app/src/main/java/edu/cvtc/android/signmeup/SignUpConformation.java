package edu.cvtc.android.signmeup;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Greg on 5/17/2017.
 */

public  class SignUpConformation extends AppCompatActivity  implements UserView.OnUserChangeListener, LoaderManager.LoaderCallbacks<Cursor> {


    private ListView userListView;

    private UserCursorAdapter userCursorAdapter;

    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up_conformation);

        final TextView conformationTextView = (TextView) findViewById(R.id.conformationTextView);

        userCursorAdapter = new UserCursorAdapter(this, null, 0);

        userListView = (ListView) findViewById(R.id.userListView);
        userListView.setAdapter(userCursorAdapter);

        final Bundle bundle = getIntent().getExtras();

        if (null == bundle) {

            conformationTextView.setText("Error: Bundle was null...");

        } else {

            final String firstName = bundle.getString("firstName");
            final String lastName = bundle.getString("lastName");
            final String phone = bundle.getString("phone");
            final String email = bundle.getString("email");

            final String iOS = bundle.getString("iOSDevelopment");
            final String android = bundle.getString("androidDevelopment");
            final String php = bundle.getString("phpDevelopment");

            Log.d("iOS", "Value: " + iOS);
            Log.d("android", "Value: " + android);
            Log.d("php", "Value: " + php);

            if (null == firstName || firstName.isEmpty()
                    || null == lastName || lastName.isEmpty()
                    || null == phone || phone.isEmpty()
                    || null == email || email.isEmpty()) {


                conformationTextView.setText("Error: Missing information...");

            } else if (null != iOS && null == android && null == php) {

                conformationTextView.setText("Hello " + firstName
                        + " " + lastName + "! Thank you for confirming "
                        + "you will be coming to my event(s). You will receive "
                        + "a conformation email at " + email + " shortly. Thank you again for "
                        + "signing up for " + iOS);

            } else if (null != iOS && null != android && null == php) {

                conformationTextView.setText("Hello " + firstName
                        + " " + lastName + "! Thank you for confirming "
                        + "you will be coming to my event(s). You will receive "
                        + "a conformation email at " + email + " shortly. Thank you again for "
                        + "signing up for " + iOS + " and " + android);

            } else if (null != iOS && null == android && null != php) {

                conformationTextView.setText("Hello " + firstName
                        + " " + lastName + "! Thank you for confirming "
                        + "you will be coming to my event(s). You will receive "
                        + "a conformation email at " + email + " shortly. Thank you again for "
                        + "signing up for " + iOS + " and " + php);

            } else if (null == iOS && null != android && null == php) {

                conformationTextView.setText("Hello " + firstName
                        + " " + lastName + "! Thank you for confirming "
                        + "you will be coming to my event(s). You will receive "
                        + "a conformation email at " + email + " shortly. Thank you again for "
                        + "signing up for " +  android);

            } else if (null == iOS && null != android && null != php) {

                conformationTextView.setText("Hello " + firstName
                        + " " + lastName + "! Thank you for confirming "
                        + "you will be coming to my event(s). You will receive "
                        + "a conformation email at " + email + " shortly. Thank you again for "
                        + "signing up for " + android + " and " + php);

            } else if (null == iOS && null == android && null != php) {

                conformationTextView.setText("Hello " + firstName
                        + " " + lastName + "! Thank you for confirming "
                        + "you will be coming to my event(s). You will receive "
                        + "a conformation email at " + email + " shortly. Thank you again for "
                        + "signing up for " + php);

            } else if (null != iOS && null != android && null != php) {

                conformationTextView.setText("Hello " + firstName
                        + " " + lastName + "! Thank you for confirming "
                        + "you will be coming to my event(s). You will receive "
                        + "a conformation email at " + email + " shortly. Thank you again for "
                        + "signing up for " + iOS + ", " + android + " and " + php);

            }
        }

        //getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        //reloadData();

    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        final String[] projection = {

                UserTable.KEY_ID,
                UserTable.KEY_FIRST_NAME,
                UserTable.KEY_LAST_NAME,
                UserTable.KEY_PHONE,
                UserTable.KEY_EMAIL
        };

        final Uri uri = Uri.parse(UserContentProvider.CONTENT_URI + "/user/" + UserTable.KEY_PHONE);

        final CursorLoader cursorLoader = new CursorLoader(this, uri, projection, null, null, null);

        return cursorLoader;

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        userCursorAdapter.swapCursor(data);
        userCursorAdapter.setOnUserChangeListener(this);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        userCursorAdapter.swapCursor(null);

    }

    @Override
    public void onUserChanged(UserView view, User user) {

        final Uri uri = Uri.parse(UserContentProvider.CONTENT_URI + "/user/" + user.getId());
        getContentResolver().update(uri, setUpContentValues(user), null, null);

        reloadData();

    }

    private void reloadData() {

        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);

    }

    private ContentValues setUpContentValues(final User user) {

        final ContentValues contentValues = new ContentValues();

        contentValues.put(UserTable.KEY_FIRST_NAME, user.getFirstName());
        contentValues.put(UserTable.KEY_LAST_NAME, user.getLastName());
        contentValues.put(UserTable.KEY_PHONE, user.getPhone());
        contentValues.put(UserTable.KEY_EMAIL, user.getEmail());

        return contentValues;

    }
}
