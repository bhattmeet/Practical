package com.meet.owlutilities.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

import com.meet.owlutilities.callbacks.DialogButtonClickListener;

/**
 * @AutherBy Dhaval Jivani
 */

public final class AlertDialogHelper {

    public static void showDialog(Context context,
                                  String dialogTitle, String dialogMessage, String textPositiveButton,
                                  String textNegativeButton, boolean isCancelable,
                                  final DialogButtonClickListener buttonClickListener,
                                  final int dialogIdentifier) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        if (!StringHelper.isEmpty(dialogTitle)) {
            alertDialogBuilder.setTitle(dialogTitle);
        }
        alertDialogBuilder.setMessage(dialogMessage);
        if (isCancelable) {
            alertDialogBuilder.setCancelable(true);
        } else {
            alertDialogBuilder.setCancelable(false);
        }


        alertDialogBuilder.setPositiveButton(textPositiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id1) {
                if (buttonClickListener == null) {
                    dialog.dismiss();
                } else {
                    buttonClickListener.onPositiveButtonClicked(dialogIdentifier);
                }
            }
        });

        if (textNegativeButton != null) {
            alertDialogBuilder.setNegativeButton(textNegativeButton, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id1) {
                    if (buttonClickListener == null) {
                        dialog.dismiss();
                    } else {
                        buttonClickListener.onNegativeButtonClicked(dialogIdentifier);
                    }
                }
            });
        }

        AlertDialog alertDialog = alertDialogBuilder.create();
        //	alertDialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;

        if (!((Activity) context).isFinishing()) {
            alertDialog.show();
        }
    }

}
