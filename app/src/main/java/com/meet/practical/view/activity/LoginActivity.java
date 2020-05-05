package com.meet.practical.view.activity;

import android.Manifest;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.meet.practical.R;
import com.meet.practical.databinding.ActivityLoginBinding;
import com.meet.practical.model.entity.request.LoginRequest;
import com.meet.practical.util.LoginViewModelFactory;
import com.meet.practical.util.ResourceProvider;
import com.meet.practical.viewModel.LoginViewModel;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private Context mContext;
    private String[] EXTERNAL_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private final int RC_EXTERNAL_STORAGE_PERM = 124;
    private boolean isVisible = false;
    private String email = "";
    private LoginViewModel loginViewModel;
    private LoginRequest socialLoginRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor();
        mContext = this;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory(new ResourceProvider(getResources()))).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        loginViewModel.createView(this);
//        setupFbLogin();
//        setupGoogleLogin();
//        requestPermission();

        binding.ivPasswordVisibility.setOnClickListener(this);
        binding.tvSignUp.setOnClickListener(this);
        binding.txtLogin.setOnClickListener(this);
        binding.tvForgotPwd.setOnClickListener(this);
        binding.imgFbLogin.setOnClickListener(this);
        binding.imgGoogleLogin.setOnClickListener(this);
        binding.imgTwitterLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPasswordVisibility:
                setPasswordVisibility();
                break;
            case R.id.tvSignUp:
//                moveActivity(mContext, SignUpActivity.class, true, false, null);
//                moveActivityForResult (mContext,SignUpActivity.class,false,false,AppConstant.IntentKey.SIGNUP_UPDATE,null);
                break;
            case R.id.tvForgotPwd:
//                moveActivity(mContext, ForgotPasswordActivity.class, false, false, null);
                break;
            case R.id.txtLogin:
//                doLogin();
                break;
            case R.id.imgFbLogin:
//                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
                break;
            case R.id.imgGoogleLogin:
//                googleLogin();
                break;
            case R.id.imgTwitterLogin:
//                twitterLogin();
                break;

        }
    }

    public void setPasswordVisibility() {
        if (isVisible()) {
            setVisible(false);
            binding.edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            binding.ivPasswordVisibility.setImageResource(R.drawable.ic_eye_visible);
            binding.edtPassword.setSelection(binding.edtPassword.getText().length());
        } else {
            setVisible(true);
            binding.edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            binding.ivPasswordVisibility.setImageResource(R.drawable.ic_eye_invisible);
            binding.edtPassword.setSelection(binding.edtPassword.getText().length());
        }
    }

//    private boolean hasPermission() {
//        return EasyPermissions.hasPermissions(this, EXTERNAL_STORAGE);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

//    @AfterPermissionGranted(RC_EXTERNAL_STORAGE_PERM)
//    public void requestPermission() {
//        if (hasPermission()) {
//            // Have permissions, do the thing!
//            /* getEmailAccount();
//             */
//        } else {
//            // Ask for both permissions
//            EasyPermissions.requestPermissions(
//                    this,
//                    getString(R.string.rational_external_storage),
//                    RC_EXTERNAL_STORAGE_PERM,
//                    EXTERNAL_STORAGE);
//
//        }
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        /*   getEmailAccount();*/
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            new AppSettingsDialog.Builder(this).build().show();
//        }
//    }

    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

