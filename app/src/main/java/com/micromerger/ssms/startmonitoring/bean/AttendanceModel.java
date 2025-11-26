package com.micromerger.ssms.startmonitoring.bean;

/* loaded from: classes2.dex */
public class AttendanceModel {
    public String SEMES;
    public String date;
    public String end;
    public boolean isChecked;
    public String name;
    public String start;

    public AttendanceModel(String SEMES, String name, String date, String start, String end, boolean isChecked) {
        this.SEMES = SEMES;
        this.name = name;
        this.date = date;
        this.start = start;
        this.end = end;
        this.isChecked = isChecked;
    }

    public String getSEMES() {
        return this.SEMES;
    }

    public void setSEMES(String SEMES) {
        this.SEMES = SEMES;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
