package com.example.trashapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnterEmployeeID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_employee_id);
    }

    public void SetEmployeeID(View theButton){

        //set the systems employee ID
        EditText CurrentEmployeeID = (EditText) findViewById(R.id.ID_entered);

        setContentView(R.layout.activity_main);

    }
}
