package com.sidnei.crudapp.view.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sidnei.crudapp.R;

public class PersonSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_search);

        setTitle("Pesquisar");
    }
}
