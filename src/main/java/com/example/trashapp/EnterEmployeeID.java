package com.example.trashapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterEmployeeID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_employee_id);
    }

    public void SetEmployeeID(View theButton){

        //set the systems employee ID
        EditText CurrentEmployeeID = (EditText) findViewById(R.id.ID_entered);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.CurrentEmployeeID), newHighScore);
        editor.commit();

        CharSequence text = getString(R.string.CurrentEmployeeID);
        Toast toast = Toast.makeText(getApplicationContext(),text,
                Toast.LENGTH_SHORT);

        toast.show();

        setContentView(R.layout.activity_main);

    }
}
