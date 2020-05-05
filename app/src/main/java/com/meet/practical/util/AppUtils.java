package com.meet.practical.util;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.meet.practical.Practical;
import com.meet.practical.R;
import com.meet.practical.model.entity.response.BaseResponse;
import com.meet.practical.model.entity.response.User;
import com.meet.practical.network.RetrofitException;
import com.meet.practical.view.activity.BaseActivity;
import com.meet.practical.view.activity.LoginActivity;
import com.meet.owlimagepicker.Model.FileWithPath;
import com.meet.owlimagepicker.utils.ImageUtil;
import com.meet.owlutilities.callbacks.DialogButtonClickListener;
import com.meet.owlutilities.utils.AlertDialogHelper;
import com.meet.owlutilities.utils.DateFormatsConstants;
import com.meet.owlutilities.utils.ImageUtils;
import com.meet.owlutilities.utils.StringHelper;
import com.meet.owlutilities.utils.ToastHelper;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.meet.practical.Practical.getContext;
import static com.meet.practical.util.AppConstant.IMAGE_QUALITY;
import static com.meet.practical.util.AppConstant.MAX_IMAGE_HEIGHT;
import static com.meet.practical.util.AppConstant.MAX_IMAGE_WIDTH;

/**
 * Created by Jacks on 31-05-2017.
 */

public final class AppUtils {

    public static void setUserPrefrence(Context context, User userInfo) {
        if (context.getApplicationContext() != null) {
            Gson gson = ((Practical) context.getApplicationContext()).getNetworkComponent().provideGson();
            if (gson != null) {
                Practical.preferencePutString(AppConstant.SharedPrefKey.USER_INFO, gson.toJson(userInfo));
            }
        }
    }

    public static User getUserPrefrence(Context context) {
        if (context.getApplicationContext() != null) {
            Gson gson = ((Practical) context.getApplicationContext()).getNetworkComponent().provideGson();
            if (gson != null) {
                String userInfo = Practical.preferenceGetString(AppConstant.SharedPrefKey.USER_INFO, "");
                if (!StringHelper.isEmpty(userInfo)) {
                    return gson.fromJson(userInfo, User.class);
                }
            }
        }
        return null;
    }

