package com.example.trashapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class BackgroundWorker{
    private Employee employeeObject;
    private MapView mapObject;
    private Customer customerObject;
    private Ticket ticket;

    private List<MapView> mapList;
    private List<Ticket> ticketList;
    private List<Customer> customerList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Customer");

    public Employee getEmployeeObject() {
        return employeeObject;
    }

    public void setEmployeeObject(Employee employeeObject) {
        this.employeeObject = employeeObject;
    }

    public MapView getMapObject() {
        return mapObject;
    }

    public void setMapObject(MapView mapObject) {
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

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
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

    //Hello guys!!
    /**suck my toes*/
}