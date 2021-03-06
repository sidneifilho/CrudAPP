package com.sidnei.crudapp.view.searchPerson;

import android.content.Context;

import com.sidnei.crudapp.R;
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

        if(view != null){
            view.showProgress(view.getContext().getResources().getString(R.string.searching_persons), view.getContext().getResources().getString(R.string.please_wait_instant));
        }

        ArrayList<Person> searchList;

        /// verifying which column was selected and which whereClause we have to put to search the right elements
        if(filterColumn.equalsIgnoreCase("Nome") || filterColumn.equalsIgnoreCase("Name")){

            searchList = personRep.selectAll("Name like '%" + filterValue + "%'");

        }else if(filterColumn.equalsIgnoreCase("CPF")){

            searchList = personRep.selectAll("CPF='" + filterValue + "'");

        }else if(filterColumn.equalsIgnoreCase("CEP")){

            searchList = personRep.selectAll("CEP='" + filterValue + "'");

        }else {
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

        if(view != null){
            view.hideProgress();
        }
    }

    /***/
    public void delete(){

        /// verifying if there is a selected person valid in the adapter list
        Person p = personRecyclerAdapter.getSelectedPerson();
        if(p != null){

            /// showing message that the operation was started
            if(view != null){
                view.showProgress(view.getContext().getResources().getString(R.string.removing_record), view.getContext().getResources().getString(R.string.please_wait_instant));
            }

            ///delete the person from repository
            boolean res = personRep.delete(p);

            /// if was succeed than we will remove the person from the adapter
            if(res){
                personRecyclerAdapter.removePerson(p);

                if(view != null){
                    view.showDeleteOk();
                }
            }else{
                if(view != null){
                    view.showDeleteFail();
                }
            }


        }else{
            if(view != null){
                view.showMessage(view.getContext().getResources().getString(R.string.select_any_record));
            }
        }

        if(view != null){
            view.hideProgress();
        }
    }

    /***/
    public Person getSelectedPerson(){
        return personRecyclerAdapter.getSelectedPerson();
    }

    public void setView(IPersonSearchView view){
        this.view = view;
    }
}
