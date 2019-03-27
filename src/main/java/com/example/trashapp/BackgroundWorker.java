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
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import static com.example.trashapp.MainActivity.customer;
import static android.support.constraint.Constraints.TAG;



public class BackgroundWorker {


    private Employee employeeObject;
    private Map mapObject;
    private Customer customerObject;
    private Ticket ticket;
    private List<Customer> yList = new ArrayList<>();
    private List<Map> mapList;
    private List<Ticket> ticketList = new ArrayList<>();
    private List<Customer> customerList;

    /**
     * Firebase properties
     */
    FirebaseApp f;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef1;

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
        myRef1 = database.getReference("Customer");

        myRef = database.getReference();
        customerList = createCustomerList();
            }

    public interface DataStatus{
        void DataIsLoaded(List<Ticket> tickets, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readTickets(final DataStatus dataStatus){
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ticketList.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Ticket ticket = keyNode.getValue(Ticket.class);
                    ticketList.add(ticket);
                }
                dataStatus.DataIsLoaded(ticketList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

    public Customer getCustomerObject(int position) {
        return customerList.get(position);

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
        Query ref = myRef.child("TrashAppleDatabase").orderByChild("Customer");
        System.out.print(ref.toString());
        //List<Customer> customerList = getCustomerList();
        if (customerList.size() >= ticketList.size()){
            ticketList.clear();
            for (int i = 0; i < customerList.size(); i++){
                ticketList.add(customerList.get(i).getTicket());
            }
        }


        //myRef.child("TrashAppleDatabase").child("Customer").child(customer.getFirstName()).getKey();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<String> h = new ArrayList<String>();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Customer customer = postSnapshot.getValue(Customer.class);
                    Ticket temp = customer.getTicket();
                        //ticketList.add(customer.getTicket());
                        yList.add(customer);
                        //h.add(customer.getFirstName());
                        //System.out.print(h.get(0));
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

        Query query = myRef.child("TrashAppleDatabase").child("Customer").orderByValue();
        String myUserId = "Customers";

        query.addChildEventListener(new ChildEventListener() {
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
     //* @param ticket
     */

    public void makeSampleCustomer(){
        newChildRef = myRef.push();

        Ticket testTicket = new Ticket();
        Customer customer = new Customer();
        customer.setFirstName("We are bafoons");
        testTicket.setCustomer(customer);
        myRef.child("TrashAppleDatabase").child("Customer").child(customer.getFirstName()).setValue(testTicket);
        myRef.child("TrashAppleDatabase").child("Customer").orderByValue();
        //var playersRef = firebase.database().ref("players/");

       // myRef.orderByChild("firstName").on("child_added", function(data) {
       //     console.log(data.val().name);
       // });
    }

    /**
     * save the ticket to the database by either creation of a new one
     * or updating a current one
     * @param ticket
     */
    public static void saveTicket(Ticket ticket){

    }

    public List createCustomerList(){
        final List<Customer> testList = new ArrayList<>();

        Customer cust1 = new Customer();
        cust1.setFirstName("George");
        cust1.setLastName("Foreman");
        cust1.setAddress("1234 goAway");
        cust1.setGarbageDay("Monday");
        cust1.setPhoneNumber("12345678");
        cust1.setSubscriptionInfo("Until July");
        cust1.setSpecialNotes("has dog");
        cust1.setEmail("george@foreman.com");
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
        testList.add(cust3);

        return testList;
    }
}