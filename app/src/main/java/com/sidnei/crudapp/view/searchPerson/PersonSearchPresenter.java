package com.sidnei.crudapp.view.searchPerson;

import android.content.Context;

import com.sidnei.crudapp.model.Person;
import com.sidnei.crudapp.repository.IPersonRepository;
import com.sidnei.crudapp.repository.PersonRepository;
import com.sidnei.crudapp.view.personRecyclerView.PersonRecyclerAdapter;

import java.util.ArrayList;

public class PersonSearchPresenter {

    private IPersonSearchView view;
    private PersonRecyclerAdapter personRecyclerAdapter;
    private IPersonRepository personRep;

    public PersonSearchPresenter(IPersonSearchView view, Context ctx){
        this.view = view;
        personRecyclerAdapter = new PersonRecyclerAdapter();
        personRep = new PersonRepository(ctx);
    }

    /***/
    public PersonRecyclerAdapter getPersonListAdapter(){
        return personRecyclerAdapter;
    }

    /***/
    public void onDestroy(){
        personRep.close();
        view = null;
    }

    /***/
    public void clearView(){
        personRecyclerAdapter.clearList();
        if(view != null){
            view.clearView();
        }
    }

    /***/
    public void search(String filterColumn, String filterValue){

        ArrayList<Person> searchList = new ArrayList<>();

        /// verifying which column was selected and which whereClause we have to put to search the right elements
        if(filterColumn.equalsIgnoreCase("Nome")){

            if(!filterValue.equalsIgnoreCase("")){
                searchList = personRep.selectAll("Name like '%" + filterValue + "%'");
            }else{
                searchList = new ArrayList<>();
            }

        }else if(filterColumn.equalsIgnoreCase("CPF")){

            if(!filterValue.equalsIgnoreCase("")){
                searchList = personRep.selectAll("CPF='" + filterValue + "'");
            }else{
                searchList = new ArrayList<>();
            }

        }else if(filterColumn.equalsIgnoreCase("CEP")){

            if(!filterValue.equalsIgnoreCase("")){
                searchList = personRep.selectAll("CEP='" + filterValue + "'");
            }else{
                searchList = new ArrayList<>();
            }

        }else if(filterColumn.equalsIgnoreCase("TODOS")){
            searchList = personRep.selectAll("");
        }

        /// verifying if the search has receive a error or the search has some results
        if(searchList == null){
            if(view != null){
                view.showSearchFail();
            }
        }else{
            /// updating the adapter with the list received from the repository
            personRecyclerAdapter.setListPersons(searchList);
        }
    }

    /***/
    public void delete(){

        /// verifying i there is a selected person valid in the adapter list
        Person p = personRecyclerAdapter.getSelectedPerson();
        if(p == null){
            if(view != null){
                view.showMessage("Selecione algum registro para remover!");
            }
            return;
        }

        ///delete the person from repository
        boolean res = personRep.delete(p);

        /// if was succeed than we will remove the person from the adapter
        if(res){
            personRecyclerAdapter.removePerson(p);
        }

        /// showing message to the UI indicating if the process was succeed or not
        if(view != null){
            if(res){
                view.showDeleteOk();
            }else{
                view.showDeleteFail();
            }
        }
    }

    /***/
    public Person getSelectedPerson(){
        return personRecyclerAdapter.getSelectedPerson();
    }
}
