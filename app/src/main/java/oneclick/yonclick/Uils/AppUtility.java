package oneclick.yonclick.Uils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import oneclick.yonclick.R;

public class AppUtility {
    private static AppUtility mAppUtility = null;

    // create single instance
    public static AppUtility getInstance() {
        if (mAppUtility == null) {
            mAppUtility = new AppUtility();
        }
        return mAppUtility;
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() !=
                null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void noInternetWarning(View view, final Context context) {
       /* if (!isNetworkAvailable(context)) {
            Snackbar snackbar = Snackbar.make(view, context.getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(context.getString(R.string.connect), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            snackbar.show();
        }*/
    }

}
