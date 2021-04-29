package com.assignment.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper db = new DbHelper(this);

        // Inserting Employees
        Log.d(TAG, "INSERTING ");
        db.insertData(new Employees("Ravi", "91000"));
        db.insertData(new Employees("Srinivas", "91999"));
        db.insertData(new Employees("Tommy", "95222"));
        db.insertData(new Employees("Karthik", "95333"));

        // Reading all Employees
        Log.d("Reading: ", "Reading all contacts..");
        List<Employees> employees = db.getAllEmployees();

        for (Employees emp : employees) {
            String log = "Id: " + emp.geteId() + " ,Name: " + emp.geteName() + " ,Salary: " + emp.geteSalary();
            // Writing Employees to log
            Log.d("Name: ", log);
        }

    }
}