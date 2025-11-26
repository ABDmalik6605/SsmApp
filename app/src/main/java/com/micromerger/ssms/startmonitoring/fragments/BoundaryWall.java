package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes2.dex */
public class BoundaryWall extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener {
    Button cancel_button;
    RelativeLayout commentsLayout;
    EditText editText_comments;
    View mView;
    ImageView previewImage;
    Button save_button;
    Spinner spinner;
    RelativeLayout statusLayout;
    String title;
    TextView tv_census_widget;
    ImageView uploadImage;
    List<ReferenceDataResponse.ReferenceData> STATUS_LIST = new ArrayList();
    int statusId = 0;
    String imageBase64 = "";
    boolean isCapturing = false;

    public BoundaryWall() {
    }

    public BoundaryWall(String s) {
        this.title = s;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_widget, (ViewGroup) null);
        this.mView = viewInflate;
        this.parentView = viewInflate.findViewById(R.id.parentView);
        setCameraActivityCallBack(this);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.tv_census_widget);
        this.tv_census_widget = textView;
        textView.setText(this.title);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.statusLayout);
        this.statusLayout = relativeLayout;
        relativeLayout.setVisibility(0);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.BoundaryWall.1
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.BUILDING_CONDITION);
            }
        });
        if (collectionSelect instanceof List) {
            this.STATUS_LIST = (List) collectionSelect;
        } else {
            this.STATUS_LIST = new ArrayList(collectionSelect);
        }
        Spinner spinner = (Spinner) this.mView.findViewById(R.id.spinner);
        this.spinner = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.BoundaryWall.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view2, int position, long l) {
                for (ReferenceDataResponse.ReferenceData referenceData : BoundaryWall.this.STATUS_LIST) {
                    if (referenceData.getSortOrder() == position) {
                        BoundaryWall.this.statusId = referenceData.getReferencekey().intValue();
                        return;
                    }
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(BoundaryWall.this.getActivityContext(), BoundaryWall.this.getActivity().getCurrentFocus());
            }
        });
        this.spinner.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.STATUS_LIST));
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.commentsLayout);
        this.commentsLayout = relativeLayout2;
        relativeLayout2.setVisibility(0);
        EditText editText = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.BoundaryWall.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.editText_comments) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & 255) == 1) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setVisibility(0);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.BoundaryWall.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BoundaryWall.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    BoundaryWall.this.getPhotoFromCamera(iIntValue + "_122_" + Constant.BoundaryWall_Monitering_Image);
                } catch (Exception unused) {
                    BoundaryWall.this.isCapturing = false;
                    Toast.makeText(BoundaryWall.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
        if (this.isCapturing) {
            this.isCapturing = false;
            return;
        }
        List<ReferenceDataResponse.ReferenceData> list = this.STATUS_LIST;
        if (list == null || list.isEmpty()) {
            Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$BoundaryWall$ZcBvsOkcW2ZFG3_jX-8KzJYgQ5o
                @Override // org.apache.commons.collections4.Predicate
                public final boolean evaluate(Object obj) {
                    return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals(Constant.BUILDING_CONDITION);
                }
            });
            if (collectionSelect instanceof List) {
                this.STATUS_LIST = (List) collectionSelect;
            } else {
                this.STATUS_LIST = new ArrayList(collectionSelect);
            }
        }
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.boundary_wall.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.BoundaryWall_Status)) {
                    this.statusId = Integer.parseInt((String) value.getDataValue());
                    Iterator<ReferenceDataResponse.ReferenceData> it = this.STATUS_LIST.iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (it.next().getReferencekey().intValue() == this.statusId && i < this.STATUS_LIST.size()) {
                            this.spinner.setSelection(i);
                            break;
                        }
                        i++;
                    }
                } else if (value.getKRAName().equals(Constant.Comments)) {
                    this.editText_comments.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.BoundaryWall_Monitering_Image)) {
                    this.imageBase64 = (String) value.getDataValue();
                    Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBase64));
                    if (bitmapDecodeFile != null) {
                        this.previewImage.setImageBitmap(bitmapDecodeFile);
                    } else {
                        String str = this.imageBase64;
                        if (str != null) {
                            byte[] bArrDecode = Base64.decode(str, 0);
                            try {
                                this.previewImage.setImageBitmap(BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length));
                                this.previewImage.setVisibility(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (this.imageBase64.length() > 0) {
                        this.previewImage.setVisibility(0);
                    }
                } else if (value.getKRAName().equals(Constant.Image_Date)) {
                    Log.e(Constant.Image_Date, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                }
            }
        }
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.uploadImage.setEnabled(false);
        this.uploadImage.setFocusable(false);
        this.uploadImage.setAlpha(0.5f);
        this.spinner.setEnabled(false);
        this.spinner.setFocusable(false);
        this.editText_comments.setEnabled(false);
        this.editText_comments.setFocusable(false);
        this.save_button.setEnabled(false);
        this.save_button.setAlpha(0.5f);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBack
    public void onCameraActivityResult(final Bitmap bitmap, String bitmapPath) {
        this.location = CommonObjects.tracker.getLocation(getActivityContext());
        if (CommonObjects.isMock) {
            return;
        }
        if (bitmap != null) {
            this.previewImage.setVisibility(0);
            this.previewImage.setImageBitmap(bitmap);
        }
        if (bitmapPath != null) {
            this.imageBase64 = bitmapPath;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 == R.id.cancel_button) {
            checkData();
            return;
        }
        if (id2 != R.id.save_button) {
            return;
        }
        if (!this.STATUS_LIST.get(this.statusId).getReferencevalue().equals(Constant.Select)) {
            if (this.statusId == 4) {
                saveFieldsData();
                return;
            } else if (this.imageBase64.equals("")) {
                DialogCustom.showError(getActivityContext(), "Please upload image.");
                return;
            } else {
                saveFieldsData();
                return;
            }
        }
        DialogCustom.showError(getActivityContext(), "Please select the status.");
    }

    private void checkData() {
        try {
            if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                if (getActivity() != null) {
                    this.fm.popBackStack();
                }
            } else if (this.imageBase64.length() > 0 || this.editText_comments.getText().toString().length() > 0) {
                DialogCustom.showCancelWarning(getActivityContext(), this.fm);
            } else if (getActivity() != null) {
                this.fm.popBackStack();
            }
        } catch (NullPointerException e) {
            Log.e("checkData", "checkData: " + e.getMessage());
        }
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.BoundaryWall.5
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.boundary_wall.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.BoundaryWall_Status)) {
                        value.setDataValue(String.valueOf(BoundaryWall.this.statusId));
                    } else if (value.getKRAName().equals(Constant.Comments)) {
                        value.setDataValue(BoundaryWall.this.editText_comments.getText().toString());
                    } else if (value.getKRAName().equals(Constant.BoundaryWall_Monitering_Image)) {
                        value.setDataValue(BoundaryWall.this.imageBase64);
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equals(Constant.Latitude)) {
                        if (BoundaryWall.this.location != null) {
                            value.setDataValue(Double.valueOf(BoundaryWall.this.location.getLatitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Longitude)) {
                        if (BoundaryWall.this.location != null) {
                            value.setDataValue(Double.valueOf(BoundaryWall.this.location.getLongitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Image_Date) && !BoundaryWall.this.imageBase64.equals("")) {
                        value.setDataValue(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                    }
                    CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
                }
                int i = 0;
                while (true) {
                    if (i >= CommonObjects.employeeData.size()) {
                        break;
                    }
                    if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                        CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                        CommonActions.getDbHandler(BoundaryWall.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.isBoundaryWallDATA = true;
            }
        }).start();
        if (getActivity() != null) {
            this.fm.popBackStack();
        }
    }
}
