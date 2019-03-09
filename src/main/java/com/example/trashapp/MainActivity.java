package com.example.trashapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ArrayAdapter<String> arrayAdapter;
    List<String> listTest;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);

                    return true;
                case R.id.navigation_current_ticket:
                    mTextMessage.setText(R.string.title_current_ticket);
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
        listTest = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.listview,R.id.ListView, listTest);
        ListView listView;
        listView = findViewById(R.id.ListView);
        listView.setAdapter(arrayAdapter);

        /**if there is no previous instance of the employees ID*/
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String text = sharedPref.getString("CurrentEmployeeID", "");
        if (text.equals("")) {
            Intent obtainID = new Intent(this, EnterEmployeeID.class);
            startActivity(obtainID);
        }

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void runTest(View view) {
        Intent obtainID = new Intent(this, EnterEmployeeID.class);
        startActivity(obtainID);

    }
}
