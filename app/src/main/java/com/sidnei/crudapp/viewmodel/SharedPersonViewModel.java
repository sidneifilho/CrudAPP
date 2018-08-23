package com.sidnei.crudapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ClipData;

import com.sidnei.crudapp.model.Person;

/**
 * This class will be used when we need share a Person object between two Fragments.
 * Ex: When we have a ListFragment where we select a person from that list and we want to show the details of the record in another Fragment
 * */
public class SharedPersonViewModel extends ViewModel {

    private final MutableLiveData<Person> selected = new MutableLiveData<>();

    /**
     * Function used to set the person that was selected to view
     * @param p Person object that was select by the User in the view, to visualize the details of the object
     * */
    public void select(Person p) {
        selected.setValue(p);
    }

    /**
     * Function used to observe if the object person was changed
     * @return LiveData object to observe in the View
     * */
    public MutableLiveData<Person> getSelected() {
        return selected;
    }
}
