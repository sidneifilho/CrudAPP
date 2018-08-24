package com.sidnei.crudapp.repository;

import android.content.Context;

import com.sidnei.crudapp.dao.PersonDAO;
import com.sidnei.crudapp.model.Person;

import java.io.Serializable;
import java.util.ArrayList;

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
    public boolean delete(Person p){ return personDAO.delete(p); }

    public ArrayList<Person> selectAll(String whereClause) {
        return personDAO.selectAll(whereClause);
    }
}
