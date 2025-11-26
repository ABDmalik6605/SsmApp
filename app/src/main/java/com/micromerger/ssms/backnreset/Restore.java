package com.micromerger.ssms.backnreset;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.micromerger.ssms.R;
import com.micromerger.ssms.backnreset.beans.RestoreBackupResponse;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.bean.AllSchoolsResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import com.mindorks.paracamera.Camera;
import com.mindorks.paracamera.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import net.sqlcipher.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Restore extends BaseFragment {
    private static final String TAG = "com.micromerger.ssms.backnreset.Restore";
    Button btn_restore_backup;
    View mView;
    TextView tv_lastbackup;
    TextView tv_sizevalue;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_restorebkup, (ViewGroup) null);
        this.mView = viewInflate;
        this.tv_lastbackup = (TextView) viewInflate.findViewById(R.id.tv_lastbackup);
        if (this.preferenceHelper.getBackupDate().length() > 0) {
            this.tv_lastbackup.setText(this.preferenceHelper.getBackupDate().substring(0, this.preferenceHelper.getBackupDate().indexOf(ExifInterface.GPS_DIRECTION_TRUE)));
        } else {
            this.tv_lastbackup.setText("");
        }
        TextView textView = (TextView) this.mView.findViewById(R.id.tv_sizevalue);
        this.tv_sizevalue = textView;
        textView.setText(this.preferenceHelper.getBackupSize());
        Button button = (Button) this.mView.findViewById(R.id.btn_restore_backup);
        this.btn_restore_backup = button;
        button.setOnClickListener(new AnonymousClass1());
        return this.mView;
    }

    /* renamed from: com.micromerger.ssms.backnreset.Restore$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CommonActions.isConnected(Restore.this.getActivity())) {
                new SweetAlertDialog(Restore.this.getActivity(), 3).setTitleText("Are you sure?").setContentText("You will lost the current data. Do you want to restore backup?").setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Restore.1.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        Restore.this.loadingStarted();
                        new Thread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Restore.1.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Restore.this.restoreBackup();
                            }
                        }).start();
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Restore.1.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                }).show();
            } else {
                DialogCustom.showError(Restore.this.getActivity(), "No internet connection found.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreBackup() {
        AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/DataRestore/RestoreBackup?userid=" + CommonObjects.userObj.userId).setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "RestoreBackup").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE).setPriority(Priority.MEDIUM).build().getAsObject(RestoreBackupResponse.class, new ParsedRequestListener<RestoreBackupResponse>() { // from class: com.micromerger.ssms.backnreset.Restore.2
            @Override // com.androidnetworking.interfaces.ParsedRequestListener
            public void onResponse(final RestoreBackupResponse response) throws IOException, SQLException, IllegalArgumentException {
                Log.e("response", response.getMessage());
                Restore.this.loadingFinished();
                if (response.getStatus().booleanValue()) {
                    Log.e("response", String.valueOf(response.getData().getDataUrl()));
                    if (response.getData().getDataUrl() != null) {
                        final String str = new String(Base64.decode(String.valueOf(response.getData().getDataUrl()), 0));
                        Log.e("decodedData", CommonActions.formatFileSize(str.getBytes().length) + " " + str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.has("schools") && CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name())) {
                                AllSchoolsResponse allSchoolsResponse = (AllSchoolsResponse) new Gson().fromJson(jSONObject.get("schools").toString(), new TypeToken<AllSchoolsResponse>() { // from class: com.micromerger.ssms.backnreset.Restore.2.1
                                }.getType());
                                Restore.this.preferenceHelper.putAllSchools(allSchoolsResponse);
                                Restore.this.preferenceHelper.setCheckDate(allSchoolsResponse.getDatetime());
                                CommonObjects.schools = allSchoolsResponse.getData();
                            }
                            CommonObjects.employeeData = (List) new Gson().fromJson(jSONObject.get("monitoring_data").toString(), new TypeToken<List<EmployeeData>>() { // from class: com.micromerger.ssms.backnreset.Restore.2.2
                            }.getType());
                            Log.e("size", CommonObjects.employeeData.size() + "_");
                            for (EmployeeData employeeData : CommonObjects.employeeData) {
                                for (int i = 0; i < employeeData.getMonitoring().get(0).getKRAData().size(); i++) {
                                    KRAData kRAData = employeeData.getMonitoring().get(0).getKRAData().get(i);
                                    if (kRAData.getKRAName().contains(Constant._Image) && ((String) kRAData.getDataValue()) != null && !((String) kRAData.getDataValue()).equals("")) {
                                        Log.e("image", (String) kRAData.getDataValue());
                                        Bitmap bitmapConvertToBitmap = CommonActions.convertToBitmap((String) kRAData.getDataValue());
                                        if (bitmapConvertToBitmap != null) {
                                            File fileCreateImageFile = Utils.createImageFile(Restore.this.getActivity(), "SSMS", "image_" + System.currentTimeMillis(), Camera.IMAGE_JPEG);
                                            if (fileCreateImageFile != null) {
                                                try {
                                                    FileOutputStream fileOutputStream = new FileOutputStream(fileCreateImageFile);
                                                    bitmapConvertToBitmap.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
                                                    fileOutputStream.close();
                                                    employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue(fileCreateImageFile.getAbsolutePath());
                                                } catch (FileNotFoundException e) {
                                                    Log.d(Restore.TAG, "File not found: " + e.getMessage());
                                                    employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue("");
                                                } catch (IOException e2) {
                                                    Log.d(Restore.TAG, "Error accessing file: " + e2.getMessage());
                                                    employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue("");
                                                }
                                            } else {
                                                employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue("");
                                                throw new NullPointerException("Image file could not be created");
                                            }
                                        } else {
                                            employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue("");
                                        }
                                    }
                                }
                            }
                            CommonActions.getDbHandler(Restore.this.getActivity()).deleteSchoolData();
                            CommonActions.getDbHandler(Restore.this.getActivity()).addSchoolData(CommonObjects.employeeData);
                            Restore.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Restore.2.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    Restore.this.preferenceHelper.setBackupDate(response.getData().getBackupDate());
                                    Restore.this.preferenceHelper.setBackupSize(CommonActions.formatFileSize(str.getBytes().length));
                                    Restore.this.tv_lastbackup.setText(response.getData().getBackupDate());
                                    Restore.this.tv_sizevalue.setText(CommonActions.formatFileSize(str.getBytes().length));
                                }
                            });
                            DialogCustom.showSuccessDialog(Restore.this.getActivityContext(), "Done", response.getMessage());
                        } catch (JsonSyntaxException e3) {
                            e3.printStackTrace();
                            DialogCustom.showError(Restore.this.getActivity(), e3.getMessage());
                        } catch (JSONException e4) {
                            e4.printStackTrace();
                            DialogCustom.showError(Restore.this.getActivity(), e4.getMessage());
                        }
                    } else {
                        DialogCustom.showError(Restore.this.getActivity(), "Backup could not be restored.");
                    }
                } else {
                    DialogCustom.showError(Restore.this.getActivity(), response.getMessage());
                }
                if (Restore.this.getActivity() != null) {
                    Restore.this.fm.popBackStack();
                }
            }

            @Override // com.androidnetworking.interfaces.ParsedRequestListener
            public void onError(ANError anError) {
                Restore.this.loadingFinished();
                DialogCustom.showError(Restore.this.getActivityContext(), anError.getErrorDetail());
                Restore.this.fm.popBackStack();
            }
        });
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
