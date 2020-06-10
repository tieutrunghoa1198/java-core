/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worker;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author songm
 */
public class Management {

    Scanner sc = new Scanner(System.in);
    List<Worker> list = new ArrayList<>();

    public void mainMenu() {
        boolean isLoop = true;
        Management worker = new Management();
        Validation validate = new Validation();
        loadData();
        while (isLoop) {
            System.out.println("======== Worker Management =========");
            System.out.println(
                    "1. Add a Worker." + "\n"
                    + "2. Increase salary for worker." + "\n"
                    + "3. Decrease for worker." + "\n"
                    + "4. Show adjusted salary worker information." + "\n"
                    + "0. Exit." + "\n"
            );
            System.out.print("Enter option: ");
            int option = validate.inputRange(0, 4, "Option");
            switch (option) {
                case 1:
                    Worker newWorker = worker.initWorker();
                    if (newWorker == null) {
                        System.out.println("Cannot add new worker.");
                    } else {
                        list.add(newWorker);
                    }
                    System.out.println();
                    break;
                case 2:
                    worker.adjustSalary("UP", list);
                    System.out.println();
                    break;
                case 3:
                    worker.adjustSalary("DOWN", list);
                    System.out.println();
                    break;
                case 4:
                    worker.display(list);
                    System.out.println();
                    break;
                case 0:
                    System.out.println(" -> System exit.");
                    isLoop = false;
                    break;

            }
        }
    }

    public void loadData() {
        String id, name, status, date;
        int age;
        double salary;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("worker.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, "|");
                while (token.hasMoreTokens()) {
                    id = token.nextToken().trim();
                    name = token.nextToken().trim();
                    age = Integer.parseInt(token.nextToken().trim());
                    salary = Double.parseDouble(token.nextToken().trim());
                    status = token.nextToken().trim();
                    date = token.nextToken().trim();
                    Worker newPro = new Worker(id, name, age, salary, status, date, line);
                    list.add(newPro);
                }
            }
            System.out.println("Data is successfully loaded.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Can not load data.");
            System.err.println(e);
        }
    }

    public Worker searchId(List<Worker> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    public void sort(List<Worker> list) {
        boolean isSwapped;

        for (int i = 0; i < list.size() - 1; i++) {
            isSwapped = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getId().compareTo(list.get(j + 1).getId()) > 0) {
                    Worker temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    isSwapped = true;
                }
            }
            if (isSwapped == false) {
                break;
            }
        }
    }

    public void display(List<Worker> list) {
        if (list.isEmpty()) {
            System.out.println("Product list is empty.");
            return;
        }
        sort(list);
        System.out.println("--------------------Display Information Salary-----------------------");
        System.out.printf("%-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s   ",
                "Code", "Name", "Age", "Salary", "Status", "Date"
        );
        System.out.println();
        for (int i = 0; i < list.size(); i++) {

            Worker worker = list.get(i);
            String id = worker.getId();
            String name = worker.getName();
            int age = worker.getAge();
            double salary = worker.getSalary();
            String status = worker.getStatus();
            String date = worker.getDate();

            System.out.printf("%-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  ",
                    id, name, age, salary, status, date
            );
            System.out.println();
        }
    }

    public void adjustSalary(String status, List<Worker> list) {
        Validation validate = new Validation();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        System.out.println("------- " + status + " Salary --------");

        System.out.print("Enter code: ");
        String code = validate.getId();
        Worker worker = searchId(list, code);

        if (worker == null) {
            System.out.println("Entered code is not exist.");
            return;
        }

        System.out.print("Enter salary: ");
        double salary = validate.getSalary(0);

        double newSalary = 0;

        if (status.equalsIgnoreCase("UP")) {
            newSalary = worker.getSalary() + salary;
        } else if (status.equalsIgnoreCase("DOWN")) {
            newSalary = worker.getSalary() - salary;
        }

        worker.setSalary(newSalary);
        worker.setStatus(status);
        worker.setDate(date);
    }

    public Worker initWorker() {
        System.out.println("--------- Add Worker ----------");
        Validation validate = new Validation();
        System.out.println("Enter code: ");
        String id = validate.getId();

        if (searchId(list, id) != null) {
            return null;
        }

        System.out.println("Enter name: ");
        String name = validate.getName();

        System.out.println("Enter age: ");
        int age = validate.inputRange(18, 50, "Age");

        System.out.println("Enter salary: ");
        double salary = validate.getSalary(0);

        System.out.println("Enter work location: ");
        String location = validate.getLocation();

        String status = "-";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String date = sdf.format(new Date());
        Worker newWorker = new Worker(id, name, age, salary, status, date, location);

        return newWorker;
    }

}
