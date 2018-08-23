package com.sidnei.crudapp.dao;

import java.util.List;

/***
 * Interface used to guarantee that other class has the same methods to persist and request a data.
 */
public interface IDAO<T> {
    /**
     * function to save the object into a database
     * @param object Object that will ve saved or updated. If the object already have a ID, than it will be saved
     * @return Indicates if the operation was succeed or not
     * */
    boolean saveOrUpdate(T object);

    /**
     * function to delete all records from the table
     * @return Indicates if the operation was succeed or not
     * */
    boolean deleteAll();

    /**
     * function to delete some objects from a database
     * @param object Used to select the object that will be deleted
     * @return Indicates if the operation was succeed or not
     * */
    boolean delete(T object);

    /**
     * function that request all records from a database
     * @param whereClause Indicates if we will retrieve all records or a small part of it
     * @return List of records from a database.
     * */
    List<T> selectAll(String whereClause);

    /**
     * Function used to retrieve a object that has a ID from a table
     * @param id Id of the object that we want select from the table
     * @return Object that have the same ID passed as paramater
     * */
    T selectByID(int id);

    /**
     * Function that returns the last object that was inserted in the database
     * @return Last object inserted
     * */
    T selectLastRecordInserted();
}
