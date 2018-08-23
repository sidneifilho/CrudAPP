package com.sidnei.crudapp.repositorymodel;

import android.content.Context;

import com.sidnei.crudapp.dao.PersonDAO;

public class PersonRepository {

    private PersonDAO personDAO;

    public PersonRepository(Context cx){
        personDAO = new PersonDAO(cx);
    }
}
