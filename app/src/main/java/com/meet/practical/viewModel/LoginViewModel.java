package com.meet.practical.viewModel;

import com.meet.practical.Practical;
import com.meet.practical.model.state.UserAuthenticationServiceInterface;
import com.meet.practical.util.ResourceProvider;

import javax.inject.Inject;


public class LoginViewModel extends BaseViewModel {

    @Inject
    UserAuthenticationServiceInterface userAuthenticationServiceInterface;

//    private MutableLiveData<UserResponse> mLoginResponse;

    public LoginViewModel(ResourceProvider resourceProvider) {
        Practical.getServiceComponent().inject(this);
    }

//    public void sendLoginRequest() {
//        loginRequest.setDevice_token (AppUtils.getDeviceUniqueId ( ));
//        loginRequest.setDevice_name (DeviceName.getDeviceName ());
//        loginRequest.setDevice_type(AppConstant.DEVICE_TYPE);
//        if (view != null) {
//            view.showProgress();
//        }
//        new RXRetroManager<UserResponse>() {
//            @Override
//            protected void onSuccess(UserResponse response) {
//                if (view != null) {
//                    mLoginResponse.postValue(response);
//                    view.hideProgress();
//                }
//            }
//
//            @Override
//            protected void onFailure(RetrofitException retrofitException, String errorCode) {
//                super.onFailure(retrofitException, errorCode);
//                if (view != null) {
//                    view.showApiError(retrofitException, errorCode);
//                    view.hideProgress();
//                }
//            }
//        }.rxSingleCall(userAuthenticationServiceInterface.login(loginRequest));
//
//    }


//    public MutableLiveData<UserResponse> getmUserResponse() {
//        if (mLoginResponse == null) {
//            mLoginResponse = new MutableLiveData<>();
//        }
//        return mLoginResponse;
//    }
}
