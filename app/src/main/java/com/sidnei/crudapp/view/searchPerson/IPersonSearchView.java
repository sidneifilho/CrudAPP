package com.sidnei.crudapp.view.searchPerson;

import android.content.Context;

public interface IPersonSearchView {
    void showProgress(String title, String message);

    void hideProgress();

    void showSearchFail();

    void showDeleteOk();

    void showDeleteFail();

    void showMessage(String msg);

    void clearView();

    Context getContext();
}
