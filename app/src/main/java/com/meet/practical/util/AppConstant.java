package com.meet.practical.util;

/**
 * Created by Dhaval on 05-02-2018.
 */

public final class AppConstant {
    public static final int UNAUTHORIZED = 401;
    public static final String ERROR_UNKNOWN = "ERR0001";
    public static final String EXTRA_CHANNEL_SID = "C_SID";
    public static final int DATA_PER_PAGE = 10;
    public static final int DEVICE_TYPE = 1;

    public static final int PWD_MIN_LENGTH = 6;
    public static final int PWD_MAX_LENGTH = 12;

    public static boolean IS_WANT_TOAST_ICON = true;
    public static boolean IS_NOT_WANT_TOAST_ICON = false;
    public static final int STATUS_CODE_200 = 200;

    public static final int MAX_IMAGE_WIDTH = 1280;
    public static final int MAX_IMAGE_HEIGHT = 1280;

    public static final int IMAGE_QUALITY = 80;

    public static boolean REFRESH_SAVED_VIDEO = false;
    public static boolean REFRESH_CART_COUNT = false;

    public static final class DialogIdentifier {
        public static final int LOGOUT = 1;
        public static final int QUIZ_TIME_OUT = 2;
        public static final int REMOVE_ITEM_FROM_CART = 3;
        public static final int SELECT_MONTH = 4;
        public static final int SELECT_YEAR = 5;
        public static final int SELECT_ASSIGN_USER = 6;
        public static final int PAYMENT_SUCCESS_INFO = 7;
        public static final int EMPTY_ASSIGNED_COURSES = 8;
        public static final int SELECT_COUNTRY= 9;
        public static final int ASSIGNSELF = 10;
        public static final int COUNTRY_CODE= 11;
        public static final int REMOVE_ITEM_FROM_CARD = 12;
    }

    public static final class IntentKey {
        public static final String COURSE_VIDEO_INFO = "course_video_info";
        public static final String USER_ID = "user_id";
        public static final String COURSE_ID = "course_id";
        public static final String ORDER_ID = "order_id";
        public static final String COURSE_NAME = "course_name";
        public static final String COURSE_INFO = "course_info";
        public static final String VIDEO_ID = "video_id";
        public static final String VIDEO_DETAILS_RESPONSE = "video_details_response";
        public static final String QUIZ_DATA = "quiz_data";
        public static final String QUIZ_ID = "quiz_id";
        public static final String QUIZ_INFO = "quiz_info";
        public static final String COURSE_QUIZ = "course_quiz";
        public static final String IMAGE_URI = "image_uri";
        public static final String CROP_RATIO_X = "crop_ratio_X";
        public static final String CROP_RATIO_Y = "crop_ratio_Y";
        public static final String FILE_EXTENSION = "file_extension";
        public static final String TOTAL_AMOUNT = "total_amount";
        public static final String FROM_PAYMENT = "from_payment";
        public static final String FROM_RESULT_SCREEN = "from_result_screen";
        public static final String SOCIAL_SIGN_IN_DATA = "social_sign_in_data";
        public static final String VIDEO_LAST_DURATION = "video_last_duration";
        public static final String VIDEO_TOTAL_DURATION = "video_total_duration";
        public static final String GET_ADDRESS= "get_address";
        public static final String CART_COUNT = "cart_count";
        public static final String SEND_ADDRESS = "send_address";
        public static final String CARD = "card";
        public static final int TAKE_QUIZ = 1;
        public static final int CONGRATULATION = 2;
        public static final int REQUEST_CROP_IMAGE = 3;
        public static final int REQUEST_GALLERY = 4;
        public static final int REQUEST_CAMERA = 5;
        public static final int REQUEST_CAMERA_KITKAT = 6;
        public static final int PROFILE_UPDATE = 7;
        public static final int EXTERNAL_STORAGE_PERMISSION = 8;
        public static final int VIEW_VIDEO_DETAILS = 9;
        public static final int VIEW_MY_CART = 10;
        public static final int VIEW_COURSES = 11;
        public static final int ASSIGN_USERS = 12;
        public static final int GOOGLE_LOGIN = 13;
        public static final int LOGIN_UPDATE = 14;
        public static final int SAVE_ADDRESS = 15;
        public static final int GET_ADDRESSES = 16;
        public static final int SIGNUP_UPDATE = 17;
        public static final int SELECT_ASSIGN_USER = 18;
        public static final int MYCOURSE = 19;
        public static final int END_QUIZ = 20;
    }


    public static final class AppLanguage {
        public static final String ISO_CODE_ENG = "en";
        public static final String ISO_CODE_MARATHI = "mr";
    }


    public static final class SharedPrefKey {
        public static final String USER_INFO = "USER_INFO";
        public static final String APP_LANGUAGE = "APP_LANGUAGE";
    }

    public static final class Type {
        public static final int SINGLE_OPTION_QUESTION = 1;
        public static final int MULTIPLE_OPTION_QUESTION = 2;
        public static final int SINGLE_USER = 2;
        public static final int CORPORATE = 1;
        public static final String DOWNLOAD_CERTIFICATE = "download_certificate";
        public static final String CAMERA = "camera";
        public static final String FB = "facebook";
        public static final String GOOGLE = "google";
        public static final String TWITTER = "twitter";
    }

    public static final class Directory {
        public static final String DEFAULT = "owlacademic";
        public static final String CERTIFICATE = "/OWL Academic/Certificates/";
        public static final String INVOICE = "/OWL Academic/Invoices/";
        public static final String PDF = "OWLAcademic/Certificates";
        public static final String IMAGES = "owlacademic/images";
    }

    public static final class ACTION {
        public static final int CONFIRM = 0;
        public static final int NEXT = 1;
        public static final int END = 2;
    }

    public static final class FileExtension {
        public static final String JPG = ".jpg";
        public static final String PNG = ".png";
        public static final String PDF = ".pdf";
    }


    public static final String APP_APK_NAME = "RetailerNavneetDev.apk";


    public static boolean isRefreshSavedVideo() {
        return REFRESH_SAVED_VIDEO;
    }

    public static void setRefreshSavedVideo(boolean refreshSavedVideo) {
        REFRESH_SAVED_VIDEO = refreshSavedVideo;
    }

    public static boolean isRefreshCartCount() {
        return REFRESH_CART_COUNT;
    }

    public static void setRefreshCartCount(boolean refreshCartCount) {
        REFRESH_CART_COUNT = refreshCartCount;
    }
}
