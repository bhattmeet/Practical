package com.meet.practical.injection.module;



import com.meet.practical.injection.scope.UserScope;
import com.meet.practical.model.state.UserAuthenticationServiceInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 */
@Module
public class UserAuthenticationModule {

    @UserScope
    @Provides
    public UserAuthenticationServiceInterface provideUserAuthenticationService(Retrofit retrofit){
        return retrofit.create(UserAuthenticationServiceInterface.class);
    }

}
