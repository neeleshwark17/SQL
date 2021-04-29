package com.assignment.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "SQL_DB";
    public static final String TABLE_NAME = "employee";
    public static final String COL_1 = "EID";
    public static final String COL_2 = "ENAME";
    public static final String COL_3 = "ESALARY";
    public static final String TAG = "DbHelper";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table if not exists " + TABLE_NAME + "(EID integer primary key autoincrement,ENAME varchar,ESALARY integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }


    ////insert function
    public boolean insertData(Employees employees) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, employees.geteName());    //NAME
        values.put(COL_3, employees.geteSalary());  //SALARY

        //Insert Row
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            Log.e(TAG, "insertData:::::::: Not Inserted");
            return false;
        } else {
            Log.e(TAG, "insertData::::::: Inserted");
            return true;
        }
    }

    ////get single employee record
    Employees getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_1, COL_2, COL_3}, COL_1 + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Employees employees = new Employees(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return employees;
    }

    // code to get all employees in a list view
    public List<Employees> getAllEmployees() {
        List<Employees> employeesList = new ArrayList<Employees>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employees employees = new Employees();
                employees.seteId(Integer.parseInt(cursor.getString(0)));
                employees.seteName(cursor.getString(1));
                employees.seteSalary(cursor.getString(2));
                // Adding employee to list
                employeesList.add(employees);
            } while (cursor.moveToNext());
        }
        // return employee list
        return employeesList;
    }


    // code to update the single employees
    public int updateEmployee(Employees employees) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, employees.geteName());
        values.put(COL_3, employees.geteSalary());

        // updating row
        return db.update(TABLE_NAME, values, COL_1 + " = ?", new String[]{String.valueOf(employees.geteId())});
    }


    // Deleting single employee
    public void deleteEmployee(Employees employees) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?",
                new String[]{String.valueOf(employees.geteId())});
        db.close();
    }

    // Getting employee Count
    public int getEmployeesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