//    private boolean validate() {
//        boolean valid = true;
//        if (!ValidationUtil.isEmptyEditText(loginViewModel.getLoginRequest().getEmail())) {
//            if (ValidationUtil.isValidEmail(binding.edtEmail.getText().toString())) {
//                binding.edtEmail.setError(null);
//            } else {
//                ValidationUtil.setErrorIntoEditext(binding.edtEmail, mContext.getString(R.string.error_valid_email));
//                valid = false;
//            }
//        } else {
//            ValidationUtil.setErrorIntoEditext(binding.edtEmail, mContext.getString(R.string.error_email));
//            valid = false;
//        }
//
//        if (!ValidationUtil.isEmptyEditText(loginViewModel.getLoginRequest().getPassword())) {
//            if (ValidationUtil.checkMinTextValidation(
//                    loginViewModel.getLoginRequest().getPassword(), AppConstant.PWD_MIN_LENGTH)) {
//                if (ValidationUtil.checkMaxTextValidation(
//                        loginViewModel.getLoginRequest().getPassword(), AppConstant.PWD_MAX_LENGTH)) {
//                    binding.edtPassword.setError(null);
//                } else {
//                    ValidationUtil.setErrorIntoEditext(
//                            binding.edtPassword, mContext.getString(R.string.error_pwd_min_length));
//                    valid = false;
//                }
//
//            } else {
//                ValidationUtil.setErrorIntoEditext(binding.edtPassword, mContext.getString(R.string.error_pwd_min_length));
//                valid = false;
//            }
//
//        } else {
//            ValidationUtil.setErrorIntoEditext(binding.edtPassword, mContext.getString(R.string.error_pwd_mandatory));
//            valid = false;
//        }
//        return valid;
//    }

//    private void doApiCall() {
//        loginViewModel.sendLoginRequest();
//    }

//    private void doLogin() {
//        if (validate()) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (hasPermission()) {
//                    doApiCall();
//                } else {
//                    requestPermission();
//                }
//            } else {
//                doApiCall();
//            }
//        }
//    }

//    public Observer userDetail() {
//        return (Observer<UserResponse>) response -> {
//            try {
//                if (response == null) {
//                    AlertDialogHelper.showDialog(mContext, null,
//                            mContext.getString(R.string.error_unknown), mContext.getString(R.string.ok),
//                            null, false, null, 0);
//                    return;
//                }
//                if (response.isSuccess()) {
//                    AppUtils.setUserPrefrence(mContext, response.getInfo());
////                    moveActivity(mContext, MainActivity.class, true, true, null);
//                    setResult (1);
//                    finish ();
//                } else {
//                    AppUtils.handleUnauthorized(mContext, response);
//                }
//            } catch (Exception e) {
////                Crashlytics.logException(e);
//            }
//        };
//    }

//    public Observer socialLoginResponse() {
//        return (Observer<UserResponse>) response -> {
//            try {
//                if (response == null) {
//                    AlertDialogHelper.showDialog(mContext, null,
//                            mContext.getString(R.string.error_unknown), mContext.getString(R.string.ok),
//                            null, false, null, 0);
//                    return;
//                }
//                if (response.isSuccess()) {
//                    //ERR107 for User already exist
//                    if (!StringHelper.isEmpty(response.getMessage())
//                            && response.getMessage().equals("ERR107")) {
//                        if (socialLoginRequest != null) {
//                            SignUpRequest request = new SignUpRequest();
//                            request.setSocial_type(socialLoginRequest.getSocial_type());
//                            request.setSocial_type_id(socialLoginRequest.getSocial_type_id());
//                            request.setName(socialLoginRequest.getName());
//                            request.setImage(socialLoginRequest.getImage());
//                            request.setEmail(socialLoginRequest.getEmail());
//                            Bundle bundle = new Bundle();
//                            bundle.putParcelable(AppConstant.IntentKey.SOCIAL_SIGN_IN_DATA, Parcels.wrap(request));
//                            moveActivity(mContext, SignUpActivity.class, false, false, bundle);
//                        }
//                    } else {
//                        AppUtils.setUserPrefrence(mContext, response.getInfo());
//                        moveActivity(mContext, MainActivity.class, true, true, null);
//                    }
//                } else {
//                    AppUtils.handleUnauthorized(mContext, response);
//                }
//            } catch (Exception e) {
//                Crashlytics.logException(e);
//            }
//        };
//    }

    @Override
    protected void onDestroy() {
        loginViewModel.destroyView();
        super.onDestroy();
    }

