package com.meet.practical;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatDelegate;

import com.meet.practical.injection.component.DaggerNetworkComponent;
import com.meet.practical.injection.component.DaggerServiceComponent;
import com.meet.practical.injection.component.NetworkComponent;
import com.meet.practical.injection.component.ServiceComponent;
import com.meet.practical.injection.module.AppModule;
import com.meet.practical.injection.module.NetworkModule;
import com.meet.practical.injection.module.UserAuthenticationModule;
import com.meet.practical.util.AppConstant;
import com.meet.practical.util.LocaleManager;
import com.meet.practical.util.VariantConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Practical extends Application {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static NetworkComponent networkComponent;
    public static ServiceComponent serviceComponent;
    private static SharedPreferences.Editor sharedPreferencesEditor;
    private static SharedPreferences sharedPreferences;
    private static Context context;
    private static Practical instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
        // CalligraphyContextWrapper.wrap(base);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        context = getApplicationContext();
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        FirebaseApp.initializeApp(this);
        setupFont();

//        TwitterAuthConfig authConfig =
//                new TwitterAuthConfig(context.getString(R.string.twitter_consumer_key),
//                        context.getString(R.string.twitter_consumer_secret));
//        Fabric.with(new Fabric.Builder(this).kits(new Crashlytics(), new Twitter(authConfig)).build());
//        Fabric.with(this, new Twitter(authConfig));

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(VariantConfig.getServerBaseUrl()))
                .appModule(new AppModule(this))
                .build();

        serviceComponent = DaggerServiceComponent.builder()
                .networkComponent(networkComponent)
                .userAuthenticationModule(new UserAuthenticationModule())
//                .galleryModule(new GalleryModule())
                .build();

        sharedPreferencesEditor = networkComponent.provideSharedPreference().edit();
        sharedPreferences = networkComponent.provideSharedPreference();
        Practical.instance = this;
    }

    public static Practical get() {
        return instance;
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public void setNetworkComponent(NetworkComponent networkComponent) {
        this.networkComponent = networkComponent;
    }

    public static ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

    public void setServiceComponent(ServiceComponent serviceComponent) {
        this.serviceComponent = serviceComponent;
    }

    public static SharedPreferences.Editor getSharedPreferencesEditor() {
        return sharedPreferencesEditor;
    }

    public static void setSharedPreferencesEditor(SharedPreferences.Editor sharedPreferencesEditor) {
        Practical.sharedPreferencesEditor = sharedPreferencesEditor;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void setSharedPreferences(SharedPreferences sharedPreferences) {
        Practical.sharedPreferences = sharedPreferences;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Practical.context = context;
    }

    /**
     * Application level preference work.
     */
    public static void preferencePutInteger(String key, int value) {
        sharedPreferencesEditor.putInt(key, value).apply();
    }

    public static int preferenceGetInteger(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void preferencePutBoolean(String key, boolean value) {
        sharedPreferencesEditor.putBoolean(key, value).apply();
    }

    public static boolean preferenceGetBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void preferencePutString(String key, String value) {
        sharedPreferencesEditor.putString(key, value).apply();
    }

    public static String preferenceGetString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void preferencePutLong(String key, long value) {
        sharedPreferencesEditor.putLong(key, value).apply();
    }

    public static long preferenceGetLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static void preferenceRemoveKey(String key) {
        sharedPreferencesEditor.remove(key).apply();
    }

    public static void preferencePutFloat(String key, float value) {
        sharedPreferencesEditor.putFloat(key, value).apply();
    }

    public static float preferenceGetFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static void clearPreference() {
        sharedPreferencesEditor.clear().apply();
    }


    private void setupFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_pop_in_regular))
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public void clearData() {
        try {
//            FirebaseAuth.getInstance().signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Practical.preferenceRemoveKey(AppConstant.SharedPrefKey.USER_INFO);
    }

}
