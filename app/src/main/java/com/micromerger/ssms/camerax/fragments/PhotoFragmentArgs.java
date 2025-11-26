package com.micromerger.ssms.camerax.fragments;

import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.micromerger.ssms.camerax.CameraActivity;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class PhotoFragmentArgs implements NavArgs {
    private final HashMap arguments;

    private PhotoFragmentArgs() {
        this.arguments = new HashMap();
    }

    private PhotoFragmentArgs(HashMap argumentsMap) {
        HashMap map = new HashMap();
        this.arguments = map;
        map.putAll(argumentsMap);
    }

    public static PhotoFragmentArgs fromBundle(Bundle bundle) {
        PhotoFragmentArgs photoFragmentArgs = new PhotoFragmentArgs();
        bundle.setClassLoader(PhotoFragmentArgs.class.getClassLoader());
        if (bundle.containsKey(CameraActivity.IMAGE_FILE_NAME)) {
            String string = bundle.getString(CameraActivity.IMAGE_FILE_NAME);
            if (string == null) {
                throw new IllegalArgumentException("Argument \"file_name\" is marked as non-null but was passed a null value.");
            }
            photoFragmentArgs.arguments.put(CameraActivity.IMAGE_FILE_NAME, string);
            return photoFragmentArgs;
        }
        throw new IllegalArgumentException("Required argument \"file_name\" is missing and does not have an android:defaultValue");
    }

    public static PhotoFragmentArgs fromSavedStateHandle(SavedStateHandle savedStateHandle) {
        PhotoFragmentArgs photoFragmentArgs = new PhotoFragmentArgs();
        if (savedStateHandle.contains(CameraActivity.IMAGE_FILE_NAME)) {
            String str = (String) savedStateHandle.get(CameraActivity.IMAGE_FILE_NAME);
            if (str == null) {
                throw new IllegalArgumentException("Argument \"file_name\" is marked as non-null but was passed a null value.");
            }
            photoFragmentArgs.arguments.put(CameraActivity.IMAGE_FILE_NAME, str);
            return photoFragmentArgs;
        }
        throw new IllegalArgumentException("Required argument \"file_name\" is missing and does not have an android:defaultValue");
    }

    public String getFileName() {
        return (String) this.arguments.get(CameraActivity.IMAGE_FILE_NAME);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.arguments.containsKey(CameraActivity.IMAGE_FILE_NAME)) {
            bundle.putString(CameraActivity.IMAGE_FILE_NAME, (String) this.arguments.get(CameraActivity.IMAGE_FILE_NAME));
        }
        return bundle;
    }

    public SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        if (this.arguments.containsKey(CameraActivity.IMAGE_FILE_NAME)) {
            savedStateHandle.set(CameraActivity.IMAGE_FILE_NAME, (String) this.arguments.get(CameraActivity.IMAGE_FILE_NAME));
        }
        return savedStateHandle;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        PhotoFragmentArgs photoFragmentArgs = (PhotoFragmentArgs) object;
        if (this.arguments.containsKey(CameraActivity.IMAGE_FILE_NAME) != photoFragmentArgs.arguments.containsKey(CameraActivity.IMAGE_FILE_NAME)) {
            return false;
        }
        return getFileName() == null ? photoFragmentArgs.getFileName() == null : getFileName().equals(photoFragmentArgs.getFileName());
    }

    public int hashCode() {
        return 31 + (getFileName() != null ? getFileName().hashCode() : 0);
    }

    public String toString() {
        return "PhotoFragmentArgs{fileName=" + getFileName() + "}";
    }

    public static class Builder {
        private final HashMap arguments;

        public Builder(PhotoFragmentArgs original) {
            HashMap map = new HashMap();
            this.arguments = map;
            map.putAll(original.arguments);
        }

        public Builder(String fileName) {
            HashMap map = new HashMap();
            this.arguments = map;
            if (fileName == null) {
                throw new IllegalArgumentException("Argument \"file_name\" is marked as non-null but was passed a null value.");
            }
            map.put(CameraActivity.IMAGE_FILE_NAME, fileName);
        }

        public PhotoFragmentArgs build() {
            return new PhotoFragmentArgs(this.arguments);
        }

        public Builder setFileName(String fileName) {
            if (fileName == null) {
                throw new IllegalArgumentException("Argument \"file_name\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put(CameraActivity.IMAGE_FILE_NAME, fileName);
            return this;
        }

        public String getFileName() {
            return (String) this.arguments.get(CameraActivity.IMAGE_FILE_NAME);
        }
    }
}
