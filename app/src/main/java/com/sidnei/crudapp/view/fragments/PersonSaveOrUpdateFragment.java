package com.sidnei.crudapp.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.sidnei.crudapp.R;
import com.sidnei.crudapp.model.Person;
import com.sidnei.crudapp.presenters.PersonSaveOrUpdatePresenter;
import com.sidnei.crudapp.repository.PersonRepository;
import com.sidnei.crudapp.view.activitys.IPersonSaveOrUpdateView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonSaveOrUpdateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonSaveOrUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonSaveOrUpdateFragment extends Fragment implements IPersonSaveOrUpdateView {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";
    private static final String ARG_NAME = "name";
    private static final String ARG_CPF = "cpf";
    private static final String ARG_SEX = "sex";
    private static final String ARG_CEP = "cep";
    private static final String ARG_UF = "uf";
    private static final String ARG_ADDRESS = "address";
    private Person argPerson;

    /// fields
    private EditText etName;
    private EditText etCPF;
    private EditText etCEP;
    private RadioButton rbSexFemale;
    private RadioButton rbSexMale;
    private RadioButton rbSexOther;
    private Spinner spnUF;
    private EditText etAddress;
    private Button btnSave;
    private Button btnCancel;

    private OnFragmentInteractionListener mListener;

    private PersonSaveOrUpdatePresenter personPresenter;
    private ArrayAdapter<String> ufAdapter;

    public PersonSaveOrUpdateFragment() {
        argPerson = new Person();
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     * This is a way to communicate between the Parent Activity before start
     * @param id
     * @param name
     * @param cpf
     * @param sex
     * @param cep
     * @param uf
     * @param address
     * @return A new instance of fragment PersonSaveOrUpdateFragment.
     */
    public static PersonSaveOrUpdateFragment newInstance(int id, String name, String cpf, Person.SEX sex, String cep, String uf, String address) {
        PersonSaveOrUpdateFragment fragment = new PersonSaveOrUpdateFragment();

        try{
            Bundle args = new Bundle();
            args.putInt(ARG_ID, id);
            args.putString(ARG_NAME, name);
            args.putString(ARG_CPF, cpf);
            switch (sex){
                case FEMALE:
                    args.putString(ARG_SEX, "f");
                    break;
                case MALE:
                    args.putString(ARG_SEX, "m");
                    break;
                case OTHER:
                    args.putString(ARG_SEX, "o");
                    break;
            }
            args.putString(ARG_CEP, cep);
            args.putString(ARG_UF, uf);
            args.putString(ARG_ADDRESS, address);
            fragment.setArguments(args);
        }catch (Exception ex){
            Log.d("PersonSaveOrUpdateFragment", "Erro when create a new instance, ERROR: " + ex.getMessage());
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this Fragment so that it will not be destroyed when an orientation
        // change happens and we can keep our AsyncTask running
        setRetainInstance(true);

        /// if exist arguments than we will update the argPerson because we will edit the Person Object
        if (getArguments() != null) {
            argPerson.setId(getArguments().getInt(ARG_ID));
            argPerson.setName(getArguments().getString(ARG_NAME));
            argPerson.setCpf(getArguments().getString(ARG_CPF));
            argPerson.setCep(getArguments().getString(ARG_CEP));
            argPerson.setUf(getArguments().getString(ARG_UF));
            argPerson.setAddress(getArguments().getString(ARG_ADDRESS));
            argPerson.setSex(getArguments().getString(ARG_SEX));
        }else{
            argPerson.clearValues();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_save_or_update, container, false);

        /// fields get
        btnCancel = view.findViewById(R.id.btnCancel);
        btnSave  = view.findViewById(R.id.btnSave);
        etName = view.findViewById(R.id.etName);
        etAddress = view.findViewById(R.id.etAddress);
        etCEP = view.findViewById(R.id.etCEP);
        etCPF = view.findViewById(R.id.etCPF);
        rbSexFemale = view.findViewById(R.id.rbSexFemale);
        rbSexMale = view.findViewById(R.id.rbSexMale);
        rbSexOther = view.findViewById(R.id.rbSexOther);
        spnUF = view.findViewById(R.id.spnUF);

        // attaching data adapter to spinner
        String states[] = {"CE","RS","SC"}; /// @todo insert the other states
        ufAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, states);
        ufAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnUF.setAdapter(ufAdapter);

        /// init the presenter with the activity associate to the fragment
        personPresenter = new PersonSaveOrUpdatePresenter(this, view.getContext());

        /// verifying if has a argPerson passed by parameter through other fragment or activity, if exist we will update the fields with the new values
        if(argPerson != null){
            etName.setText(argPerson.getName());
            etCPF.setText(argPerson.getCpf());
            etCEP.setText(argPerson.getCep());
            etAddress.setText(argPerson.getAddress());
            spnUF.setSelection(ufAdapter.getPosition(argPerson.getUf()));

            switch (argPerson.getSex()){
                case FEMALE:
                    rbSexFemale.setChecked(true);
                    break;
                case MALE:
                    rbSexMale.setChecked(true);
                    break;
                case OTHER:
                    rbSexOther.setChecked(true);
                    break;
            }
        }

        ///When we clicked in the cancel button we will finish the current Activy, and we will be back to the main activity
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(personPresenter != null) {
                    personPresenter.save(argPerson);
                }
            }
        });

        ///When we clicked in the cancel button we will finish the current Activy, and we will be back to the main activity
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(personPresenter != null){
                    personPresenter.cancel();
                }
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()  + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {

        /// destroying the presenter
        if(personPresenter != null) {
            personPresenter.onDestroy();
        }
        personPresenter = null;
        mListener = null;

        super.onDetach();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String str) {
        if (mListener != null) {
            mListener.onFragmentInteraction(str);
        }
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

    @Override
    public void clearFields(){
        argPerson.clearValues();

        etName.setText("");
        etCPF.setText("");
        etCEP.setText("");
        etAddress.setText("");
        spnUF.setSelection(0);
        rbSexOther.setChecked(true);

        etName.requestFocus();
    }
}
