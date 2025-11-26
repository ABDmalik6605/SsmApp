package com.micromerger.ssms.settings;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.micromerger.ssms.R;
import com.micromerger.ssms.settings.beans.ChangePasswordResponse;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ChangePassword extends Fragment {
    Button btn_done;
    EditText et_comfirm_pw;
    EditText et_new_pw;
    EditText et_old_pw;
    View mView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_change_password, (ViewGroup) null);
        this.mView = viewInflate;
        this.et_old_pw = (EditText) viewInflate.findViewById(R.id.et_old_pw);
        this.et_new_pw = (EditText) this.mView.findViewById(R.id.et_new_pw);
        this.et_comfirm_pw = (EditText) this.mView.findViewById(R.id.et_comfirm_pw);
        Button button = (Button) this.mView.findViewById(R.id.btn_done);
        this.btn_done = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.settings.ChangePassword.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                ChangePassword.this.checkValidation();
            }
        });
        return this.mView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkValidation() throws JSONException {
        if (this.et_old_pw.getText().toString().length() > 0 && this.et_new_pw.getText().toString().length() > 0 && this.et_comfirm_pw.getText().toString().length() > 0) {
            if (this.et_new_pw.getText().toString().equals(this.et_comfirm_pw.getText().toString())) {
                changePassword();
                return;
            } else {
                DialogCustom.showError(getActivity(), "New and confirm passwords do not match.");
                return;
            }
        }
        DialogCustom.showError(getActivity(), "Please fill all the fields");
    }

    private void changePassword() throws JSONException {
        if (CommonActions.isConnected(getContext())) {
            final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(getActivity());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("User_ID", CommonObjects.userObj.userId);
                jSONObject.put("OldPassword", this.et_old_pw.getText().toString());
                jSONObject.put("NewPassword", this.et_new_pw.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("changePasswordRequest", jSONObject.toString());
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/ChangePassword").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "Change Password").setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(ChangePasswordResponse.class, new ParsedRequestListener<ChangePasswordResponse>() { // from class: com.micromerger.ssms.settings.ChangePassword.2
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(ChangePasswordResponse response) {
                    if (progressDialogCreateProgressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    if (response.getStatus().booleanValue()) {
                        DialogCustom.showSuccessDialog(ChangePassword.this.getActivity(), "Success", response.getMessage(), ChangePassword.this.getActivity().getSupportFragmentManager());
                    } else {
                        DialogCustom.showError(ChangePassword.this.getActivity(), response.getMessage());
                    }
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(ANError anError) {
                    if (progressDialogCreateProgressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    DialogCustom.showError(ChangePassword.this.getActivity(), anError.getErrorDetail());
                }
            });
            return;
        }
        DialogCustom.showError(getActivity(), "No Internet Connection");
    }
}
