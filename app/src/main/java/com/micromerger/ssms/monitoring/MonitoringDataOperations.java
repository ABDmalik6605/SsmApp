package com.micromerger.ssms.monitoring;

import com.google.gson.Gson;
import com.micromerger.ssms.api.RetrofitApiService;
import com.micromerger.ssms.startmonitoring.bean.SubmitEndMonitoringMultipartResponse;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.WebConstant;
import com.micromerger.ssms.utils.util;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* loaded from: classes2.dex */
public class MonitoringDataOperations {
    ArrayList<Integer> kraIds = new ArrayList<>(Arrays.asList(6, 28, 40, 41, 74, 122, 134, 146, 161, 173, 254, 278, 279, 280, 281, 282, 283, 284, 285, 286, 291, 292, 293, 294, 297, 328, 351));

    public interface SubmitEndMonitoring {
        void exceptionCallback(Exception e);

        void failureCallback(Response<SubmitEndMonitoringMultipartResponse> response);

        void finishLoading();

        void onErrorCallback(String error);

        void progressStatus();

        void successCallback(SubmitEndMonitoringMultipartResponse response);
    }

    public ArrayList<KRAData> getImageKRAs(EmployeeData employeeData) throws Exception {
        ArrayList<KRAData> arrayList = new ArrayList<>();
        try {
            for (KRAData kRAData : employeeData.getMonitoring().get(0).getKRAData()) {
                if (this.kraIds.contains(kRAData.getKraid()) && kRAData.getDataValue() != null && !String.valueOf(kRAData.getDataValue()).isEmpty()) {
                    arrayList.add(kRAData);
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void postMonitoringData(EmployeeData employeeData, final SubmitEndMonitoring submitEndMonitoringInterface, final boolean single) throws JSONException {
        try {
            if (CommonObjects.userObj != null && CommonObjects.monitoring != null) {
                util.logException(new Exception("Before Sync call API: User ID: " + CommonObjects.userObj.userId + " Monitoring ID: " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()));
            }
            ArrayList<KRAData> imageKRAs = getImageKRAs(employeeData);
            new JSONObject().put("monitoring_json", new JSONObject(new Gson().toJson(employeeData, EmployeeData.class)));
            ArrayList arrayList = new ArrayList();
            Iterator<KRAData> it = imageKRAs.iterator();
            while (it.hasNext()) {
                KRAData next = it.next();
                try {
                    File file = new File("" + next.getDataValue());
                    arrayList.add(MultipartBody.Part.createFormData(next.getKraid() + "_" + next.getKRAName(), next.getKraid() + "_" + next.getKRAName(), RequestBody.create(MediaType.parse("image/*"), file)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ((RetrofitApiService) new Retrofit.Builder().client(CommonActions.getOkHttpClient()).baseUrl(WebConstant.baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitApiService.class)).submitEndMonitoringMultipart(RequestBody.create(MultipartBody.FORM, new Gson().toJson(employeeData, EmployeeData.class)), arrayList).enqueue(new Callback<SubmitEndMonitoringMultipartResponse>() { // from class: com.micromerger.ssms.monitoring.MonitoringDataOperations.1
                @Override // retrofit2.Callback
                public void onResponse(Call<SubmitEndMonitoringMultipartResponse> responseCall, Response<SubmitEndMonitoringMultipartResponse> response) {
                    if (single) {
                        submitEndMonitoringInterface.finishLoading();
                    }
                    if (response.isSuccessful()) {
                        submitEndMonitoringInterface.successCallback(response.body());
                    } else {
                        submitEndMonitoringInterface.failureCallback(response);
                    }
                    submitEndMonitoringInterface.progressStatus();
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<SubmitEndMonitoringMultipartResponse> endMonitoringResponseCall, Throwable t) {
                    if (CommonObjects.userObj != null && CommonObjects.monitoring != null) {
                        util.logException(new Exception("Sync API Failure: User ID: " + CommonObjects.userObj.userId + " Monitoring ID: " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID() + " Failure: " + t));
                    }
                    if (single) {
                        submitEndMonitoringInterface.finishLoading();
                    }
                    submitEndMonitoringInterface.onErrorCallback(t.getMessage());
                    util.logException(new Exception(t.toString()));
                }
            });
        } catch (Exception e2) {
            submitEndMonitoringInterface.exceptionCallback(e2);
            if (CommonObjects.userObj == null || CommonObjects.monitoring == null) {
                return;
            }
            util.logException(new Exception("Sync API Exception: User ID: " + CommonObjects.userObj.userId + " Monitoring ID: " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID() + " Exception : " + e2));
        }
    }
}
