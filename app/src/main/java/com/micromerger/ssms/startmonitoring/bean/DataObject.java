package com.micromerger.ssms.startmonitoring.bean;

/* loaded from: classes2.dex */
public class DataObject {

    /* renamed from: id, reason: collision with root package name */
    private String f18id;
    private boolean isChecked;
    private String title;

    public DataObject() {
    }

    public DataObject(String title, String id2, boolean isChecked) {
        this.title = title;
        this.f18id = id2;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return this.f18id;
    }

    public void setId(String id2) {
        this.f18id = id2;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
