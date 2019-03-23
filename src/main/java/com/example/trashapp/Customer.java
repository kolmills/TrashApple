package com.example.trashapp;

import java.util.List;

public class Customer{

    private String firstName;
    private String lastName;
    private String address;
    private String garbageDay;
    private String specialNotes;
    private String subscriptionInfo;
    private String email;
    private String phoneNumber;
    private List<Ticket> ticketList;


    public Customer() {
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.garbageDay = "";
        this.specialNotes = "";
        this.subscriptionInfo = "";
        this.email = "";
        this.phoneNumber = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGarbageDay() {
        return garbageDay;
    }

    public void setGarbageDay(String garbageDay) {
        this.garbageDay = garbageDay;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public String getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public void setSubscriptionInfo(String subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Ticket getTicket() {
        Ticket  currentTicket = new Ticket();
        return currentTicket;}
}