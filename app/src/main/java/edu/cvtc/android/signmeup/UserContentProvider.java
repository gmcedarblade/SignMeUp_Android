package edu.cvtc.android.signmeup;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Greg on 5/17/2017.
 */

public class UserContentProvider extends ContentProvider {

    private UserDatabaseHelper userDatabaseHelper;

    private static final int UPDATE = 1;
    private static final int QUERY = 2;

    private static final String AUTHORITY = "edu.cvtc.android.signmeup.provider";

    public static final String BASE_PATH = "user_table";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/user/#", UPDATE);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/filter/#", QUERY);
    }

    @Override
    public boolean onCreate() {
        userDatabaseHelper = new UserDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        checkColumns(projection);

        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(UserTable.TABLE_NAME);

        final int uriCode = uriMatcher.match(uri);

        switch (uriCode) {
            case QUERY:
                final String filter = uri.getLastPathSegment();
                if(!filter.equals("" + User.PHONE)) {
                    queryBuilder.appendWhere(UserTable.KEY_ID + "=" + filter);
                } else {
                    selection = null;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }

        final SQLiteDatabase database = userDatabaseHelper.getReadableDatabase();
        final Cursor cursor = queryBuilder.query(database, projection, selection, null, null, null, null);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) { return null; }


    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long id = 0;

        final int uriCode = uriMatcher.match(uri);

        switch (uriCode) {
            case UPDATE:
                final SQLiteDatabase database = userDatabaseHelper.getWritableDatabase();
                id = database.insert(UserTable.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(BASE_PATH + "/" + id);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int rowsDeleted = 0;
        final int uriCode = uriMatcher.match(uri);

        switch (uriCode) {

            case UPDATE:
                final String id = uri.getLastPathSegment();
                final SQLiteDatabase database = userDatabaseHelper.getWritableDatabase();
                rowsDeleted = database.delete(UserTable.TABLE_NAME, UserTable.KEY_ID + "=" + id, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);

        }

        if (rowsDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;

    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int rowsUpdated = 0;

        int uriCode = uriMatcher.match(uri);

        switch (uriCode) {

            case UPDATE:
                final String id = uri.getLastPathSegment();
                final SQLiteDatabase database = userDatabaseHelper.getWritableDatabase();
                rowsUpdated = database.update(UserTable.TABLE_NAME, values, UserTable.KEY_ID + "=" + id, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);


        }


        if (rowsUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;


    }

    private void checkColumns(String[] projection) {

        final String[] available = {
                UserTable.KEY_ID,
                UserTable.KEY_FIRST_NAME,
                UserTable.KEY_LAST_NAME,
                UserTable.KEY_PHONE,
                UserTable.KEY_EMAIL
        };

        if(projection != null) {

            final HashSet<String> requestedColumns = new HashSet<>(Arrays.asList(projection));
            final HashSet<String> availableColumns = new HashSet<>(Arrays.asList(available));

            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");

            }

        }

    }

}