//    public void setupFbLogin() {
//        callbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(
//                                    JSONObject object,
//                                    GraphResponse response) {
//                                email = object.optString("email");
//                                if (!StringHelper.isEmpty(email)) {
//                                    AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
//                                    handleAccessToken(credential, AppConstant.Type.FB);
//                                }
//                            }
//                        });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email,link");
//                request.setParameters(parameters);
//                request.executeAsync();
//            }
//
//            @Override
//            public void onCancel() {
////                Toast.makeText(getBaseContext(), "Login Cancelled", Toast.LENGTH_SHORT).show();
//                LoginManager.getInstance().logOut();
//            }
//
//            @Override
//            public void onError(FacebookException e) {
////                Toast.makeText(getBaseContext(), "Problem connecting to Facebook", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    public void setupGoogleLogin() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.web_client_id))
//                .requestEmail()
//                .build();
//        googleSignInClient = GoogleSignIn.getClient(this, gso);
//    }

//    public void googleLogin() {
//        Intent signInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, AppConstant.IntentKey.GOOGLE_LOGIN);
//    }

//    public void twitterLogin() {
//        mTwitterAuthClient.authorize(this, new Callback<TwitterSession>() {
//            @Override
//            public void success(Result<TwitterSession> twitterSessionResult) {
//                mTwitterAuthClient.requestEmail(twitterSessionResult.data, new Callback<String>() {
//                    @Override
//                    public void success(Result<String> emailResult) {
//                        email = emailResult.data;
//                        if (!StringHelper.isEmpty(email)) {
//                            Log.e("test", "Email:" + email);
//                            AuthCredential credential = TwitterAuthProvider.getCredential(
//                                    twitterSessionResult.data.getAuthToken().token,
//                                    twitterSessionResult.data.getAuthToken().secret);
//                            handleAccessToken(credential, AppConstant.Type.TWITTER);
//                        }
//                    }
//
//                    @Override
//                    public void failure(TwitterException e) {
////                        ca.onTwitterSignInFailed(e);
//                    }
//                });
//            }
//
//            @Override
//            public void failure(TwitterException e) {
//                e.printStackTrace();
//            }
//        });
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
////        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK)
//            switch (requestCode) {
//                case AppConstant.IntentKey.GOOGLE_LOGIN:
//                    try {
//                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                        GoogleSignInAccount account = task.getResult(ApiException.class);
//                        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//                        email = account.getEmail();
//
//                        if (!StringHelper.isEmpty(email)) {
//                            Log.e("test", "Email:" + account.getEmail());
//                            handleAccessToken(credential, AppConstant.Type.GOOGLE);
//                        }
//                    } catch (ApiException e) {
//                    }
//                    break;
//
//                case AppConstant.IntentKey.SIGNUP_UPDATE:
//                    setResult (1);
//                    finish ();
//                    break;
//            }
//    }


//    public void updateUI(FirebaseUser data, String socialType) {
//        try {
//            Log.e("test", "Id:" + data.getUid());
//            Log.e("test", "Name:" + data.getDisplayName());
//            Log.e("test", "Image:" + data.getPhotoUrl().toString());
//
//            socialLoginRequest = new LoginRequest();
//            socialLoginRequest.setSocial_type(socialType);
//            socialLoginRequest.setSocial_type_id(data.getUid());
//            socialLoginRequest.setName(data.getDisplayName());
//            socialLoginRequest.setImage(data.getPhotoUrl().toString());
//            if (!StringHelper.isEmpty(email)) {
//                Log.e("test", "EMAIL:" + email);
//                socialLoginRequest.setEmail(email);
//                loginViewModel.sendSocialLoginRequest(socialLoginRequest);
//            } else {
//                Log.e("test", "null email");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handleAccessToken(AuthCredential credential, String socialType) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        updateUI(user, socialType);
//                    } else {
//                        Toast.makeText(mContext, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

}
