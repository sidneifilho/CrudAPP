package com.sidnei.crudapp.view.searchPerson;

public interface IPersonSearchView {
    void showProgress();
    void hideProgress();
    void showSearchFail();

    void showDeleteOk();
    void showDeleteFail();

    void showMessage(String msg);

    void clearView();
}
