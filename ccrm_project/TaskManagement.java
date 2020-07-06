/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccrm_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author songm
 */
public class TaskManagement {

    Scanner sc = new Scanner(System.in);
    Validation validate = new Validation();

    void mainMenu() {
        boolean isLoop = true;
        List<Task> listTask = new ArrayList<>();
        List<TaskType> listTaskType = new ArrayList<>();
        TaskManagement manage = new TaskManagement();
        initTaskType(listTaskType);
        manage.loadData(listTask);
        while (isLoop) {
            System.out.println("========= Task program =========");
            System.out.println(
                    "1. Add Task" + "\n"
                    + "2. Delete Task" + "\n"
                    + "3. Display Task" + "\n"
                    + "4. Exit" + "\n"
            );
            System.out.print("Enter option: ");
            int option = validate.inputRange(1, 4);
            switch (option) {
                case 1:
                    try {
                        Task t = manage.createTask(listTask, listTaskType);
                        listTask.add(t);
                    } catch (Exception e) {
                        System.out.println("Cannot add new task.");
                    }
                    break;
                case 2:
                    manage.deleteTask(listTask);
                    break;
                case 3:
                    manage.display(listTask);
                    break;
                case 4:
                    System.out.println(" -> System exit.");
                    isLoop = false;
                    break;
            }
        }
    }

    Task createTask(List<Task> list, List<TaskType> listType) {
        int id = list.size() + 1;
        System.out.print("Requirement Name:");
        String name = validate.inputString();
        System.out.print("Task Type:");
        int type = validate.inputRange(1, 4);
        System.out.print("Date:");
        String date = validate.inputDate();
        System.out.print("From: ");
        double startTime = validate.inputTime(8, 17.5);
        System.out.print("To: ");
        double endTime = validate.inputTime(8, 17.5);

        double time = endTime - startTime;
        if (time < 0) {
            System.out.println("Start time must be greater than finish time.");
            return null;
        }

        System.out.print("Assignee: ");
        String assignee = validate.inputString();
        System.out.print("Reviewer ");
        String reviewer = validate.inputString();
        String taskType = listType.get(type - 1).getName();
        System.out.println(taskType);

        Task t = new Task(id, name, taskType, date, time, assignee, reviewer);
        return t;
    }

    Task search(List<Task> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    void deleteTask(List<Task> list) {
        System.out.println("---------Del Task------");
        System.out.print("ID: ");
        int id = validate.inputRange(1, list.size());
        Task t = search(list, id);
        if (t == null) {
            System.out.println("Task cannot be found.");
        } else {
            list.remove(t);
            resetID(list);
            System.out.println("Remove successfully.");
        }
    }

    void resetID(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i + 1);
        }
    }

    void display(List<Task> list) {
        if (list.isEmpty()) {
            System.out.println("There is no data in database.");
            return;
        }
        System.out.println("----------------------------------------- Task ---------------------------------------");
        System.out.printf("%-2s  |  %-10s  |  %-8s  |  %-10s  |  %-8s  |  %-12s  |  %-8s   ",
                "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer"
        );
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            int id = t.getId();
            String name = t.getName();
            String taskType = t.getTaskType();
            String date = t.getDate();
            double time = t.getTime();
            String assignee = t.getAssignee();
            String reviewer = t.getReviewer();

            System.out.printf("%-2s  |  %-10s  |  %-8s   |  %-10s  |  %-8s  |  %-12s  |  %-8s   ",
                    id, name, taskType, date, time, assignee, reviewer
            );
            System.out.println();
        }
    }

    void loadData(List<Task> list) {
        String name, taskType, date, assignee, reviewer;
        int id;
        double time;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("task.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, "|");
                while (token.hasMoreTokens()) {
                    id = Integer.parseInt(token.nextToken().trim());
                    name = token.nextToken().trim();
                    taskType = token.nextToken().trim();
                    date = token.nextToken().trim();
                    time = Double.parseDouble(token.nextToken().trim());
                    assignee = token.nextToken().trim();
                    reviewer = token.nextToken().trim();
                    Task t = new Task(id, name, taskType, date, time, assignee, reviewer);
                    list.add(t);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Can not load data.");
            System.err.println(e);
        }
    }

    void initTaskType(List<TaskType> listTaskType) {
        TaskType code = new TaskType(1, "Code");
        TaskType test = new TaskType(2, "Test");
        TaskType design = new TaskType(3, "Design");
        TaskType review = new TaskType(4, "Review");

        listTaskType.add(code);
        listTaskType.add(test);
        listTaskType.add(design);
        listTaskType.add(review);
    }

}
