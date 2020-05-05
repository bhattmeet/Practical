package com.meet.practical.callback;

import com.meet.practical.network.RetrofitException;

public interface ViewListener {

    void showProgress();

    void hideProgress();

    void showApiError(RetrofitException retrofitException, String errorCode);
}
