package br.edu.ifspsaocarlos.agenda.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifspsaocarlos.agenda.model.Contact;

public class ContactDAO {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private SQLiteHelper mDatabaseHelper;

    public ContactDAO(Context context) {
        this.mContext = context;
        this.mDatabaseHelper = new SQLiteHelper(context);
    }

    public List<Contact> buscaTodosContatos() {
        mDatabase = mDatabaseHelper.getReadableDatabase();
        List<Contact> contacts = new ArrayList<Contact>();
        Cursor cursor = mDatabase.query(SQLiteHelper.CONTACT_TABLE_NAME, new String[]{SQLiteHelper.KEY_ID,
                        SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_FONE, SQLiteHelper.KEY_FONE2, SQLiteHelper
                        .KEY_EMAIL,
                        SQLiteHelper.KEY_DATA_NASCIMENTO, SQLiteHelper.KEY_ENDERECO}, null, null,
                null, null, SQLiteHelper.KEY_NAME);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Contact contato = new Contact();
                contato.setId(cursor.getInt(0));
                contato.setNome(cursor.getString(1));
                contato.setFone2(cursor.getString(2));
                contato.setFone(cursor.getString(3));
                contato.setEmail(cursor.getString(4));
                contato.setDataNascimento(cursor.getString(5));
                contato.setEndereco(cursor.getString(6));
                contacts.add(contato);
                cursor.moveToNext();
            }
            cursor.close();
        }
        mDatabase.close();
        return contacts;
    }

    public List<Contact> buscaContato(String nome) {
        mDatabase = mDatabaseHelper.getReadableDatabase();
        List<Contact> contacts = new ArrayList<Contact>();
        Cursor cursor = mDatabase.query(SQLiteHelper.CONTACT_TABLE_NAME, new String[]{SQLiteHelper.KEY_ID,
                        SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_FONE, SQLiteHelper.KEY_EMAIL,
                        SQLiteHelper.KEY_DATA_NASCIMENTO, SQLiteHelper.KEY_ENDERECO},
                SQLiteHelper.KEY_NAME + " like ? or " + SQLiteHelper.KEY_EMAIL + " = ?", new String[]{nome + "%",
                        nome},
                null, null, SQLiteHelper.KEY_NAME);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Contact contato = new Contact();
                contato.setId(cursor.getInt(0));
                contato.setNome(cursor.getString(1));
                contato.setFone(cursor.getString(2));
                contato.setEmail(cursor.getString(3));
                contato.setFone2(cursor.getString(4));
                contato.setDataNascimento(cursor.getString(5));
                contato.setEndereco(cursor.getString(6));
                contacts.add(contato);
                cursor.moveToNext();
            }
            cursor.close();
        }
        mDatabase.close();
        return contacts;
    }

    public void updateContact(Contact contact) {
        mDatabase = mDatabaseHelper.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put(SQLiteHelper.KEY_NAME, contact.getNome());
        updateValues.put(SQLiteHelper.KEY_FONE, contact.getFone());
        updateValues.put(SQLiteHelper.KEY_EMAIL, contact.getEmail());
        updateValues.put(SQLiteHelper.KEY_FONE2, contact.getFone2());
        updateValues.put(SQLiteHelper.KEY_DATA_NASCIMENTO, contact.getDataNascimento());
        updateValues.put(SQLiteHelper.KEY_ENDERECO, contact.getEndereco());
        mDatabase.update(SQLiteHelper.CONTACT_TABLE_NAME, updateValues, SQLiteHelper.KEY_ID + "=" + contact.getId(), null);
        mDatabase.close();
    }

    public void createContact(Contact contact) {
        mDatabase = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_NAME, contact.getNome());
        values.put(SQLiteHelper.KEY_FONE, contact.getFone());
        values.put(SQLiteHelper.KEY_EMAIL, contact.getEmail());
        values.put(SQLiteHelper.KEY_FONE2, contact.getFone2());
        values.put(SQLiteHelper.KEY_DATA_NASCIMENTO, contact.getDataNascimento());
        values.put(SQLiteHelper.KEY_ENDERECO, contact.getEndereco());
        mDatabase.insert(SQLiteHelper.CONTACT_TABLE_NAME, null, values);
        mDatabase.close();
    }

    public void deleteContact(Contact contact) {
        mDatabase = mDatabaseHelper.getWritableDatabase();
        mDatabase.delete(SQLiteHelper.CONTACT_TABLE_NAME, SQLiteHelper.KEY_ID + "="
                + contact.getId(), null);
        mDatabase.close();
    }
}