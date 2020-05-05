package com.meet.practical.util;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.meet.practical.viewModel.DashboardViewModel;
import com.meet.practical.viewModel.LoginViewModel;
import com.meet.practical.viewModel.ManageCartViewModel;
import com.meet.practical.viewModel.SignUpViewModel;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ResourceProvider resourceProvider;


    public LoginViewModelFactory(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(resourceProvider);
        }else if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
            return (T) new SignUpViewModel(resourceProvider);
        }else if (modelClass.isAssignableFrom(DashboardViewModel.class)) {
            return (T) new DashboardViewModel(resourceProvider);
        }
        else if (modelClass.isAssignableFrom(ManageCartViewModel.class)) {
            return (T) new ManageCartViewModel(resourceProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}