package com.micromerger.ssms.location;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.micromerger.ssms.R;
import com.micromerger.ssms.printreport.beans.ReportResponse;
import com.micromerger.ssms.utils.BasePreferenceHelper;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class UserLocation extends Fragment {
    Button btn_update_loc;
    String lat;
    String lng;
    View mView;
    BasePreferenceHelper preferenceHelper;
    TextView tv_lat;
    TextView tv_long;
    TextView tv_note;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.frag_user_location, (ViewGroup) null);
        this.preferenceHelper = new BasePreferenceHelper(getActivity());
        this.tv_lat = (TextView) this.mView.findViewById(R.id.tv_lat);
        this.tv_long = (TextView) this.mView.findViewById(R.id.tv_long);
        this.tv_note = (TextView) this.mView.findViewById(R.id.tv_note);
        Button button = (Button) this.mView.findViewById(R.id.btn_update_loc);
        this.btn_update_loc = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.location.UserLocation.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                UserLocation.this.getMyLocation();
            }
        });
        if (CommonObjects.userObj.latitude != null && CommonObjects.userObj.latitude != "") {
            this.tv_lat.setText(CommonObjects.userObj.latitude);
            this.tv_note.setVisibility(0);
            this.btn_update_loc.setVisibility(8);
        } else {
            this.tv_note.setVisibility(8);
            this.btn_update_loc.setVisibility(0);
        }
        if (CommonObjects.userObj.longitude != null && CommonObjects.userObj.longitude != "") {
            this.tv_long.setText(CommonObjects.userObj.longitude);
        }
        return this.mView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMyLocation() throws JSONException {
        if (!CommonObjects.tracker.canGetLocation()) {
            CommonObjects.tracker.showSettingsAlert();
            return;
        }
        if (CommonObjects.tracker.getLatitude() != 0.0d && CommonObjects.tracker.getLongitude() != 0.0d) {
            this.lat = String.valueOf(CommonObjects.tracker.getLatitude());
            this.lng = String.valueOf(CommonObjects.tracker.getLongitude());
            updateMyLocation();
            return;
        }
        DialogCustom.showError(getActivity(), "No coordinates found.");
    }

    private void updateMyLocation() throws JSONException {
        if (CommonActions.isConnected(getContext())) {
            final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(getActivity());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("User_ID", CommonObjects.userObj.userId);
                jSONObject.put(Constant.Latitude, this.lat);
                jSONObject.put(Constant.Longitude, this.lng);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Users/UpdateUserCoordinates").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "Get Report").setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(ReportResponse.class, new ParsedRequestListener<ReportResponse>() { // from class: com.micromerger.ssms.location.UserLocation.2
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(ReportResponse response) {
                    if (progressDialogCreateProgressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    if (response.getStatus().booleanValue()) {
                        UserLocation.this.btn_update_loc.setVisibility(8);
                        UserLocation.this.tv_note.setVisibility(0);
                        UserLocation.this.tv_lat.setText(UserLocation.this.lat);
                        UserLocation.this.tv_long.setText(UserLocation.this.lng);
                        CommonObjects.userObj.latitude = UserLocation.this.lat;
                        CommonObjects.userObj.longitude = UserLocation.this.lng;
                        UserLocation.this.preferenceHelper.putUser(CommonObjects.userObj);
                        return;
                    }
                    DialogCustom.showError(UserLocation.this.getActivity(), response.getMessage());
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(ANError anError) {
                    if (progressDialogCreateProgressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    DialogCustom.showError(UserLocation.this.getActivity(), anError.getErrorDetail());
                }
            });
            return;
        }
        DialogCustom.showError(getActivity(), "No Internet Connection");
    }
}