    public static void setToolbarTextColor(MenuItem item, String title, int color) {
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(color), 0, s.length(), 0);
        item.setTitle(s);
    }

    public static void showError(Context mContext, String errorCode) {
        try {
            if (errorCode.contains("ERR")) {
                ToastHelper.error(mContext, StringHelper.getStringResourceByName(mContext, errorCode)
                        , Toast.LENGTH_LONG, AppConstant.IS_NOT_WANT_TOAST_ICON);
            } else if (errorCode.contains("SUCC")) {
                ToastHelper.success(mContext, StringHelper.getStringResourceByName(mContext, errorCode)
                        , Toast.LENGTH_LONG, AppConstant.IS_NOT_WANT_TOAST_ICON);
            } else {
                ToastHelper.error(mContext, errorCode
                        , Toast.LENGTH_LONG, AppConstant.IS_NOT_WANT_TOAST_ICON);
            }
        } catch (Exception e) {
//            Crashlytics.logException(e);
        }
    }

    public static FileWithPath compressImage(String path, File file) throws IOException {
        if (file != null && file.exists()) {
            FileWithPath fileWithPath;
            long fileSize = file.length();
            long fileSizeInKB = fileSize / 1024;
            if (fileSizeInKB > 1024) {
                fileWithPath = ImageUtil.createImageFile(Environment.DIRECTORY_DCIM);
                fileWithPath.setUri(Uri.fromFile(fileWithPath.getFile()));
                ImageUtils.compress(path, fileWithPath.getFile().getAbsolutePath(), MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT, IMAGE_QUALITY);
                return fileWithPath;
            }
        }
        return null;
    }


    public static FileWithPath createImageFile(String title, String type, String imageExtension) throws IOException {
        String imageFileName = "";
        File storageDir = null;
        File image = null;
        FileWithPath fileWithPath = new FileWithPath();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());

        if (imageExtension.equals(AppConstant.FileExtension.PDF)) {
            if (type.equals(AppConstant.Type.DOWNLOAD_CERTIFICATE)) {
                imageFileName = title;
                storageDir = new File(Environment.getExternalStorageDirectory(), AppConstant.Directory.PDF);
                Log.e("test", "Directory:" + storageDir.getAbsolutePath());
            } else {
                imageFileName = "DOCUMENT_" + timeStamp + "_";
                storageDir = new File(Environment.getExternalStorageDirectory(), AppConstant.Directory.PDF);
            }

        } else {
            if (type.equals(AppConstant.Type.CAMERA)) {
                imageFileName = "IMAGE_" + timeStamp + "_";
                storageDir = new File(Environment.getExternalStoragePublicDirectory(
                        type), "Camera");
            } else {
                imageFileName = "IMAGE_" + timeStamp + "_";
                storageDir = new File(Environment.getExternalStorageDirectory(), AppConstant.Directory.IMAGES);
            }
        }

        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        if (type.equals(AppConstant.Type.DOWNLOAD_CERTIFICATE)) {
            image = new File(storageDir, imageFileName);
            ;
        } else {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    imageExtension,         /* suffix */
                    storageDir      /* directory */
            );
        }

        String mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        fileWithPath.setFile(image);
        fileWithPath.setFilePath(mCurrentPhotoPath);
        fileWithPath.setUri(Uri.fromFile(image));
        return fileWithPath;
    }

    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    public static String getFilePathFromBitmap(Context mContext, Bitmap bitmap, String imageExtension) {
        String filePath = "";
        File filesDir = mContext.getFilesDir();
        String timeStamp = new SimpleDateFormat(DateFormatsConstants.YYYY_MM_DDD_TIME_24, Locale.US).format(new Date());
        File imageFile = new File(filesDir, timeStamp + imageExtension);
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            if (imageExtension.equals(AppConstant.FileExtension.PNG))
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            else
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
            filePath = imageFile.getAbsolutePath();
        } catch (Exception e) {
//            Crashlytics.logException(e);
        }
        return filePath;
    }


    //------------------------------------get application version-------------------------------------------
    public static String getApplicationVersion(Context mContext) {
        PackageInfo pinfo = null;
        try {
            pinfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return pinfo.versionName;//versionCode
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
    }

    //------------------------------------to get android id--------------------------------------
    public static String getAndroidId(Context ctx) {
        String androidId;
        final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        final String ID_KEY = "android_id";
        String[] params = {ID_KEY};
        Cursor c = null;

        c = ctx.getContentResolver().query(URI, null, null, params, null);

        if (c == null || !c.moveToFirst() || c.getColumnCount() < 2)
            return null;
        try {
            androidId = Long.toHexString(Long.parseLong(c.getString(1)));
            c.close();
            return androidId;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //------------------------------------get android version-------------------------------------------
    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    //-----------------------------------------------------getDevice Name------------------------------------------
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }

    public static String apkStorePath(Context context, String storePath) {

       /* String EXTERNAL_DIRECTORY_PATH = Environment.getExternalStorageDirectory() + "/NavneetRetailer";//OrderManagement Download

        return EXTERNAL_DIRECTORY_PATH;*/
        String root = Environment.getExternalStorageDirectory().toString();
        if (StringHelper.isEmpty(storePath)) {
            return root + File.separator + Environment.DIRECTORY_DOWNLOADS;
        } else {
            return root + File.separator + storePath;
        }
    }


    public static File downloadFile(Context mContext) {

        File direct = new File(AppUtils.apkStorePath(mContext, mContext.getString(R.string.app_name)));

        if (!direct.exists()) {
            direct.mkdirs();
        }
        File directcheck = new File(AppUtils.apkStorePath(mContext, mContext.getString(R.string.app_name)) + "/" + AppConstant.APP_APK_NAME);
        if (directcheck.exists()) {
            Boolean chk = directcheck.delete();
            if (chk) {
                Log.e("file :", "Sucessfully delete");
            }
        }

        direct = new File(AppUtils.apkStorePath(mContext, mContext.getString(R.string.app_name)));
        final DownloadManager mgr = (DownloadManager) mContext.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        long totalBytesRead = (direct.exists()) ? direct.length() : 0;

        DownloadManager.Request request = new DownloadManager.Request((Uri.parse(VariantConfig.getApkfilePath())));

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(mContext.getResources().getString(R.string.app_name))
                .setDescription(mContext.getString(R.string.notification_msg_download))
                .setDestinationInExternalPublicDir(mContext.getString(R.string.app_name), AppConstant.APP_APK_NAME)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .allowScanningByMediaScanner();

        long downloadManagerId = mgr.enqueue(request);
        return direct;
    }

    //------------------------------------Copy and rename  image-------------------------------------------
    public static String copyFileFromUri(Context context, Uri fileUri, String newFileName) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File saveDirectory = null;
        try {
            ContentResolver content = context.getContentResolver();
            inputStream = content.openInputStream(fileUri);

            saveDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera/");

            // create direcotory if it doesn't exists
            if (!saveDirectory.exists()) {
                saveDirectory.mkdirs();
            }

            outputStream = new FileOutputStream(saveDirectory + "/" + newFileName); // filename.png, .mp3, .mp4 ...
            if (outputStream != null) {
                Log.e("occurred", "Output Stream Opened successfully");
            }

            byte[] buffer = new byte[1000];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) >= 0) {
                outputStream.write(buffer, 0, buffer.length);
            }
        } catch (Exception e) {
            Log.e("Exception occurred ", "" + e.getMessage());
        }
        return saveDirectory.getAbsolutePath() + "/" + newFileName;
    }

    //------------------------------------When User's account signin from another device-------------------------------------------
    public static void handleUnauthorized(Context context, BaseResponse baseResponse) {
        if (context == null || baseResponse == null) return;
        if (baseResponse.getErrorCode()
                == AppConstant.UNAUTHORIZED
        ) {
            AlertDialogHelper.showDialog(context, null, context.getString(R.string.msg_unauthorized),
                    context.getString(R.string.ok), null, false, new DialogButtonClickListener() {
                        @Override
                        public void onPositiveButtonClicked(int dialogIdentifier) {
                            Practical.get().clearData();
                            if (context instanceof BaseActivity) {
                                ((BaseActivity) context).moveActivity(context, LoginActivity.class, true, true, null);
                            }
                        }

                        @Override
                        public void onNegativeButtonClicked(int dialogIdentifier) {

                        }
                    }, 0);
        } else {
            handleResponseMessage(context, baseResponse.getMessage());
        }
    }

    public static String getStringResourceByName(Context mContext, String aString) {
        try {

            if (mContext == null) return "";

            if (!StringHelper.isEmpty(aString)) {
                String packageName = mContext.getPackageName();
                String message = mContext.getString(mContext.getResources().getIdentifier(aString, "string", packageName));
                if (StringHelper.isEmpty(message)) {
                    message = mContext.getString(R.string.error_unknown);
                }
                return message;
            } else {
                return mContext.getString(R.string.error_unknown);
            }
        } catch (Exception e) {
//            Crashlytics.logException(e);
        }
        return "";
    }


    //------------------------------------Server Errors-------------------------------------------
    public static String getHttpErrorMessage(Context context, int statusCode) {
        String errorMessage = "";
        switch (statusCode) {
            case 400:
                errorMessage = context.getString(R.string.error_bad_request_400);
                break;
            case 401:
                errorMessage = context.getString(R.string.error_unauthorized_401);
                break;
            case 403:
                errorMessage = context.getString(R.string.error_forbidden_403);
                break;
            case 404:
                errorMessage = context.getString(R.string.error_not_found_404);
                break;
            case 405:
                errorMessage = context.getString(R.string.error_method_not_allowed_405);
                break;
            case 408:
                errorMessage = context.getString(R.string.error_request_timeout_408);
                break;
            case 413:
                errorMessage = context.getString(R.string.error_request_entity_too_large_413);
                break;
            case 414:
                errorMessage = context.getString(R.string.error_request_uri_too_long_414);
                break;
            case 500:
                errorMessage = context.getString(R.string.error_internal_server_error_500);
                break;
            default:
                errorMessage = context.getString(R.string.error_unknown);
                break;
        }
        return errorMessage;

    }

    public static void handleResponseMessage(Context context, String messageString) {
        try {
            if (context == null) return;

            String message = AppUtils.getStringResourceByName(context, messageString);
            AlertDialogHelper.showDialog(context, null, message, context.getString(R.string.ok),
                    null, false, null, 0);
        } catch (Exception e) {
//            Crashlytics.logException(e);
        }
    }

    public static void handleApiError(Context context, RetrofitException retrofitException) {
        try {
            if (context == null) return;

            if (retrofitException != null) {

                switch (retrofitException.getKind()) {
                    case HTTP:
                        if (retrofitException.getRetrofitExceptionBody() != null) {
                            String errorMessage = getHttpErrorMessage(context, retrofitException.getRetrofitExceptionBody().getStatus());
                            AlertDialogHelper.showDialog(context, null, errorMessage
                                    , context.getString(R.string.ok_utils), null, false,
                                    null, 0);
                        }
                        break;
                    case NETWORK:
                        AlertDialogHelper.showDialog(context, null, context.getString(R.string.error_network)
                                , context.getString(R.string.ok_utils), null, false,
                                null, 0);
                        break;
                    case UNEXPECTED:
                        AlertDialogHelper.showDialog(context, null, context.getString(R.string.error_unknown)
                                , context.getString(R.string.ok_utils), null, false,
                                null, 0);
                        break;
                }
            } else {
                AlertDialogHelper.showDialog(context, null, context.getString(R.string.error_unknown_utils)
                        , context.getString(R.string.ok_utils), null, false,
                        null, 0);
            }
        } catch (Exception e) {
//            Crashlytics.logException(e);
        }
    }
    
    public static InputFilter editTextFilter(Context mContext) {
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {
            for (int i = start; i < end; i++) {
                int type = Character.getType(source.charAt(i));
                //System.out.println("Type : " + type);
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    // showToastMsg(mContext ,mContext.getString(R.string.error_enter_emoji) ,Toast.LENGTH_SHORT);
                    ToastHelper.success(mContext, mContext.getString(R.string.error_enter_emoji), Toast.LENGTH_SHORT, false);
                    return "";
                }
            }
            return null;
        };

        return filter;
    }

    public static String getDeviceUniqueId() {
        try {
            String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            return android_id;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void openUrlInBrowser(Context mContext,String url){
        if(!StringHelper.isEmpty (url)){
            Intent helpIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
            mContext.startActivity(helpIntent);
        }
    }

}
