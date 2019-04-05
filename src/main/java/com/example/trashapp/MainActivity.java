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
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

import static com.example.trashapp.BackgroundWorker.currentCustomer;
import static com.example.trashapp.BackgroundWorker.currentTicketPosition;
import static com.example.trashapp.BackgroundWorker.myRef;

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
    public static Switch simpleSwitch;

    /**
     * this is the controller for the navigation view when a item is selected
     * it will then change the fragment being viewed along with certain specifics for it
     */
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
                //when the home screen is selected
                case R.id.navigation_home:
                    //Put function for uploading from datbase
                    //ticketList = BackgroundWorker.getTicketList();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    selectedFragment = HomeScreen.newInstance("Andy", "James");
                    transaction.replace(R.id.content, selectedFragment);
                    transaction.commit();
                    return true;
                //when the current ticket view is selected
                case R.id.navigation_current_ticket:
                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                    selectedFragment = CurrentTicketView.newInstance("Andy", "James");
                    transaction2.replace(R.id.content, selectedFragment);
                    transaction2.commit();
                    return true;
                //when the maps view is selected
                case R.id.navigation_Map:
                    //checks if thre are tickets to show to change it over
                    if(customer.getTicketList() != null) {
                        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                        selectedFragment = MapDisplay.newInstance("Andy", "James");
                        transaction3.replace(R.id.content, selectedFragment);
                        transaction3.commit();
                        return true;
                    }
                    //if no tickets toast that and dont change display
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select the ticket first", Toast.LENGTH_SHORT);
                        toast.show();
                        return false;
                    }
                //when the ticketlist view is selected
                case R.id.navigation_TicketList:

                    FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                    selectedFragment = TicketListFragment.newInstance(backgroundWorker.getTicketList());
                    transaction4.replace(R.id.content, selectedFragment);
                    transaction4.commit();

                    return true;
                //when the ticket editor is selected
                case R.id.navigation_TicketEditor:

                    FragmentTransaction transaction5 = getSupportFragmentManager().beginTransaction();
                    selectedFragment = TicketEditor.newInstance("Andy", "James");
                    transaction5.replace(R.id.content, selectedFragment);
                    transaction5.commit();

                    return true;
            }
            //if there is no selection
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
            simpleSwitch = (Switch) findViewById(R.id.switch1);

            /**SETS UP THE FRAGMENTS*/
            FirebaseApp.initializeApp(this);
            backgroundWorker = new BackgroundWorker(this);
           // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           // transaction.replace(R.id.content, TicketListFragment.newInstance(backgroundWorker.getTicketList()));
            //transaction.commit();
            /**SETS UP THE NAVIGATION ON BOTTOM*/
            BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            navigation.setSelectedItemId(R.id.navigation_TicketList);
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

    /**
     * this is an interface function, required in other areas
      * @param uri the Uri being passed in
     */
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
     * gets all of the values from the ticketeditor and saves them to the current ticket
     * @param view the view of the button to be pressed
     */
    public void saveTicketInfo(View view) {

        //LOGIC FOR SAVING ALL OF THE INFORMATION
        EditText note = findViewById(R.id.specialnoteSave);
        currentCustomer.setSpecialNotes(note.getText().toString());

        EditText first = findViewById(R.id.firstNameSave);
        currentCustomer.setFirstName(first.getText().toString());

        EditText last = findViewById(R.id.lastNameSave);
        currentCustomer.setLastName(last.getText().toString());

        EditText addr = findViewById(R.id.addressSave);
        currentCustomer.setAddress(addr.getText().toString());

        EditText email = findViewById(R.id.emailSave);
        currentCustomer.setEmail(email.getText().toString());

        EditText phone = findViewById(R.id.phoneNumberSave);
        currentCustomer.setPhoneNumber(phone.getText().toString());

        EditText garbDay = findViewById(R.id.garbageDaySave);
        currentCustomer.setGarbageDay(garbDay.getText().toString());

        EditText subDay = findViewById(R.id.subscribeDateSave);
        backgroundWorker.currentCustomer.setSubscriptionInfo(subDay.getText().toString());

        backgroundWorker.customerList.set(currentTicketPosition, currentCustomer);

        BackgroundWorker.saveTicket();



        //Add a toast displaying the info
        Toast toast=Toast.makeText(getApplicationContext(),"Ticket Updated!",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();

        Log.i("info", "Customer Saved!!");
    }

    /**
     * the switch function for the ticket list fragment
     * if selected is will display all tickets
     * if not selected it will display tickets not conpleted
     */
    public static void updateSwitch(){
        Boolean switchState = simpleSwitch.isChecked();
    }

    /**
     * uploads everytihng in the ticketlist to the database
     * @param view the button pressed to activate it
     */
    public void UploadToDatabase(View view) {
            //Logic for uploading to our database
        Log.i("info", "Uploading to Database");
        BackgroundWorker.saveTicket();
        Log.i("info", "Upload successful!");
    }

    /**
     * this sets whether or not a ticket has been completed
     * @param view the view of the checkbox being checked
     */
    public void onCheckboxClicked(View view) {
            currentTicket.setStatus(((CheckBox) view).isChecked());
            BackgroundWorker.saveTicket();
    }

    /**
     * the function for the ticketlist fragment that passes in the position
     * of the list that has been pressed by the user.
     * it will then change the current customer/ticket to the one selected
     * @param position an integer of the position that was selected
     */
    @Override
    public void onArticleSelected(int position) {
        //set the current ticket to be the one selected
        customer = backgroundWorker.getCustomerObject(position);
        currentTicket.setCustomer(customer);
    }

    /**
     * if the attacked fragment is the ticket list it will include a listener for the selector
     * @param fragment the fragment that we are changing to
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof TicketListFragment) {
            TicketListFragment Tfrag = (TicketListFragment) fragment;
            Tfrag.setOnHeadlineSelectedListener(this);
        }
    }

}
