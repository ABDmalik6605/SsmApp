package com.micromerger.ssms.staffpresence;

import android.content.Context;
import com.google.gson.Gson;
import com.micromerger.ssms.addemployee.EmployeeDbRecord;
import com.micromerger.ssms.addemployee.bean.EmployeeUpdateRecord;
import com.micromerger.ssms.addemployee.bean.EmployeeUpdateRecordList;
import com.micromerger.ssms.api.RetrofitApiService;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.DatabaseHandler;
import com.micromerger.ssms.utils.WebConstant;
import com.micromerger.ssms.utils.util;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* loaded from: classes2.dex */
public class StaffPresenceOperations {
    DatabaseHandler databaseHandler;

    public interface EmployeeRecordsInterface {
        void emptyListCallback();

        void exceptionCallback(Exception e);

        void failureCallback(Response<EmployeeRecordsResponse> employeeRecordsResponse);

        void finishLoading();

        void onErrorCallback(String error);

        void successCallback(EmployeeRecordsResponse employeeRecordsResponse);
    }

    public StaffPresenceOperations(Context context) {
        this.databaseHandler = new DatabaseHandler(context);
    }

    public List<EmployeeDbRecord> getEmployeeDbRecords() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.databaseHandler.getEmployeeRecords(false);
        } catch (Exception e) {
            util.logException(e);
            return arrayList;
        }
    }

    public List<EmployeeDbRecord> getNotSyncedEmployeeDbRecords() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.databaseHandler.getEmployeeRecords(true);
        } catch (Exception e) {
            util.logException(e);
            return arrayList;
        }
    }

    public void deleteEmployeeRecord(Integer recordId) {
        if (recordId != null) {
            try {
                this.databaseHandler.deleteEmployeeRecord(recordId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void postEmployeeRecords(final EmployeeRecordsInterface employeeRecordsInterface) {
        try {
            List<EmployeeDbRecord> notSyncedEmployeeDbRecords = getNotSyncedEmployeeDbRecords();
            try {
                if (notSyncedEmployeeDbRecords == null || notSyncedEmployeeDbRecords.isEmpty()) {
                    employeeRecordsInterface.emptyListCallback();
                } else {
                    EmployeeUpdateRecordList employeeUpdateRecordList = new EmployeeUpdateRecordList();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (EmployeeDbRecord employeeDbRecord : notSyncedEmployeeDbRecords) {
                        EmployeeUpdateRecord addEmployeeRecord = employeeDbRecord.getAddEmployeeRecord();
                        if (employeeDbRecord.getEmployeePicturePath() != null && !employeeDbRecord.getEmployeePicturePath().isEmpty()) {
                            String string = UUID.randomUUID().toString();
                            File file = new File(employeeDbRecord.getEmployeePicturePath());
                            if (file.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string, file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file)));
                                addEmployeeRecord.setEmployeePicture(string);
                            }
                        }
                        if (employeeDbRecord.getCnicPicturePath() != null && !employeeDbRecord.getCnicPicturePath().isEmpty()) {
                            String string2 = UUID.randomUUID().toString();
                            File file2 = new File(employeeDbRecord.getCnicPicturePath());
                            if (file2.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string2, file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2)));
                                addEmployeeRecord.setcNICPicture(string2);
                            }
                        }
                        if (employeeDbRecord.getGovtLetterPicturePath() != null && !employeeDbRecord.getGovtLetterPicturePath().isEmpty()) {
                            String string3 = UUID.randomUUID().toString();
                            File file3 = new File(employeeDbRecord.getGovtLetterPicturePath());
                            if (file3.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string3, file3.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file3)));
                                addEmployeeRecord.setGovtLetterPicture(string3);
                            }
                        }
                        if (employeeDbRecord.getOtherPicturePath() != null && !employeeDbRecord.getOtherPicturePath().isEmpty()) {
                            String string4 = UUID.randomUUID().toString();
                            File file4 = new File(employeeDbRecord.getOtherPicturePath());
                            if (file4.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string4, file4.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file4)));
                                addEmployeeRecord.setOtherPicture(string4);
                            }
                        }
                        if (employeeDbRecord.getPostingOrderPicturePath() != null && !employeeDbRecord.getPostingOrderPicturePath().isEmpty()) {
                            String string5 = UUID.randomUUID().toString();
                            File file5 = new File(employeeDbRecord.getPostingOrderPicturePath());
                            if (file5.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string5, file5.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file5)));
                                addEmployeeRecord.setPostingPicture(string5);
                            }
                        }
                        if (employeeDbRecord.getReasonOrderPicturePath() != null && !employeeDbRecord.getReasonOrderPicturePath().isEmpty()) {
                            String string6 = UUID.randomUUID().toString();
                            File file6 = new File(employeeDbRecord.getReasonOrderPicturePath());
                            if (file6.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string6, file6.getName(), RequestBody.create(MediaType.parse(file6.getName().endsWith(".pdf") ? "application/pdf" : "image/jpeg"), file6)));
                                int iIntValue = addEmployeeRecord.getReasonForUpdate().intValue();
                                if (iIntValue == 1) {
                                    addEmployeeRecord.setAppointmentOrderPicture(string6);
                                } else if (iIntValue == 2) {
                                    addEmployeeRecord.setTransferOrderPicture(string6);
                                } else if (iIntValue == 4) {
                                    addEmployeeRecord.setPromotionOrderPicture(string6);
                                } else if (iIntValue == 6) {
                                    addEmployeeRecord.setAuthorityOrderPicture(string6);
                                } else if (iIntValue == 7) {
                                    addEmployeeRecord.setObituaryOrderPicture(string6);
                                } else if (iIntValue == 8) {
                                    addEmployeeRecord.setRetirementOrderPicture(string6);
                                }
                            }
                        }
                        if (employeeDbRecord.getEmployeePayslipPicturePath() != null && !employeeDbRecord.getEmployeePayslipPicturePath().isEmpty()) {
                            String string7 = UUID.randomUUID().toString();
                            File file7 = new File(employeeDbRecord.getEmployeePayslipPicturePath());
                            if (file7.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string7, file7.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file7)));
                                addEmployeeRecord.setEmployeePayslipPicture(string7);
                            }
                        }
                        if (employeeDbRecord.getOfferOrderPicturePath() != null && !employeeDbRecord.getOfferOrderPicturePath().isEmpty()) {
                            String string8 = UUID.randomUUID().toString();
                            File file8 = new File(employeeDbRecord.getOfferOrderPicturePath());
                            if (file8.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string8, file8.getName(), RequestBody.create(MediaType.parse(file8.getName().endsWith(".pdf") ? "application/pdf" : "image/jpeg"), file8)));
                                addEmployeeRecord.setOfferOrderPicture(string8);
                            }
                        }
                        if (employeeDbRecord.getJoiningReportPicturePath() != null && !employeeDbRecord.getJoiningReportPicturePath().isEmpty()) {
                            String string9 = UUID.randomUUID().toString();
                            File file9 = new File(employeeDbRecord.getJoiningReportPicturePath());
                            if (file9.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string9, file9.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file9)));
                                addEmployeeRecord.setJoiningReportPicture(string9);
                            }
                        }
                        if (employeeDbRecord.getAttendanceRegisterPicturePath() != null && !employeeDbRecord.getAttendanceRegisterPicturePath().isEmpty()) {
                            String string10 = UUID.randomUUID().toString();
                            File file10 = new File(employeeDbRecord.getAttendanceRegisterPicturePath());
                            if (file10.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string10, file10.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file10)));
                                addEmployeeRecord.setAttendanceRegisterPicture(string10);
                            }
                        }
                        if (employeeDbRecord.getInformationLetterPicturePath() != null && !employeeDbRecord.getInformationLetterPicturePath().isEmpty()) {
                            String string11 = UUID.randomUUID().toString();
                            File file11 = new File(employeeDbRecord.getInformationLetterPicturePath());
                            if (file11.exists()) {
                                arrayList2.add(MultipartBody.Part.createFormData(string11, file11.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file11)));
                                int iIntValue2 = addEmployeeRecord.getReasonForUpdate().intValue();
                                if (iIntValue2 == 7) {
                                    addEmployeeRecord.setExpiredInformationLetterPicture(string11);
                                } else if (iIntValue2 == 8) {
                                    addEmployeeRecord.setRetirementInformationLetterPicture(string11);
                                }
                            }
                        }
                        addEmployeeRecord.setEmployeeRecordID(null);
                        arrayList.add(addEmployeeRecord);
                    }
                    employeeUpdateRecordList.setEmployeeUpdateRecordList(arrayList);
                    ((RetrofitApiService) new Retrofit.Builder().client(CommonActions.getOkHttpClient()).baseUrl(WebConstant.baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitApiService.class)).addEmployeeRecord(RequestBody.create(MultipartBody.FORM, new Gson().toJson(employeeUpdateRecordList, EmployeeUpdateRecordList.class)), arrayList2).enqueue(new Callback<EmployeeRecordsResponse>() { // from class: com.micromerger.ssms.staffpresence.StaffPresenceOperations.1
                        @Override // retrofit2.Callback
                        public void onResponse(Call<EmployeeRecordsResponse> call, Response<EmployeeRecordsResponse> response) {
                            employeeRecordsInterface.finishLoading();
                            if (response.isSuccessful()) {
                                employeeRecordsInterface.successCallback(response.body());
                            } else {
                                employeeRecordsInterface.failureCallback(response);
                            }
                        }

                        @Override // retrofit2.Callback
                        public void onFailure(Call<EmployeeRecordsResponse> call, Throwable t) {
                            employeeRecordsInterface.finishLoading();
                            employeeRecordsInterface.onErrorCallback(t.getMessage());
                        }
                    });
                }
            } catch (Exception e) {
                e = e;
                employeeRecordsInterface.exceptionCallback(e);
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void updateEmployeeRecords(List<EmployeeDbRecord> employeeDbRecords) {
        Iterator<EmployeeDbRecord> it = employeeDbRecords.iterator();
        while (it.hasNext()) {
            this.databaseHandler.updateEmployeeRecord(it.next());
        }
    }
}
