package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.startmonitoring.adapters.MusterRollImagesRA;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class MusterRollImages extends BaseFragment implements View.OnClickListener, BaseFragment.CameraActivityCallBack {
    MusterRollImagesRA adapter;
    Button cancel_button;
    ImageView capturedImage;
    int imagesSize_atStart;
    LinearLayoutManager layoutManager;
    RecyclerView rv_capturedImages;
    Button save_button;
    TextView tv_noImages;
    ImageView uploadImage;
    View view;
    List<Bitmap> capturedImagesList = new ArrayList();
    List<String> bitmapPathList = new ArrayList();
    boolean isCapturing = false;
    String imageBaseUrl64 = "";
    int maxImages = 15;
    boolean isResumed = false;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.frag_muster_roll, (ViewGroup) null);
        this.bitmapPathList = new ArrayList();
        setCameraActivityCallBack(this);
        ImageView imageView = (ImageView) this.view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView;
        imageView.setOnClickListener(this);
        Button button = (Button) this.view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
        this.capturedImage = (ImageView) this.view.findViewById(R.id.capturedImage);
        this.tv_noImages = (TextView) this.view.findViewById(R.id.tv_noImages);
        this.rv_capturedImages = (RecyclerView) this.view.findViewById(R.id.rv_capturedImages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), 0, false);
        this.layoutManager = linearLayoutManager;
        this.rv_capturedImages.setLayoutManager(linearLayoutManager);
        MusterRollImagesRA musterRollImagesRA = new MusterRollImagesRA(this.capturedImagesList);
        this.adapter = musterRollImagesRA;
        this.rv_capturedImages.setAdapter(musterRollImagesRA);
        if (this.capturedImagesList.size() > 0) {
            this.rv_capturedImages.setVisibility(0);
            this.tv_noImages.setVisibility(8);
        }
        this.adapter.setOnItemClickListener(new MusterRollImagesRA.MyClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MusterRollImages.1
            @Override // com.micromerger.ssms.startmonitoring.adapters.MusterRollImagesRA.MyClickListener
            public void onItemClick(int position, View v) {
                int id2 = v.getId();
                if (id2 != R.id.deleteImage) {
                    if (id2 != R.id.image) {
                        return;
                    }
                    MusterRollImages.this.capturedImage.setImageBitmap(MusterRollImages.this.capturedImagesList.get(position));
                    return;
                }
                if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                    return;
                }
                MusterRollImages.this.bitmapPathList.remove(position);
                MusterRollImages.this.capturedImagesList.remove(position);
                MusterRollImages.this.adapter.updateBitmapList(MusterRollImages.this.capturedImagesList);
                if (MusterRollImages.this.capturedImagesList.size() == 0) {
                    MusterRollImages.this.capturedImage.setImageBitmap(null);
                    MusterRollImages.this.tv_noImages.setVisibility(0);
                    for (Map.Entry<Integer, KRAData> entry : CommonObjects.attendance_fields.entrySet()) {
                        KRAData value = entry.getValue();
                        if (value.getKRAName().contains(Constant.Muster_Roll_Image)) {
                            Integer key = entry.getKey();
                            value.setDataValue("");
                            CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
                        }
                    }
                }
            }
        });
        return this.view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id2 = v.getId();
        if (id2 == R.id.cancel_button) {
            onBackPressed();
            return;
        }
        if (id2 == R.id.save_button) {
            saveFieldsData();
            return;
        }
        if (id2 != R.id.uploadImage) {
            return;
        }
        if (this.bitmapPathList.size() < this.maxImages) {
            this.isCapturing = true;
            try {
                getPhotoFromCamera(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue() + "_Muster_Roll_Image_" + System.currentTimeMillis());
                return;
            } catch (Exception unused) {
                this.isCapturing = false;
                Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
                return;
            }
        }
        Toast.makeText(getContext(), "Not more than " + this.maxImages + " images allowed.", 0).show();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        if (this.capturedImagesList.size() < this.imagesSize_atStart || this.capturedImagesList.size() > this.imagesSize_atStart) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
            return true;
        }
        this.fm.popBackStack();
        return true;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isResumed) {
            return;
        }
        this.isResumed = true;
        if (this.isCapturing) {
            this.isCapturing = false;
            return;
        }
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.attendance_fields.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().contains(Constant.Muster_Roll_Image) && !((String) value.getDataValue()).equals("")) {
                this.imageBaseUrl64 = (String) value.getDataValue();
                Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBaseUrl64));
                if (bitmapDecodeFile != null) {
                    this.rv_capturedImages.setVisibility(0);
                    this.tv_noImages.setVisibility(8);
                    this.capturedImagesList.add(bitmapDecodeFile);
                    this.capturedImage.setImageBitmap(bitmapDecodeFile);
                    this.bitmapPathList.add((String) value.getDataValue());
                    this.adapter.updateBitmapList(this.capturedImagesList);
                } else {
                    this.rv_capturedImages.setVisibility(0);
                    this.tv_noImages.setVisibility(8);
                    byte[] bArrDecode = Base64.decode(this.imageBaseUrl64, 0);
                    Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
                    this.capturedImagesList.add(bitmapDecodeByteArray);
                    this.capturedImage.setImageBitmap(bitmapDecodeByteArray);
                    this.bitmapPathList.add((String) value.getDataValue());
                    this.adapter.updateBitmapList(this.capturedImagesList);
                }
            }
        }
        this.imagesSize_atStart = this.capturedImagesList.size();
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.uploadImage.setEnabled(false);
        this.uploadImage.setFocusable(false);
        this.uploadImage.setAlpha(0.5f);
        this.save_button.setEnabled(false);
        this.save_button.setAlpha(0.5f);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBack
    public void onCameraActivityResult(Bitmap bitmap, String bitmapPath) {
        if (bitmap != null) {
            this.rv_capturedImages.setVisibility(0);
        } else {
            this.tv_noImages.setVisibility(8);
        }
        if (bitmap != null) {
            if (this.capturedImagesList.size() < this.maxImages) {
                this.capturedImagesList.add(bitmap);
                this.capturedImage.setImageBitmap(bitmap);
                if (bitmapPath != null) {
                    this.bitmapPathList.add(bitmapPath);
                }
                this.adapter.updateBitmapList(this.capturedImagesList);
                return;
            }
            Toast.makeText(getActivity().getApplicationContext(), "Not more than " + this.maxImages + " images allowed.", 0).show();
        }
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MusterRollImages.2
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.attendance_fields.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().contains(Constant.Muster_Roll_Image)) {
                        Log.e("key", key + "");
                        if (MusterRollImages.this.bitmapPathList != null) {
                            if (value.getKRAName().equals(Constant.Muster_Roll_Image)) {
                                if (MusterRollImages.this.bitmapPathList.size() > 0) {
                                    value.setDataValue(MusterRollImages.this.bitmapPathList.get(0));
                                } else {
                                    value.setDataValue("");
                                }
                            }
                            for (int i = 1; i < MusterRollImages.this.maxImages; i++) {
                                if (value.getKRAName().equals(Constant.Muster_Roll_Image_ + i)) {
                                    if (MusterRollImages.this.bitmapPathList.size() > i) {
                                        value.setDataValue(MusterRollImages.this.bitmapPathList.get(i));
                                    } else {
                                        value.setDataValue("");
                                    }
                                }
                            }
                        }
                        CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
                    }
                }
                for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
                    if (CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                        CommonObjects.employeeData.set(i2, CommonObjects.monitoring);
                        CommonActions.getDbHandler(MusterRollImages.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i2).getDbId().intValue(), CommonObjects.monitoring);
                        return;
                    }
                }
            }
        }).start();
        this.fm.popBackStack();
    }
}
