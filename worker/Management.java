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
    List<History> historyList = new ArrayList<>();

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
                    + "5. Worker Info" + "\n"
                    + "0. Exit." + "\n"
            );
            System.out.print("Enter option: ");
            int option = validate.inputRange(0, 5, "Option");
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
                    worker.adjustSalary("UP", list, historyList);
                    System.out.println();
                    break;
                case 3:
                    worker.adjustSalary("DOWN", list, historyList);
                    System.out.println();
                    break;
                case 4:
                    worker.display(historyList);
                    System.out.println();
                    break;
                case 5:
                    worker.displayWorker(list);
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
        String id, name, location;
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
                    location = token.nextToken();
                    Worker newWorker = new Worker(id, name, age, salary, location);
                    list.add(newWorker);
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

    public void sort(List<History> list) {
        boolean isSwapped;

        for (int i = 0; i < list.size() - 1; i++) {
            isSwapped = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                String str1 = list.get(j).getWorkerID();
                String str2 = list.get(j + 1).getWorkerID();

                String[] firstId = str1.split("W");
                int a = Integer.parseInt(firstId[1]);
                String[] secondId = str2.split("W");
                int b = Integer.parseInt(secondId[1]);

                if (a > b) {
                    History temp = list.get(j);
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

    public void display(List<History> list) {
        if (list.isEmpty()) {
            System.out.println("History is empty.");
            return;
        }
        sort(list);
        System.out.println("--------------------Display Information Salary-----------------------");
        System.out.printf("%-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s   ",
                "Code", "Name", "Age", "Salary", "Status", "Date"
        );
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            History history = list.get(i);
            String id = history.getWorkerID();
            String name = history.getWorkerName();
            int age = history.getWorkerAge();
            double salary = history.getSalary();
            String status = history.getStatus();
            String date = history.getDate();
            System.out.printf("%-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  ",
                    id, name, age, salary, status, date
            );
            System.out.println();
        }
    }

    public void displayWorker(List<Worker> list) {
        if (list.isEmpty()) {
            System.out.println("Product list is empty.");
            return;
        }
        System.out.println("--------------------Display Information Salary-----------------------");
        System.out.printf("%-8s  |  %-8s  |  %-8s  |  %-8s  ",
                "Code", "Name", "Age", "Salary"
        );
        System.out.println();
        for (int i = 0; i < list.size(); i++) {

            Worker worker = list.get(i);
            String id = worker.getId();
            String name = worker.getName();
            int age = worker.getAge();
            double salary = worker.getSalary();

            System.out.printf("%-8s  |  %-8s  |  %-8s  |  %-8s  ",
                    id, name, age, salary
            );
            System.out.println();
        }
    }

    public void adjustSalary(String status, List<Worker> list, List<History> historyList) {
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

        double salary = 0;

        while (true) {
            System.out.print("Enter salary: ");
            salary = validate.getSalary(0);
            if (status.equalsIgnoreCase("down")) {
                if (worker.getSalary() - salary < 0) {
                    System.out.println("Entered salary must be smaller than " + worker.getSalary());
                } else {
                    System.out.println("greater");
                    break;
                }
            } else {
                System.out.println("up");
                break;
            }
        }

        History history = new History(salary, status, date, worker);

        if (status.equalsIgnoreCase("UP")) {
            history.increaseSalary();
        } else if (status.equalsIgnoreCase("DOWN")) {
            history.decreaseSalary();
        }

        historyList.add(history);
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

        Worker newWorker = new Worker(id, name, age, salary, location);

        return newWorker;
    }

}
