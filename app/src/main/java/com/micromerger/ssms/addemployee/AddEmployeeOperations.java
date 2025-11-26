package com.micromerger.ssms.addemployee;

import android.content.Context;
import com.micromerger.ssms.utils.DatabaseHandler;

/* loaded from: classes2.dex */
public class AddEmployeeOperations {
    DatabaseHandler databaseHandler;

    public AddEmployeeOperations(Context context) {
        this.databaseHandler = new DatabaseHandler(context);
    }

    public void saveEmployeeRecord(EmployeeDbRecord employeeDbRecord, boolean editMode) {
        if (editMode) {
            this.databaseHandler.updateEmployeeRecord(employeeDbRecord);
        } else {
            this.databaseHandler.addEmployeeRecord(employeeDbRecord);
        }
    }

    public EmployeeDbRecord getEmployeeDbRecord(int recordId) {
        return this.databaseHandler.getEmployeeDbRecord(recordId);
    }
}
