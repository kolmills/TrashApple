package com.example.trashapp;

public class Customer{

    private String firstName;
    private String lastName;
    private String address;
    private String garbageDay;
    private String specialNotes;
    private String subscriptionInfo;

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
}