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
        repository.close();
        view = null;
    }

    public void cancel(){
        if(view != null){
            view.clearFields();
        }
    }

    public void save(Person p){
        /// ***** verifying if the person data is valid *************

        /// person's name cannot be a empty value
        if(p.getName().equals("")){
            if(view != null){
                view.setNameError();
            }
            return;
        }

        /// verifying if the cpf is a valid one
        if(!p.isValidCpf()){
            if(view != null){
                view.setCpfError();
            }
            return;
        }
        ////*************************************************

        /// saving the person into a repository
        boolean res = repository.save(p);
        if(res){
            if(view != null){
                view.showSaveSuccessful();
            }
        }else{
            if(view != null){
                view.showSaveFail();
            }
        }
    }
}
