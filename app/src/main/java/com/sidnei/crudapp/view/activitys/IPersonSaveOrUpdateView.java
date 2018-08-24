package com.sidnei.crudapp.view.activitys;

public interface IPersonSaveOrUpdateView {

    void showProgress();

    void hideProgress();

    void clearFields();

    void showSaveSuccessful();

    void setNameError();

    void setCpfError();

}
