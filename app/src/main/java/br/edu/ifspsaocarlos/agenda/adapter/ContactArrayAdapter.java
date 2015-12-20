package br.edu.ifspsaocarlos.agenda.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ifspsaocarlos.agenda.R;
import br.edu.ifspsaocarlos.agenda.model.Contact;

public class ContactArrayAdapter extends ArrayAdapter<Contact> {
    private LayoutInflater mInflater;


    public ContactArrayAdapter(Activity activity, List<Contact> objects) {
        super(activity, R.layout.contact_item, objects);
        this.mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.contact_item, null);
            holder = new ViewHolder();
            holder.mName = (TextView) convertView.findViewById(R.id.name);
            holder.mEmail = (TextView) convertView.findViewById(R.id.email);
            holder.mPhone = (TextView) convertView.findViewById(R.id.phone);
            holder.mPhone2 = (TextView) convertView.findViewById(R.id.phone2);
            holder.mAddress = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = getItem(position);
        holder.mName.setText(contact.getNome());
        holder.mEmail.setText(contact.getEmail());
        holder.mPhone.setText(contact.getFone());
        holder.mPhone2.setText(contact.getFone2());
        holder.mAddress.setText(contact.getEndereco());
        return convertView;
    }

    static class ViewHolder {
        public TextView mName;
        public TextView mEmail;
        public TextView mPhone;
        public TextView mPhone2;
        public TextView mAddress;
    }

}


