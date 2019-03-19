package com.example.trashapp;

import java.sql.Time;
import java.util.Date;

public class Ticket{
    private Customer customer;
    private Employee employee;
    private Date date;
    private Time time;
    private MapView mapPoint;
    private boolean status;
    private String specialNotes;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public MapView getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(MapView mapPoint) {
        this.mapPoint = mapPoint;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }
}