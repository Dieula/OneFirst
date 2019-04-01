package oneclick.yonclick.Uils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import oneclick.yonclick.R;

public class DialogUtils {

    Context mContext;
    public static void showDialogPrompt(Activity activity, String title, String message, String positiveButtonText, String negativeButtonText, boolean isCancellable, final DialogActionListener dialogActionListener) {
        showDialog(activity, title, message, positiveButtonText, negativeButtonText, isCancellable, dialogActionListener);
    }


    public static void showMessageDialog(Activity activity, String title, String message, String negativeButtonText) {
        showDialog(activity, title, message, null, negativeButtonText, true, null);
    }


    public static void showActionDialog(Activity activity, String title, String message, String positiveButtonText, DialogActionListener dialogActionListener) {
        showDialog(activity, title, message, positiveButtonText, null, false, dialogActionListener);
    }


    private static void showDialog(Activity activity, String title, String message, String positiveButtonText, String negativeButtonText, boolean isCancellable, final DialogActionListener dialogActionListener) {
        if(activity != null) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.DialogTheme);
            if (title != null) {
                alertDialogBuilder.setTitle(title);
            }
            alertDialogBuilder.setMessage(message);
            alertDialogBuilder.setCancelable(isCancellable);

            if (dialogActionListener != null) {
                alertDialogBuilder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        dialogActionListener.onPositiveClick();
                    }
                });
            }

            if (dialogActionListener != null) {
                alertDialogBuilder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
            }

            alertDialogBuilder.create().show();
        }
    }

    public static ProgressDialog showProgressDialog(Activity activity, String message, boolean isCancellable) {
        ProgressDialog progressDialog = new ProgressDialog(activity, R.style.DialogTheme);
        progressDialog.setCancelable(isCancellable);
        if(message != null) {
            progressDialog.setMessage(message);
        }
        progressDialog.show();
        return progressDialog;
    }


    public static void dismissProgressDialog(ProgressDialog progressDialog) {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public interface DialogActionListener {
        public void onPositiveClick();
    }
}
