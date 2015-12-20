package br.edu.ifspsaocarlos.agenda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifspsaocarlos.agenda.R;
import br.edu.ifspsaocarlos.agenda.data.ContactDAO;
import br.edu.ifspsaocarlos.agenda.model.Contact;

public class DetailActivity extends AppCompatActivity {
    private Contact mContact;
    private ContactDAO mContactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("contact")) {

            this.mContact = (Contact) getIntent().getSerializableExtra("contact");
            EditText nameText = (EditText) findViewById(R.id.editText1);
            nameText.setText(mContact.getNome());

            EditText foneText = (EditText) findViewById(R.id.editText2);
            foneText.setText(mContact.getFone());

            EditText foneText2 = (EditText) findViewById(R.id.editText6);
            foneText2.setText(mContact.getFone2());

            EditText emailText = (EditText) findViewById(R.id.editText3);
            emailText.setText(mContact.getEmail());

            EditText addressText = (EditText) findViewById(R.id.editText4);
            addressText.setText(mContact.getEndereco());

            EditText dataNascimento = (EditText) findViewById(R.id.editText5);
            dataNascimento.setText(mContact.getDataNascimento());

            int pos = mContact.getNome().indexOf(" ");
            if (pos == -1)
                pos = mContact.getNome().length();

            setTitle(mContact.getNome().substring(0, pos));
        }

        mContactDAO = new ContactDAO(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        if (!getIntent().hasExtra("contact")) {
            MenuItem item = menu.findItem(R.id.deleteContact);
            item.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveContact:
                salvar();
                return true;
            case R.id.deleteContact:
                mContactDAO.deleteContact(mContact);
                Toast.makeText(getApplicationContext(), R.string.msg_delete_successful, Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void salvar() {
        EditText nameEdtTxt = ((EditText) findViewById(R.id.editText1));
        EditText foneEdtTxt = ((EditText) findViewById(R.id.editText2));
        EditText fone2EdtTxt = ((EditText) findViewById(R.id.editText6));
        EditText emailEdtTxt = ((EditText) findViewById(R.id.editText3));
        EditText enderecoEdtTxt = ((EditText) findViewById(R.id.editText4));
        EditText dataNascimentoEdtTxt = ((EditText) findViewById(R.id.editText5));

        final EditText[] fields = {nameEdtTxt, foneEdtTxt, fone2EdtTxt, emailEdtTxt, enderecoEdtTxt,
                dataNascimentoEdtTxt};
        if (isValidFields(fields)) {
            if (mContact == null) {
                mContact = new Contact();
                mContact.setNome(nameEdtTxt.getText().toString());
                mContact.setFone(foneEdtTxt.getText().toString());
                mContact.setFone2(fone2EdtTxt.getText().toString());
                mContact.setEmail(emailEdtTxt.getText().toString());
                mContact.setDataNascimento(dataNascimentoEdtTxt.getText().toString());
                mContact.setEndereco(enderecoEdtTxt.getText().toString());

                mContactDAO.createContact(mContact);
                Toast.makeText(this, R.string.msg_inserted_successful, Toast.LENGTH_SHORT).show();
            } else {
                mContact.setNome(nameEdtTxt.getText().toString());
                mContact.setFone(foneEdtTxt.getText().toString());
                mContact.setFone2(fone2EdtTxt.getText().toString());
                mContact.setEmail(emailEdtTxt.getText().toString());
                mContact.setDataNascimento(dataNascimentoEdtTxt.getText().toString());
                mContact.setEndereco(enderecoEdtTxt.getText().toString());

                mContactDAO.updateContact(mContact);
                Toast.makeText(this, R.string.msg_update_successful, Toast.LENGTH_SHORT).show();
            }

            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    private boolean isValidFields(EditText[] fields) {
        boolean isValid = true;
        for (EditText field : fields) {
            if (field.getText().toString().isEmpty()) {
                field.setError("Campo Obrigatório");
                isValid = false;
            }
        }
        return isValid;
    }

}
