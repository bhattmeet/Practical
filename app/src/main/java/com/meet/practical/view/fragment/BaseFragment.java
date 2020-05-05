package com.meet.practical.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.meet.practical.callback.OnFragmentBackListener;
import com.meet.practical.callback.BackPressImpl;
import com.meet.practical.callback.BaseCallBack;


public class BaseFragment extends Fragment implements OnFragmentBackListener {

    public BaseCallBack baseCallBack;
    private boolean isHidden = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseCallBack) {
            baseCallBack = (BaseCallBack) context;
        }

    }

    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }

    public BaseCallBack getBaseCallBack() {
        return baseCallBack;
    }

    public void moveActivity(Context context, Class<?> c, boolean finish, boolean clearStack, Bundle bundle) {
        Intent intent = new Intent(context, c);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (clearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(intent);
        if (finish) {
            ((Activity) context).finish();
        }
    }

    public void moveActivity(Context context, Intent intent, boolean finish, boolean clearStack, Bundle bundle) {
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (finish) {
            ((Activity) context).finish();
        }

        if (clearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(intent);

    }

    public void moveActivityForResult(Context context, Class<?> c, boolean finish, boolean clearStack, int requestCode, Bundle bundle) {
        Intent intent = new Intent(context, c);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (clearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        ((Activity) context).startActivityForResult(intent, requestCode);
//        Activity activity = (Activity) context;
//        activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        if (finish) {
            ((Activity) context).finish();
        }
    }
}
