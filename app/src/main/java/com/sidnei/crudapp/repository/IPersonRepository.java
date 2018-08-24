package com.sidnei.crudapp.repository;

import com.sidnei.crudapp.model.Person;

public interface IPersonRepository {

    void open();
    void close();
    boolean save(Person p);
}
