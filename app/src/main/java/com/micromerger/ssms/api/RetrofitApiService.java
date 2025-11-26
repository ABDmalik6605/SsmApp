package com.micromerger.ssms.api;

import com.micromerger.ssms.staffpresence.EmployeeRecordsResponse;
import com.micromerger.ssms.startmonitoring.bean.SubmitEndMonitoringMultipartResponse;
import com.micromerger.ssms.utils.WebConstant;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/* loaded from: classes2.dex */
public interface RetrofitApiService {
    @POST(WebConstant.AddEmployeeRecord)
    @Multipart
    Call<EmployeeRecordsResponse> addEmployeeRecord(@Part("employee_record_update_json") RequestBody requestBody, @Part List<MultipartBody.Part> files);

    @POST("MobileApp/SubmitMonitoring")
    @Multipart
    Call<SubmitEndMonitoringMultipartResponse> submitEndMonitoringMultipart(@Part("monitoring_json") RequestBody requestBody, @Part List<MultipartBody.Part> files);
}
