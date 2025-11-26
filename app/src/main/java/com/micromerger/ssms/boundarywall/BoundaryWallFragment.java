package com.micromerger.ssms.boundarywall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.MarshmallowPermissions;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class BoundaryWallFragment extends Fragment {
    private static final String[] ANDROID_VERSIONS = {"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};
    private static final int CAMERA_RESULT = 101;
    private static final int READ_EXTERNAL_STORAGE_RESULT = 103;
    private static final int WRITE_EXTERNAL_STORAGE_RESULT = 102;
    public static Bitmap photo;
    private TextView bt_my_obs;
    private TextView bt_view_obs;
    MarshmallowPermissions marshMallowPermission;
    RelativeLayout parentView;
    private ArrayList<String> permissionsToRequest;
    ImageView preview_image;
    View rootView;
    MaterialSpinner spinner;
    ImageView uploadImage;
    int CAMERA_REQUEST = 1;
    int CAMERA_CROP = 2;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_census_widget, (ViewGroup) null);
        initialize();
        this.spinner.setItems(ANDROID_VERSIONS);
        this.spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() { // from class: com.micromerger.ssms.boundarywall.BoundaryWallFragment.1
            @Override // com.jaredrummler.materialspinner.MaterialSpinner.OnItemSelectedListener
            public void onItemSelected(MaterialSpinner view, int position, long id2, String item) {
            }
        });
        this.spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() { // from class: com.micromerger.ssms.boundarywall.BoundaryWallFragment.2
            @Override // com.jaredrummler.materialspinner.MaterialSpinner.OnNothingSelectedListener
            public void onNothingSelected(MaterialSpinner spinner) {
            }
        });
        return this.rootView;
    }

    public void initialize() {
        this.marshMallowPermission = new MarshmallowPermissions(getActivity());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("android.permission.CAMERA");
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        ArrayList<String> arrayListFindUnAskedPermissions = findUnAskedPermissions(arrayList);
        this.permissionsToRequest = arrayListFindUnAskedPermissions;
        if (arrayListFindUnAskedPermissions.size() > 0 && Build.VERSION.SDK_INT >= 23) {
            ArrayList<String> arrayList2 = this.permissionsToRequest;
            requestPermissions((String[]) arrayList2.toArray(new String[arrayList2.size()]), 0);
        }
        ImageView imageView = (ImageView) this.rootView.findViewById(R.id.uploadImage);
        this.uploadImage = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.boundarywall.BoundaryWallFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BoundaryWallFragment.this.getPhotoFromCamera();
            }
        });
        this.preview_image = (ImageView) this.rootView.findViewById(R.id.preview_image);
        this.spinner = (MaterialSpinner) this.rootView.findViewById(R.id.spinner);
        this.parentView = (RelativeLayout) this.rootView.findViewById(R.id.parentView);
    }

    public void getPhotoFromCamera() {
        if (!this.marshMallowPermission.checkPermissionForCamera()) {
            this.marshMallowPermission.requestPermissionForCamera();
            return;
        }
        if (!this.marshMallowPermission.checkPermissionForExternalStorage()) {
            this.marshMallowPermission.requestPermissionForExternalStorage();
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(file));
        startActivityForResult(intent, this.CAMERA_REQUEST);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == this.CAMERA_REQUEST && resultCode == -1) {
                Log.e(ExifInterface.TAG_MODEL, Build.MODEL + " ");
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
                photo = BitmapFactory.decodeFile(file.getAbsolutePath());
                try {
                    photo = modifyOrientation(photo, file.getAbsolutePath());
                    getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.boundarywall.BoundaryWallFragment.4
                        @Override // java.lang.Runnable
                        public void run() {
                            BoundaryWallFragment.this.preview_image.setVisibility(0);
                            BoundaryWallFragment.this.preview_image.setImageBitmap(BoundaryWallFragment.photo);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("SUCEES", "SUCCESS");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
        }
    }

    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        int attributeInt = new android.media.ExifInterface(image_absolute_path).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        if (attributeInt == 2) {
            return flip(bitmap, true, false);
        }
        if (attributeInt == 3) {
            return rotate(bitmap, 180.0f);
        }
        if (attributeInt == 4) {
            return flip(bitmap, false, true);
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? bitmap : rotate(bitmap, 270.0f);
        }
        return rotate(bitmap, 90.0f);
    }

    public static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1.0f : 1.0f, vertical ? -1.0f : 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                hasPermission("android.permission.CAMERA");
                break;
            case 102:
                hasPermission("android.permission.WRITE_EXTERNAL_STORAGE");
                break;
            case 103:
                hasPermission("android.permission.READ_EXTERNAL_STORAGE");
                break;
        }
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = wanted.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!hasPermission(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private boolean hasPermission(String permission) {
        return !canMakeSmores() || Build.VERSION.SDK_INT < 23 || getActivity().checkSelfPermission(permission) == 0;
    }

    private boolean canMakeSmores() {
        return Build.VERSION.SDK_INT > 22;
    }
}
