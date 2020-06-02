/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0063;

/**
 *
 * @author Tiêu Trung Hòa
 */
public class Person {
    private String name;
    private String address;
    private double salary;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getSalary() {
        return salary;
    }

    public Person(String name, String address, double salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return ("Name: "+name+"\n"+"Address: "+address+"\n"+"Salary: "+salary);
    }
    
    
}
