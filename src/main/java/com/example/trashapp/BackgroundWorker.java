package com.example.trashapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


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
import java.util.List;

import static android.support.constraint.Constraints.TAG;










public class BackgroundWorker {


    private Employee employeeObject;
    private Map mapObject;
    private Customer customerObject;
    private Ticket ticket;
    private List<Ticket> yList;
    private List<Map> mapList;
    private List<Ticket> ticketList;
    private List<Customer> customerList;
    FirebaseApp f;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference newChildRef;



    BackgroundWorker(Context tree){
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("com.example.trashapp")
                .setApiKey("AIzaSyD6ukG9i9bbjCPn80e0_daOaPFWrnipeF0")
                .setDatabaseUrl("https://trashapple-89328.firebaseio.com")
                .setProjectId("trashapple-89328")
                .build();
        FirebaseApp.initializeApp(tree, options);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }






    public Employee getEmployeeObject() {
        return employeeObject;
    }

    public void setEmployeeObject(Employee employeeObject) {
        this.employeeObject = employeeObject;
    }

    public Map getMapObject() {
        return mapObject;
    }

    public void setMapObject(Map mapObject) {
        this.mapObject = mapObject;
    }

    public Customer getCustomerObject() {
        return customerObject;

    }

    public void setCustomerObject(Customer customerObject) {
        this.customerObject = customerObject;
        myRef.child("Customers").setValue(customerObject);
    }

    public List getMapList() {
        return mapList;
    }

    public void setMapList(List mapList) {
        this.mapList = mapList;
    }

    public List getTicketList() {
        yList = new ArrayList<>();
        //L = myRef.child("TrashAppleDatabase").orderByChild("Customer");

        //Object datab = myRef.getDatabase();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Customer customer = postSnapshot.getValue(Customer.class);
                        ticketList.add(customer.getTicket());


        //String temp = Tickets.getEmployee().getEmployeeID();
        //if(temp.contains(CurrentEmployeeID)){
          //ticketList.add(Tickets);
       // }
                    // here you canTick access to name property like Tickets.name

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        return ticketList;
    }

    public void setTicketList(List ticketList) {

        this.ticketList = ticketList;
    }

    public List getCustomerList() {
        myRef.child("Customers").getDatabase();

                String myUserId = "Customers";
        Query myTopPostsQuery = myRef.child("Customers").child(myUserId)
                .orderByChild("");
        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
            // TODO: implement the ChildEventListener methods as documented above
            // ...
        });
        return customerList;
    }

    public void setCustomerList(List customerList) {
        this.customerList = customerList;

        myRef.child("Customers").setValue(customerObject);
    }

    public void UpdateJsonStorage(){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    /**
     * save the ticket to the database by either creation of a new one
     * or updating a current one
     * @param ticket
     */
    public static void saveTicket(Ticket ticket){

    }
    public void makeSampleCustomer(){
        newChildRef = myRef.push();
        String key = newChildRef.getKey();
        Ticket testTicket = new Ticket();
        Customer customer = new Customer();
        customer.setFirstName("We are bafoons");
        testTicket.setCustomer(customer);
        myRef.child("TrashAppleDatabase").child("Customer").child(customer.getFirstName()).setValue(testTicket);
    }

}