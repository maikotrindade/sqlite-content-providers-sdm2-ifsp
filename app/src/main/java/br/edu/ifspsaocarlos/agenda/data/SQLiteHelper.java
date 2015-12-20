package br.edu.ifspsaocarlos.agenda.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "agenda.db";

    public static final String CONTACT_TABLE_NAME = "contatos";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "nome";
    public static final String KEY_FONE = "fone";
    public static final String KEY_FONE2 = "fone2";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DATA_NASCIMENTO = "datanascimento";
    public static final String KEY_ENDERECO = "endereco";
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_CREATE = "CREATE TABLE " + CONTACT_TABLE_NAME + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_FONE + " TEXT, " +
            KEY_EMAIL + " TEXT, " +
            KEY_FONE2 + " TEXT, " +
            KEY_DATA_NASCIMENTO + " TEXT, " +
            KEY_ENDERECO + " TEXT);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            final String ALTER_TABLE =
                    "ALTER TABLE " + CONTACT_TABLE_NAME +
                            " ADD COLUMN " + KEY_FONE2 + " text;";
            database.execSQL(ALTER_TABLE);
        }

        if (oldVersion < 3) {
            final String ALTER_TABLE =
                    "ALTER TABLE " + CONTACT_TABLE_NAME +
                            " ADD COLUMN " + KEY_DATA_NASCIMENTO + " text;";
            database.execSQL(ALTER_TABLE);
        }
    }
}