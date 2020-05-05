package com.meet.practical.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jaredrummler.android.device.DeviceName;
import com.meet.owlutilities.utils.AlertDialogHelper;
import com.meet.practical.R;
import com.meet.practical.adapter.PracticalListAdapter;
import com.meet.practical.databinding.ActivityPracticalBinding;
import com.meet.practical.model.entity.response.PracticalInfo;
import com.meet.practical.model.entity.response.PracticalResponse;
import com.meet.practical.util.AppUtils;
import com.meet.practical.util.LoginViewModelFactory;
import com.meet.practical.util.ResourceProvider;
import com.meet.practical.viewModel.DashboardViewModel;

import java.util.List;

public class PracticalActivity extends BaseActivity {

    private ActivityPracticalBinding binding;
    private DashboardViewModel dashboardViewModel;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_practical);

        dashboardViewModel = ViewModelProviders.of(this, new LoginViewModelFactory(new ResourceProvider(getResources()))).get(DashboardViewModel.class);
        dashboardViewModel.createView(this);
        dashboardViewModel.getPracticalResponse()
                .observe(this, getPracticalResponse());
        dashboardViewModel.practicalResponse();
    }

    private Observer getPracticalResponse() {
        return (Observer<PracticalResponse>) response -> {
            try {
//                if (response == null) {
//                    AlertDialogHelper.showDialog(mContext, null,
//                            mContext.getString(R.string.error_unknown), mContext.getString(R.string.ok),
//                            null, false, null, 0);
//                    return;
//                }
//                if (response.isSuccess()) {
//                    setAdapter(response.getResult());
//                } else {
//                    AppUtils.handleUnauthorized(mContext, response);
//                }
                Log.e("test","response.getResult:"+response.getResult().size());
                setAdapter(response.getResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private void setAdapter(List<PracticalInfo> list) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            binding.recyclerView.setLayoutManager(linearLayoutManager);
            binding.recyclerView.setAdapter(new PracticalListAdapter(mContext, list));
    }
}
