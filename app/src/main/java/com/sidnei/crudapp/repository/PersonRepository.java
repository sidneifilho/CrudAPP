package com.sidnei.crudapp.repository;

import android.content.Context;

import com.sidnei.crudapp.dao.PersonDAO;

import java.io.Serializable;

public class PersonRepository implements IRepository, Serializable {

    private PersonDAO personDAO;

    public PersonRepository(Context cx){
        personDAO = new PersonDAO(cx);
    }

    public void openDB(){
        personDAO.openDB();
    }

    public void closeDB(){
        personDAO.closeDB();
    }

}
