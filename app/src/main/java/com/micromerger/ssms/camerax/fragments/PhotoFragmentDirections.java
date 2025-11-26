package com.micromerger.ssms.camerax.fragments;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public class PhotoFragmentDirections {
    private PhotoFragmentDirections() {
    }

    public static NavDirections actionPhotoToCamera() {
        return new ActionOnlyNavDirections(R.id.action_photo_to_camera);
    }
}
