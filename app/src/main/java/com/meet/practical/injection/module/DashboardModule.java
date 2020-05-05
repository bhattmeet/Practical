package com.meet.practical.injection.module;



import com.meet.practical.injection.scope.UserScope;
import com.meet.practical.model.state.DashboardServiceInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 */
@Module
public class DashboardModule {

    @UserScope
    @Provides
    public DashboardServiceInterface provideUserAuthenticationService(Retrofit retrofit){
        return retrofit.create(DashboardServiceInterface.class);
    }

}
