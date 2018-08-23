package com.sidnei.crudapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.sidnei.crudapp.model.Person;
import com.sidnei.crudapp.repositorymodel.PersonRepository;

import java.util.ArrayList;

public class PersonViewModel extends ViewModel {

    private PersonRepository personRep;
    private MutableLiveData<Person> person;
    private MutableLiveData<ArrayList<Person>> listPersons;

    public PersonViewModel(Context cx) {
        personRep = new PersonRepository(cx);
        person = new MutableLiveData<>();
        listPersons = new MutableLiveData<>();
    }
}
