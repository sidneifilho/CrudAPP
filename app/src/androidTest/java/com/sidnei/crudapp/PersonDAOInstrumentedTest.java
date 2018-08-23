package com.sidnei.crudapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.sidnei.crudapp.model.*;
import com.sidnei.crudapp.dao.PersonDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PersonDAOInstrumentedTest {

    private PersonDAO pDAO;

    @Before
    public void setUp(){
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        pDAO = new PersonDAO(appContext.getApplicationContext());
    }

    @After
    public void finish(){
    }

    @Test
    public void saveOrUpdatePersonTest(){
        Person p = new Person(0,"Tamara", "456465", "62960000", "CE", "Rua Jose Muniz", Person.SEX.MALE);
        pDAO.saveOrUpdate(p);
        assertNotEquals(0, p.getId());
    }

    public void deletePersonTest(){
        assertEquals(true, pDAO.delete(new Person("Sidnei Carlos", "123", "62960000", "CE", "Rua Jose Muniz", Person.SEX.MALE)));
    }

    public void deleteAllPersonsTest(){
        assertEquals(true, pDAO.deleteAll());
    }

    public void getAllPersonTestWithoutClauseTest() {
        ArrayList<Person> list = pDAO.selectAll("");
        assertEquals(false, list.isEmpty());
    }

    public void getAllPersonTestWithWhereClauseTest() {
        ArrayList<Person> list = pDAO.selectAll("CPF='123'");
        assertEquals(false, list.isEmpty());
    }

    public void getPersonByIdTest(){
        Person p = pDAO.selectByID(2);
        assertNotEquals(null, p);
    }

    public void getLastPerson(){
        assertEquals("123", pDAO.selectLastRecordInserted().getCpf());
    }
}
