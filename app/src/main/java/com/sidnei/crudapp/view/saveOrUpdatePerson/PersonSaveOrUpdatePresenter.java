package com.sidnei.crudapp.view.saveOrUpdatePerson;

import android.content.Context;

import com.sidnei.crudapp.model.Person;
import com.sidnei.crudapp.repository.IPersonRepository;
import com.sidnei.crudapp.repository.PersonRepository;

public class PersonSaveOrUpdatePresenter {

    private IPersonRepository repository;
    private IPersonSaveOrUpdateView view;
    private Person p;

    public PersonSaveOrUpdatePresenter(IPersonSaveOrUpdateView view, Context cx){
        this.view = view;
        repository = new PersonRepository(cx);
        p = new Person();
    }

    public void onDestroy(){
        repository.close();
        view = null;
    }

    public void clear(){
        p = new Person();

        if(view != null){
            view.clearFields();
        }
    }

    public void setPerson(Person p){
        this.p = p;
        if(view != null){
            view.updateFields(p);
        }
    }

    public Person getPerson(){
        return p;
    }

    public void save(){

        if(view != null){
            view.showProgress("Salvando dados...", "Por favor, aguarde um instance!");
        }

        /// ***** verifying if all person data is valid *************
        if(!p.getName().equals("") && p.isValidCpf()) {

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

            /// person's name cannot be a empty value
        }else if(p.getName().equals("")){

            if(view != null){
                view.setNameError();
            }

            /// verifying if the cpf is a valid one
        }else if(!p.isValidCpf()){
            if(view != null){
                view.setCpfError();
            }
        }
        ////*************************************************

        if(view != null){
            view.hideProgress();
        }
    }

    public void setSex(int index){
        switch (index){
            case 0:
                this.p.setSex(Person.SEX.MALE);
                break;
            case 1:
                this.p.setSex(Person.SEX.FEMALE);
                break;
            case 2:
                this.p.setSex(Person.SEX.OTHER);
                break;
        }
    }

    public void setUF(String uf){
        this.p.setUf(uf);
    }

    public void setName(String name){
        this.p.setName(name);
    }

    public void setCPF(String cpf){
        this.p.setCpf(cpf);
    }

    public void setCEP(String cep){
        this.p.setCep(cep);
    }

    public void setAddress(String address){
        this.p.setAddress(address);
    }

    public void setView(IPersonSaveOrUpdateView view){
        this.view = view;
    }
}
