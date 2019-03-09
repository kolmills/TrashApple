package com.example.trashapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class EnterEmployeeID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_employee_id);
    }

    public void SetEmployeeID(View theButton){

        //set the systems employee ID
        EditText ID = (EditText) findViewById(R.id.ID_entered);
        String temp = ID.getText().toString();

        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("CurrentEmployeeID", temp);
        editor.commit();

        CharSequence text = sharedPref.getString("CurrentEmployeeID", "");
        Toast toast = Toast.makeText(getApplicationContext(),text,
                Toast.LENGTH_SHORT);

        toast.show();

        setContentView(R.layout.activity_main);

    }
}
