package com.sidnei.crudapp.repository;

import android.content.Context;

import com.sidnei.crudapp.dao.PersonDAO;
import com.sidnei.crudapp.model.Person;

import java.io.Serializable;

public class PersonRepository implements IPersonRepository, Serializable {

    private PersonDAO personDAO;

    public PersonRepository(Context cx){
        personDAO = new PersonDAO(cx);
    }

    public void open(){
        personDAO.openDB();
    }

    public void close(){
        personDAO.closeDB();
    }

    public boolean save(Person p){
        return personDAO.saveOrUpdate(p);
    }

}
