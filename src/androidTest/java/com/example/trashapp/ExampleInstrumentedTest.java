package com.example.trashapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.trashapp", appContext.getPackageName());
    }
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
