package com.micromerger.ssms.utils.widgets;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.FragmentManager;
import cn.pedant.SweetAlert.SweetAlertDialog;

/* loaded from: classes2.dex */
public class DialogCustom {
    public static void showError(final Context activityContext, final String error) {
        ((Activity) activityContext).runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.1
            @Override // java.lang.Runnable
            public void run() {
                new SweetAlertDialog(activityContext, 1).setTitleText("Error").setContentText(error).setConfirmClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE).show();
            }
        });
    }

    public static void showCancelWarning(final Context activityContext, final FragmentManager fm) {
        ((Activity) activityContext).runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.2
            @Override // java.lang.Runnable
            public void run() {
                new SweetAlertDialog(activityContext, 3).setTitleText("Are you sure?").setContentText("Updated changes will not be saved.").setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.2.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        fm.popBackStack();
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.2.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                }).show();
            }
        });
    }

    public static void showCancelWarning(final Context activityContext, final String content, final FragmentManager fm) {
        ((Activity) activityContext).runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.3
            @Override // java.lang.Runnable
            public void run() {
                new SweetAlertDialog(activityContext, 3).setTitleText("Are you sure?").setContentText(content).setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.3.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        fm.popBackStack();
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.3.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                }).show();
            }
        });
    }

    public static void showSuccessDialog(final Context activityContext, final String title, final String content) {
        ((Activity) activityContext).runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.4
            @Override // java.lang.Runnable
            public void run() {
                new SweetAlertDialog(activityContext, 2).setTitleText(title).setContentText(content).setConfirmClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE).show();
            }
        });
    }

    public static void showSuccessDialog(final Context activityContext, final String title, final String content, final FragmentManager fm) {
        ((Activity) activityContext).runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.5
            @Override // java.lang.Runnable
            public void run() {
                new SweetAlertDialog(activityContext, 2).setTitleText(title).setContentText(content).setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.utils.widgets.DialogCustom.5.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        fm.popBackStack();
                    }
                }).show();
            }
        });
    }

    public static void showInfoDialog(final Context context, final String title, final String message) {
        ((Activity) context).runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.widgets.-$$Lambda$DialogCustom$Xmm7BgqlGm8XbCQowQKkV7RXls4
            @Override // java.lang.Runnable
            public final void run() {
                Context context2 = context;
                new SweetAlertDialog(context2, 3).setTitleText(title).setContentText(message).setConfirmText("OK").setConfirmClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE).show();
            }
        });
    }

    public static SweetAlertDialog attendanceSuccessDialog(final Context context, final String title, final String content) {
        return new SweetAlertDialog(context, 2).setTitleText(title).setContentText(content);
    }

    public static SweetAlertDialog attendanceErrorDialog(final Context context, final String title, final String error) {
        return new SweetAlertDialog(context, 1).setTitleText(title).setContentText(error);
    }
}
