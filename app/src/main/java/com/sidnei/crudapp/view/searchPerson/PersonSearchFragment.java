package com.sidnei.crudapp.view.searchPerson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sidnei.crudapp.R;
import com.sidnei.crudapp.model.Person;
import com.sidnei.crudapp.view.saveOrUpdatePerson.PersonSaveOrUpdateActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonSearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonSearchFragment extends Fragment implements IPersonSearchView {

    private OnFragmentInteractionListener mListener;
    private PersonSearchPresenter presenter;
    private Activity parentActivity;

    ///  fields
    private RecyclerView rvListPersons;
    private Button btnSearch;
    private Button btnClearView;
    private Button btnDelete;
    private Button btnEdit;
    private Spinner spnFilterColumn;
    private EditText etFilter;
    private ProgressDialog progressDialog;

    public PersonSearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this Fragment so that it will not be destroyed when an orientation
        // change happens and we can keep our AsyncTask running
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_search, container, false);

        /// getting the fields
        rvListPersons = view.findViewById(R.id.rvListPersons);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnClearView = view.findViewById(R.id.btnClearView);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnEdit = view.findViewById(R.id.btnEdit);
        spnFilterColumn = view.findViewById(R.id.spnFilterColumn);
        etFilter = view.findViewById(R.id.etFilter);

        if(savedInstanceState == null){
            presenter = new PersonSearchPresenter(this, view.getContext());
        }else{
            presenter.setView(this);
        }

        /// atualizando o recyclerView com o adapter que existia anteriormente
        configurePersonAdapterToRecyclerView();

        /// configure the onClick button events
        btnSearch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                presenter.search(spnFilterColumn.getSelectedItem().toString(), etFilter.getText().toString());
            }
        });

        btnClearView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                presenter.clearView();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                presenter.delete(); /// @todo get the recyclerview item position
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                /// if has some person selected we will edit in another VIEW
                if(parentActivity != null){

                    Person p = presenter.getSelectedPerson();
                    if(p != null){
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.setClass(parentActivity.getApplicationContext(), PersonSaveOrUpdateActivity.class);
                        intent.putExtra("person", p);
                        startActivity(intent);
                        parentActivity.finish();
                    }
                }
            }
        });

        return view;
    }

    /***/
    public void configurePersonAdapterToRecyclerView(){
        try{
            // Configurando o gerenciador de layout para ser uma lista.
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
            rvListPersons.setLayoutManager(layoutManager);
            rvListPersons.setAdapter(presenter.getPersonListAdapter());
            rvListPersons.addItemDecoration(new DividerItemDecoration(this.getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        }catch (Exception ex){
            Log.d("configurePersonAdapterToRecyclerView", "Error: " + ex.getMessage());
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        parentActivity = this.getActivity();

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if(presenter != null){
            presenter.onDestroy();
        }

        mListener = null;
    }

    /***/
    public void showProgress(String title, String message){
        hideProgress();
        progressDialog = ProgressDialog.show(getActivity(), title, message, true);
    }
    /***/
    public void hideProgress(){
        if(progressDialog != null) {
            progressDialog.hide();
        }
    }

    /***/
    public void showMessage(String msg){
        try{
            Toast.makeText(this.getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Log.d("showSearchFail", "ERROR: " + ex.getMessage());
        }
    }

    /***/
    public void showSearchFail(){
        try{
            Toast.makeText(this.getActivity().getApplicationContext(), "Erro ao tentar pesquisar registro", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Log.d("showSearchFail", "ERROR: " + ex.getMessage());
        }
    }
    /***/
    public void showDeleteOk(){
        try{
            Toast.makeText(this.getActivity().getApplicationContext(), "Pessoa removida com sucesso!", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Log.d("showDeleteFail", "ERROR: " + ex.getMessage());
        }
    }
    /***/
    public void showDeleteFail(){
        try{

            Toast.makeText(this.getActivity().getApplicationContext(), "Erro ao tentar deletar registro", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Log.d("showDeleteFail", "ERROR: " + ex.getMessage());
        }
    }

    /***/
    public void clearView(){
        etFilter.setText("");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String str);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String str) {
        if (mListener != null) {
            mListener.onFragmentInteraction(str);
        }
    }
}
