package com.micromerger.ssms.startmonitoring.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.integratedbiometrics.ibscancommon.IBCommon;
import com.integratedbiometrics.ibscanmatcher.IBMatcher;
import com.integratedbiometrics.ibscanmatcher.IBMatcherException;
import com.integratedbiometrics.ibscanultimate.IBScan;
import com.integratedbiometrics.ibscanultimate.IBScanDevice;
import com.integratedbiometrics.ibscanultimate.IBScanDeviceListener;
import com.integratedbiometrics.ibscanultimate.IBScanException;
import com.integratedbiometrics.ibscanultimate.IBScanListener;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.EmployeeImpressionsZipResponse;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.adapters.AttendanceListRA;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.Employee;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.CommonObjectsUtils;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.Utils;
import com.micromerger.ssms.utils.biometric.IBMatcherDatabase;
import com.micromerger.ssms.utils.biometric.PlaySound;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class TakeAttendance extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener, IBScanListener, IBScanDeviceListener {
    private static final String ACTION_STATE_DEFAULT = "";
    private static final int CAPTURE_TYPE_INVALID = -1;
    private static final int DEVICE_DESCRIPTION_COLOR = -7829368;
    private static final String FILE_NAME_DEFAULT = "output";
    private static final int FINGER_QUALITIES_COUNT = 4;
    private static final int FINGER_QUALITY_FAIR_COLOR = -256;
    private static final int FINGER_QUALITY_GOOD_COLOR = -16711936;
    private static final int FINGER_QUALITY_NOT_PRESENT_COLOR = -3355444;
    private static final int FINGER_QUALITY_POOR_COLOR = -65536;
    private static final String FRAME_TIME_DEFAULT = "n/a";
    private static final int INITIALIZING_DEVICE_INDEX = 0;
    private static final int NO_DEVICE_DESCRIPTION_COLOR = -65536;
    private static final String NO_DEVICE_DESCRIPTION_STRING = "(no scanner)";
    private static final int PREVIEW_IMAGE_BACKGROUND = -3355444;
    private static final String SIMPLE_MATCH_TAG = "Simple Match";
    private static final String STATUS_DEFAULT = "";
    private static final int STOPPING_CAPTURE_DELAY_MILLIS = 250;
    private static final String USER_DESCRIPTION_DEFAULT = "";
    private static final String USER_NAME_DEFAULT = "";
    private AttendanceListRA adapter;
    Button cancel_button;
    CardView cv_noData_RL;
    String eId;
    SweetAlertDialog errorAlertDialog;
    EditText et_attendenceSearch;
    RelativeLayout finger;
    LinearLayout footer;
    SweetAlertDialog impressionsZipDialog;
    LinearLayout ll_attendenceSearch;
    LinearLayoutManager mLayoutManager;
    View mView;
    private ActionType m_actionType;
    private Button m_closeScannerBtn;
    private Dialog m_databaseDialog;
    private Dialog m_enlargedDialog;
    private IBMatcher m_ibMatcher;
    private IBMatcherDatabase m_ibMatcherDatabase;
    private IBScan m_ibScan;
    private IBScanDevice m_ibScanDevice;
    private ImageView m_imagePreviewImage;
    private IBScanDevice.ImageType m_imageType;
    private int m_imagesCaptured;
    private IBCommon.ImageDataExt m_lastImageExt;
    private IBCommon.ImageDataExt[] m_lastImageExts;
    private Button m_openScannerBtn;
    private ProgressDialog m_progressDialog;
    private Button m_refreshBtn;
    private Spinner m_spinnerActionType;
    private Spinner m_spinnerCaptureType;
    private TextView m_txtActionState;
    private TextView m_txtDesciption;
    private TextView m_txtDeviceCount;
    private TextView m_txtFrameTime;
    private TextView m_txtStatus;
    boolean matchCheck;
    ImageView previewImage;
    RecyclerView recyclerView;
    Button save_button;
    SweetAlertDialog successAlertDialog;
    TextView tv_non_teaching_staff_count;
    TextView tv_teaching_staff_count;
    ImageView uploadImage;
    private List<Employee> mDataset = new ArrayList();
    boolean isUpdate = false;
    boolean isCapturing = false;
    boolean isBackPressed = false;
    private String schoolID = "";
    private AppData m_savedData = new AppData();
    boolean isEmployeeSearching = false;
    boolean checkMA = false;
    boolean takeAttendance = false;
    private TextView[] m_txtFingerQuality = new TextView[4];
    private PlaySound m_beeper = new PlaySound();
    TextWatcher et_searchWatcher = new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.9
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            TakeAttendance.this.filter(s.toString());
        }
    };
    List<Employee> temp = new ArrayList();
    boolean isfiltered = false;
    private View.OnClickListener m_startCaptureBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.10
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[TakeAttendance.this.m_savedData.state.ordinal()] == 1) {
                int selectedItemPosition = TakeAttendance.this.m_spinnerActionType.getSelectedItemPosition();
                TakeAttendance.this.transitionToStartingCapture(selectedItemPosition == -1 ? ActionType.values()[0] : ActionType.values()[selectedItemPosition]);
            } else {
                Log.e(TakeAttendance.SIMPLE_MATCH_TAG, "Received unexpected start button event in state " + TakeAttendance.this.m_savedData.state.toString());
            }
        }
    };
    private View.OnClickListener m_stopCaptureBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.11
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[TakeAttendance.this.m_savedData.state.ordinal()] == 2) {
                TakeAttendance.this.transitionToStoppingCapture();
                return;
            }
            Log.e(TakeAttendance.SIMPLE_MATCH_TAG, "Received unexpected stop button event in state " + TakeAttendance.this.m_savedData.state.toString());
        }
    };
    private View.OnClickListener m_openScannerBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.12
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[TakeAttendance.this.m_savedData.state.ordinal()] == 3) {
                TakeAttendance.this.transitionToInitializing(0);
                return;
            }
            Log.e(TakeAttendance.SIMPLE_MATCH_TAG, "Received unexpected open button event in state " + TakeAttendance.this.m_savedData.state.toString());
        }
    };
    private View.OnClickListener m_closeScannerBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.13
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[TakeAttendance.this.m_savedData.state.ordinal()] == 1) {
                TakeAttendance.this.transitionToClosing();
                return;
            }
            Log.e(TakeAttendance.SIMPLE_MATCH_TAG, "Received unexpected close button event in state " + TakeAttendance.this.m_savedData.state.toString());
        }
    };
    private View.OnLongClickListener m_imagePreviewImageLongClickListener = new View.OnLongClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.14
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(final View v) {
            PopupMenu popupMenu = new PopupMenu(TakeAttendance.this.getActivity(), TakeAttendance.this.m_txtDesciption);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.14.1
                @Override // android.widget.PopupMenu.OnMenuItemClickListener
                public boolean onMenuItemClick(final MenuItem item) {
                    int itemId = item.getItemId();
                    return itemId == R.id.email_image || itemId == R.id.enlarge;
                }
            });
            popupMenu.getMenuInflater().inflate(R.menu.scanimage_menu, popupMenu.getMenu());
            popupMenu.show();
            return true;
        }
    };
    private View.OnClickListener m_refreshBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.15
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            int i = AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[TakeAttendance.this.m_savedData.state.ordinal()];
            if (i == 3 || i == 4) {
                TakeAttendance.this.transitionToRefresh();
                return;
            }
            Log.e(TakeAttendance.SIMPLE_MATCH_TAG, "Received unexpected refresh button event in state " + TakeAttendance.this.m_savedData.state.toString());
        }
    };
    private AdapterView.OnItemSelectedListener m_captureTypeItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.16
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(final AdapterView<?> parent, final View view, final int pos, final long id2) {
            TakeAttendance.this.m_savedData.captureType = pos;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(final AdapterView<?> parent) {
            TakeAttendance.this.m_savedData.captureType = -1;
        }
    };
    private View.OnClickListener m_enlargedImageClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.17
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (TakeAttendance.this.m_enlargedDialog != null) {
                TakeAttendance.this.m_enlargedDialog.hide();
                TakeAttendance.this.m_enlargedDialog = null;
            }
        }
    };
    private View.OnClickListener m_databaseDoneClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.18
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (TakeAttendance.this.m_databaseDialog != null) {
                TakeAttendance.this.m_databaseDialog.hide();
                TakeAttendance.this.m_databaseDialog = null;
            }
        }
    };
    private View.OnClickListener m_databaseClearClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.19
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            TakeAttendance.this.m_ibMatcherDatabase.clear();
            Cursor cursor = TakeAttendance.this.m_ibMatcherDatabase.getCursor();
            if (TakeAttendance.this.m_databaseDialog != null) {
                ((SimpleCursorAdapter) ((ListView) TakeAttendance.this.m_databaseDialog.findViewById(android.R.id.list)).getAdapter()).changeCursor(cursor);
                ((TextView) TakeAttendance.this.m_databaseDialog.findViewById(R.id.database_list_size)).setText("" + TakeAttendance.this.m_ibMatcherDatabase.getSize() + " bytes");
            }
        }
    };
    private View.OnClickListener m_viewDatabaseBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.20
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
        }
    };
    private Handler m_scanHandler = new Handler(new Handler.Callback() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.21
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(final Message msg) throws IllegalStateException {
            if (TakeAttendance.this.getActivity() != null) {
                switch (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.values()[msg.what].ordinal()]) {
                    case 1:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleTransitionToInitialized((IBScanDevice) msg.obj);
                            break;
                        }
                        break;
                    case 2:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleTransitionToCapturing();
                            break;
                        }
                        break;
                    case 3:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleTransitionToScannerAttached((String) msg.obj, msg.arg1);
                            break;
                        }
                        break;
                    case 4:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleTransitionToNoScannerAttached();
                            break;
                        }
                        break;
                    case 5:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleTransitionToRefresh();
                            break;
                        }
                        break;
                    case 6:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleTransitionToInitializing(msg.arg1);
                            break;
                        }
                        break;
                    case 7:
                        if (TakeAttendance.this.getActivity() != null) {
                            System.gc();
                            TakeAttendance.this.handleTransitionToClosing();
                            break;
                        }
                        break;
                    case 8:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleTransitionToStartingCapture((ActionType) msg.obj);
                            break;
                        }
                        break;
                    case 9:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleTransitionToStoppingCapture();
                            break;
                        }
                        break;
                    case 10:
                        if (TakeAttendance.this.getActivity() != null) {
                            Object[] objArr = (Object[]) msg.obj;
                            TakeAttendance.this.handleTransitionToImageCaptured((IBScanDevice.ImageData) objArr[0], (IBScanDevice.ImageType) objArr[1], (IBScanDevice.ImageData[]) objArr[2]);
                            break;
                        }
                        break;
                    case 11:
                        if (TakeAttendance.this.getActivity() != null) {
                            TakeAttendance.this.handleConenctionBreakDevice(msg, "Connection Break", "Device not connected");
                            break;
                        }
                        break;
                }
            }
            return false;
        }
    });
    private int deviceDisconnectcount = 1;
    private Handler handle = new Handler() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.45
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws JSONException {
            if (msg.what == 1) {
                TakeAttendance.this.dismissProgressDialog();
                CommonObjects.schoolThumbCheck = true;
                if (!TakeAttendance.this.isEmployeeSearching && !TakeAttendance.this.checkMA && !TakeAttendance.this.takeAttendance) {
                    TakeAttendance.this.dismissProgressDialog();
                    if (TakeAttendance.this.matchCheck) {
                        TakeAttendance.this.onResume();
                        return;
                    } else {
                        TakeAttendance.this.onResume();
                        return;
                    }
                }
                TakeAttendance.this.onBackPressed();
                return;
            }
            TakeAttendance.this.dismissProgressDialog();
        }
    };

    private enum ActionType {
        MATCH,
        CAPTURE,
        SINGLE_ENROLL,
        MULTI_ENROLL
    }

    private enum AppState {
        NO_SCANNER_ATTACHED,
        SCANNER_ATTACHED,
        REFRESH,
        INITIALIZING,
        INITIALIZED,
        CLOSING,
        STARTING_CAPTURE,
        CAPTURING,
        STOPPING_CAPTURE,
        IMAGE_CAPTURED,
        COMMUNICATION_BREAK
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceImageResultExtendedAvailable(IBScanDevice ibScanDevice, IBScanException e, IBScanDevice.ImageData imageData, IBScanDevice.ImageType imageType, int i, IBScanDevice.ImageData[] imageDatas, IBScanDevice.SegmentPosition[] segmentPositions) {
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void devicePressedKeyButtons(IBScanDevice ibScanDevice, int i) {
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Utils.checkEmployeeImpressionsExistence(getContext()).booleanValue()) {
            return;
        }
        new SweetAlertDialog(getActivityContext(), 3).setTitleText("Employee data not downloaded").setContentText("The employee attendance data is not downloaded. To mark the attendance you must have to download employee data").setCancelText(getString(R.string.dialog_cancel)).setCancelClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE).setConfirmText("Download").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$TakeAttendance$3t_OgZ0ykCdtp7VaE9cALn-6_wk
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public final void onClick(SweetAlertDialog sweetAlertDialog) {
                this.f$0.lambda$onCreate$0$TakeAttendance(sweetAlertDialog);
            }
        }).show();
    }

    public /* synthetic */ void lambda$onCreate$0$TakeAttendance(SweetAlertDialog sweetAlertDialog) {
        sweetAlertDialog.dismiss();
        if (CommonActions.isConnected(getContext())) {
            downloadEmployeeImpressionsData();
        } else {
            DialogCustom.showError(getActivityContext(), "No Internet Connection");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.frag_attendance, (ViewGroup) null);
        this.successAlertDialog = new SweetAlertDialog(getContext(), 2);
        this.errorAlertDialog = new SweetAlertDialog(getContext(), 1);
        IBScan iBScan = IBScan.getInstance(getActivity().getApplicationContext());
        this.m_ibScan = iBScan;
        iBScan.setScanListener(this);
        this.m_ibMatcher = IBMatcher.getInstance();
        this.m_ibMatcherDatabase = new IBMatcherDatabase(getActivity().getApplicationContext());
        initUIFields(this.mView);
        this.recyclerView = (RecyclerView) this.mView.findViewById(R.id.recyclerview);
        this.footer = (LinearLayout) this.mView.findViewById(R.id.footer);
        populateUI();
        transitionToRefresh();
        setCameraActivityCallBack(this);
        if (CommonObjects.userObj == null || CommonObjects.school == null || CommonObjects.monitoring == null) {
            new CommonObjectsUtils().checkCommonObjects();
        }
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.m_ibScan.setScanListener(null);
            this.m_openScannerBtn.setEnabled(false);
            this.m_openScannerBtn.setClickable(false);
            this.m_refreshBtn.setEnabled(false);
            this.m_refreshBtn.setClickable(false);
        }
        this.adapter = new AttendanceListRA(CommonObjects.mainActivity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mLayoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new AttendanceListRA.MyClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.1
            @Override // com.micromerger.ssms.startmonitoring.adapters.AttendanceListRA.MyClickListener
            public void onItemClick(int position, View v) {
                try {
                    TakeAttendance.this.transitionToStoppingCapture();
                    new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TakeAttendance.this.transitionToClosing();
                        }
                    }, 500L);
                    if (TakeAttendance.this.m_ibScanDevice != null) {
                        TakeAttendance.this.m_ibScanDevice.setScanDeviceListener(null);
                    }
                    if (TakeAttendance.this.m_ibScan != null) {
                        TakeAttendance.this.m_ibScan.setScanListener(null);
                    }
                    String roleInOrganization = "";
                    if (!TakeAttendance.this.isfiltered) {
                        Employee employee = (Employee) TakeAttendance.this.mDataset.get(position);
                        TakeAttendance.this.isUpdate = true;
                        if (employee != null) {
                            try {
                                if (employee.getEmployeeId() != null) {
                                    TakeAttendance takeAttendance = TakeAttendance.this;
                                    takeAttendance.ft = takeAttendance.fm.beginTransaction();
                                    FragmentTransaction fragmentTransaction = TakeAttendance.this.ft;
                                    String strValueOf = String.valueOf(employee.getEmployeeId());
                                    if (employee.getRoleInOrganization() != null) {
                                        roleInOrganization = employee.getRoleInOrganization();
                                    }
                                    fragmentTransaction.replace(R.id.container, new AttendanceDetail(strValueOf, roleInOrganization));
                                    TakeAttendance.this.ft.addToBackStack(null);
                                    TakeAttendance.this.ft.commit();
                                    return;
                                }
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("Attd Click EmpID", employee.getEmployeeId().toString());
                                Log.e("Attd Click EmpID", employee.getRoleInOrganization().toString());
                                util.logException(e);
                                return;
                            }
                        }
                        return;
                    }
                    TakeAttendance.this.isUpdate = true;
                    if (TakeAttendance.this.temp != null) {
                        try {
                            if (TakeAttendance.this.temp.get(position).getEmployeeId() != null) {
                                TakeAttendance takeAttendance2 = TakeAttendance.this;
                                takeAttendance2.ft = takeAttendance2.fm.beginTransaction();
                                FragmentTransaction fragmentTransaction2 = TakeAttendance.this.ft;
                                String strValueOf2 = String.valueOf(TakeAttendance.this.temp.get(position).getEmployeeId());
                                if (TakeAttendance.this.temp.get(position).getRoleInOrganization() != null) {
                                    roleInOrganization = TakeAttendance.this.temp.get(position).getRoleInOrganization();
                                }
                                fragmentTransaction2.replace(R.id.container, new AttendanceDetail(strValueOf2, roleInOrganization));
                                TakeAttendance.this.ft.addToBackStack(null);
                                TakeAttendance.this.ft.commit();
                                return;
                            }
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            Log.e("Attd Click EmpID", TakeAttendance.this.temp.get(position).getEmployeeId().toString());
                            Log.e("Attd Click EmpID", TakeAttendance.this.temp.get(position).getRoleInOrganization().toString());
                            util.logException(e2);
                            return;
                        }
                    }
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    Log.e("Attd Click EmpID", ((Employee) TakeAttendance.this.mDataset.get(position)).getEmployeeId().toString());
                    Log.e("Attd Click EmpID", ((Employee) TakeAttendance.this.mDataset.get(position)).getRoleInOrganization().toString());
                    util.logException(e3);
                    FirebaseCrashlytics.getInstance().recordException(new Exception("emp ID Attendence :" + ((Employee) TakeAttendance.this.mDataset.get(position)).getEmployeeId().toString()));
                    FirebaseCrashlytics.getInstance().recordException(new Exception("ROLLINORG Attendence :" + ((Employee) TakeAttendance.this.mDataset.get(position)).getRoleInOrganization()));
                }
                e3.printStackTrace();
                Log.e("Attd Click EmpID", ((Employee) TakeAttendance.this.mDataset.get(position)).getEmployeeId().toString());
                Log.e("Attd Click EmpID", ((Employee) TakeAttendance.this.mDataset.get(position)).getRoleInOrganization().toString());
                util.logException(e3);
                FirebaseCrashlytics.getInstance().recordException(new Exception("emp ID Attendence :" + ((Employee) TakeAttendance.this.mDataset.get(position)).getEmployeeId().toString()));
                FirebaseCrashlytics.getInstance().recordException(new Exception("ROLLINORG Attendence :" + ((Employee) TakeAttendance.this.mDataset.get(position)).getRoleInOrganization()));
            }
        });
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) this.mView.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setVisibility(0);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TakeAttendance.this.isCapturing = true;
                try {
                    TakeAttendance.this.getPhotoFromCamera("");
                } catch (Exception unused) {
                    TakeAttendance.this.isCapturing = false;
                    Toast.makeText(TakeAttendance.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        Button button = (Button) this.mView.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.mView.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
        if (this.mDataset.size() <= 0) {
            showViewNoDataRecycler(true);
        }
        return this.mView;
    }

    public void downloadEmployeeImpressionsData() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), 5);
        this.impressionsZipDialog = sweetAlertDialog;
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
        this.impressionsZipDialog.setTitleText("Downloading ...");
        this.impressionsZipDialog.setCancelable(false);
        this.impressionsZipDialog.show();
        HashMap map = new HashMap();
        map.put("userID", CommonObjects.userObj.userId);
        Log.d(ImagesContract.URL, "https://mne.seld.gos.pk/Services/api/Employee/GetEmployeeImpressionsSchoolWiseByMAID");
        AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Employee/GetEmployeeImpressionsSchoolWiseByMAID").setOkHttpClient(CommonActions.getOkHttpClient()).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addApplicationJsonBody(map).setTag((Object) "GetEmployeeImpressionsSchoolWiseByMAID").setPriority(Priority.MEDIUM).build().getAsObject(EmployeeImpressionsZipResponse.class, new AnonymousClass3());
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance$3, reason: invalid class name */
    class AnonymousClass3 implements ParsedRequestListener<EmployeeImpressionsZipResponse> {
        AnonymousClass3() {
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onResponse(final EmployeeImpressionsZipResponse response) {
            TakeAttendance.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$TakeAttendance$3$yeXrifIOwANbllBbzEXAoCBAwe0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onResponse$0$TakeAttendance$3(response);
                }
            });
        }

        public /* synthetic */ void lambda$onResponse$0$TakeAttendance$3(EmployeeImpressionsZipResponse employeeImpressionsZipResponse) {
            if (employeeImpressionsZipResponse.getStatus().booleanValue()) {
                TakeAttendance.this.onFileDownloadStart(employeeImpressionsZipResponse.getData(), new File(TakeAttendance.this.getContext().getFilesDir().toString()));
                return;
            }
            try {
                TakeAttendance.this.impressionsZipDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DialogCustom.showError(TakeAttendance.this.getActivityContext(), "Error Downloading Employee Data");
            util.logException(new Exception("Error Downloading Employee Data " + employeeImpressionsZipResponse));
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onError(ANError anError) {
            TakeAttendance.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$TakeAttendance$3$HC7W0xqvl25qma1W7ETfj-SCkVY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onError$1$TakeAttendance$3();
                }
            });
        }

        public /* synthetic */ void lambda$onError$1$TakeAttendance$3() {
            try {
                TakeAttendance.this.impressionsZipDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFileDownloadStart(final String url, final File file) {
        AndroidNetworking.download(url, file.getPath(), url.substring(url.lastIndexOf("/") + 1)).setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "SchoolsEmployee").setPriority(Priority.MEDIUM).build().startDownload(new AnonymousClass4(file, url));
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance$4, reason: invalid class name */
    class AnonymousClass4 implements DownloadListener {
        final /* synthetic */ File val$file;
        final /* synthetic */ String val$url;

        AnonymousClass4(final File val$file, final String val$url) {
            this.val$file = val$file;
            this.val$url = val$url;
        }

        @Override // com.androidnetworking.interfaces.DownloadListener
        public void onDownloadComplete() {
            FragmentActivity activity = TakeAttendance.this.getActivity();
            final File file = this.val$file;
            final String str = this.val$url;
            activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$TakeAttendance$4$onaXmPAcp4ew-5NUX15sBvuZjPw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadComplete$1$TakeAttendance$4(file, str);
                }
            });
        }

        public /* synthetic */ void lambda$onDownloadComplete$1$TakeAttendance$4(final File file, final String str) {
            TakeAttendance.this.preferenceHelper.setEmployeeImpressionsDownloaded(true);
            TakeAttendance.this.impressionsZipDialog.setTitleText("Unzipping files...");
            new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$TakeAttendance$4$mrGXZdQjPYp5WbMQly9RzA3VMXw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadComplete$0$TakeAttendance$4(file, str);
                }
            }).start();
        }

        public /* synthetic */ void lambda$onDownloadComplete$0$TakeAttendance$4(File file, String str) {
            String str2 = file.getPath() + str.substring(str.lastIndexOf("/"));
            File file2 = new File(file.getPath() + "/unzipped/");
            if (file2.exists()) {
                file2.delete();
            }
            try {
                util.unzip(new File(str2), file2, "");
            } catch (IOException e) {
                util.logException(new Exception("Error while unzipping " + e));
            }
            try {
                TakeAttendance.this.impressionsZipDialog.dismiss();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.androidnetworking.interfaces.DownloadListener
        public void onError(ANError anError) {
            util.logException(new Exception("Employee Impression Downloading Error: " + anError));
            TakeAttendance.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$TakeAttendance$4$FjfKVZ8KLJVeq4FRyeYo9Cs8y0w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onError$2$TakeAttendance$4();
                }
            });
        }

        public /* synthetic */ void lambda$onError$2$TakeAttendance$4() {
            try {
                TakeAttendance.this.impressionsZipDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkEmpAttendence() {
        for (int i = 0; i < this.mDataset.size(); i++) {
            if (this.mDataset.get(i).getAttendanceStatus().equals("") && !this.mDataset.get(i).getPresent().booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 != R.id.cancel_button) {
            if (id2 == R.id.save_button && this.mDataset != null) {
                loadingStarted();
                if (markListOnLeave()) {
                    if (this.mDataset != null) {
                        saveAttendenceListData(false);
                    }
                } else if (checkIfAnyEmployeeMarkedIfFlood()) {
                    saveAttendenceListData(true);
                } else {
                    saveAndMarkRemainingEmp();
                }
                loadingFinished();
                return;
            }
            return;
        }
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.fm.popBackStack();
        } else {
            onBackPressed();
        }
    }

    private boolean checkIfAnyEmployeeMarkedIfFlood() {
        boolean z;
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.school_status_fields.entrySet().iterator();
        String strValueOf = null;
        String strValueOf2 = null;
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().equals(Constant.Status_Id)) {
                strValueOf = String.valueOf(value.getDataValue());
            } else if (value.getKRAName().equals("School_Status_Closed_Duration")) {
                strValueOf2 = String.valueOf(value.getDataValue());
            }
        }
        boolean z2 = Objects.equals(strValueOf, "1") && Objects.equals(strValueOf2, Constant.Class_6);
        if (this.mDataset != null) {
            for (int i = 0; i < this.mDataset.size(); i++) {
                Employee employee = this.mDataset.get(i);
                if (employee.getAttendanceStatus().equals("Present") || employee.getAttendanceStatus().equals(Constant.THUMB_NOT_MATCH)) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        return z2 && !z;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws JSONException {
        super.onResume();
        if (CommonObjects.userObj == null || CommonObjects.school == null || CommonObjects.monitoring == null) {
            new CommonObjectsUtils().checkCommonObjects();
        }
        SweetAlertDialog sweetAlertDialog = this.successAlertDialog;
        if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
            this.successAlertDialog.dismiss();
        }
        SweetAlertDialog sweetAlertDialog2 = this.errorAlertDialog;
        if (sweetAlertDialog2 != null && sweetAlertDialog2.isShowing()) {
            this.errorAlertDialog.dismiss();
        }
        if (CommonObjects.monitoring != null && CommonObjects.monitoring.getSchoolId() != null && CommonObjects.monitoring.getDistrict_Id() != -1) {
            this.schoolID = String.valueOf(CommonObjects.monitoring.getDistrict_Id() + "/" + CommonObjects.monitoring.getSchoolId());
        }
        SSMS.setCurrentFragment(this);
        if (CommonObjects.schoolThumbCheck) {
            int selectedItemPosition = this.m_spinnerActionType.getSelectedItemPosition();
            transitionToStartingCapture(selectedItemPosition == -1 ? ActionType.values()[0] : ActionType.values()[selectedItemPosition]);
            CommonObjects.schoolThumbCheck = false;
            if (CommonObjects.searchedEmployeeId.equals("")) {
                Log.e("id", CommonObjects.searchedEmployeeId);
                try {
                    SweetAlertDialog sweetAlertDialog3 = this.errorAlertDialog;
                    if (sweetAlertDialog3 != null && sweetAlertDialog3.isShowing()) {
                        this.errorAlertDialog.dismiss();
                    }
                    if (getActivity() != null && !getActivity().isFinishing()) {
                        SweetAlertDialog sweetAlertDialogAttendanceErrorDialog = DialogCustom.attendanceErrorDialog(getContext(), "Error", "No employee exist");
                        this.errorAlertDialog = sweetAlertDialogAttendanceErrorDialog;
                        sweetAlertDialogAttendanceErrorDialog.setConfirmText("OK");
                        this.errorAlertDialog.setConfirmClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE);
                        this.errorAlertDialog.show();
                    }
                } catch (NullPointerException e) {
                    util.logException(new NullPointerException("NullPointerException showing Dialog in Taking Attendance" + e.getMessage()));
                } catch (Exception e2) {
                    util.logException(new NullPointerException("Exception showing Dialog in Taking Attendance" + e2.getMessage()));
                }
                util.logException(new Exception("No employee exist while taking attendance with UserID: " + CommonObjects.userObj.userId + " UserRole: " + CommonObjects.userObj.roleId + " Monitoring Id: " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID() + " School Id: " + CommonObjects.monitoring.getSchoolId()));
            } else {
                Log.e("id", CommonObjects.searchedEmployeeId);
                saveFieldsData();
            }
        }
        if (this.isCapturing) {
            this.isCapturing = false;
        } else {
            this.mDataset.clear();
            if (CommonObjects.monitoring != null) {
                getEmployees();
            } else if (CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name()) || CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name())) {
                loadingStarted();
                boolean dataAfterLoginForMA_CMO = CommonActions.getDataAfterLoginForMA_CMO(getActivity(), CommonObjects.school, this.preferenceHelper);
                loadingFinished();
                if (dataAfterLoginForMA_CMO) {
                    getEmployees();
                } else {
                    try {
                        SweetAlertDialog sweetAlertDialog4 = this.errorAlertDialog;
                        if (sweetAlertDialog4 != null && sweetAlertDialog4.isShowing()) {
                            this.errorAlertDialog.dismiss();
                        }
                        if (getActivity() != null && !getActivity().isFinishing()) {
                            SweetAlertDialog sweetAlertDialogAttendanceErrorDialog2 = DialogCustom.attendanceErrorDialog(getContext(), "Error", "Unable to get Employees");
                            this.errorAlertDialog = sweetAlertDialogAttendanceErrorDialog2;
                            sweetAlertDialogAttendanceErrorDialog2.setConfirmText("OK");
                            this.errorAlertDialog.setConfirmClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE);
                            this.errorAlertDialog.show();
                        }
                    } catch (NullPointerException e3) {
                        util.logException(new NullPointerException("NullPointerException showing Dialog in Taking Attendance" + e3.getMessage()));
                    } catch (Exception e4) {
                        util.logException(new NullPointerException("Exception showing Dialog in Taking Attendance" + e4.getMessage()));
                    }
                }
            } else {
                loadingStarted();
                boolean dataAfterLoginForManagers = CommonActions.getDataAfterLoginForManagers(getActivity(), CommonObjects.school, this.preferenceHelper);
                loadingFinished();
                if (dataAfterLoginForManagers) {
                    getEmployees();
                } else {
                    try {
                        SweetAlertDialog sweetAlertDialog5 = this.errorAlertDialog;
                        if (sweetAlertDialog5 != null && sweetAlertDialog5.isShowing()) {
                            this.errorAlertDialog.dismiss();
                        }
                        if (getActivity() != null && !getActivity().isFinishing()) {
                            SweetAlertDialog sweetAlertDialogAttendanceErrorDialog3 = DialogCustom.attendanceErrorDialog(getContext(), "Error", "Unable to get Employees");
                            this.errorAlertDialog = sweetAlertDialogAttendanceErrorDialog3;
                            sweetAlertDialogAttendanceErrorDialog3.setConfirmText("OK");
                            this.errorAlertDialog.setConfirmClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE);
                            this.errorAlertDialog.show();
                        }
                    } catch (NullPointerException e5) {
                        util.logException(new NullPointerException("NullPointerException showing Dialog in Taking Attendance" + e5.getMessage()));
                    } catch (Exception e6) {
                        util.logException(new NullPointerException("Exception showing Dialog in Taking Attendance" + e6.getMessage()));
                    }
                }
            }
            this.adapter.updateData(this.mDataset);
            this.adapter.sortList();
            setTeachingNonTeachingStaffCount();
            if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                this.save_button.setEnabled(false);
                this.save_button.setAlpha(0.5f);
                this.uploadImage.setEnabled(false);
            }
        }
        this.et_attendenceSearch.setText("");
        dismissProgressDialog();
        this.adapter.sortList();
    }

    private void getEmployees() {
        if (CommonObjects.monitoring.getEmployees().size() > 0) {
            for (int i = 0; i < CommonObjects.monitoring.getEmployees().size(); i++) {
                Employee employee = CommonObjects.monitoring.getEmployees().get(i);
                Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.attendance_fields.entrySet().iterator();
                while (it.hasNext()) {
                    KRAData value = it.next().getValue();
                    if (value.getKRAName().contains(Constant.is_Present_ + employee.getEmployeeId())) {
                        if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                            employee.setPresent(true);
                        } else {
                            employee.setPresent(false);
                        }
                        this.mDataset.add(employee);
                    }
                }
            }
            return;
        }
        Iterator<Map.Entry<Integer, KRAData>> it2 = CommonObjects.attendance_fields.entrySet().iterator();
        while (it2.hasNext()) {
            it2.next().getValue();
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        dismissProgressDialog();
        super.onPause();
    }

    public void closeScreen() {
        transitionToStoppingCapture();
        dismissProgressDialog();
        new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.5
            @Override // java.lang.Runnable
            public void run() {
                TakeAttendance.this.transitionToClosing();
            }
        }, 500L);
        new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TakeAttendance.this.fm.popBackStack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000L);
        IBScanDevice iBScanDevice = this.m_ibScanDevice;
        if (iBScanDevice != null) {
            iBScanDevice.setScanDeviceListener(null);
        }
        IBScan iBScan = this.m_ibScan;
        if (iBScan != null) {
            iBScan.setScanListener(null);
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        ProgressDialog progressDialog = this.m_progressDialog;
        if (progressDialog == null || !progressDialog.isShowing()) {
            this.isBackPressed = true;
            closeScreen();
        } else {
            dismissProgressDialog();
        }
        return true;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBack
    public void onCameraActivityResult(final Bitmap bitmap, String bitmapPath) {
        if (bitmap != null) {
            this.previewImage.setVisibility(0);
            this.previewImage.setImageBitmap(bitmap);
        }
    }

    private void saveFieldsData() {
        SuccessAttendanceFragment successAttendanceFragment;
        boolean z = false;
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.attendance_fields.entrySet()) {
            if (entry.getValue().getKRAName().contains(CommonObjects.searchedEmployeeId)) {
                Integer key = entry.getKey();
                KRAData value = entry.getValue();
                if (value.getKRAName().equals(Constant.Absent_Reason_id_ + CommonObjects.searchedEmployeeId)) {
                    value.setDataValue("");
                } else {
                    if (value.getKRAName().equals(Constant.Is_MsgSent_ILMI_ + CommonObjects.searchedEmployeeId)) {
                        value.setDataValue("");
                    } else {
                        if (value.getKRAName().equals(Constant.Leave_Type_ + CommonObjects.searchedEmployeeId)) {
                            value.setDataValue("");
                        } else {
                            if (value.getKRAName().equals(Constant.Office_Duty_Type_ + CommonObjects.searchedEmployeeId)) {
                                value.setDataValue("");
                            } else {
                                if (value.getKRAName().equals(Constant.Comments_ + CommonObjects.searchedEmployeeId)) {
                                    value.setDataValue("");
                                } else {
                                    if (value.getKRAName().equals(Constant.Monitoring_Image_ + CommonObjects.searchedEmployeeId)) {
                                        value.setDataValue("");
                                    } else {
                                        if (value.getKRAName().equals(Constant.is_Present_ + CommonObjects.searchedEmployeeId)) {
                                            value.setDataValue(String.valueOf(true));
                                            z = true;
                                        } else {
                                            if (value.getKRAName().equals(Constant.Attendance_Date_ + CommonObjects.searchedEmployeeId)) {
                                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                                Date date = new Date();
                                                System.out.println(simpleDateFormat.format(date));
                                                value.setDataValue(simpleDateFormat.format(date));
                                            } else {
                                                if (value.getKRAName().equals(Constant.Is_Completed_ + CommonObjects.searchedEmployeeId)) {
                                                    value.setDataValue(true);
                                                } else {
                                                    if (!value.getKRAName().equals("Latitude_" + CommonObjects.searchedEmployeeId)) {
                                                        if (!value.getKRAName().equals("Longitude_" + CommonObjects.searchedEmployeeId)) {
                                                            if (value.getKRAName().equals("Image_Date_" + CommonObjects.searchedEmployeeId)) {
                                                                value.setDataValue(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                                                            } else {
                                                                if (value.getKRAName().equals(Constant.Absent_Leave_From + CommonObjects.searchedEmployeeId)) {
                                                                    value.setDataValue("");
                                                                } else {
                                                                    if (value.getKRAName().equals(Constant.Absent_Leave_To + CommonObjects.searchedEmployeeId)) {
                                                                        value.setDataValue("");
                                                                    } else if (CommonObjects.searchedEmployeeId != null && !CommonObjects.searchedEmployeeId.isEmpty()) {
                                                                        int i = 0;
                                                                        while (true) {
                                                                            if (i >= CommonObjects.monitoring.getEmployees().size()) {
                                                                                break;
                                                                            }
                                                                            Employee employee = CommonObjects.monitoring.getEmployees().get(i);
                                                                            if (!CommonObjects.searchedEmployeeId.equalsIgnoreCase(String.valueOf(employee.getEmployeeId()))) {
                                                                                i++;
                                                                            } else if (employee.getDonotAskForVaccination() != null && employee.getDonotAskForVaccination().booleanValue()) {
                                                                                if (value.getKRAName().equals("Covid19_Vaccine_Type_" + CommonObjects.searchedEmployeeId)) {
                                                                                    if (employee.getVaccinationType() != null) {
                                                                                        value.setDataValue(String.valueOf(employee.getVaccinationType()));
                                                                                    }
                                                                                } else {
                                                                                    boolean zEquals = value.getKRAName().equals("1st_Dose_of_Covid19_Vaccine_" + CommonObjects.searchedEmployeeId);
                                                                                    String str = Constant.ECE_Katchi;
                                                                                    if (zEquals) {
                                                                                        if (employee.getIsFirstDose() != null) {
                                                                                            if (!employee.getIsFirstDose().booleanValue()) {
                                                                                                str = "1";
                                                                                            }
                                                                                            value.setDataValue(str);
                                                                                        }
                                                                                    } else {
                                                                                        if (value.getKRAName().equals("2nd_Dose_of_Covid19_Vaccine_" + CommonObjects.searchedEmployeeId) && employee.getIsSecondDose() != null) {
                                                                                            if (!employee.getIsSecondDose().booleanValue()) {
                                                                                                str = "1";
                                                                                            }
                                                                                            value.setDataValue(str);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else if (this.location != null) {
                                                            value.setDataValue(Double.valueOf(this.location.getLongitude()));
                                                        }
                                                    } else if (this.location != null) {
                                                        value.setDataValue(Double.valueOf(this.location.getLatitude()));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
            }
        }
        Employee employee2 = null;
        int i2 = 0;
        while (true) {
            if (i2 >= CommonObjects.monitoring.getEmployees().size()) {
                break;
            }
            Employee employee3 = CommonObjects.monitoring.getEmployees().get(i2);
            if (CommonObjects.searchedEmployeeId.toLowerCase().equals(String.valueOf(employee3.getEmployeeId()).toLowerCase())) {
                try {
                    CommonObjects.monitoring.getEmployees().get(i2).setAttendanceStatus(getString(R.string.attendPresent));
                } catch (Exception e) {
                    e.printStackTrace();
                    CommonObjects.monitoring.getEmployees().get(i2).setAttendanceStatus("Present");
                }
                employee2 = employee3;
                break;
            }
            i2++;
        }
        if (!z) {
            try {
                SweetAlertDialog sweetAlertDialog = this.errorAlertDialog;
                if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
                    this.errorAlertDialog.dismiss();
                }
                if (getActivity() != null && !getActivity().isFinishing()) {
                    SweetAlertDialog sweetAlertDialogAttendanceErrorDialog = DialogCustom.attendanceErrorDialog(getContext(), "Error", "No employee exist");
                    this.errorAlertDialog = sweetAlertDialogAttendanceErrorDialog;
                    sweetAlertDialogAttendanceErrorDialog.setConfirmText("OK");
                    this.errorAlertDialog.setConfirmClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE);
                    this.errorAlertDialog.show();
                }
            } catch (NullPointerException e2) {
                util.logException(new NullPointerException("NullPointerException showing Dialog in Taking Attendance" + e2.getMessage()));
            } catch (Exception e3) {
                util.logException(new NullPointerException("Exception showing Dialog in Taking Attendance" + e3.getMessage()));
            }
        } else if (employee2 != null) {
            try {
                try {
                    if (this.successAlertDialog.isShowing()) {
                        this.successAlertDialog.dismiss();
                    }
                    try {
                        if (getActivity() != null && !getActivity().isFinishing()) {
                            if (employee2.getDonotAskForVaccination() != null && employee2.getDonotAskForVaccination().booleanValue()) {
                                SweetAlertDialog sweetAlertDialogAttendanceSuccessDialog = DialogCustom.attendanceSuccessDialog(getContext(), "Success", employee2.getEmployeeName() + "(" + employee2.getEmployeeCode() + ") attendance marked successfully");
                                this.successAlertDialog = sweetAlertDialogAttendanceSuccessDialog;
                                sweetAlertDialogAttendanceSuccessDialog.show();
                            } else {
                                if ((this.fm.findFragmentById(R.id.container) instanceof SuccessAttendanceFragment) && (successAttendanceFragment = (SuccessAttendanceFragment) this.fm.findFragmentById(R.id.container)) != null && successAttendanceFragment.isVisible()) {
                                    this.fm.popBackStack();
                                }
                                this.fm.beginTransaction().add(R.id.container, new SuccessAttendanceFragment(String.valueOf(employee2.getEmployeeId()), employee2.getEmployeeName() + "(" + employee2.getEmployeeCode() + ") attendance marked successfully")).addToBackStack(getTag()).commit();
                            }
                        } else {
                            Toast.makeText(getContext(), employee2.getEmployeeName() + "(" + employee2.getEmployeeCode() + ") attendance marked successfully", 0).show();
                        }
                        util.logException(new Exception("Thumb matched: Monitoring Id: " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID() + " School Id: " + CommonObjects.monitoring.getSchoolId() + " Employee Id: " + CommonObjects.searchedEmployeeId));
                    } catch (Exception unused) {
                        Toast.makeText(getContext(), employee2.getEmployeeName() + "(" + employee2.getEmployeeCode() + ") attendance marked successfully", 0).show();
                    }
                } catch (Exception e4) {
                    util.logException(new NullPointerException("Exception showing Dialog in Taking Attendance" + e4.getMessage()));
                }
            } catch (NullPointerException e5) {
                util.logException(new NullPointerException("NullPointerException showing Dialog in Taking Attendance" + e5.getMessage()));
            }
        }
        saveInDatabase();
    }

    private void saveInDatabase() {
        for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
            if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                CommonActions.getDbHandler(getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAttendenceListData(boolean direct) {
        Log.e("employee list", this.mDataset.size() + "_");
        if (this.mDataset.size() > 0) {
            for (Employee employee : this.mDataset) {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.attendance_fields.entrySet()) {
                    if (entry.getValue().getKRAName().contains(String.valueOf(employee.getEmployeeId()))) {
                        if (entry.getValue().getKRAName().equals(Constant.is_Present_ + String.valueOf(employee.getEmployeeId()))) {
                            if (direct) {
                                entry.getValue().setDataValue("");
                            } else {
                                entry.getValue().setDataValue(String.valueOf(employee.getPresent()));
                            }
                            CommonObjects.attendance_fields.put(entry.getKey(), entry.getValue());
                        } else {
                            if (entry.getValue().getKRAName().equals(Constant.Is_Completed_ + String.valueOf(employee.getEmployeeId()))) {
                                entry.getValue().setDataValue(true);
                                CommonObjects.attendance_fields.put(entry.getKey(), entry.getValue());
                            } else {
                                if (entry.getValue().getKRAName().equals(Constant.Attendance_Date_ + String.valueOf(employee.getEmployeeId()))) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                    Date date = new Date();
                                    System.out.println(simpleDateFormat.format(date));
                                    if (direct) {
                                        entry.getValue().setDataValue("");
                                    } else {
                                        entry.getValue().setDataValue(simpleDateFormat.format(date));
                                    }
                                    CommonObjects.attendance_fields.put(entry.getKey(), entry.getValue());
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (Map.Entry<Integer, KRAData> entry2 : CommonObjects.attendance_fields.entrySet()) {
                if (entry2.getValue().getKRAName().equals(Constant.Is_Completed)) {
                    entry2.getValue().setDataValue(true);
                    CommonObjects.attendance_fields.put(entry2.getKey(), entry2.getValue());
                }
            }
        }
        saveInDatabase();
        onBackPressed();
    }

    private boolean markListOnLeave() {
        if (this.mDataset != null) {
            for (int i = 0; i < this.mDataset.size(); i++) {
                if (!this.mDataset.get(i).getPresent().booleanValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setTeachingNonTeachingStaffCount() {
        if (this.mDataset != null) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.mDataset.size(); i3++) {
                if (this.mDataset.get(i3).getCadre_ID().intValue() == 1) {
                    i++;
                } else {
                    i2++;
                }
            }
            this.tv_teaching_staff_count.setText(String.valueOf(i));
            this.tv_non_teaching_staff_count.setText(String.valueOf(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentDate() {
        String strValueOf;
        String strValueOf2;
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(5);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(1);
        if (String.valueOf(i).length() > 1) {
            strValueOf = String.valueOf(i);
        } else {
            strValueOf = Constant.ECE_Katchi + i;
        }
        if (String.valueOf(i2).length() > 1) {
            strValueOf2 = String.valueOf(i2);
        } else {
            strValueOf2 = Constant.ECE_Katchi + i2;
        }
        return i3 + "-" + strValueOf2 + "-" + strValueOf + "T00:00:00";
    }

    private void saveAndMarkRemainingEmp() {
        new SweetAlertDialog(getActivityContext(), 3).setTitleText("Alert").setContentText("Do you want to mark Absent on All Remaining Employees").setConfirmText(getString(R.string.dialog_ok)).setCancelText(getString(R.string.dialog_cancel)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.8
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        }).setConfirmClickListener(new AnonymousClass7()).show();
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance$7, reason: invalid class name */
    class AnonymousClass7 implements SweetAlertDialog.OnSweetClickListener {
        AnonymousClass7() {
        }

        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
        public void onClick(SweetAlertDialog sDialog) {
            List arrayList;
            if (TakeAttendance.this.mDataset != null) {
                for (int i = 0; i < TakeAttendance.this.mDataset.size(); i++) {
                    Employee employee = (Employee) TakeAttendance.this.mDataset.get(i);
                    if (((Employee) TakeAttendance.this.mDataset.get(i)).getAttendanceStatus().equals("") && !((Employee) TakeAttendance.this.mDataset.get(i)).getPresent().booleanValue()) {
                        ((Employee) TakeAttendance.this.mDataset.get(i)).setAttendanceStatus(TakeAttendance.this.getResources().getString(R.string.absent));
                        int iIntValue = ((Employee) TakeAttendance.this.mDataset.get(i)).getEmployeeId().intValue();
                        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.attendance_fields.entrySet().iterator();
                        while (it.hasNext()) {
                            KRAData value = it.next().getValue();
                            if (value.getKRAName().contains(Constant.Absent_Leave_From + iIntValue)) {
                                value.setDataValue(TakeAttendance.this.getCurrentDate());
                            } else {
                                if (value.getKRAName().contains(Constant.Absent_Leave_To + iIntValue)) {
                                    value.setDataValue(TakeAttendance.this.getCurrentDate());
                                } else {
                                    if (value.getKRAName().contains(Constant.Absent_Reason_id_ + iIntValue)) {
                                        Collection collectionSelect = CollectionUtils.select(TakeAttendance.this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$TakeAttendance$7$p4PHXdvszRllaQTRf_S73Z1bq6g
                                            @Override // org.apache.commons.collections4.Predicate
                                            public final boolean evaluate(Object obj) {
                                                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals(Constant.ABSENT_REASON);
                                            }
                                        });
                                        if (collectionSelect instanceof List) {
                                            arrayList = (List) collectionSelect;
                                        } else {
                                            arrayList = new ArrayList(collectionSelect);
                                        }
                                        Iterator it2 = arrayList.iterator();
                                        while (true) {
                                            if (it2.hasNext()) {
                                                ReferenceDataResponse.ReferenceData referenceData = (ReferenceDataResponse.ReferenceData) it2.next();
                                                if (referenceData.getReferencevalue().equals(Constant.ABSENT)) {
                                                    value.setDataValue(String.valueOf(referenceData.getReferencekey()));
                                                    break;
                                                }
                                            }
                                        }
                                    } else if (employee.getDonotAskForVaccination() != null && employee.getDonotAskForVaccination().booleanValue()) {
                                        if (value.getKRAName().equals("Covid19_Vaccine_Type_" + employee.getEmployeeId())) {
                                            if (employee.getVaccinationType() != null) {
                                                value.setDataValue(String.valueOf(employee.getVaccinationType()));
                                            }
                                        } else {
                                            boolean zEquals = value.getKRAName().equals("1st_Dose_of_Covid19_Vaccine_" + employee.getEmployeeId());
                                            String str = Constant.ECE_Katchi;
                                            if (zEquals) {
                                                if (employee.getIsFirstDose() != null) {
                                                    if (!employee.getIsFirstDose().booleanValue()) {
                                                        str = "1";
                                                    }
                                                    value.setDataValue(str);
                                                }
                                            } else {
                                                if (value.getKRAName().equals("2nd_Dose_of_Covid19_Vaccine_" + employee.getEmployeeId()) && employee.getIsSecondDose() != null) {
                                                    if (!employee.getIsSecondDose().booleanValue()) {
                                                        str = "1";
                                                    }
                                                    value.setDataValue(str);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Log.e("Updated employee list", TakeAttendance.this.mDataset.size() + "_");
                TakeAttendance.this.adapter.addAbsentData(TakeAttendance.this.mDataset);
            }
            if (TakeAttendance.this.mDataset != null) {
                TakeAttendance.this.saveAttendenceListData(false);
            }
            sDialog.dismiss();
        }
    }

    private class AppData {
        public String actionState;
        public int captureType;
        public String description;
        public int descriptionColor;
        public int deviceCount;
        public int[] fingerQualityColors;
        public String frameTime;
        public Bitmap imageBitmap;
        public boolean imagePreviewImageClickable;
        public int nonTeachingStaffCount;
        public AppState state;
        public String status;
        public int teachingStaffCount;

        private AppData() {
            this.state = AppState.NO_SCANNER_ATTACHED;
            this.captureType = -1;
            this.status = "";
            this.frameTime = TakeAttendance.FRAME_TIME_DEFAULT;
            this.actionState = "";
            this.imageBitmap = null;
            this.fingerQualityColors = new int[]{-3355444, -3355444, -3355444, -3355444};
            this.imagePreviewImageClickable = false;
            this.description = TakeAttendance.NO_DEVICE_DESCRIPTION_STRING;
            this.descriptionColor = SupportMenu.CATEGORY_MASK;
            this.deviceCount = 0;
            this.teachingStaffCount = 0;
            this.nonTeachingStaffCount = 0;
        }
    }

    private void initUIFields(View view) {
        this.tv_teaching_staff_count = (TextView) view.findViewById(R.id.tv_teaching_staff_count_id);
        this.tv_non_teaching_staff_count = (TextView) view.findViewById(R.id.tv_non_teaching_staff_count_id);
        this.m_txtDeviceCount = (TextView) view.findViewById(R.id.device_count);
        this.m_txtStatus = (TextView) view.findViewById(R.id.status);
        this.m_txtDesciption = (TextView) view.findViewById(R.id.description);
        this.m_txtFingerQuality[0] = (TextView) view.findViewById(R.id.scan_states_color1);
        this.m_txtFingerQuality[1] = (TextView) view.findViewById(R.id.scan_states_color2);
        this.m_txtFingerQuality[2] = (TextView) view.findViewById(R.id.scan_states_color3);
        this.m_txtFingerQuality[3] = (TextView) view.findViewById(R.id.scan_states_color4);
        this.m_txtFrameTime = (TextView) view.findViewById(R.id.frame_time);
        this.m_txtActionState = (TextView) view.findViewById(R.id.action_state);
        ImageView imageView = (ImageView) view.findViewById(R.id.preview_image);
        this.m_imagePreviewImage = imageView;
        imageView.setBackgroundColor(-3355444);
        Button button = (Button) view.findViewById(R.id.open_scanner_btn);
        this.m_openScannerBtn = button;
        button.setOnClickListener(this.m_openScannerBtnClickListener);
        Button button2 = (Button) view.findViewById(R.id.close_scanner_btn);
        this.m_closeScannerBtn = button2;
        button2.setOnClickListener(this.m_closeScannerBtnClickListener);
        Button button3 = (Button) view.findViewById(R.id.refresh_btn);
        this.m_refreshBtn = button3;
        button3.setOnClickListener(this.m_refreshBtnClickListener);
        this.m_spinnerCaptureType = (Spinner) view.findViewById(R.id.capture_type);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, new CharSequence[0]);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.m_spinnerCaptureType.setAdapter((SpinnerAdapter) arrayAdapter);
        this.m_spinnerCaptureType.setOnItemSelectedListener(this.m_captureTypeItemSelectedListener);
        this.m_spinnerActionType = (Spinner) view.findViewById(R.id.action_type);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, new CharSequence[]{"Match", "Capture", "Single enroll", "Multi enroll"});
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.m_spinnerActionType.setAdapter((SpinnerAdapter) arrayAdapter2);
        this.ll_attendenceSearch = (LinearLayout) view.findViewById(R.id.ll_attendenceSearch);
        EditText editText = (EditText) view.findViewById(R.id.et_attendenceSearch);
        this.et_attendenceSearch = editText;
        editText.addTextChangedListener(this.et_searchWatcher);
        this.cv_noData_RL = (CardView) view.findViewById(R.id.cv_noData_RL);
    }

    void filter(String text) {
        if (text.equals("")) {
            this.isfiltered = false;
            if (this.mDataset.size() > 0) {
                showViewNoDataRecycler(false);
                this.adapter.updateData(this.mDataset);
                return;
            } else {
                showViewNoDataRecycler(true);
                this.adapter.updateData(this.mDataset);
                return;
            }
        }
        this.temp.clear();
        for (Employee employee : this.mDataset) {
            if (employee != null && employee.getEmployeeName() != null) {
                showViewNoDataRecycler(false);
                if (employee.getEmployeeName().toLowerCase().contains(text.toLowerCase()) || employee.getEmployeeName().contains(text)) {
                    this.temp.add(employee);
                }
            }
        }
        if (this.temp.size() > 0) {
            this.isfiltered = true;
            this.adapter.updateData(this.temp);
        } else {
            showViewNoDataRecycler(true);
        }
    }

    private void showViewNoDataRecycler(boolean value) {
        if (value) {
            this.recyclerView.setVisibility(8);
            this.cv_noData_RL.setVisibility(0);
            this.footer.setVisibility(8);
        } else {
            this.recyclerView.setVisibility(0);
            this.cv_noData_RL.setVisibility(8);
            this.footer.setVisibility(0);
        }
    }

    private void populateUI() {
        resetButtonsForState(this.m_savedData.state);
        setDeviceCount(this.m_savedData.deviceCount);
        setDescription(this.m_savedData.description, this.m_savedData.descriptionColor);
        this.tv_teaching_staff_count.setText(String.valueOf(this.m_savedData.teachingStaffCount));
        this.tv_non_teaching_staff_count.setText(String.valueOf(this.m_savedData.nonTeachingStaffCount));
        if (this.m_savedData.status != null) {
            this.m_txtStatus.setText(this.m_savedData.status);
        }
        if (this.m_savedData.frameTime != null) {
            this.m_txtFrameTime.setText(this.m_savedData.frameTime);
        }
        if (this.m_savedData.actionState != null) {
            this.m_txtActionState.setText(this.m_savedData.actionState);
        }
        if (this.m_savedData.imageBitmap != null) {
            this.m_imagePreviewImage.setImageBitmap(this.m_savedData.imageBitmap);
        }
        for (int i = 0; i < 4; i++) {
            this.m_txtFingerQuality[i].setBackgroundColor(this.m_savedData.fingerQualityColors[i]);
        }
        if (this.m_savedData.captureType != -1) {
            this.m_spinnerCaptureType.setSelection(this.m_savedData.captureType);
        }
        this.m_imagePreviewImage.setLongClickable(this.m_savedData.imagePreviewImageClickable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConenctionBreakDevice(final Message msg, final String errorType, String errormsg) {
        int i;
        System.gc();
        SweetAlertDialog sweetAlertDialog = this.errorAlertDialog;
        if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
            this.errorAlertDialog.dismiss();
        }
        SweetAlertDialog sweetAlertDialog2 = this.successAlertDialog;
        if (sweetAlertDialog2 != null && sweetAlertDialog2.isShowing()) {
            this.successAlertDialog.dismiss();
        }
        ProgressDialog progressDialog = this.m_progressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.m_progressDialog.dismiss();
        }
        if (getActivity() == null || (i = this.deviceDisconnectcount) != 1) {
            return;
        }
        this.deviceDisconnectcount = i + 1;
        SweetAlertDialog confirmClickListener = new SweetAlertDialog(getActivity(), 1).setTitleText(errorType).setContentText(errormsg).setConfirmText("OK").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.22
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sDialog) {
                if (TakeAttendance.this.getActivity() != null) {
                    TakeAttendance.this.deviceDisconnectcount = 1;
                    TakeAttendance.this.handleTransitionToInitializing(msg.arg1);
                    TakeAttendance.this.handleTransitionToCommunicationBreak();
                }
                if (sDialog != null) {
                    sDialog.dismiss();
                }
            }
        });
        this.errorAlertDialog = confirmClickListener;
        confirmClickListener.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToNoScannerAttached() {
        if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()] != 5) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to NO_SCANNER_ATTACHED from " + this.m_savedData.state.toString());
            return;
        }
        this.m_savedData.state = AppState.NO_SCANNER_ATTACHED;
        resetButtonsForState(AppState.NO_SCANNER_ATTACHED);
        setStatus("no scanners");
        setFrameTime(FRAME_TIME_DEFAULT);
        setDeviceCount(0);
        setDescription(NO_DEVICE_DESCRIPTION_STRING, SupportMenu.CATEGORY_MASK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToScannerAttached(final String deviceDesc, final int deviceCount) {
        if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()] != 5) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to SCANNER_ATTACHED from " + this.m_savedData.state.toString());
            return;
        }
        this.m_savedData.state = AppState.SCANNER_ATTACHED;
        resetButtonsForState(AppState.SCANNER_ATTACHED);
        setStatus("uninitialized");
        setFrameTime(FRAME_TIME_DEFAULT);
        setDeviceCount(deviceCount);
        setDescription(deviceDesc, DEVICE_DESCRIPTION_COLOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToRefresh() {
        switch (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()]) {
            case 1:
                IBScanDevice iBScanDevice = this.m_ibScanDevice;
                if (iBScanDevice != null) {
                    try {
                        iBScanDevice.isCaptureActive();
                        break;
                    } catch (IBScanException unused) {
                        transitionToClosing();
                        return;
                    }
                }
                break;
            case 2:
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
                break;
            case 3:
            case 4:
            case 7:
                this.m_savedData.state = AppState.REFRESH;
                resetButtonsForState(AppState.REFRESH);
                setStatus("refreshing");
                setFrameTime(FRAME_TIME_DEFAULT);
                setActionState("");
                setCaptureTypes(new String[0]);
                UsbManager usbManager = (UsbManager) CommonObjects.mainActivity.getSystemService("usb");
                for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
                    try {
                        if (IBScan.isScanDevice(usbDevice) && !usbManager.hasPermission(usbDevice)) {
                            this.m_ibScan.requestPermission(usbDevice.getDeviceId());
                        }
                    } catch (Exception e) {
                        util.logException(e);
                        getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.23
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(TakeAttendance.this.getActivityContext(), "Unable to initialize Scanner", 0).show();
                            }
                        });
                    }
                }
                try {
                    int deviceCount = this.m_ibScan.getDeviceCount();
                    if (deviceCount > 0) {
                        try {
                            IBScan.DeviceDesc deviceDescription = this.m_ibScan.getDeviceDescription(0);
                            transitionToScannerAttached(deviceDescription.productName + " - " + deviceDescription.serialNumber, deviceCount);
                        } catch (IBScanException e2) {
                            Log.e(SIMPLE_MATCH_TAG, "Received exception getting device description " + e2.getType().toString());
                            transitionToNoScannerAttached();
                        }
                    } else {
                        transitionToNoScannerAttached();
                    }
                    break;
                } catch (IBScanException e3) {
                    Log.e(SIMPLE_MATCH_TAG, "Received exception getting device count " + e3.getType().toString());
                    transitionToNoScannerAttached();
                    return;
                }
                break;
            case 5:
                break;
            default:
                Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to REFRESH from " + this.m_savedData.state.toString());
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToInitializing(final int deviceIndex) {
        if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()] != 3) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to INITIALIZING from " + this.m_savedData.state.toString());
            return;
        }
        this.m_savedData.state = AppState.INITIALIZING;
        resetButtonsForState(AppState.INITIALIZING);
        setStatus("initializing");
        setFrameTime(FRAME_TIME_DEFAULT);
        this.m_imagePreviewImage.setLongClickable(false);
        this.m_savedData.imagePreviewImageClickable = false;
        this.m_lastImageExts = null;
        this.m_lastImageExt = null;
        try {
            this.m_ibScan.openDeviceAsync(deviceIndex);
        } catch (IBScanException e) {
            showToastOnUiThread("Could not initialize device with exception " + e.getType().toString(), 0);
            transitionToClosing();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToInitialized(final IBScanDevice device) {
        switch (this.m_savedData.state) {
            case INITIALIZING:
            case STARTING_CAPTURE:
            case STOPPING_CAPTURE:
            case IMAGE_CAPTURED:
                this.m_savedData.state = AppState.INITIALIZED;
                resetButtonsForState(AppState.INITIALIZED);
                setStatus("initialized");
                setFrameTime(FRAME_TIME_DEFAULT);
                if (device != null) {
                    try {
                        device.setProperty(IBScanDevice.PropertyId.ENABLE_POWER_SAVE_MODE, "TRUE");
                    } catch (IBScanException e) {
                        Log.e(SIMPLE_MATCH_TAG, "Could not begin enable power save mode " + e.getType().toString());
                    }
                    Vector vector = new Vector();
                    for (IBScanDevice.ImageType imageType : IBScanDevice.ImageType.values()) {
                        try {
                            if (device.isCaptureAvailable(imageType, IBScanDevice.ImageResolution.RESOLUTION_500)) {
                                vector.add(imageType.toDescription());
                            }
                        } catch (IBScanException e2) {
                            Log.e(SIMPLE_MATCH_TAG, "Could not check capture availability " + e2.getType().toString());
                        }
                    }
                    setCaptureTypes((String[]) vector.toArray(new String[0]));
                    this.m_ibScanDevice = device;
                    new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.24
                        @Override // java.lang.Runnable
                        public void run() {
                            int selectedItemPosition = TakeAttendance.this.m_spinnerActionType.getSelectedItemPosition();
                            TakeAttendance.this.transitionToStartingCapture(selectedItemPosition == -1 ? ActionType.values()[0] : ActionType.values()[selectedItemPosition]);
                        }
                    }, 500L);
                    break;
                }
                break;
            case CLOSING:
            default:
                Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to INITIALIZED from " + this.m_savedData.state.toString());
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToClosing() {
        int i = AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()];
        if (i == 1 || i == 6 || i == 11) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected COMMUNICATION BREAK " + this.m_savedData.state.toString());
            this.m_savedData.state = AppState.CLOSING;
            resetButtonsForState(AppState.CLOSING);
            setStatus("closing");
            setFrameTime(FRAME_TIME_DEFAULT);
            IBScanDevice iBScanDevice = this.m_ibScanDevice;
            if (iBScanDevice != null) {
                try {
                    iBScanDevice.close();
                } catch (IBScanException e) {
                    Log.e(SIMPLE_MATCH_TAG, "Could not close device " + e.getType().toString());
                }
                this.m_ibScanDevice = null;
            }
            transitionToRefresh();
            return;
        }
        Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to CLOSING from " + this.m_savedData.state.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToStartingCapture(final ActionType actionType) {
        int i = AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()];
        if (i != 1 && i != 10) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to STARTING_CAPTURE from " + this.m_savedData.state.toString());
            return;
        }
        this.m_savedData.state = AppState.STARTING_CAPTURE;
        resetButtonsForState(AppState.STARTING_CAPTURE);
        setStatus("starting");
        setFrameTime(FRAME_TIME_DEFAULT);
        int[] iArr = {1, 1, 3, 6};
        int i2 = actionType != null ? iArr[actionType.ordinal()] : iArr[this.m_actionType.ordinal()];
        if (actionType != null) {
            this.m_imagesCaptured = 0;
            this.m_actionType = actionType;
            this.m_lastImageExts = new IBCommon.ImageDataExt[i2];
        }
        setActionState("capturing image " + (this.m_imagesCaptured + 1) + " of " + i2);
        try {
            IBScanDevice.ImageType imageType = IBScanDevice.ImageType.TYPE_NONE;
            IBScanDevice.ImageType[] imageTypeArrValues = IBScanDevice.ImageType.values();
            int length = imageTypeArrValues.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                IBScanDevice.ImageType imageType2 = imageTypeArrValues[i3];
                if (((CharSequence) this.m_spinnerCaptureType.getSelectedItem()).equals(imageType2.toDescription())) {
                    imageType = imageType2;
                    break;
                }
                i3++;
            }
            this.m_ibScanDevice.beginCaptureImage(imageType, IBScanDevice.ImageResolution.RESOLUTION_500, 3);
            this.m_imageType = imageType;
            transitionToCapturing();
        } catch (IBScanException e) {
            showToastOnUiThread("Could not begin capturing with error " + e.getType().toString(), 0);
            transitionToInitialized(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToCapturing() {
        if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()] != 8) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to CAPTURING from " + this.m_savedData.state.toString());
            return;
        }
        this.m_savedData.state = AppState.CAPTURING;
        resetButtonsForState(AppState.CAPTURING);
        setStatus("capturing");
        setFrameTime(FRAME_TIME_DEFAULT);
        this.m_ibScanDevice.setScanDeviceListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToStoppingCapture() {
        int i = AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()];
        if (i != 2 && i != 9) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to STOPPING_CAPTURE from " + this.m_savedData.state.toString());
            return;
        }
        this.m_savedData.state = AppState.STOPPING_CAPTURE;
        resetButtonsForState(AppState.STOPPING_CAPTURE);
        setStatus("stopping");
        setFrameTime(FRAME_TIME_DEFAULT);
        setActionState("capture stopped");
        boolean z = true;
        try {
            if (this.m_ibScanDevice.isCaptureActive()) {
                try {
                    this.m_ibScanDevice.cancelCaptureImage();
                    z = false;
                } catch (IBScanException e) {
                    showToastOnUiThread("Could not cancel capturing with error " + e.getType().toString(), 0);
                }
            }
        } catch (IBScanException e2) {
            showToastOnUiThread("Could not query capture active state " + e2.getType().toString(), 0);
        }
        if (z) {
            transitionToInitialized(null);
        } else {
            transitionToStoppingCaptureWithDelay(250);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToImageCaptured(final IBScanDevice.ImageData image, final IBScanDevice.ImageType imageType, final IBScanDevice.ImageData[] splitImageArray) throws IllegalStateException {
        if (AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()] != 2) {
            showToastOnUiThread("Received unexpected transition to STOPPING_CAPTURE from " + this.m_savedData.state.toString(), 0);
            return;
        }
        this.m_savedData.state = AppState.IMAGE_CAPTURED;
        resetButtonsForState(AppState.IMAGE_CAPTURED);
        setStatus("captured");
        setFrameTime(FRAME_TIME_DEFAULT);
        this.m_beeper.playSound();
        int i = new int[]{1, 1, 3, 6}[this.m_actionType.ordinal()];
        try {
            Object[] resultImageExt = this.m_ibScanDevice.getResultImageExt(IBCommon.FingerPosition.UNKNOWN);
            this.m_lastImageExt = (IBCommon.ImageDataExt) resultImageExt[0];
            this.m_lastImageExts[this.m_imagesCaptured] = (IBCommon.ImageDataExt) resultImageExt[0];
        } catch (IBScanException e) {
            this.m_lastImageExt = null;
            this.m_lastImageExts[this.m_imagesCaptured] = null;
            showToastOnUiThread("Error creating imageDataExt " + e.getType().toString(), 0);
        }
        int i2 = this.m_imagesCaptured + 1;
        this.m_imagesCaptured = i2;
        if (i2 < i) {
            setActionState("captured image " + this.m_imagesCaptured + " of " + i);
            transitionToStartingCapture(null);
            return;
        }
        setActionState("performed " + new String[]{"capture", "match", "single enrollment", "multiple enrollment"}[this.m_actionType.ordinal()]);
        this.m_savedData.imagePreviewImageClickable = true;
        this.m_imagePreviewImage.setLongClickable(true);
        int i3 = AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$ActionType[this.m_actionType.ordinal()];
        if (i3 == 1) {
            new Thread() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.25
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        int iCalculateNfiqScore = TakeAttendance.this.m_ibScanDevice.calculateNfiqScore(image);
                        TakeAttendance.this.showToastOnUiThread("NFIQ score for print is " + iCalculateNfiqScore, 0);
                    } catch (IBScanException e2) {
                        TakeAttendance.this.showToastOnUiThread("Error calculating NFIQ score " + e2.getType().toString(), 0);
                    }
                }
            }.start();
        } else if (i3 == 2) {
            final IBCommon.ImageDataExt[] imageDataExtArr = this.m_lastImageExts;
            showProgressDialog("Processing...");
            new Thread() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.26
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        try {
                            IBMatcher.Template templateExtractTemplate = TakeAttendance.this.m_ibMatcher.extractTemplate(imageDataExtArr[0]);
                            if (templateExtractTemplate != null) {
                                if (TakeAttendance.this.schoolID != null) {
                                    TakeAttendance takeAttendance = TakeAttendance.this;
                                    takeAttendance.matchCheck = takeAttendance.m_ibMatcherDatabase.getSavedEmployee(templateExtractTemplate, TakeAttendance.this.getActivity(), TakeAttendance.this.schoolID, TakeAttendance.this.getActivity().getApplicationContext());
                                }
                                Log.d("SchoolIDDistrict", TakeAttendance.this.schoolID);
                                Message message = new Message();
                                message.what = 1;
                                TakeAttendance.this.handle.sendMessage(message);
                                return;
                            }
                            TakeAttendance.this.showAlert("Could not match", "Unable to Read Thumb Impressions");
                        } catch (IBMatcherException e2) {
                            TakeAttendance.this.dismissProgressDialog();
                            TakeAttendance.this.showAlert("Could not match", "Error generating template");
                            Log.e(TakeAttendance.SIMPLE_MATCH_TAG, "Error generating template for " + e2.getType().toString());
                        }
                    } catch (Exception e3) {
                        TakeAttendance.this.dismissProgressDialog();
                        e3.printStackTrace();
                        TakeAttendance.this.showAlert("Could not match", "Error generating template");
                    }
                }
            }.start();
        }
        transitionToInitialized(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToCommunicationBreak() {
        int i = AnonymousClass46.$SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[this.m_savedData.state.ordinal()];
        if (i != 1 && i != 2 && i != 9) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to COMMUNICATION_BREAK from " + this.m_savedData.state.toString());
            return;
        }
        this.m_savedData.state = AppState.COMMUNICATION_BREAK;
        resetButtonsForState(AppState.COMMUNICATION_BREAK);
        setStatus("comm break");
        setFrameTime(FRAME_TIME_DEFAULT);
        transitionToClosing();
    }

    private void transitionToNoScannerAttached() {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.NO_SCANNER_ATTACHED.ordinal()));
    }

    private void transitionToScannerAttached(final String deviceDesc, final int deviceCount) {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.SCANNER_ATTACHED.ordinal(), deviceCount, 0, deviceDesc));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToRefresh() {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.REFRESH.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToInitializing(final int deviceIndex) {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.INITIALIZING.ordinal(), deviceIndex, 0));
    }

    private void transitionToInitialized(final IBScanDevice device) {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.INITIALIZED.ordinal(), device));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToClosing() {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.CLOSING.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToStartingCapture(final ActionType actionType) {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.STARTING_CAPTURE.ordinal(), actionType));
    }

    private void transitionToCapturing() {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.CAPTURING.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToStoppingCapture() {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.STOPPING_CAPTURE.ordinal()));
    }

    private void transitionToStoppingCaptureWithDelay(final int delayMillis) {
        this.m_scanHandler.sendMessageDelayed(this.m_scanHandler.obtainMessage(AppState.STOPPING_CAPTURE.ordinal()), delayMillis);
    }

    private void transitionToImageCaptured(final IBScanDevice.ImageData image, final IBScanDevice.ImageType imageType, final IBScanDevice.ImageData[] splitImageArray) {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.IMAGE_CAPTURED.ordinal(), 0, 0, new Object[]{image, imageType, splitImageArray}));
    }

    private void transitionToCommunicationBreak() {
        this.m_scanHandler.sendMessage(this.m_scanHandler.obtainMessage(AppState.COMMUNICATION_BREAK.ordinal()));
    }

    private void resetButtonsForState(final AppState state) {
        boolean[] zArr = {false, false, false, false, true, false, false, false, false, false, false};
        boolean z = new boolean[]{false, false, false, false, false, false, false, true, false, false, false}[state.ordinal()];
        boolean z2 = new boolean[]{false, false, false, false, true, false, false, false, false, false, false}[state.ordinal()];
        final boolean z3 = new boolean[]{true, true, false, false, false, false, false, false, false, false, false}[state.ordinal()];
        boolean z4 = new boolean[]{false, false, false, false, true, false, false, false, false, false, false}[state.ordinal()];
        final boolean z5 = new boolean[]{false, true, false, false, false, false, false, false, false, false, false}[state.ordinal()];
        final boolean z6 = zArr[state.ordinal()];
        final boolean z7 = zArr[state.ordinal()];
        boolean z8 = new boolean[]{true, true, false, false, true, false, false, false, false, false, false}[state.ordinal()];
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.27
            @Override // java.lang.Runnable
            public void run() {
                TakeAttendance.this.m_refreshBtn.setEnabled(z3);
                TakeAttendance.this.m_refreshBtn.setClickable(z3);
                TakeAttendance.this.m_openScannerBtn.setEnabled(z5);
                TakeAttendance.this.m_openScannerBtn.setClickable(z5);
                TakeAttendance.this.m_closeScannerBtn.setEnabled(z6);
                TakeAttendance.this.m_closeScannerBtn.setClickable(z6);
                TakeAttendance.this.m_spinnerActionType.setEnabled(z7);
                TakeAttendance.this.m_spinnerActionType.setClickable(z7);
            }
        });
    }

    private void setStatus(final String s) {
        this.m_savedData.status = s;
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.28
            @Override // java.lang.Runnable
            public void run() {
                TakeAttendance.this.m_txtStatus.setText(s);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrameTime(final String s) {
        this.m_savedData.frameTime = s;
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.29
            @Override // java.lang.Runnable
            public void run() {
                TakeAttendance.this.m_txtFrameTime.setText(s);
            }
        });
    }

    private void setDescription(final String description, final int color) {
        this.m_savedData.description = description;
        this.m_savedData.descriptionColor = color;
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.30
            @Override // java.lang.Runnable
            public void run() {
                TakeAttendance.this.m_txtDesciption.setText(description);
                TakeAttendance.this.m_txtDesciption.setBackgroundColor(color);
            }
        });
    }

    private void setActionState(final String s) {
        this.m_savedData.actionState = s;
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.31
            @Override // java.lang.Runnable
            public void run() {
                TakeAttendance.this.m_txtActionState.setText(s);
            }
        });
    }

    private void setCaptureTypes(final String[] captureTypes) {
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.32
            @Override // java.lang.Runnable
            public void run() {
                ArrayAdapter arrayAdapter = new ArrayAdapter(CommonObjects.mainActivity, android.R.layout.simple_spinner_item, captureTypes);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                TakeAttendance.this.m_spinnerCaptureType.setAdapter((SpinnerAdapter) arrayAdapter);
            }
        });
    }

    private void setDeviceCount(final int deviceCount) {
        this.m_savedData.deviceCount = deviceCount;
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.33
            @Override // java.lang.Runnable
            public void run() {
                TakeAttendance.this.m_txtDeviceCount.setText("" + deviceCount);
            }
        });
    }

    private void showProgressDialog(final String title) {
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.34
            @Override // java.lang.Runnable
            public void run() {
                TakeAttendance.this.m_progressDialog = new ProgressDialog(CommonObjects.mainActivity);
                TakeAttendance.this.m_progressDialog.setIndeterminate(true);
                TakeAttendance.this.m_progressDialog.setTitle(title);
                TakeAttendance.this.m_progressDialog.show();
                TakeAttendance.this.m_progressDialog.setCancelable(false);
            }
        });
    }

    private void hideProgressDialog() {
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.35
            @Override // java.lang.Runnable
            public void run() {
                if (TakeAttendance.this.m_progressDialog != null) {
                    TakeAttendance.this.m_progressDialog.hide();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissProgressDialog() {
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.36
            @Override // java.lang.Runnable
            public void run() {
                if (TakeAttendance.this.m_progressDialog != null) {
                    TakeAttendance.this.m_progressDialog.dismiss();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlert(final String title, final String message) {
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.37
            @Override // java.lang.Runnable
            public void run() {
                new AlertDialog.Builder(TakeAttendance.this.getActivity()).setTitle(title).setMessage(message).setPositiveButton("OK", (DialogInterface.OnClickListener) null).create().show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastOnUiThread(final String message, final int duration) {
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.38
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(CommonObjects.mainActivity.getApplicationContext(), message, duration).show();
            }
        });
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceCommunicationBroken(IBScanDevice ibScanDevice) {
        if (getActivity() != null) {
            showToastOnUiThread("Communication break with device", 0);
            transitionToCommunicationBreak();
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceImagePreviewAvailable(IBScanDevice ibScanDevice, final IBScanDevice.ImageData image) {
        IBScanDevice.RollingData rollingInfo;
        final Bitmap bitmapScaled;
        try {
            int width = this.m_imagePreviewImage.getWidth();
            int height = this.m_imagePreviewImage.getHeight();
            int i = (image.height * width) / image.width;
            if (i > height) {
                width = (image.width * height) / image.height;
            } else {
                height = i;
            }
            if (this.m_imageType.equals(IBScanDevice.ImageType.ROLL_SINGLE_FINGER)) {
                try {
                    rollingInfo = this.m_ibScanDevice.getRollingInfo();
                } catch (IBScanException e) {
                    Log.e("Simple Scan", "failure getting rolling line " + e.getType().toString());
                    rollingInfo = null;
                }
                if (rollingInfo != null) {
                    bitmapScaled = image.toBitmapScaled(width, height, rollingInfo.rollingState, rollingInfo.rollingLineX);
                } else {
                    bitmapScaled = image.toBitmapScaled(width, height);
                }
            } else {
                bitmapScaled = image.toBitmapScaled(width, height);
            }
            if (bitmapScaled != null) {
                CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.39
                    @Override // java.lang.Runnable
                    public void run() {
                        TakeAttendance.this.setFrameTime(String.format("%1$.3f", Double.valueOf(image.frameTime)));
                        TakeAttendance.this.m_savedData.imageBitmap = bitmapScaled;
                        TakeAttendance.this.m_imagePreviewImage.setImageBitmap(bitmapScaled);
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceFingerCountChanged(IBScanDevice ibScanDevice, IBScanDevice.FingerCountState fingerState) {
        int i = AnonymousClass46.$SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerCountState[fingerState.ordinal()];
        if (i == 2) {
            setStatus("too many fingers");
            return;
        }
        if (i == 3) {
            setStatus("too few fingers");
        } else if (i != 4) {
            setStatus("capturing");
        } else {
            setStatus("non-finger");
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceFingerQualityChanged(IBScanDevice ibScanDevice, final IBScanDevice.FingerQualityState[] fingerQualities) {
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.40
            @Override // java.lang.Runnable
            public void run() {
                IBScanDevice.FingerQualityState[] fingerQualityStateArr;
                int i = 0;
                while (true) {
                    fingerQualityStateArr = fingerQualities;
                    int i2 = -3355444;
                    if (i >= fingerQualityStateArr.length) {
                        break;
                    }
                    int i3 = AnonymousClass46.$SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerQualityState[fingerQualities[i].ordinal()];
                    if (i3 == 2) {
                        i2 = TakeAttendance.FINGER_QUALITY_GOOD_COLOR;
                    } else if (i3 == 3) {
                        i2 = -256;
                    } else if (i3 == 4) {
                        i2 = SupportMenu.CATEGORY_MASK;
                    }
                    if (i < TakeAttendance.this.m_txtFingerQuality.length) {
                        TakeAttendance.this.m_savedData.fingerQualityColors[i] = i2;
                        TakeAttendance.this.m_txtFingerQuality[i].setBackgroundColor(i2);
                    }
                    i++;
                }
                for (int length = fingerQualityStateArr.length; length < TakeAttendance.this.m_txtFingerQuality.length; length++) {
                    TakeAttendance.this.m_savedData.fingerQualityColors[length] = -3355444;
                    TakeAttendance.this.m_txtFingerQuality[length].setBackgroundColor(-3355444);
                }
            }
        });
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceAcquisitionBegun(IBScanDevice ibScanDevice, IBScanDevice.ImageType imageType) {
        showToastOnUiThread("Beginning acquisition...roll finger left", 0);
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceAcquisitionCompleted(IBScanDevice ibScanDevice, IBScanDevice.ImageType imageType) {
        showToastOnUiThread("Completed acquisition...roll finger right", 0);
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceImageResultAvailable(IBScanDevice ibScanDevice, final IBScanDevice.ImageData image, IBScanDevice.ImageType imageType, IBScanDevice.ImageData[] splitImageArray) {
        int width = this.m_imagePreviewImage.getWidth();
        int height = this.m_imagePreviewImage.getHeight();
        int i = (image.height * width) / image.width;
        if (i > height) {
            width = (image.width * height) / image.height;
        } else {
            height = i;
        }
        final Bitmap bitmapScaled = image.toBitmapScaled(width, height);
        if (bitmapScaled != null) {
            CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.41
                @Override // java.lang.Runnable
                public void run() {
                    TakeAttendance.this.setFrameTime(String.format("%1$.3f", Double.valueOf(image.frameTime)));
                    TakeAttendance.this.m_savedData.imageBitmap = bitmapScaled;
                    TakeAttendance.this.m_imagePreviewImage.setImageBitmap(bitmapScaled);
                }
            });
        }
        transitionToImageCaptured(image, imageType, splitImageArray);
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance$46, reason: invalid class name */
    static /* synthetic */ class AnonymousClass46 {
        static final /* synthetic */ int[] $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerCountState;
        static final /* synthetic */ int[] $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerQualityState;
        static final /* synthetic */ int[] $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$PlatenState;
        static final /* synthetic */ int[] $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$ActionType;

        static {
            int[] iArr = new int[IBScanDevice.PlatenState.values().length];
            $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$PlatenState = iArr;
            try {
                iArr[IBScanDevice.PlatenState.HAS_FINGERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$PlatenState[IBScanDevice.PlatenState.CLEARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[IBScanDevice.FingerQualityState.values().length];
            $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerQualityState = iArr2;
            try {
                iArr2[IBScanDevice.FingerQualityState.FINGER_NOT_PRESENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerQualityState[IBScanDevice.FingerQualityState.GOOD.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerQualityState[IBScanDevice.FingerQualityState.FAIR.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerQualityState[IBScanDevice.FingerQualityState.POOR.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[IBScanDevice.FingerCountState.values().length];
            $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerCountState = iArr3;
            try {
                iArr3[IBScanDevice.FingerCountState.FINGER_COUNT_OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerCountState[IBScanDevice.FingerCountState.TOO_MANY_FINGERS.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerCountState[IBScanDevice.FingerCountState.TOO_FEW_FINGERS.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerCountState[IBScanDevice.FingerCountState.NON_FINGER.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr4 = new int[ActionType.values().length];
            $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$ActionType = iArr4;
            try {
                iArr4[ActionType.CAPTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$ActionType[ActionType.MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$ActionType[ActionType.SINGLE_ENROLL.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$ActionType[ActionType.MULTI_ENROLL.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            int[] iArr5 = new int[AppState.values().length];
            $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState = iArr5;
            try {
                iArr5[AppState.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.CAPTURING.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.SCANNER_ATTACHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.NO_SCANNER_ATTACHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.REFRESH.ordinal()] = 5;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.INITIALIZING.ordinal()] = 6;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.CLOSING.ordinal()] = 7;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.STARTING_CAPTURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.STOPPING_CAPTURE.ordinal()] = 9;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.IMAGE_CAPTURED.ordinal()] = 10;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$fragments$TakeAttendance$AppState[AppState.COMMUNICATION_BREAK.ordinal()] = 11;
            } catch (NoSuchFieldError unused25) {
            }
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void devicePlatenStateChanged(IBScanDevice ibScanDevice, IBScanDevice.PlatenState platenState) {
        int i = AnonymousClass46.$SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$PlatenState[platenState.ordinal()];
        if (i == 1) {
            setActionState("please remove fingers from platen");
            return;
        }
        if (i != 2) {
            return;
        }
        setActionState("capturing image " + (this.m_imagesCaptured + 1) + " of " + new int[]{1, 1, 3, 6}[this.m_actionType.ordinal()]);
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceWarningReceived(IBScanDevice ibScanDevice, IBScanException warning) {
        showToastOnUiThread("Warning received " + warning.getType().toString(), 0);
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceAttached(int deviceId) {
        try {
            if (this.m_ibScan.hasPermission(deviceId)) {
                return;
            }
            this.m_ibScan.requestPermission(deviceId);
        } catch (Exception e) {
            util.logException(e);
            getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.42
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(TakeAttendance.this.getActivityContext(), "Unable to initialize Scanner", 0).show();
                }
            });
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceDetached(int i) {
        if (getActivity() != null) {
            transitionToStoppingCapture();
            new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.43
                @Override // java.lang.Runnable
                public void run() {
                    TakeAttendance.this.transitionToClosing();
                }
            }, 500L);
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDevicePermissionGranted(int deviceId, boolean granted) {
        if (granted) {
            transitionToRefresh();
            new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TakeAttendance.44
                @Override // java.lang.Runnable
                public void run() {
                    TakeAttendance.this.transitionToInitializing(0);
                }
            }, 1000L);
        }
        dismissProgressDialog();
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceCountChanged(int deviceCount) {
        if (getActivity() != null) {
            transitionToRefresh();
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceInitProgress(int deviceIndex, int progressValue) {
        if (getActivity() != null) {
            setStatus("init " + progressValue + "%");
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceOpenComplete(final int deviceIndex, final IBScanDevice device, final IBScanException exception) {
        if (getActivity() != null) {
            if (device != null) {
                transitionToInitialized(device);
                return;
            }
            if (exception != null) {
                exception.getType().toString();
            }
            transitionToClosing();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        Log.e("ondestroy", "true");
        if (!this.isBackPressed) {
            closeScreen();
        }
        super.onDestroy();
    }
}
