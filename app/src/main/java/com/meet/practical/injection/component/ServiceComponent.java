package com.meet.practical.injection.component;

import com.meet.practical.injection.module.DashboardModule;
import com.meet.practical.injection.module.ManageCartModule;
import com.meet.practical.injection.module.UserAuthenticationModule;
import com.meet.practical.injection.scope.UserScope;
import com.meet.practical.viewModel.DashboardViewModel;
import com.meet.practical.viewModel.LoginViewModel;
import com.meet.practical.viewModel.ManageCartViewModel;
import com.meet.practical.viewModel.SignUpViewModel;

import dagger.Component;

/**
 */
@UserScope
@Component(dependencies = NetworkComponent.class, modules = {UserAuthenticationModule.class,DashboardModule.class, ManageCartModule.class})

public interface ServiceComponent {
    // for authentication view model
    void inject(LoginViewModel loginViewModel);
    void inject(SignUpViewModel signUpViewModel);
    void inject(DashboardViewModel dashboardViewModel);
    void inject(ManageCartViewModel manageCartViewModel);
}
