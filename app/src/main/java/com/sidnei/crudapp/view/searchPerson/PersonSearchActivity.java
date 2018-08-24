package com.sidnei.crudapp.view.searchPerson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sidnei.crudapp.R;
import com.sidnei.crudapp.view.searchPerson.PersonSearchFragment;

public class PersonSearchActivity extends AppCompatActivity implements PersonSearchFragment.OnFragmentInteractionListener {

    private String TAG_PERSONSEARCH_FRAGMENT = "personSearch";
    private PersonSearchFragment personSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_search);

        if(savedInstanceState == null){
            // The Activity is NOT being re-created so we can instantiate a new Fragment
            // and add it to the Activity
            personSearchFragment = new PersonSearchFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, personSearchFragment, TAG_PERSONSEARCH_FRAGMENT).commit();
        }else{
            // The Activity IS being re-created so we don't need to instantiate the Fragment or add it,
            // but if we need a reference to it, we can use the tag we passed to .replace
            personSearchFragment = (PersonSearchFragment) getSupportFragmentManager().findFragmentByTag(TAG_PERSONSEARCH_FRAGMENT);
        }

        setTitle("Pesquisar Pessoa");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onFragmentInteraction(String str){
        Log.d("PersonSearchACtivity", str);
    }
}
