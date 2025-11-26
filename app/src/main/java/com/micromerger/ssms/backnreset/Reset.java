package com.micromerger.ssms.backnreset;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.utils.BasePreferenceHelper;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.widgets.DialogCustom;

/* loaded from: classes2.dex */
public class Reset extends BaseFragment {
    Button btn_submit;
    EditText et_code;
    View mView;
    BasePreferenceHelper preferenceHelper;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.layout_reset, (ViewGroup) null);
        this.preferenceHelper = new BasePreferenceHelper(getActivity());
        this.et_code = (EditText) this.mView.findViewById(R.id.et_code);
        Button button = (Button) this.mView.findViewById(R.id.btn_submit);
        this.btn_submit = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.backnreset.Reset.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new SweetAlertDialog(Reset.this.getContext(), 3).setTitleText("Are you sure?").setContentText("Do you want to reset data?").setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Reset.1.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        new asyncResetData().execute(new Void[0]);
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Reset.1.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                }).show();
            }
        });
        return this.mView;
    }

    private class asyncResetData extends AsyncTask<Void, Void, Void> {
        private SweetAlertDialog progressdialog;

        private asyncResetData() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Reset.this.getActivity(), 5);
            this.progressdialog = sweetAlertDialog;
            sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            this.progressdialog.setTitleText("Please wait");
            this.progressdialog.setContentText("Resetting Data...");
            this.progressdialog.setCancelable(false);
            this.progressdialog.show();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... strings) {
            try {
                CommonActions.getDbHandler(Reset.this.getActivity()).resetSchoolData();
                CommonObjects.employeeData = CommonActions.getDbHandler(Reset.this.getActivity()).getAllSchoolsData();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void aVoid) {
            super.onPostExecute((asyncResetData) aVoid);
            SweetAlertDialog sweetAlertDialog = this.progressdialog;
            if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
                this.progressdialog.dismiss();
            }
            DialogCustom.showSuccessDialog(Reset.this.getActivity(), "Done", "Data has been reset.");
            if (Reset.this.getActivity() != null) {
                Reset.this.fm.popBackStack();
            }
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }
}
