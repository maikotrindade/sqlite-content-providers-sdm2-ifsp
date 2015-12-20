package br.edu.ifspsaocarlos.agenda.provider;

import android.net.Uri;

public class AgendaContract {

    public interface Provider {
        String AUTHORITY = "br.edu.ifspsaocarlos.agenda.provider";
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contacts");
        String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.br.edu.ifspsaocarlos.agenda.contacts";
        String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.br.edu.ifspsaocarlos.agenda.contacts";
        String KEY_ID = "id";
    }


}


