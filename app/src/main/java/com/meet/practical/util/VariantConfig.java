package com.meet.practical.util;

/**
 */
public class VariantConfig {

    public static String getServerBaseUrl() {
//        return "http://192.168.1.116/OwlAcademic/api/v1/";
//        live ip
//       return "https://owlacademic.com/api/v1/";
        return "https://gorest.co.in/public-api/";
    }

    //for the get time slab service
    public static String getTimeSlabUrl(){
        return "http://app.pustakbank.in/getTimeSlab";
      //  return "http://192.168.1.102:80/getTimeSlab";
    }

    public static String getApkfilePath() {
        return getServerBaseUrl() + "mobile/" + AppConstant.APP_APK_NAME;
    }


}
