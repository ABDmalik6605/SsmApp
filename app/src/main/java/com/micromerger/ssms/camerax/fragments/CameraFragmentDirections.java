package com.micromerger.ssms.camerax.fragments;

import android.os.Bundle;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.micromerger.ssms.R;
import com.micromerger.ssms.camerax.CameraActivity;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class CameraFragmentDirections {
    private CameraFragmentDirections() {
    }

    public static NavDirections actionCameraToPermissions() {
        return new ActionOnlyNavDirections(R.id.action_camera_to_permissions);
    }

    public static ActionCameraToPhoto actionCameraToPhoto(String fileName) {
        return new ActionCameraToPhoto(fileName);
    }

    public static class ActionCameraToPhoto implements NavDirections {
        private final HashMap arguments;

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return R.id.action_camera_to_photo;
        }

        private ActionCameraToPhoto(String fileName) {
            HashMap map = new HashMap();
            this.arguments = map;
            if (fileName == null) {
                throw new IllegalArgumentException("Argument \"file_name\" is marked as non-null but was passed a null value.");
            }
            map.put(CameraActivity.IMAGE_FILE_NAME, fileName);
        }

        public ActionCameraToPhoto setFileName(String fileName) {
            if (fileName == null) {
                throw new IllegalArgumentException("Argument \"file_name\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put(CameraActivity.IMAGE_FILE_NAME, fileName);
            return this;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            if (this.arguments.containsKey(CameraActivity.IMAGE_FILE_NAME)) {
                bundle.putString(CameraActivity.IMAGE_FILE_NAME, (String) this.arguments.get(CameraActivity.IMAGE_FILE_NAME));
            }
            return bundle;
        }

        public String getFileName() {
            return (String) this.arguments.get(CameraActivity.IMAGE_FILE_NAME);
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            ActionCameraToPhoto actionCameraToPhoto = (ActionCameraToPhoto) object;
            if (this.arguments.containsKey(CameraActivity.IMAGE_FILE_NAME) != actionCameraToPhoto.arguments.containsKey(CameraActivity.IMAGE_FILE_NAME)) {
                return false;
            }
            if (getFileName() == null ? actionCameraToPhoto.getFileName() == null : getFileName().equals(actionCameraToPhoto.getFileName())) {
                return getActionId() == actionCameraToPhoto.getActionId();
            }
            return false;
        }

        public int hashCode() {
            return (((getFileName() != null ? getFileName().hashCode() : 0) + 31) * 31) + getActionId();
        }

        public String toString() {
            return "ActionCameraToPhoto(actionId=" + getActionId() + "){fileName=" + getFileName() + "}";
        }
    }
}
