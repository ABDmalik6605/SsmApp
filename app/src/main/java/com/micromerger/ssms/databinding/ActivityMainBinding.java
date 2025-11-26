package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.navigation.NavigationView;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final DrawerLayout drawerLayout;
    public final NavigationView navView;
    private final DrawerLayout rootView;

    private ActivityMainBinding(DrawerLayout rootView, DrawerLayout drawerLayout, NavigationView navView) {
        this.rootView = rootView;
        this.drawerLayout = drawerLayout;
        this.navView = navView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public DrawerLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainBinding bind(View rootView) {
        DrawerLayout drawerLayout = (DrawerLayout) rootView;
        NavigationView navigationView = (NavigationView) rootView.findViewById(R.id.nav_view);
        if (navigationView != null) {
            return new ActivityMainBinding(drawerLayout, drawerLayout, navigationView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.nav_view)));
    }
}
