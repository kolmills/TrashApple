package com.example.trashapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.trashapp.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeScreen.OnFragmentInteractionListener ,
        TicketListFragment.OnFragmentInteractionListener , CurrentTicketView.OnFragmentInteractionListener,
        MapView.OnFragmentInteractionListener, TicketEditor.OnFragmentInteractionListener {

    private TextView mTextMessage;
    ArrayAdapter<String> arrayAdapter;
    List<String> ticketList;
    public static Customer customer;
    public static String mainEmployeeID;
    CurrentTicketView CT;


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;


            switch (item.getItemId()) {
                case R.id.navigation_home:

                    //Put function for uploading from datbase
                    //ticketList = BackgroundWorker.getTicketList();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    selectedFragment = HomeScreen.newInstance("Andy", "James");
                    transaction.replace(R.id.content, selectedFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_current_ticket:

                    /**CREATE THE FRAGMENT*/
                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                    selectedFragment = CurrentTicketView.newInstance("Andy", "James");

                    transaction2.replace(R.id.content, selectedFragment);
                    transaction2.commit();

                    return true;
                case R.id.navigation_Map:

                    FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                    selectedFragment = MapView.newInstance("Andy", "James");
                    transaction3.replace(R.id.content, selectedFragment);
                    transaction3.commit();

                    return true;

                case R.id.navigation_TicketList:

                    FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                    selectedFragment = TicketListFragment.newInstance("Andy", "James");
                    transaction4.replace(R.id.content, selectedFragment);
                    transaction4.commit();

                    return true;

                case R.id.navigation_TicketEditor:

                    FragmentTransaction transaction5 = getSupportFragmentManager().beginTransaction();
                    selectedFragment = TicketEditor.newInstance("Andy", "James");
                    transaction5.replace(R.id.content, selectedFragment);
                    transaction5.commit();

                    return true;
            }
            return false;
        }

    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            /**SETS UP CURRENT VARIABLES*/
            customer = new Customer();
            //ticketList =


            /**SETS UP THE FRAGMENTS*/
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, HomeScreen.newInstance("What","Ever"));
            transaction.commit();
            /**SETS UP THE NAVIGATION ON BOTTOM*/
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            /**if there is no previous instance of the employees ID*/
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String text = sharedPref.getString("CurrentEmployeeID", "");
            if (text.equals("")) {
                Log.i("info", "creating and going to login page");
                Intent obtainID = new Intent(this, EnterEmployeeID.class);
                startActivity(obtainID);
             }
             mainEmployeeID = sharedPref.getString("CurrentEmployeeID", "");
        }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void logoutEmployee(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("CurrentEmployeeID", "");
        editor.commit();

        Log.i("info", "User has logged out");
        Log.i("info", "creating and going to login page");

        Intent obtainID = new Intent(this, EnterEmployeeID.class);
        startActivity(obtainID);

    }

    public void updateTicketList(View view) {
            //LOGIC FOR UPDATING THE TICKETLIST

    }

    public void saveSpecialNote(View view) {
            //LOGIC FOR SAVING A SPECIAL NOTE ENTERED BY THE USER
        EditText ID = (EditText) findViewById(R.id.specialNoteToSave);
        String note = ID.getText().toString();

        //now set the special note for the customer
        customer.setSpecialNotes(note);
        Log.i("info", "Special note Saved");
    }

    public void UploadToDatabase(View view) {
            //Logic for uploading to our database
        Log.i("info", "Uploading to Database");


    }

    /** public void runTest(View view) {
         Intent obtainID = new Intent(this, EnterEmployeeID.class);
         startActivity(obtainID);

         }*/

    }
