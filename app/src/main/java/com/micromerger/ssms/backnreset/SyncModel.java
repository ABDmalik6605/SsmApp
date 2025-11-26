package com.micromerger.ssms.backnreset;

/* loaded from: classes2.dex */
public class SyncModel {
    public String SEMES;
    public String date;
    public String end;
    public boolean isChecked;
    public String name;
    public String start;

    public SyncModel(String SEMES, String name, String date, String start, String end, boolean isChecked) {
        this.SEMES = SEMES;
        this.name = name;
        this.date = date;
        this.start = start;
        this.end = end;
        this.isChecked = isChecked;
    }

    public String toString() {
        return "SyncModel{SEMES='" + this.SEMES + "', name='" + this.name + "', date='" + this.date + "', start='" + this.start + "', end='" + this.end + "', isChecked=" + this.isChecked + '}';
    }
}
