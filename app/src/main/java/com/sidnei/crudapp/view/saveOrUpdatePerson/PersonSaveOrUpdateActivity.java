package com.sidnei.crudapp.view.saveOrUpdatePerson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sidnei.crudapp.R;
import com.sidnei.crudapp.model.Person;

public class PersonSaveOrUpdateActivity extends AppCompatActivity implements PersonSaveOrUpdateFragment.OnFragmentInteractionListener {

    private String TAG_PERSONSAVEUPDATE_FRAGMENT = "personSaveOrUpdate";
    private PersonSaveOrUpdateFragment personSaveOrUpdateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_save_or_update);

        if(savedInstanceState == null){

            /// verifying if we will needed edit a person data
            Intent intent = getIntent();
            if(intent.hasExtra("person")){
                Person p = (Person) intent.getSerializableExtra("person");
                personSaveOrUpdateFragment = PersonSaveOrUpdateFragment.newInstance(p);
            }else{
                personSaveOrUpdateFragment = PersonSaveOrUpdateFragment.newInstance(new Person());
            }

            // The Activity is NOT being re-created so we can instantiate a new Fragment
            // and add it to the Activity
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, personSaveOrUpdateFragment, TAG_PERSONSAVEUPDATE_FRAGMENT).commit();
        }else{
            // The Activity IS being re-created so we don't need to instantiate the Fragment or add it,
            // but if we need a reference to it, we can use the tag we passed to .replace
            personSaveOrUpdateFragment = (PersonSaveOrUpdateFragment) getSupportFragmentManager().findFragmentByTag(TAG_PERSONSAVEUPDATE_FRAGMENT);
        }

        setTitle("Cadastrar ou atualizar dados");
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
        Log.d("PersonSaveOrUpdateActivity", str);
    }
}
