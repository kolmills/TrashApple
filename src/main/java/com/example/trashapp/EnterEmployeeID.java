package com.example.trashapp;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class EnterEmployeeID extends AppCompatActivity {
    /**
     * on creation of the activity for an employee to login
     * @param savedInstanceState the previous instance of this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_employee_id);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * the saves the employee id that was entered, it saves it to the shared prefrences
     * of the phone so that on restart is will remember who logged into it last
     * @param theButton this is the view of the button pressed by the user to activate it
     */
    public void SetEmployeeID(View theButton){

        //set the systems employee ID
        EditText ID = (EditText) findViewById(R.id.ID_entered);
        String temp = ID.getText().toString();
        //save to shared prefrences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("CurrentEmployeeID", temp);
        editor.commit();

        Log.i("info", "User has logged in");
        //close the activity
        this.finish();

    }
}
