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
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeScreen.OnFragmentInteractionListener ,
        TicketListFragment.OnHeadlineSelectedListener , TicketListFragment.OnFragmentInteractionListener, CurrentTicketView.OnFragmentInteractionListener,
        MapDisplay.OnFragmentInteractionListener, TicketEditor.OnFragmentInteractionListener {

    private TextView mTextMessage;
    ArrayAdapter<String> arrayAdapter;
    List listTest;

    public static BackgroundWorker backgroundWorker;
    List<Customer> customerList;
    public static Customer customer;
    public static Ticket currentTicket;
    public static String mainEmployeeID;
    CurrentTicketView CT;



    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        /**
         * ths is the selector for which fragment that is going to be viewed. it is
         * a case that is ran every time there is a new selection on the navigation
         * bar on the bottom of the screen. it calls and runs things according as to what
         * is currently being viewed
         * @param item this is the current item being selected
         * @return returns true if screen needs to be changed, false if no screen
         *         change is necessary
         */
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
                    selectedFragment = MapDisplay.newInstance("Andy", "James");
                    transaction3.replace(R.id.content, selectedFragment);
                    transaction3.commit();

                    return true;

                case R.id.navigation_TicketList:
                    //backgroundWorker.getTicketList();
                    FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                    selectedFragment = TicketListFragment.newInstance(backgroundWorker.getTicketList());
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


    /**
     * creates everything to be ran, the customer, ticket, fragments, and navigation viewer
     * @param savedInstanceState this is the previous state from last run
     */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            /**SETS UP CURRENT VARIABLES*/
            customer = new Customer();
            currentTicket = new Ticket();
           // customerList = createCustomerList();


            /**SETS UP THE FRAGMENTS*/
            FirebaseApp.initializeApp(this);
            backgroundWorker = new BackgroundWorker(this);
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

    /**
     * logs the current employee out and sets the activity to a login screen until they enter a new employee id
     * @param view looks at the logout button
     */
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

    /**
     * this runs a ticketlist obtainer function in BackgroundWorker
     * @param view the button pressed to activate it
     */
    public void getTicketList(View view) {
        listTest = backgroundWorker.getTicketList();
        }

    /**
     * updates the ticket list from the database
     * @param view the button pressed to activate it
     */
    public void updateTicketList(View view) {
            //LOGIC FOR UPDATING THE TICKETLIST

    }

    /**
     * gets all of the values from the ticketeditor and saves them to the current ticket
     * @param view the view of the button to be pressed
     */
    public void saveTicketInfo(View view) {
            //LOGIC FOR SAVING A SPECIAL NOTE ENTERED BY THE USER
        EditText note = (EditText) findViewById(R.id.specialnoteSave);
        customer.setSpecialNotes(note.getText().toString());

        EditText first = (EditText) findViewById(R.id.firstNameSave);
        customer.setFirstName(first.getText().toString());

        EditText last = (EditText) findViewById(R.id.lastNameSave);
        customer.setLastName(last.getText().toString());

        EditText addr = (EditText) findViewById(R.id.addressSave);
        customer.setAddress(addr.getText().toString());

        EditText email = (EditText) findViewById(R.id.emailSave);
        customer.setEmail(email.getText().toString());

        EditText phone = (EditText) findViewById(R.id.phoneNumberSave);
        customer.setPhoneNumber(phone.getText().toString());

        EditText garbDay = (EditText) findViewById(R.id.garbageDaySave);
        customer.setGarbageDay(garbDay.getText().toString());

        EditText subDay = (EditText) findViewById(R.id.subscribeDateSave);
        customer.setSubscriptionInfo(subDay.getText().toString());

        currentTicket.setCustomer(customer);
        BackgroundWorker.saveTicket(currentTicket);
        Log.i("info", "Customer Saved!!");
    }

    /**
     * uploads everytihng in the ticketlist to the database
     * @param view the button pressed to activate it
     */
    public void UploadToDatabase(View view) {
            //Logic for uploading to our database
        Log.i("info", "Uploading to Database");
        BackgroundWorker.saveTicket(currentTicket);


    }

    /**
     * this sets whether or not a ticket has been completed
     * @param view the view of the checkbox being checked
     */
    public void onCheckboxClicked(View view) {
            currentTicket.setStatus(((CheckBox) view).isChecked());
    }

    @Override
    public void onArticleSelected(int position) {
        //set the current ticket to be the one selected
        customer = backgroundWorker.getCustomerObject(position);
    }

    /** public void runTest(View view) {
         Intent obtainID = new Intent(this, EnterEmployeeID.class);
         startActivity(obtainID);

         }*/


    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof TicketListFragment) {
            TicketListFragment Tfrag = (TicketListFragment) fragment;
            Tfrag.setOnHeadlineSelectedListener(this);
        }
    }

}
