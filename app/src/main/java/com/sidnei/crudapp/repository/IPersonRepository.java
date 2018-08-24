package com.sidnei.crudapp.repository;

import com.sidnei.crudapp.model.Person;

import java.util.ArrayList;

public interface IPersonRepository {
    void open();
    void close();
    boolean save(Person p);
    boolean delete(Person p);
    ArrayList<Person> selectAll(String whereClause);
}
