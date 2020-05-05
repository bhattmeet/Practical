package com.meet.practical.viewModel;

import com.meet.practical.Practical;
import com.meet.practical.model.state.ManageCartInterface;
import com.meet.practical.util.ResourceProvider;

import javax.inject.Inject;


public class ManageCartViewModel extends BaseViewModel {
    @Inject
    ManageCartInterface manageCartInterface;


    public ManageCartViewModel(ResourceProvider resourceProvider) {
        Practical.getServiceComponent().inject(this);

    }
}
