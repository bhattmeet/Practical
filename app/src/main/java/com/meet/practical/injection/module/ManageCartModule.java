package com.meet.practical.injection.module;



import com.meet.practical.injection.scope.UserScope;
import com.meet.practical.model.state.ManageCartInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ManageCartModule {

    @UserScope
    @Provides
    public ManageCartInterface provideUserAuthenticationService(Retrofit retrofit){
        return retrofit.create(ManageCartInterface.class);
    }

}
