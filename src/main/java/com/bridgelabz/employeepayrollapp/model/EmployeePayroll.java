package com.bridgelabz.employeepayrollapp.model;

public class EmployeePayroll {
    private long id;
    private String name;
    private int salary;

    public EmployeePayroll(long id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public int getSalary() { return salary; }
    public void setName(String name) { this.name = name; }
    public void setSalary(int salary) { this.salary = salary; }
}
