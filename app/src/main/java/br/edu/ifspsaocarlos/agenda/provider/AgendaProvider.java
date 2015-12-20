package br.edu.ifspsaocarlos.agenda.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import br.edu.ifspsaocarlos.agenda.data.SQLiteHelper;
import br.edu.ifspsaocarlos.agenda.provider.AgendaContract.Provider;


public class AgendaProvider extends ContentProvider {

    public static final int CONTACTS = 1;
    public static final int CONTACTS_ID = 2;

    private SQLiteDatabase mDatabase;
    private SQLiteHelper mDatabaseHelper;

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(Provider.AUTHORITY, "contacts", CONTACTS);
        sURIMatcher.addURI(Provider.AUTHORITY, "contacts/#", CONTACTS_ID);
    }

    @Override
    public boolean onCreate()
    {
        mDatabaseHelper = new SQLiteHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        mDatabase = mDatabaseHelper.getWritableDatabase();
        Cursor cursor;
        switch(sURIMatcher.match(uri))
        {
            case CONTACTS:
                cursor = mDatabase.query(SQLiteHelper.CONTACT_TABLE_NAME, projection, selection, selectionArgs, null,null,sortOrder);
                break;
            case CONTACTS_ID:
                cursor = mDatabase.query(SQLiteHelper.CONTACT_TABLE_NAME, projection, Provider.KEY_ID + "=" + uri
                        .getLastPathSegment(), null,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
        return cursor;
    }


    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
       /* mDatabase = mDatabaseHelper.getWritableDatabase();
        int uriType = sURIMatcher.match(uri);
        int count;
        switch(uriType)
        {
            case CONTACTS:
                count=mDatabase.delete(SQLiteHelper.CONTACT_TABLE_NAME, where,whereArgs);
                break;
            case CONTACTS_ID:
                count=mDatabase.delete(SQLiteHelper.CONTACT_TABLE_NAME, Provider.KEY_ID + "=" + uri.getPathSegments().get(1), null);
                break;

            default:
                throw new IllegalArgumentException("URI desconhecida");

        }
        mDatabase.close();
        return count;*/
        return 0;
    }



    @Override
    public String getType(Uri uri) {
        switch(sURIMatcher.match(uri))
        {
            case CONTACTS:
                return Provider.CONTENT_TYPE;
            case CONTACTS_ID:
                return Provider.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       /* mDatabase = mDatabaseHelper.getWritableDatabase();
        int uriType = sURIMatcher.match(uri);
        long id;
        switch(uriType)
        {
            case CONTACTS:
                id=mDatabase.insert(SQLiteHelper.CONTACT_TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("URI desconhecida");

        }
        uri = ContentUris.withAppendedId(uri, id);
        return uri;*/
        return null;
    }


    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        /*mDatabase = mDatabaseHelper.getWritableDatabase();
        int uriType = sURIMatcher.match(uri);
        int count;
        switch(uriType)
        {
            case CONTACTS:
                count=mDatabase.update(SQLiteHelper.CONTACT_TABLE_NAME, values,where,whereArgs);
                break;
            case CONTACTS_ID:
                count=mDatabase.update(SQLiteHelper.CONTACT_TABLE_NAME, values, Provider.KEY_ID + "=" + uri.getPathSegments().get(1), null);
                break;

            default:
                throw new IllegalArgumentException("URI desconhecida");
        }
        mDatabase.close();
        return count;*/
        return 0;
    }
}