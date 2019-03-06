package com.example.trashapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_Map:
                    mTextMessage.setText(R.string.title_Map);
                    return true;
                case R.id.navigation_TicketList:
                    mTextMessage.setText(R.string.title_TicketList);
                    return true;
            }
            return false;
        }
    };
 //HELOO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**if there is no previous instance of the employees ID*/
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String text = sharedPref.getString("CurrentEmployeeID", "");
        if (text.equals("")) {
            Intent obtainID = new Intent(this, EnterEmployeeID.class);
            startActivity(obtainID);
        }


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
