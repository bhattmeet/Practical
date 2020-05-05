package com.meet.practical.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.meet.practical.Practical;
import com.meet.practical.model.entity.response.PracticalResponse;
import com.meet.practical.model.state.DashboardServiceInterface;
import com.meet.practical.network.RXRetroManager;
import com.meet.practical.network.RetrofitException;
import com.meet.practical.util.ResourceProvider;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DashboardViewModel extends BaseViewModel {
    @Inject
    DashboardServiceInterface dashboardServiceInterface;

    private MutableLiveData<PracticalResponse> practicalResponse;

    public DashboardViewModel(ResourceProvider resourceProvider) {
        Practical.getServiceComponent().inject(this);
    }

    public void practicalResponse() {

        if (view != null) {
            view.showProgress();
        }
        new RXRetroManager<PracticalResponse>() {
            @Override
            protected void onSuccess(PracticalResponse response) {
                if (view != null) {
                    practicalResponse.postValue(response);
                    view.hideProgress();
                }
            }

            @Override
            protected void onFailure(RetrofitException retrofitException, String errorCode) {
                super.onFailure(retrofitException, errorCode);
                if (view != null) {
                    view.showApiError(retrofitException, errorCode);
                    view.hideProgress();
                }
            }
        }.rxSingleCall(dashboardServiceInterface.getPracticalInfo());
    }

    public MutableLiveData<PracticalResponse> getPracticalResponse() {
        Log.e("test","dashboardRequest Response");
        if (practicalResponse == null) {
            practicalResponse = new MutableLiveData<>();
        }
        return practicalResponse;
    }
}
