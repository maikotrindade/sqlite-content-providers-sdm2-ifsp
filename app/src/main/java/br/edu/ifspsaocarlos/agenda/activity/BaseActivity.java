package br.edu.ifspsaocarlos.agenda.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifspsaocarlos.agenda.R;
import br.edu.ifspsaocarlos.agenda.adapter.ContactArrayAdapter;
import br.edu.ifspsaocarlos.agenda.data.ContactDAO;
import br.edu.ifspsaocarlos.agenda.model.Contact;

public class BaseActivity extends AppCompatActivity {

    protected ContactDAO contactDAO = new ContactDAO(this);
    public ListView mListView;
    public ContactArrayAdapter mContactArrayAdapter;
    protected SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2,
                                    long arg3) {
                Contact contact = (Contact) adapterView.getAdapter().getItem(arg2);
                Intent inte = new Intent(getApplicationContext(), DetailActivity.class);
                inte.putExtra("contact", contact);
                startActivityForResult(inte, 0);

            }

        });


        registerForContextMenu(mListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.searchContact).getActionView();

        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        mSearchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ContactArrayAdapter adapter = (ContactArrayAdapter) mListView.getAdapter();
        Contact contact = adapter.getItem(info.position);


        switch (item.getItemId()) {
            case R.id.delete_item:
                contactDAO.deleteContact(contact);
                Toast.makeText(getApplicationContext(), R.string.msg_delete_successful, Toast.LENGTH_SHORT).show();
                buildListView();
                return true;
        }
        return super.onContextItemSelected(item);
    }


    protected void buildListView() {
        List<Contact> values = contactDAO.buscaTodosContatos();
        mContactArrayAdapter = new ContactArrayAdapter(this, values);
        mListView.setAdapter(mContactArrayAdapter);

    }

    protected void buildSearchListView(String query) {
        List<Contact> values = contactDAO.buscaContato(query);
        mContactArrayAdapter = new ContactArrayAdapter(this, values);
        mListView.setAdapter(mContactArrayAdapter);

    }

}
