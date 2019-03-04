package com.example.trashapp;

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

        CharSequence text = getString(R.string.CurrentEmployeeID);
        Toast toast = Toast.makeText(getApplicationContext(),text,
                Toast.LENGTH_SHORT);

        toast.show();

        setContentView(R.layout.activity_main);

    }
}
