package com.meet.practical.viewModel;

import com.meet.practical.Practical;
import com.meet.practical.model.state.UserAuthenticationServiceInterface;
import com.meet.practical.util.ResourceProvider;

import javax.inject.Inject;

public class SignUpViewModel extends BaseViewModel {
    @Inject
    UserAuthenticationServiceInterface userAuthenticationServiceInterface;

    public SignUpViewModel(ResourceProvider resourceProvider) {
        Practical.getServiceComponent().inject(this);

    }

}
