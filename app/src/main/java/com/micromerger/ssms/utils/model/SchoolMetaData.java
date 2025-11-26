package com.micromerger.ssms.utils.model;

import javax.annotation.Nonnull;

/* loaded from: classes2.dex */
public class SchoolMetaData {

    /* renamed from: id, reason: collision with root package name */
    public int f19id;
    public String key_name;
    public int schoolId;
    public String value_name;

    public SchoolMetaData() {
    }

    public SchoolMetaData(int id2, int schoolId, String key_name, String value_name) {
        this.f19id = id2;
        this.schoolId = schoolId;
        this.key_name = key_name;
        this.value_name = value_name;
    }

    @Nonnull
    public String toString() {
        return "SchoolMetaData{id=" + this.f19id + ", schoolId='" + this.schoolId + "', key='" + this.key_name + "', value='" + this.value_name + "'}";
    }

    public int getId() {
        return this.f19id;
    }

    public void setId(int id2) {
        this.f19id = id2;
    }

    public int getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getKeyName() {
        return this.key_name;
    }

    public void setKeyName(String key_name) {
        this.key_name = key_name;
    }

    public String getValueName() {
        return this.value_name;
    }

    public void setValueName(String value_name) {
        this.value_name = value_name;
    }
}
