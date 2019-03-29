package com.example.trashapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.FirebaseApp;
import java.util.ArrayList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import static com.example.trashapp.MainActivity.currentTicket;
import static com.example.trashapp.MainActivity.customer;
import static android.support.constraint.Constraints.TAG;



public class BackgroundWorker {


    public static ArrayList<Customer> customerList;
    private Employee employeeObject;
    private Map mapObject;
    protected static Customer currentCustomer;
    private List<Map> mapList;
    private List<Ticket> ticketList = new ArrayList<>();
    public static int currentTicketPosition;
    static ArrayList<Customer> testList;
    /**
     * Firebase properties
     */
     FirebaseApp f;
    static FirebaseDatabase database;
    public static DatabaseReference myRef;
    DatabaseReference myRef1;

    DatabaseReference newChildRef;



    BackgroundWorker(Context tree){
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("com.example.trashapp")
                .setApiKey("AIzaSyD6ukG9i9bbjCPn80e0_daOaPFWrnipeF0")
                .setDatabaseUrl("https://trashapple-89328.firebaseio.com/")
                .setProjectId("trashapple-89328")
                .build();
        FirebaseApp.initializeApp(tree, options);
        database = FirebaseDatabase.getInstance();
        //myRef1 = database.getReference("Customer");



        myRef = database.getReference("Database");
        myRef1 = database.getReference("Database").child("Customers");
        customerList = getCustomerList();
// Write a message to the database
        //testList = createCustomerList();
            }

    public void setCurrentCustomer(int position) {
        currentTicketPosition = position;
        currentCustomer = customerList.get(position);
    }

    public interface DataStatus{
        void DataIsLoaded(List<Ticket> tickets, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public Employee getEmployeeObject(String employeeID) {
        return employeeObject;
    }
    public void setEmployeeObject(Employee employeeObject) {
        this.employeeObject = employeeObject;
    }
    public Customer getCustomerObject(int position) {
        return customerList.get(position);

    }
    public List getMapList() {
        return mapList;
    }
    public void setMapList(List mapList) {
        this.mapList = mapList;
    }

    public List getTicketList() {
        customerList = getCustomerList();

        return ticketList;
    }

    public void setTicketList(List ticketList) {
        this.ticketList = ticketList;
    }

    public ArrayList getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList customerList) {
        //myRef.child("Customers").setValue(customerList);
        this.customerList = customerList;
    }

    /**
     * save the ticket to the database by either creation of a new one
     * or updating a current one
     //* @param ticket
     */

    public void makeSampleCustomer(Customer customenr){
        newChildRef = myRef.push();
        Customer customery = new Customer();
        customery.setFirstName("getertrt");
        myRef.child("TrashAppleDatabase").child("Customer").child(customer.getFirstName()).setValue(customery);
      myRef.child("TrashAppleDatabase").child("Customer").orderByValue();
        //var playersRef = firebase.database().ref("players/");

       // myRef.orderByChild("firstName").on("child_added", function(data) {
       //     console.log(data.val().name);
       // });
    }
    /**
     * save the ticket to the database by either creation of a new one
     * or updating a current one
     */
    public static void saveTicket(){
        //customerList.set(currentTicketPosition, currentCustomer);


        myRef.child("Customers").setValue(customerList);
    }
    public Ticket createSampleTicket(){
        Ticket t = new Ticket();
        t.setSpecialNotes("HAS TO POOP");
        return t;
    }
    public ArrayList createCustomerList(){
        final ArrayList<Customer> testList = new ArrayList<>();
       //myRef.child("Customers").setValue(testList);
            Ticket t = new Ticket();
            ArrayList<Ticket> test = new ArrayList<>();
            test.add(t);
            Customer cust1 = new Customer();
            cust1.setFirstName("George");
            cust1.setLastName("Foreman");
            cust1.setAddress("1234 goAway");
            cust1.setGarbageDay("Monday");
            cust1.setPhoneNumber("12345678");
            cust1.setSubscriptionInfo("Until July");
            cust1.setSpecialNotes("has dog");
            cust1.setEmail("george@foreman.com");
            cust1.setTicketList(test);
            testList.add(cust1);
            Customer cust2 = new Customer();
            cust2.setFirstName("Sally");
            cust2.setLastName("Seashells");
            cust2.setAddress("1 Seashore");
            cust2.setGarbageDay("Monday");
            cust2.setPhoneNumber("987654321");
            cust2.setSubscriptionInfo("Until June");
            cust2.setSpecialNotes("dont accepts seashells");
            cust2.setEmail("c-shell@ocean.com");
        cust2.setTicketList(test);
            testList.add(cust2);
            Customer cust3 = new Customer();
            cust3.setFirstName("Ms.");
            cust3.setLastName("Pacman");
            cust3.setAddress("the Arcade");
            cust3.setGarbageDay("Monday");
            cust3.setPhoneNumber("8675309");
            cust3.setSubscriptionInfo("Until May");
            cust3.setSpecialNotes("MR PACMAN GETS JEALOUS!!!!");
            cust3.setEmail("i8ghosts@games.com");
        cust3.setTicketList(test);
            testList.add(cust3);
        myRef.child("Customers").setValue(testList);
            return testList;

    }
}