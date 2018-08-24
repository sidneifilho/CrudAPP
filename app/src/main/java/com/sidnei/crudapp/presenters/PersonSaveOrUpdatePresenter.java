package com.sidnei.crudapp.presenters;

import android.content.Context;

import com.sidnei.crudapp.model.Person;
import com.sidnei.crudapp.repository.PersonRepository;
import com.sidnei.crudapp.view.activitys.IPersonSaveOrUpdateView;
import com.sidnei.crudapp.view.activitys.PersonSaveOrUpdateActivity;
import com.sidnei.crudapp.view.fragments.PersonSaveOrUpdateFragment;

import java.io.Serializable;

public class PersonSaveOrUpdatePresenter {

    private PersonRepository repository;
    private IPersonSaveOrUpdateView view;

    public PersonSaveOrUpdatePresenter(IPersonSaveOrUpdateView view, Context cx){
        this.view = view;
        repository = new PersonRepository(cx);
    }

    public void onDestroy(){
        view = null;
    }

    public void cancel(){
        if(view != null){
            view.clearFields();
        }
    }

    public void save(Person p){
        ///@todo imnplement
    }

    /// @todo implement the other functions to operate UI
}
