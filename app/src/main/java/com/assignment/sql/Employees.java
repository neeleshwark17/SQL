package com.assignment.sql;

public class Employees {
    int eId;
    String eName;
    String eSalary;

    public Employees() {
    }

    public Employees(String eName, String eSalary) {
        this.eName = eName;
        this.eSalary = eSalary;
    }

    public Employees(int eId, String eName, String eSalary) {
        this.eId = eId;
        this.eName = eName;
        this.eSalary = eSalary;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteSalary() {
        return eSalary;
    }

    public void seteSalary(String eSalary) {
        this.eSalary = eSalary;
    }
}
