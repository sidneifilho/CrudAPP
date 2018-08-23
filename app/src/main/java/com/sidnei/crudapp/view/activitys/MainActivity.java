package com.sidnei.crudapp.view.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sidnei.crudapp.R;
import com.sidnei.crudapp.viewmodel.PersonViewModel;

public class MainActivity extends AppCompatActivity {

    private PersonViewModel personViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personViewModel = new PersonViewModel(getApplicationContext());
    }

    @Override
    protected void onDestroy(){

        super.onDestroy();
    }
}
