package com.rd.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    database database = new database(this, "USER", null, 1);

    private EditText edtid, edtname, edtcontact, edtemail,edtpassword;
    private ListView mydatalist;
    private ArrayList<String> stringArrayList = new ArrayList<String>();
    private ArrayAdapter<String> stringArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtid=findViewById(R.id.edtid);
        edtname = findViewById(R.id.edtname);
        edtcontact = findViewById(R.id.edtcontact);
        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);

        mydatalist = findViewById(R.id.mydatalist);

        User_Select();

        mydatalist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String User = mydatalist.getItemAtPosition(i).toString();
                Cursor cursor = database.UserSelectById(Integer.parseInt(User));
                cursor.moveToNext();
                edtid.setText(cursor.getString(0));
                edtname.setText(cursor.getString(1));
                edtcontact.setText(cursor.getString(2));
                edtemail.setText(cursor.getString(3));
                edtpassword.setText(cursor.getString(4));
            }
        });
    }
    public void UserdeleteData(View view) {
        database.UserDelete(Integer.parseInt(edtid.getText().toString()));

        Reset();
    }

    public void UserupdateData(View view) {
        database.UserUpdate(Integer.parseInt(edtid.getText().toString()), edtname.getText().toString(), edtcontact.getText().toString(), edtemail.getText().toString(), edtpassword.getText().toString());

        Reset();
    }

    public void UserInsertData(View view) {
        database.UserInsert(edtname.getText().toString(), edtcontact.getText().toString(), edtemail.getText().toString(), edtpassword.getText().toString());

        Reset();
    }

    private void User_Select() {
        stringArrayList = new ArrayList<String>();
        Cursor cursor = database.UserSelect();

        stringArrayAdapter = new ArrayAdapter<String>(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, stringArrayList);
        mydatalist.setAdapter(stringArrayAdapter);

        while (cursor.moveToNext()) {
            stringArrayList.add(cursor.getString(0));
        }

        stringArrayAdapter.notifyDataSetChanged();
    }

    private void Reset(){
        edtid.setText("");
        edtname.setText("");
        edtcontact.setText("");
        edtemail.setText("");
        edtpassword.setText("");

        User_Select();
    }

}