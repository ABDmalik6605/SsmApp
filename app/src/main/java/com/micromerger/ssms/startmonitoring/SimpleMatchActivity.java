package com.micromerger.ssms.startmonitoring;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.internal.view.SupportMenu;
import com.integratedbiometrics.ibscancommon.IBCommon;
import com.integratedbiometrics.ibscanmatcher.IBMatcher;
import com.integratedbiometrics.ibscanmatcher.IBMatcherException;
import com.integratedbiometrics.ibscanultimate.IBScan;
import com.integratedbiometrics.ibscanultimate.IBScanDevice;
import com.integratedbiometrics.ibscanultimate.IBScanDeviceListener;
import com.integratedbiometrics.ibscanultimate.IBScanException;
import com.integratedbiometrics.ibscanultimate.IBScanListener;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.biometric.IBHelpers;
import com.micromerger.ssms.utils.biometric.IBMatcherDatabase;
import com.micromerger.ssms.utils.biometric.PlaySound;
import com.micromerger.ssms.utils.util;
import com.mindorks.paracamera.Camera;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

/* loaded from: classes2.dex */
public class SimpleMatchActivity extends Activity implements IBScanListener, IBScanDeviceListener {
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
    String eId;
    private ActionType m_actionType;
    private Button m_closeScannerBtn;
    private Dialog m_databaseDialog;
    private Button m_emptyBtn;
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
    private Button m_startCaptureBtn;
    private Button m_stopCaptureBtn;
    private TextView m_txtActionState;
    private TextView m_txtDesciption;
    private TextView m_txtDeviceCount;
    private TextView m_txtFrameTime;
    private TextView m_txtSDKVersion;
    private TextView m_txtStatus;
    private Button m_viewDatabaseBtn;
    boolean matchCheck;
    private TextView[] m_txtFingerQuality = new TextView[4];
    private PlaySound m_beeper = new PlaySound();
    private AppData m_savedData = new AppData();
    boolean isEmployeeSearching = false;
    boolean checkMA = false;
    boolean thumbMatchMA = false;
    boolean takeAttendance = false;
    String schoolIDWithDistrict = "";
    private Handler m_scanHandler = new Handler(new Handler.Callback() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.22
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(final Message msg) throws IllegalStateException {
            switch (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.values()[msg.what].ordinal()]) {
                case 1:
                    SimpleMatchActivity.this.handleTransitionToNoScannerAttached();
                    return false;
                case 2:
                    SimpleMatchActivity.this.handleTransitionToScannerAttached((String) msg.obj, msg.arg1);
                    return false;
                case 3:
                    SimpleMatchActivity.this.handleTransitionToRefresh();
                    return false;
                case 4:
                    SimpleMatchActivity.this.handleTransitionToInitializing(msg.arg1);
                    return false;
                case 5:
                    SimpleMatchActivity.this.handleTransitionToInitialized((IBScanDevice) msg.obj);
                    return false;
                case 6:
                    SimpleMatchActivity.this.handleTransitionToClosing();
                    return false;
                case 7:
                    SimpleMatchActivity.this.handleTransitionToStartingCapture((ActionType) msg.obj);
                    return false;
                case 8:
                    SimpleMatchActivity.this.handleTransitionToCapturing();
                    return false;
                case 9:
                    SimpleMatchActivity.this.handleTransitionToStoppingCapture();
                    return false;
                case 10:
                    Object[] objArr = (Object[]) msg.obj;
                    SimpleMatchActivity.this.handleTransitionToImageCaptured((IBScanDevice.ImageData) objArr[0], (IBScanDevice.ImageType) objArr[1], (IBScanDevice.ImageData[]) objArr[2]);
                    return false;
                case 11:
                    SimpleMatchActivity.this.handleTransitionToCommunicationBreak();
                    return false;
                default:
                    return false;
            }
        }
    });
    private View.OnClickListener m_startCaptureBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.30
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[SimpleMatchActivity.this.m_savedData.state.ordinal()] == 5) {
                int selectedItemPosition = SimpleMatchActivity.this.m_spinnerActionType.getSelectedItemPosition();
                SimpleMatchActivity.this.transitionToStartingCapture(selectedItemPosition == -1 ? ActionType.values()[0] : ActionType.values()[selectedItemPosition]);
            } else {
                Log.e(SimpleMatchActivity.SIMPLE_MATCH_TAG, "Received unexpected start button event in state " + SimpleMatchActivity.this.m_savedData.state.toString());
            }
        }
    };
    private View.OnClickListener m_stopCaptureBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.31
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[SimpleMatchActivity.this.m_savedData.state.ordinal()] == 8) {
                SimpleMatchActivity.this.transitionToStoppingCapture();
                return;
            }
            Log.e(SimpleMatchActivity.SIMPLE_MATCH_TAG, "Received unexpected stop button event in state " + SimpleMatchActivity.this.m_savedData.state.toString());
        }
    };
    private View.OnClickListener m_openScannerBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.32
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[SimpleMatchActivity.this.m_savedData.state.ordinal()] == 2) {
                SimpleMatchActivity.this.transitionToInitializing(0);
                return;
            }
            Log.e(SimpleMatchActivity.SIMPLE_MATCH_TAG, "Received unexpected open button event in state " + SimpleMatchActivity.this.m_savedData.state.toString());
        }
    };
    private View.OnClickListener m_closeScannerBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.33
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[SimpleMatchActivity.this.m_savedData.state.ordinal()] == 5) {
                SimpleMatchActivity.this.transitionToClosing();
                return;
            }
            Log.e(SimpleMatchActivity.SIMPLE_MATCH_TAG, "Received unexpected close button event in state " + SimpleMatchActivity.this.m_savedData.state.toString());
        }
    };
    private View.OnLongClickListener m_imagePreviewImageLongClickListener = new View.OnLongClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.34
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(final View v) {
            SimpleMatchActivity simpleMatchActivity = SimpleMatchActivity.this;
            PopupMenu popupMenu = new PopupMenu(simpleMatchActivity, simpleMatchActivity.m_txtDesciption);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.34.1
                @Override // android.widget.PopupMenu.OnMenuItemClickListener
                public boolean onMenuItemClick(final MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.email_image) {
                        SimpleMatchActivity.this.promptForEmail(SimpleMatchActivity.this.m_lastImageExt);
                        return true;
                    }
                    if (itemId != R.id.enlarge) {
                        return false;
                    }
                    SimpleMatchActivity.this.showEnlargedImage();
                    return true;
                }
            });
            popupMenu.getMenuInflater().inflate(R.menu.scanimage_menu, popupMenu.getMenu());
            popupMenu.show();
            return true;
        }
    };
    private View.OnClickListener m_refreshBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.35
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            int i = AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[SimpleMatchActivity.this.m_savedData.state.ordinal()];
            if (i == 1 || i == 2) {
                SimpleMatchActivity.this.transitionToRefresh();
                return;
            }
            Log.e(SimpleMatchActivity.SIMPLE_MATCH_TAG, "Received unexpected refresh button event in state " + SimpleMatchActivity.this.m_savedData.state.toString());
        }
    };
    private AdapterView.OnItemSelectedListener m_captureTypeItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.36
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(final AdapterView<?> parent, final View view, final int pos, final long id2) {
            SimpleMatchActivity.this.m_savedData.captureType = pos;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(final AdapterView<?> parent) {
            SimpleMatchActivity.this.m_savedData.captureType = -1;
        }
    };
    private View.OnClickListener m_enlargedImageClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.37
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (SimpleMatchActivity.this.m_enlargedDialog != null) {
                SimpleMatchActivity.this.m_enlargedDialog.hide();
                SimpleMatchActivity.this.m_enlargedDialog = null;
            }
        }
    };
    private View.OnClickListener m_databaseDoneClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.38
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            if (SimpleMatchActivity.this.m_databaseDialog != null) {
                SimpleMatchActivity.this.m_databaseDialog.hide();
                SimpleMatchActivity.this.m_databaseDialog = null;
            }
        }
    };
    private View.OnClickListener m_databaseClearClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.39
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            SimpleMatchActivity.this.m_ibMatcherDatabase.clear();
            Cursor cursor = SimpleMatchActivity.this.m_ibMatcherDatabase.getCursor();
            if (SimpleMatchActivity.this.m_databaseDialog != null) {
                ((SimpleCursorAdapter) ((ListView) SimpleMatchActivity.this.m_databaseDialog.findViewById(android.R.id.list)).getAdapter()).changeCursor(cursor);
                ((TextView) SimpleMatchActivity.this.m_databaseDialog.findViewById(R.id.database_list_size)).setText("" + SimpleMatchActivity.this.m_ibMatcherDatabase.getSize() + " bytes");
            }
        }
    };
    private View.OnClickListener m_viewDatabaseBtnClickListener = new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.40
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            SimpleMatchActivity.this.showDatabase();
        }
    };
    private Handler handle = new Handler(Looper.getMainLooper()) { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.44
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                CommonObjects.schoolThumbCheck = true;
                if (SimpleMatchActivity.this.isEmployeeSearching || SimpleMatchActivity.this.checkMA || SimpleMatchActivity.this.thumbMatchMA || SimpleMatchActivity.this.takeAttendance) {
                    SimpleMatchActivity.this.onBackPressed();
                    return;
                }
                if (SimpleMatchActivity.this.matchCheck) {
                    Toast.makeText(SimpleMatchActivity.this, "MATCH", 0).show();
                    SimpleMatchActivity.this.transitionToClosing();
                    new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.44.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SimpleMatchActivity.this.finish();
                        }
                    }, 500L);
                } else {
                    Toast.makeText(SimpleMatchActivity.this, "NOT MATCH", 0).show();
                    SimpleMatchActivity.this.transitionToClosing();
                    new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.44.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SimpleMatchActivity.this.finish();
                        }
                    }, 500L);
                }
            }
        }
    };

    private enum ActionType {
        CAPTURE,
        MATCH,
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

    private enum OutputFormat {
        PNG,
        WSQ,
        FIR,
        FMR,
        IBSM_IMAGE,
        IBSM_TEMPLATE
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceImageResultExtendedAvailable(IBScanDevice arg0, IBScanException arg1, IBScanDevice.ImageData arg2, IBScanDevice.ImageType arg3, int arg4, IBScanDevice.ImageData[] arg5, IBScanDevice.SegmentPosition[] arg6) {
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void devicePressedKeyButtons(IBScanDevice ibScanDevice, int i) {
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceDetached(final int deviceId) {
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDevicePermissionGranted(final int deviceId, final boolean granted) {
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
        public AppState state;
        public String status;

        private AppData() {
            this.state = AppState.NO_SCANNER_ATTACHED;
            this.captureType = -1;
            this.status = "";
            this.frameTime = SimpleMatchActivity.FRAME_TIME_DEFAULT;
            this.actionState = "";
            this.imageBitmap = null;
            this.fingerQualityColors = new int[]{-3355444, -3355444, -3355444, -3355444};
            this.imagePreviewImageClickable = false;
            this.description = SimpleMatchActivity.NO_DEVICE_DESCRIPTION_STRING;
            this.descriptionColor = SupportMenu.CATEGORY_MASK;
            this.deviceCount = 0;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constant.SEARCH_EMPLOYEE)) {
            this.isEmployeeSearching = getIntent().getBooleanExtra(Constant.SEARCH_EMPLOYEE, false);
        }
        if (getIntent().hasExtra(Constant.START_MONITORING)) {
            this.checkMA = getIntent().getBooleanExtra(Constant.START_MONITORING, false);
        }
        if (getIntent().hasExtra(Constant.START_MONITORING_MA)) {
            this.thumbMatchMA = getIntent().getBooleanExtra(Constant.START_MONITORING_MA, false);
        }
        if (getIntent().hasExtra(Constant.Take_Attendance)) {
            this.takeAttendance = true;
            this.eId = getIntent().getStringExtra(Constant.Take_Attendance);
        }
        IBScan iBScan = IBScan.getInstance(getApplicationContext());
        this.m_ibScan = iBScan;
        iBScan.setScanListener(this);
        this.m_ibMatcher = IBMatcher.getInstance();
        this.m_ibMatcherDatabase = new IBMatcherDatabase(getApplicationContext());
        onConfigurationChanged(Resources.getSystem().getConfiguration());
        resetButtonsForState(AppState.NO_SCANNER_ATTACHED);
        transitionToRefresh();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == 1) {
            setContentView(R.layout.ib_scan_port);
        } else {
            setContentView(R.layout.ib_scan_land);
        }
        initUIFields();
        populateUI();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        IBScanDevice iBScanDevice = this.m_ibScanDevice;
        if (iBScanDevice != null) {
            iBScanDevice.setScanDeviceListener(null);
        }
        IBScan iBScan = this.m_ibScan;
        if (iBScan != null) {
            iBScan.setScanListener(null);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        transitionToStoppingCapture();
        dismissProgressDialog();
        new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.1
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.transitionToClosing();
            }
        }, 500L);
        new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.2
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.finish();
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

    @Override // android.app.Activity
    public Object onRetainNonConfigurationInstance() {
        this.m_scanHandler.removeCallbacksAndMessages(null);
        IBScanDevice iBScanDevice = this.m_ibScanDevice;
        if (iBScanDevice != null) {
            try {
                if (iBScanDevice.isCaptureActive()) {
                    this.m_ibScanDevice.cancelCaptureImage();
                }
            } catch (IBScanException e) {
                Log.e(SIMPLE_MATCH_TAG, "error canceling capture " + e.getType().toString());
            }
            try {
                this.m_ibScanDevice.close();
            } catch (IBScanException e2) {
                Log.e(SIMPLE_MATCH_TAG, "error closing device " + e2.getType().toString());
            }
            this.m_ibScanDevice = null;
        }
        return null;
    }

    private void initUIFields() {
        this.m_txtDeviceCount = (TextView) findViewById(R.id.device_count);
        this.m_txtStatus = (TextView) findViewById(R.id.status);
        this.m_txtDesciption = (TextView) findViewById(R.id.description);
        this.m_txtFingerQuality[0] = (TextView) findViewById(R.id.scan_states_color1);
        this.m_txtFingerQuality[1] = (TextView) findViewById(R.id.scan_states_color2);
        this.m_txtFingerQuality[2] = (TextView) findViewById(R.id.scan_states_color3);
        this.m_txtFingerQuality[3] = (TextView) findViewById(R.id.scan_states_color4);
        this.m_txtFrameTime = (TextView) findViewById(R.id.frame_time);
        this.m_txtActionState = (TextView) findViewById(R.id.action_state);
        this.m_txtSDKVersion = (TextView) findViewById(R.id.version);
        ImageView imageView = (ImageView) findViewById(R.id.preview_image);
        this.m_imagePreviewImage = imageView;
        imageView.setBackgroundColor(-3355444);
        Button button = (Button) findViewById(R.id.stop_capture_btn);
        this.m_stopCaptureBtn = button;
        button.setOnClickListener(this.m_stopCaptureBtnClickListener);
        Button button2 = (Button) findViewById(R.id.start_capture_btn);
        this.m_startCaptureBtn = button2;
        button2.setOnClickListener(this.m_startCaptureBtnClickListener);
        Button button3 = (Button) findViewById(R.id.open_scanner_btn);
        this.m_openScannerBtn = button3;
        button3.setOnClickListener(this.m_openScannerBtnClickListener);
        Button button4 = (Button) findViewById(R.id.close_scanner_btn);
        this.m_closeScannerBtn = button4;
        button4.setOnClickListener(this.m_closeScannerBtnClickListener);
        Button button5 = (Button) findViewById(R.id.refresh_btn);
        this.m_refreshBtn = button5;
        button5.setOnClickListener(this.m_refreshBtnClickListener);
        this.m_emptyBtn = (Button) findViewById(R.id.empty_btn);
        this.m_spinnerCaptureType = (Spinner) findViewById(R.id.capture_type);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, new CharSequence[0]);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.m_spinnerCaptureType.setAdapter((SpinnerAdapter) arrayAdapter);
        this.m_spinnerCaptureType.setOnItemSelectedListener(this.m_captureTypeItemSelectedListener);
        this.m_spinnerActionType = (Spinner) findViewById(R.id.action_type);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, new CharSequence[]{"Capture", "Match", "Single enroll", "Multi enroll"});
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.m_spinnerActionType.setAdapter((SpinnerAdapter) arrayAdapter2);
        this.m_spinnerActionType.setSelection(1);
        this.m_spinnerActionType.setEnabled(false);
        Button button6 = (Button) findViewById(R.id.view_database_btn);
        this.m_viewDatabaseBtn = button6;
        button6.setOnClickListener(this.m_viewDatabaseBtnClickListener);
    }

    private void populateUI() {
        resetButtonsForState(this.m_savedData.state);
        setSDKVersionInfo();
        setDeviceCount(this.m_savedData.deviceCount);
        setDescription(this.m_savedData.description, this.m_savedData.descriptionColor);
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
    public void showToastOnUiThread(final String message, final int duration) {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.3
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(SimpleMatchActivity.this.getApplicationContext(), message, duration).show();
            }
        });
    }

    private void setSDKVersionInfo() {
        final String str;
        try {
            str = "SDK version: " + this.m_ibMatcher.getSdkVersion().file;
        } catch (IBMatcherException unused) {
            str = "(failure)";
        }
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.4
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.m_txtSDKVersion.setText(str);
            }
        });
    }

    private void setDescription(final String description, final int color) {
        this.m_savedData.description = description;
        this.m_savedData.descriptionColor = color;
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.5
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.m_txtDesciption.setText(description);
                SimpleMatchActivity.this.m_txtDesciption.setBackgroundColor(color);
            }
        });
    }

    private void setDeviceCount(final int deviceCount) {
        this.m_savedData.deviceCount = deviceCount;
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.6
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.m_txtDeviceCount.setText("" + deviceCount);
            }
        });
    }

    private void setStatus(final String s) {
        this.m_savedData.status = s;
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.7
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.m_txtStatus.setText(s);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrameTime(final String s) {
        this.m_savedData.frameTime = s;
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.8
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.m_txtFrameTime.setText(s);
            }
        });
    }

    private void setActionState(final String s) {
        this.m_savedData.actionState = s;
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.9
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.m_txtActionState.setText(s);
            }
        });
    }

    private void setCaptureTypes(final String[] captureTypes) {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.10
            @Override // java.lang.Runnable
            public void run() {
                ArrayAdapter arrayAdapter = new ArrayAdapter(SimpleMatchActivity.this, android.R.layout.simple_spinner_item, captureTypes);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SimpleMatchActivity.this.m_spinnerCaptureType.setAdapter((SpinnerAdapter) arrayAdapter);
            }
        });
    }

    private void showProgressDialog(final String title) {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.11
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.m_progressDialog = new ProgressDialog(SimpleMatchActivity.this);
                SimpleMatchActivity.this.m_progressDialog.setIndeterminate(true);
                SimpleMatchActivity.this.m_progressDialog.setTitle(title);
                SimpleMatchActivity.this.m_progressDialog.show();
                SimpleMatchActivity.this.m_progressDialog.setCancelable(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideProgressDialog() {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.12
            @Override // java.lang.Runnable
            public void run() {
                if (SimpleMatchActivity.this.m_progressDialog != null) {
                    SimpleMatchActivity.this.m_progressDialog.hide();
                }
            }
        });
    }

    private void dismissProgressDialog() {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.13
            @Override // java.lang.Runnable
            public void run() {
                if (SimpleMatchActivity.this.m_progressDialog != null) {
                    SimpleMatchActivity.this.m_progressDialog.dismiss();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEnlargedImage() {
        if (this.m_lastImageExt == null) {
            showToastOnUiThread("No last image information", 0);
            return;
        }
        Dialog dialog = new Dialog(this, R.style.Enlarged);
        this.m_enlargedDialog = dialog;
        dialog.setContentView(R.layout.enlarged);
        this.m_enlargedDialog.setCancelable(false);
        Bitmap bitmapConvertImageToBitmap = this.m_ibMatcher.convertImageToBitmap(this.m_lastImageExt);
        ImageView imageView = (ImageView) this.m_enlargedDialog.findViewById(R.id.enlarged_image);
        imageView.setImageBitmap(bitmapConvertImageToBitmap);
        imageView.setOnClickListener(this.m_enlargedImageClickListener);
        this.m_enlargedDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDatabase() {
        Dialog dialog = new Dialog(this, R.style.Enlarged);
        this.m_databaseDialog = dialog;
        dialog.setContentView(R.layout.database_list);
        this.m_databaseDialog.setCancelable(false);
        Cursor cursor = this.m_ibMatcherDatabase.getCursor();
        ListView listView = (ListView) this.m_databaseDialog.findViewById(android.R.id.list);
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this.m_databaseDialog.getContext(), R.layout.database_list_item, cursor, new String[]{"name", IBMatcherDatabase.COLUMN_NAME_CREATE_DATE, IBMatcherDatabase.COLUMN_NAME_MODIFY_DATE, IBMatcherDatabase.COLUMN_NAME_DESCRIPTION}, new int[]{R.id.database_name, R.id.database_create, R.id.database_modify, R.id.database_description}, 0);
        simpleCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.14
            @Override // android.widget.SimpleCursorAdapter.ViewBinder
            public boolean setViewValue(final View view, final Cursor cursor2, final int columnIndex) {
                if (columnIndex == 3) {
                    TextView textView = (TextView) view.findViewById(R.id.database_create);
                    Date date = new Date(Long.valueOf(cursor2.getLong(columnIndex)).longValue());
                    textView.setText(DateFormat.getDateInstance().format(date) + " " + DateFormat.getTimeInstance().format(date));
                    return true;
                }
                if (columnIndex != 4) {
                    return false;
                }
                TextView textView2 = (TextView) view.findViewById(R.id.database_modify);
                Date date2 = new Date(Long.valueOf(cursor2.getLong(columnIndex)).longValue());
                textView2.setText(DateFormat.getDateInstance().format(date2) + " " + DateFormat.getTimeInstance().format(date2));
                return true;
            }
        });
        listView.setAdapter((ListAdapter) simpleCursorAdapter);
        ((TextView) this.m_databaseDialog.findViewById(R.id.database_list_size)).setText("" + this.m_ibMatcherDatabase.getSize() + " bytes");
        ((Button) this.m_databaseDialog.findViewById(R.id.database_list_done)).setOnClickListener(this.m_databaseDoneClickListener);
        ((Button) this.m_databaseDialog.findViewById(R.id.database_list_clear)).setOnClickListener(this.m_databaseClearClickListener);
        this.m_databaseDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendImageInEmail(final IBCommon.ImageDataExt imageData, final String fileName, final OutputFormat format) throws IOException {
        boolean zCreatePng;
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + fileName);
        boolean z = false;
        try {
            file.createNewFile();
            switch (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat[format.ordinal()]) {
                case 1:
                    zCreatePng = IBHelpers.createPng(imageData, file);
                    z = zCreatePng;
                    break;
                case 2:
                    zCreatePng = IBHelpers.createWsq(imageData, file);
                    z = zCreatePng;
                    break;
                case 3:
                    zCreatePng = IBHelpers.createFir(imageData, file);
                    z = zCreatePng;
                    break;
                case 4:
                    zCreatePng = IBHelpers.createFmr(imageData, file);
                    z = zCreatePng;
                    break;
                case 5:
                    zCreatePng = IBHelpers.createIbsmImage(imageData, file);
                    z = zCreatePng;
                    break;
                case 6:
                    zCreatePng = IBHelpers.createIbsmTemplate(imageData, file);
                    z = zCreatePng;
                    break;
            }
        } catch (IOException e) {
            showToastOnUiThread("Could not create image for e-mail " + e.toString(), 1);
        }
        if (z) {
            attachAndSendEmail(Uri.fromFile(file), "Fingerprint Image", fileName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendImageInEmail(final com.integratedbiometrics.ibscanmatcher.IBMatcher.Template r4, final java.lang.String r5, final com.micromerger.ssms.startmonitoring.SimpleMatchActivity.OutputFormat r6) throws java.io.IOException {
        /*
            r3 = this;
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r2 = r2.getPath()
            r1.append(r2)
            java.lang.String r2 = "/"
            r1.append(r2)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            r1 = 0
            r0.createNewFile()     // Catch: java.io.IOException -> L3f
            int[] r2 = com.micromerger.ssms.startmonitoring.SimpleMatchActivity.AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat     // Catch: java.io.IOException -> L3f
            int r6 = r6.ordinal()     // Catch: java.io.IOException -> L3f
            r6 = r2[r6]     // Catch: java.io.IOException -> L3f
            r2 = 4
            if (r6 == r2) goto L39
            r2 = 6
            if (r6 == r2) goto L34
            goto L59
        L34:
            boolean r4 = com.micromerger.ssms.utils.biometric.IBHelpers.createIbsmTemplate(r4, r0)     // Catch: java.io.IOException -> L3f
            goto L3d
        L39:
            boolean r4 = com.micromerger.ssms.utils.biometric.IBHelpers.createFmr(r4, r0)     // Catch: java.io.IOException -> L3f
        L3d:
            r1 = r4
            goto L59
        L3f:
            r4 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r2 = "Could not create image for e-mail "
            r6.append(r2)
            java.lang.String r4 = r4.toString()
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r6 = 1
            r3.showToastOnUiThread(r4, r6)
        L59:
            if (r1 == 0) goto L64
            android.net.Uri r4 = android.net.Uri.fromFile(r0)
            java.lang.String r6 = "Fingerprint Image"
            r3.attachAndSendEmail(r4, r6, r5)
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.sendImageInEmail(com.integratedbiometrics.ibscanmatcher.IBMatcher$Template, java.lang.String, com.micromerger.ssms.startmonitoring.SimpleMatchActivity$OutputFormat):void");
    }

    private void attachAndSendEmail(final Uri uri, final String subject, final String message) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{""});
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.putExtra("android.intent.extra.TEXT", message);
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (ActivityNotFoundException unused) {
            showToastOnUiThread("There are no e-mail clients installed", 1);
        }
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$15, reason: invalid class name */
    class AnonymousClass15 implements Runnable {
        final /* synthetic */ IBCommon.ImageDataExt val$imageData;

        AnonymousClass15(final IBCommon.ImageDataExt val$imageData) {
            this.val$imageData = val$imageData;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertDialog.Builder builder = new AlertDialog.Builder(SimpleMatchActivity.this);
            String[] strArr = {Camera.IMAGE_PNG, "wsq", "fir", "fmr", "ibsm_image", "ibsm_template"};
            builder.setTitle("Select format").setItems(new String[]{"PNG", "WSQ", "Fingerprint Image Record", "Fingerprint Minutiae Record", "IBSM image", "IBSM template"}, new AnonymousClass1(strArr));
            builder.create().show();
        }

        /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$15$1, reason: invalid class name */
        class AnonymousClass1 implements DialogInterface.OnClickListener {
            final /* synthetic */ String[] val$extensions;

            AnonymousClass1(final String[] val$extensions) {
                this.val$extensions = val$extensions;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                final OutputFormat outputFormat = OutputFormat.values()[which];
                final View viewInflate = SimpleMatchActivity.this.getLayoutInflater().inflate(R.layout.file_name_dialog, (ViewGroup) null);
                AlertDialog.Builder negativeButton = new AlertDialog.Builder(SimpleMatchActivity.this).setView(viewInflate).setTitle("Enter file name").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.15.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialog2, final int which2) {
                        final String string = ((EditText) viewInflate.findViewById(R.id.file_name)).getText().toString();
                        new Thread() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.15.1.1.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() throws IOException {
                                SimpleMatchActivity.this.sendImageInEmail(AnonymousClass15.this.val$imageData, string, outputFormat);
                            }
                        }.start();
                    }
                }).setNegativeButton("Cancel", (DialogInterface.OnClickListener) null);
                ((EditText) viewInflate.findViewById(R.id.file_name)).setText("output." + this.val$extensions[which]);
                negativeButton.create().show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void promptForEmail(final IBCommon.ImageDataExt imageData) {
        runOnUiThread(new AnonymousClass15(imageData));
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$16, reason: invalid class name */
    class AnonymousClass16 implements Runnable {
        final /* synthetic */ IBMatcher.Template val$template;

        AnonymousClass16(final IBMatcher.Template val$template) {
            this.val$template = val$template;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertDialog.Builder builder = new AlertDialog.Builder(SimpleMatchActivity.this);
            builder.setTitle("Select format").setItems(new String[]{"Fingerprint Minutiae Record", "IBSM template"}, new AnonymousClass1(new String[]{"fir", "ibsm_template"}));
            builder.create().show();
        }

        /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$16$1, reason: invalid class name */
        class AnonymousClass1 implements DialogInterface.OnClickListener {
            final /* synthetic */ String[] val$extensions;

            AnonymousClass1(final String[] val$extensions) {
                this.val$extensions = val$extensions;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                final OutputFormat outputFormat = which == 0 ? OutputFormat.FMR : OutputFormat.IBSM_TEMPLATE;
                final View viewInflate = SimpleMatchActivity.this.getLayoutInflater().inflate(R.layout.file_name_dialog, (ViewGroup) null);
                AlertDialog.Builder negativeButton = new AlertDialog.Builder(SimpleMatchActivity.this).setView(viewInflate).setTitle("Enter file name").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.16.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialog2, final int which2) {
                        final String string = ((EditText) viewInflate.findViewById(R.id.file_name)).getText().toString();
                        new Thread() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.16.1.1.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() throws IOException {
                                SimpleMatchActivity.this.sendImageInEmail(AnonymousClass16.this.val$template, string, outputFormat);
                            }
                        }.start();
                    }
                }).setNegativeButton("Cancel", (DialogInterface.OnClickListener) null);
                ((EditText) viewInflate.findViewById(R.id.file_name)).setText("output." + this.val$extensions[which]);
                negativeButton.create().show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void promptForEmail(final IBMatcher.Template template) {
        runOnUiThread(new AnonymousClass16(template));
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$17, reason: invalid class name */
    class AnonymousClass17 implements Runnable {
        final /* synthetic */ IBMatcher.Template val$template;

        AnonymousClass17(final IBMatcher.Template val$template) {
            this.val$template = val$template;
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewInflate = SimpleMatchActivity.this.getLayoutInflater().inflate(R.layout.user_create_dialog, (ViewGroup) null);
            AlertDialog.Builder negativeButton = new AlertDialog.Builder(SimpleMatchActivity.this).setView(viewInflate).setTitle("Enter user details").setPositiveButton("OK", new AnonymousClass1(viewInflate)).setNegativeButton("Cancel", (DialogInterface.OnClickListener) null);
            ((EditText) viewInflate.findViewById(R.id.user_name)).setText("");
            ((EditText) viewInflate.findViewById(R.id.user_description)).setText("");
            negativeButton.create().show();
        }

        /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$17$1, reason: invalid class name */
        class AnonymousClass1 implements DialogInterface.OnClickListener {
            final /* synthetic */ View val$userDetailsView;

            AnonymousClass1(final View val$userDetailsView) {
                this.val$userDetailsView = val$userDetailsView;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                new C01211(((EditText) this.val$userDetailsView.findViewById(R.id.user_name)).getText().toString(), ((EditText) this.val$userDetailsView.findViewById(R.id.user_description)).getText().toString()).start();
            }

            /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$17$1$1, reason: invalid class name and collision with other inner class name */
            class C01211 extends Thread {
                final /* synthetic */ String val$userDescription;
                final /* synthetic */ String val$userName;

                C01211(final String val$userName, final String val$userDescription) {
                    this.val$userName = val$userName;
                    this.val$userDescription = val$userDescription;
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (SimpleMatchActivity.this.m_ibMatcherDatabase.find(this.val$userName) == null) {
                        if (SimpleMatchActivity.this.m_ibMatcherDatabase.enroll(this.val$userName, this.val$userDescription, AnonymousClass17.this.val$template)) {
                            SimpleMatchActivity.this.showAlert("User now enrolled", "");
                            return;
                        } else {
                            SimpleMatchActivity.this.showAlert("Could not enroll user", "");
                            return;
                        }
                    }
                    SimpleMatchActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.17.1.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            new AlertDialog.Builder(SimpleMatchActivity.this).setTitle("Could not enroll user").setMessage("User already enrolled.  Would you like to update user's entry?").setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.17.1.1.1.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialog, int which) {
                                    if (SimpleMatchActivity.this.m_ibMatcherDatabase.update(C01211.this.val$userName, C01211.this.val$userDescription, AnonymousClass17.this.val$template)) {
                                        SimpleMatchActivity.this.showAlert("User updated", "");
                                    } else {
                                        SimpleMatchActivity.this.showAlert("Could not update user", "");
                                    }
                                }
                            }).setNegativeButton("No", (DialogInterface.OnClickListener) null).create().show();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void promptForEnroll(final IBMatcher.Template template) {
        runOnUiThread(new AnonymousClass17(template));
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$18, reason: invalid class name */
    class AnonymousClass18 implements Runnable {
        final /* synthetic */ IBMatcher.Template val$template;

        AnonymousClass18(final IBMatcher.Template val$template) {
            this.val$template = val$template;
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewInflate = SimpleMatchActivity.this.getLayoutInflater().inflate(R.layout.user_create_dialog, (ViewGroup) null);
            AlertDialog.Builder negativeButton = new AlertDialog.Builder(SimpleMatchActivity.this).setView(viewInflate).setTitle("Enter user details").setPositiveButton("OK", new AnonymousClass1(viewInflate)).setNegativeButton("Cancel", (DialogInterface.OnClickListener) null);
            ((EditText) viewInflate.findViewById(R.id.user_name)).setText("");
            ((EditText) viewInflate.findViewById(R.id.user_description)).setText("");
            negativeButton.create().show();
        }

        /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$18$1, reason: invalid class name */
        class AnonymousClass1 implements DialogInterface.OnClickListener {
            final /* synthetic */ View val$userDetailsView;

            AnonymousClass1(final View val$userDetailsView) {
                this.val$userDetailsView = val$userDetailsView;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                new C01241(((EditText) this.val$userDetailsView.findViewById(R.id.user_name)).getText().toString(), ((EditText) this.val$userDetailsView.findViewById(R.id.user_description)).getText().toString()).start();
            }

            /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$18$1$1, reason: invalid class name and collision with other inner class name */
            class C01241 extends Thread {
                final /* synthetic */ String val$userDescription;
                final /* synthetic */ String val$userName;

                C01241(final String val$userName, final String val$userDescription) {
                    this.val$userName = val$userName;
                    this.val$userDescription = val$userDescription;
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (SimpleMatchActivity.this.m_ibMatcherDatabase.find(this.val$userName) != null) {
                        if (SimpleMatchActivity.this.m_ibMatcherDatabase.update(this.val$userName, this.val$userDescription, AnonymousClass18.this.val$template)) {
                            SimpleMatchActivity.this.showAlert("User updated", "");
                            return;
                        } else {
                            SimpleMatchActivity.this.showAlert("Could not update user", "");
                            return;
                        }
                    }
                    SimpleMatchActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.18.1.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            new AlertDialog.Builder(SimpleMatchActivity.this).setTitle("Could not enroll user").setMessage("No user with name \"" + C01241.this.val$userName + "\" enrolled.  Would you like to add an entry for this user?").setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.18.1.1.1.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(final DialogInterface dialog, final int which) {
                                    if (SimpleMatchActivity.this.m_ibMatcherDatabase.enroll(C01241.this.val$userName, C01241.this.val$userDescription, AnonymousClass18.this.val$template)) {
                                        SimpleMatchActivity.this.showAlert("User now enrolled", "");
                                    } else {
                                        SimpleMatchActivity.this.showAlert("Could not enroll user", "");
                                    }
                                }
                            }).setNegativeButton("No", (DialogInterface.OnClickListener) null).create().show();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void promptForUpdate(final IBMatcher.Template template) {
        runOnUiThread(new AnonymousClass18(template));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void promptForEnrollAction(final IBMatcher.Template template) {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.19
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(SimpleMatchActivity.this);
                builder.setTitle("Select action").setItems(new String[]{"Enroll new user", "Update existing user", "E-mail template"}, new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.19.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialog, final int which) {
                        if (which == 0) {
                            SimpleMatchActivity.this.promptForEnroll(template);
                        } else if (which == 1) {
                            SimpleMatchActivity.this.promptForUpdate(template);
                        } else {
                            if (which != 2) {
                                return;
                            }
                            SimpleMatchActivity.this.promptForEmail(template);
                        }
                    }
                });
                builder.create().show();
            }
        });
    }

    private void showMatch(final IBMatcherDatabase.Entry entry) {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.20
            @Override // java.lang.Runnable
            public void run() {
                if (entry == null) {
                    SimpleMatchActivity.this.showAlert("No matching user", "");
                    return;
                }
                View viewInflate = SimpleMatchActivity.this.getLayoutInflater().inflate(R.layout.user_details_dialog, (ViewGroup) null);
                AlertDialog.Builder positiveButton = new AlertDialog.Builder(SimpleMatchActivity.this).setView(viewInflate).setTitle("Matching user details").setPositiveButton("OK", (DialogInterface.OnClickListener) null);
                ((TextView) viewInflate.findViewById(R.id.user_details_name)).setText(entry.getName());
                ((TextView) viewInflate.findViewById(R.id.user_details_description)).setText(entry.getDescription());
                ((TextView) viewInflate.findViewById(R.id.user_details_create_date)).setText(DateFormat.getDateInstance().format(entry.getCreateDate()) + " " + DateFormat.getTimeInstance().format(entry.getCreateDate()));
                ((TextView) viewInflate.findViewById(R.id.user_details_modify_date)).setText(DateFormat.getDateInstance().format(entry.getModifyDate()) + " " + DateFormat.getTimeInstance().format(entry.getModifyDate()));
                ((TextView) viewInflate.findViewById(R.id.user_details_match_score)).setText("" + entry.getMatchScore());
                positiveButton.create().show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlert(final String title, final String message) {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.21
            @Override // java.lang.Runnable
            public void run() {
                new AlertDialog.Builder(SimpleMatchActivity.this).setTitle(title).setMessage(message).setPositiveButton("OK", (DialogInterface.OnClickListener) null).create().show();
            }
        });
    }

    private static void exitApp(final Activity ac) {
        ac.moveTaskToBack(true);
        ac.finish();
        Process.killProcess(Process.myPid());
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

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToNoScannerAttached() {
        if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()] != 3) {
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
        if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()] != 3) {
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
        switch (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()]) {
            case 1:
            case 2:
            case 6:
                this.m_savedData.state = AppState.REFRESH;
                resetButtonsForState(AppState.REFRESH);
                setStatus("refreshing");
                setFrameTime(FRAME_TIME_DEFAULT);
                setActionState("");
                setCaptureTypes(new String[0]);
                UsbManager usbManager = (UsbManager) getApplicationContext().getSystemService("usb");
                for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
                    try {
                        if (IBScan.isScanDevice(usbDevice) && !usbManager.hasPermission(usbDevice)) {
                            this.m_ibScan.requestPermission(usbDevice.getDeviceId());
                        }
                    } catch (Exception e) {
                        util.logException(e);
                        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$SimpleMatchActivity$b5idDs3obj8E2tMawwDKpYf4p6M
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$handleTransitionToRefresh$0$SimpleMatchActivity();
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
            case 3:
            case 4:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                break;
            case 5:
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
            default:
                Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to REFRESH from " + this.m_savedData.state.toString());
                break;
        }
    }

    public /* synthetic */ void lambda$handleTransitionToRefresh$0$SimpleMatchActivity() {
        Toast.makeText(this, "Unable to initialize Scanner", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToInitializing(final int deviceIndex) {
        if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()] != 2) {
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
        int i = AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()];
        if (i != 4 && i != 7 && i != 9 && i != 10) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to INITIALIZED from " + this.m_savedData.state.toString());
            return;
        }
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
            new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.23
                @Override // java.lang.Runnable
                public void run() {
                    int selectedItemPosition = SimpleMatchActivity.this.m_spinnerActionType.getSelectedItemPosition();
                    SimpleMatchActivity.this.transitionToStartingCapture(selectedItemPosition == -1 ? ActionType.values()[0] : ActionType.values()[selectedItemPosition]);
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToClosing() {
        int i = AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()];
        if (i != 4 && i != 5 && i != 11) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to CLOSING from " + this.m_savedData.state.toString());
            return;
        }
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToStartingCapture(final ActionType actionType) {
        int i = AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()];
        if (i != 5 && i != 10) {
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
        if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()] != 7) {
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
        int i = AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()];
        if (i != 8 && i != 9) {
            hideProgressDialog();
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
        if (AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()] != 8) {
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
        int i3 = AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$ActionType[this.m_actionType.ordinal()];
        if (i3 == 1) {
            new Thread() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.24
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        int iCalculateNfiqScore = SimpleMatchActivity.this.m_ibScanDevice.calculateNfiqScore(image);
                        SimpleMatchActivity.this.showToastOnUiThread("NFIQ score for print is " + iCalculateNfiqScore, 0);
                    } catch (IBScanException e2) {
                        SimpleMatchActivity.this.showToastOnUiThread("Error calculating NFIQ score " + e2.getType().toString(), 0);
                    }
                }
            }.start();
        } else if (i3 == 2) {
            final IBCommon.ImageDataExt[] imageDataExtArr = this.m_lastImageExts;
            showProgressDialog("Processing...");
            new Thread() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.25
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        try {
                            IBMatcher.Template templateExtractTemplate = SimpleMatchActivity.this.m_ibMatcher.extractTemplate(imageDataExtArr[0]);
                            if (templateExtractTemplate == null) {
                                SimpleMatchActivity.this.showAlert("Could not match", "Error generating template");
                                return;
                            }
                            if (SimpleMatchActivity.this.isEmployeeSearching) {
                                SimpleMatchActivity simpleMatchActivity = SimpleMatchActivity.this;
                                IBMatcherDatabase iBMatcherDatabase = simpleMatchActivity.m_ibMatcherDatabase;
                                SimpleMatchActivity simpleMatchActivity2 = SimpleMatchActivity.this;
                                simpleMatchActivity.matchCheck = iBMatcherDatabase.getSavedEmployee(templateExtractTemplate, simpleMatchActivity2, simpleMatchActivity2.schoolIDWithDistrict, SimpleMatchActivity.this.getApplicationContext());
                            } else if (SimpleMatchActivity.this.checkMA) {
                                SimpleMatchActivity simpleMatchActivity3 = SimpleMatchActivity.this;
                                IBMatcherDatabase iBMatcherDatabase2 = simpleMatchActivity3.m_ibMatcherDatabase;
                                SimpleMatchActivity simpleMatchActivity4 = SimpleMatchActivity.this;
                                simpleMatchActivity3.matchCheck = iBMatcherDatabase2.getSavedTemplate(templateExtractTemplate, simpleMatchActivity4, simpleMatchActivity4.getApplicationContext());
                            } else if (SimpleMatchActivity.this.thumbMatchMA) {
                                SimpleMatchActivity simpleMatchActivity5 = SimpleMatchActivity.this;
                                simpleMatchActivity5.matchCheck = simpleMatchActivity5.m_ibMatcherDatabase.getSavedTemplate(templateExtractTemplate, SimpleMatchActivity.this.getApplicationContext());
                            } else if (SimpleMatchActivity.this.takeAttendance) {
                                SimpleMatchActivity simpleMatchActivity6 = SimpleMatchActivity.this;
                                IBMatcherDatabase iBMatcherDatabase3 = simpleMatchActivity6.m_ibMatcherDatabase;
                                SimpleMatchActivity simpleMatchActivity7 = SimpleMatchActivity.this;
                                simpleMatchActivity6.matchCheck = iBMatcherDatabase3.checkEmployee(templateExtractTemplate, simpleMatchActivity7, simpleMatchActivity7.getApplicationContext(), SimpleMatchActivity.this.eId);
                            }
                            SimpleMatchActivity.this.hideProgressDialog();
                            Message message = new Message();
                            message.what = 1;
                            SimpleMatchActivity.this.handle.sendMessage(message);
                        } catch (IBMatcherException e2) {
                            SimpleMatchActivity.this.hideProgressDialog();
                            SimpleMatchActivity.this.showAlert("Could not match", "Error generating template");
                            Log.e(SimpleMatchActivity.SIMPLE_MATCH_TAG, "Error generating template for " + e2.getType().toString());
                        }
                    } catch (Exception e3) {
                        SimpleMatchActivity.this.hideProgressDialog();
                        e3.printStackTrace();
                        SimpleMatchActivity.this.showAlert("Could not match", "Error generating template");
                    }
                }
            }.start();
        } else if (i3 == 3) {
            final IBCommon.ImageDataExt[] imageDataExtArr2 = this.m_lastImageExts;
            showProgressDialog("Processing...");
            new Thread() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.26
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        IBMatcher iBMatcher = SimpleMatchActivity.this.m_ibMatcher;
                        IBCommon.ImageDataExt[] imageDataExtArr3 = imageDataExtArr2;
                        IBMatcher.Template templateSingleEnrollment = iBMatcher.singleEnrollment(imageDataExtArr3[0], imageDataExtArr3[1], imageDataExtArr3[2]);
                        SimpleMatchActivity.this.hideProgressDialog();
                        SimpleMatchActivity.this.promptForEnrollAction(templateSingleEnrollment);
                    } catch (IBMatcherException e2) {
                        SimpleMatchActivity.this.hideProgressDialog();
                        SimpleMatchActivity.this.showAlert("Could not enroll user", "Error generating template.  Please retry.");
                        Log.e(SimpleMatchActivity.SIMPLE_MATCH_TAG, "Error generating template for " + e2.getType().toString());
                    }
                }
            }.start();
        } else if (i3 == 4) {
            final IBCommon.ImageDataExt[] imageDataExtArr3 = this.m_lastImageExts;
            showProgressDialog("Processing...");
            new Thread() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.27
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        IBMatcher iBMatcher = SimpleMatchActivity.this.m_ibMatcher;
                        IBCommon.ImageDataExt[] imageDataExtArr4 = imageDataExtArr3;
                        IBMatcher.Template[] templateArrMultiEnrollment = iBMatcher.multiEnrollment(imageDataExtArr4[0], imageDataExtArr4[1], imageDataExtArr4[2], imageDataExtArr4[3], imageDataExtArr4[4], imageDataExtArr4[5]);
                        SimpleMatchActivity.this.hideProgressDialog();
                        SimpleMatchActivity.this.promptForEnrollAction(templateArrMultiEnrollment[0]);
                    } catch (IBMatcherException e2) {
                        SimpleMatchActivity.this.hideProgressDialog();
                        SimpleMatchActivity.this.showAlert("Could not enroll user", "Error generating template.  Please retry.");
                        Log.e(SimpleMatchActivity.SIMPLE_MATCH_TAG, "Error generating template for " + e2.getType().toString());
                    }
                }
            }.start();
        }
        transitionToInitialized(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionToCommunicationBreak() {
        int i = AnonymousClass45.$SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[this.m_savedData.state.ordinal()];
        if (i != 5 && i != 8 && i != 9) {
            Log.e(SIMPLE_MATCH_TAG, "Received unexpected transition to COMMUNICATION_BREAK from " + this.m_savedData.state.toString());
            return;
        }
        this.m_savedData.state = AppState.COMMUNICATION_BREAK;
        resetButtonsForState(AppState.COMMUNICATION_BREAK);
        setStatus("comm break");
        setFrameTime(FRAME_TIME_DEFAULT);
        transitionToClosing();
    }

    private void resetButtonsForState(final AppState state) {
        boolean[] zArr = {false, false, false, false, true, false, false, false, false, false, false};
        final boolean z = new boolean[]{false, false, false, false, false, false, false, true, false, false, false}[state.ordinal()];
        final boolean z2 = new boolean[]{false, false, false, false, true, false, false, false, false, false, false}[state.ordinal()];
        final boolean z3 = new boolean[]{true, true, false, false, false, false, false, false, false, false, false}[state.ordinal()];
        final boolean z4 = new boolean[]{false, false, false, false, true, false, false, false, false, false, false}[state.ordinal()];
        final boolean z5 = new boolean[]{false, true, false, false, false, false, false, false, false, false, false}[state.ordinal()];
        final boolean z6 = zArr[state.ordinal()];
        final boolean z7 = zArr[state.ordinal()];
        final boolean z8 = new boolean[]{true, true, false, false, true, false, false, false, false, false, false}[state.ordinal()];
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.28
            @Override // java.lang.Runnable
            public void run() {
                SimpleMatchActivity.this.m_stopCaptureBtn.setEnabled(z);
                SimpleMatchActivity.this.m_stopCaptureBtn.setClickable(z);
                SimpleMatchActivity.this.m_startCaptureBtn.setEnabled(z2);
                SimpleMatchActivity.this.m_startCaptureBtn.setClickable(z2);
                SimpleMatchActivity.this.m_refreshBtn.setEnabled(z3);
                SimpleMatchActivity.this.m_refreshBtn.setClickable(z3);
                SimpleMatchActivity.this.m_spinnerCaptureType.setEnabled(z4);
                SimpleMatchActivity.this.m_spinnerCaptureType.setClickable(z4);
                SimpleMatchActivity.this.m_openScannerBtn.setEnabled(z5);
                SimpleMatchActivity.this.m_openScannerBtn.setClickable(z5);
                SimpleMatchActivity.this.m_closeScannerBtn.setEnabled(z6);
                SimpleMatchActivity.this.m_closeScannerBtn.setClickable(z6);
                SimpleMatchActivity.this.m_spinnerActionType.setEnabled(z7);
                SimpleMatchActivity.this.m_spinnerActionType.setClickable(z7);
                SimpleMatchActivity.this.m_viewDatabaseBtn.setEnabled(z8);
                SimpleMatchActivity.this.m_viewDatabaseBtn.setClickable(z8);
            }
        });
        final boolean z9 = new boolean[]{false, true, false, false, false, false, false, false, false, false, false}[state.ordinal()];
        final boolean z10 = new boolean[]{false, false, false, false, false, false, false, false, false, false, false}[state.ordinal()];
        final boolean z11 = new boolean[]{false, false, false, false, false, false, false, true, false, false, false}[state.ordinal()];
        final boolean z12 = new boolean[]{false, false, false, false, true, false, false, false, false, false, false}[state.ordinal()];
        final boolean z13 = new boolean[]{true, false, true, true, false, true, true, false, true, true, true}[state.ordinal()];
        CommonObjects.mainActivity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.29
            @Override // java.lang.Runnable
            public void run() {
                if (z9) {
                    SimpleMatchActivity.this.m_openScannerBtn.setVisibility(0);
                } else {
                    SimpleMatchActivity.this.m_openScannerBtn.setVisibility(8);
                }
                if (z10) {
                    SimpleMatchActivity.this.m_closeScannerBtn.setVisibility(0);
                } else {
                    SimpleMatchActivity.this.m_closeScannerBtn.setVisibility(8);
                }
                if (z12) {
                    SimpleMatchActivity.this.m_startCaptureBtn.setVisibility(0);
                } else {
                    SimpleMatchActivity.this.m_startCaptureBtn.setVisibility(8);
                }
                if (z11) {
                    SimpleMatchActivity.this.m_stopCaptureBtn.setVisibility(0);
                } else {
                    SimpleMatchActivity.this.m_stopCaptureBtn.setVisibility(8);
                }
                if (z13) {
                    SimpleMatchActivity.this.m_emptyBtn.setVisibility(0);
                } else {
                    SimpleMatchActivity.this.m_emptyBtn.setVisibility(8);
                }
            }
        });
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceAttached(final int deviceId) {
        try {
            if (this.m_ibScan.hasPermission(deviceId)) {
                return;
            }
            this.m_ibScan.requestPermission(deviceId);
        } catch (Exception e) {
            util.logException(e);
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$SimpleMatchActivity$c6xgcPQ-V4Bq4V5L77PCVJP7EyY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$scanDeviceAttached$1$SimpleMatchActivity();
                }
            });
        }
    }

    public /* synthetic */ void lambda$scanDeviceAttached$1$SimpleMatchActivity() {
        Toast.makeText(this, "Unable to initialize Scanner", 0).show();
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceCountChanged(final int deviceCount) {
        transitionToRefresh();
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceInitProgress(final int deviceIndex, final int progressValue) {
        setStatus("init " + progressValue + "%");
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanListener
    public void scanDeviceOpenComplete(final int deviceIndex, final IBScanDevice device, final IBScanException exception) {
        if (device != null) {
            transitionToInitialized(device);
            return;
        }
        if (exception != null) {
            exception.getType().toString();
        }
        transitionToClosing();
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceCommunicationBroken(final IBScanDevice device) {
        showToastOnUiThread("Communication break with device", 0);
        transitionToCommunicationBreak();
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceFingerCountChanged(final IBScanDevice device, final IBScanDevice.FingerCountState fingerState) {
        int i = AnonymousClass45.$SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerCountState[fingerState.ordinal()];
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
    public void deviceAcquisitionBegun(final IBScanDevice device, final IBScanDevice.ImageType imageType) {
        showToastOnUiThread("Beginning acquisition...roll finger left", 0);
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceAcquisitionCompleted(final IBScanDevice device, final IBScanDevice.ImageType imageType) {
        showToastOnUiThread("Completed acquisition...roll finger right", 0);
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceFingerQualityChanged(final IBScanDevice device, final IBScanDevice.FingerQualityState[] fingerQualities) {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.41
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
                    int i3 = AnonymousClass45.$SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerQualityState[fingerQualities[i].ordinal()];
                    if (i3 == 2) {
                        i2 = SimpleMatchActivity.FINGER_QUALITY_GOOD_COLOR;
                    } else if (i3 == 3) {
                        i2 = -256;
                    } else if (i3 == 4) {
                        i2 = SupportMenu.CATEGORY_MASK;
                    }
                    if (i < SimpleMatchActivity.this.m_txtFingerQuality.length) {
                        SimpleMatchActivity.this.m_savedData.fingerQualityColors[i] = i2;
                        SimpleMatchActivity.this.m_txtFingerQuality[i].setBackgroundColor(i2);
                    }
                    i++;
                }
                for (int length = fingerQualityStateArr.length; length < SimpleMatchActivity.this.m_txtFingerQuality.length; length++) {
                    SimpleMatchActivity.this.m_savedData.fingerQualityColors[length] = -3355444;
                    SimpleMatchActivity.this.m_txtFingerQuality[length].setBackgroundColor(-3355444);
                }
            }
        });
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceImagePreviewAvailable(final IBScanDevice device, final IBScanDevice.ImageData image) {
        IBScanDevice.RollingData rollingInfo;
        final Bitmap bitmapScaled;
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
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.42
                @Override // java.lang.Runnable
                public void run() {
                    SimpleMatchActivity.this.setFrameTime(String.format("%1$.3f", Double.valueOf(image.frameTime)));
                    SimpleMatchActivity.this.m_savedData.imageBitmap = bitmapScaled;
                    SimpleMatchActivity.this.m_imagePreviewImage.setImageBitmap(bitmapScaled);
                }
            });
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void deviceImageResultAvailable(final IBScanDevice device, final IBScanDevice.ImageData image, final IBScanDevice.ImageType imageType, final IBScanDevice.ImageData[] splitImageArray) {
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
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.SimpleMatchActivity.43
                @Override // java.lang.Runnable
                public void run() {
                    SimpleMatchActivity.this.setFrameTime(String.format("%1$.3f", Double.valueOf(image.frameTime)));
                    SimpleMatchActivity.this.m_savedData.imageBitmap = bitmapScaled;
                    SimpleMatchActivity.this.m_imagePreviewImage.setImageBitmap(bitmapScaled);
                }
            });
        }
        transitionToImageCaptured(image, imageType, splitImageArray);
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.SimpleMatchActivity$45, reason: invalid class name */
    static /* synthetic */ class AnonymousClass45 {
        static final /* synthetic */ int[] $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerCountState;
        static final /* synthetic */ int[] $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$FingerQualityState;
        static final /* synthetic */ int[] $SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$PlatenState;
        static final /* synthetic */ int[] $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$ActionType;
        static final /* synthetic */ int[] $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState;
        static final /* synthetic */ int[] $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat;

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
            $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$ActionType = iArr4;
            try {
                iArr4[ActionType.CAPTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$ActionType[ActionType.MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$ActionType[ActionType.SINGLE_ENROLL.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$ActionType[ActionType.MULTI_ENROLL.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            int[] iArr5 = new int[AppState.values().length];
            $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState = iArr5;
            try {
                iArr5[AppState.NO_SCANNER_ATTACHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.SCANNER_ATTACHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.REFRESH.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.INITIALIZING.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.INITIALIZED.ordinal()] = 5;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.CLOSING.ordinal()] = 6;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.STARTING_CAPTURE.ordinal()] = 7;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.CAPTURING.ordinal()] = 8;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.STOPPING_CAPTURE.ordinal()] = 9;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.IMAGE_CAPTURED.ordinal()] = 10;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$AppState[AppState.COMMUNICATION_BREAK.ordinal()] = 11;
            } catch (NoSuchFieldError unused25) {
            }
            int[] iArr6 = new int[OutputFormat.values().length];
            $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat = iArr6;
            try {
                iArr6[OutputFormat.PNG.ordinal()] = 1;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat[OutputFormat.WSQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat[OutputFormat.FIR.ordinal()] = 3;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat[OutputFormat.FMR.ordinal()] = 4;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat[OutputFormat.IBSM_IMAGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$startmonitoring$SimpleMatchActivity$OutputFormat[OutputFormat.IBSM_TEMPLATE.ordinal()] = 6;
            } catch (NoSuchFieldError unused31) {
            }
        }
    }

    @Override // com.integratedbiometrics.ibscanultimate.IBScanDeviceListener
    public void devicePlatenStateChanged(final IBScanDevice device, final IBScanDevice.PlatenState platenState) {
        int i = AnonymousClass45.$SwitchMap$com$integratedbiometrics$ibscanultimate$IBScanDevice$PlatenState[platenState.ordinal()];
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
    public void deviceWarningReceived(final IBScanDevice device, final IBScanException warning) {
        showToastOnUiThread("Warning received " + warning.getType().toString(), 0);
    }
}
