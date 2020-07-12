/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worker;

/**
 *
 * @author songm
 */
public class History {

    private double salary;
    private String status;
    private String date;
    private Worker worker;

    public History(double salary, String status, String date, Worker worker) {
        this.salary = salary;
        this.status = status;
        this.date = date;
        this.worker = worker;
    }

    public History() {
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getWorkerName() {
        return this.worker.getName();
    }

    public String getWorkerID() {
        return this.worker.getId();
    }

    public int getWorkerAge() {
        return this.worker.getAge();
    }

    public void increaseSalary() {
        worker.setSalary(worker.getSalary() + this.salary);
    }

    public void decreaseSalary() {
        worker.setSalary(worker.getSalary() - this.salary);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
