package com.example.trashapp;

import java.sql.Date;
import java.sql.Time;

public class Ticket{
    private Customer customer;
    private Employee employee;
    private Date date;
    private Time time;
    private Map mapPoint;
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

    public Map getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(Map mapPoint) {
        this.mapPoint = mapPoint;
    }

    public bool getStatus() {
        return status;
    }

    public void setStatus(bool status) {
        this.status = status;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }
}