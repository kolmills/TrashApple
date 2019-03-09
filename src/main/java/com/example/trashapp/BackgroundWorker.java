package com.example.trashapp;

import java.util.List;

public class BackgroundWorker{
    private Employee employeeObject;
    private Map mapObject;
    private Customer customerObject;
    private Ticket ticket;

    private List<Map> mapList;
    private List<Ticket> ticketList;
    private List<Customer> customerList;

    ///We haveeeeeeeeee beeeeeeeeeeee

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
    }

    public List getMapList() {
        return mapList;
    }

    public void setMapList(List mapList) {
        this.mapList = mapList;
    }

    public List getTicketList() {
        return ticketList;
    }

    public void setTicketList(List ticketList) {
        this.ticketList = ticketList;
    }

    public List getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List customerList) {
        this.customerList = customerList;
    }

    public void UpdateJsonStorage(){

    }
}