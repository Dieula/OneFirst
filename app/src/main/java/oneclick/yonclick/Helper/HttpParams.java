package oneclick.yonclick.Helper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.loopj.android.http.RequestParams;

public class HttpParams extends RequestParams {

    public static RequestParams params;

    public static RequestParams init(Activity a) {

        if (params == null) {
            params = new RequestParams();

        }

        params.put("Content-Type", "application/json");
        params.put("apiKey", "8484884774837498");
        params.put("deviceId", getIdDev(a));
        params.put("deviceType", "2");

        return params;

    }

    public static String getIdDev(Activity a) {

        TelephonyManager tm = (TelephonyManager) a.getSystemService(Context.TELEPHONY_SERVICE);
        // get IMEI
        @SuppressLint("MissingPermission")
        String imei = tm.getDeviceId();
        return  imei;
/*


        String androidId = Settings.Secure.getString(
                a.getContentResolver(), Settings.Secure.ANDROID_ID);
        return  androidId;*/
    }
}
