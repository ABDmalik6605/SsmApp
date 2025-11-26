package com.micromerger.ssms.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public class PreferenceHelper {
    protected void putStringPreference(Context context, String prefsName, String key, String value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(prefsName, 0).edit();
        editorEdit.putString(key, value);
        editorEdit.commit();
    }

    protected String getStringPreference(Context context, String prefsName, String key) {
        return context.getSharedPreferences(prefsName, 0).getString(key, "");
    }

    protected void putBooleanPreference(Context context, String prefsName, String key, boolean value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(prefsName, 0).edit();
        editorEdit.putBoolean(key, value);
        editorEdit.commit();
    }

    protected boolean getBooleanPreference(Context context, String prefsName, String key) {
        return context.getSharedPreferences(prefsName, 0).getBoolean(key, false);
    }

    protected boolean getBooleanPreferenceDefaultTrue(Context context, String prefsName, String key) {
        return context.getSharedPreferences(prefsName, 0).getBoolean(key, true);
    }

    protected void putIntegerPreference(Context context, String prefsName, String key, int value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(prefsName, 0).edit();
        editorEdit.putInt(key, value);
        editorEdit.commit();
    }

    protected int getIntegerPreference(Context context, String prefsName, String key) {
        return context.getSharedPreferences(prefsName, 0).getInt(key, -1);
    }

    protected void putLongPreference(Context context, String prefsName, String key, long value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(prefsName, 0).edit();
        editorEdit.putLong(key, value);
        editorEdit.commit();
    }

    protected long getLongPreference(Context context, String prefsName, String key) {
        return context.getSharedPreferences(prefsName, 0).getLong(key, -2147483648L);
    }

    protected void putFloatPreference(Context context, String prefsName, String key, float value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(prefsName, 0).edit();
        editorEdit.putFloat(key, value);
        editorEdit.commit();
    }

    protected float getFloatPreference(Context context, String prefsName, String key) {
        return context.getSharedPreferences(prefsName, 0).getFloat(key, Float.MIN_VALUE);
    }

    protected void removePreference(Context context, String prefsName, String key) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(prefsName, 0).edit();
        editorEdit.remove(key);
        editorEdit.commit();
    }
}
