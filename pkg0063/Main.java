/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0063;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tiêu Trung Hòa
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        System.out.println("========== MANAGEMENT PERSON PROGRAMER ==========");
        Main main = new Main();
        main.addNewPerson(list);
        list = main.sortPerson(list);
        main.display(list);
    }
    
    public List<Person> sortPerson(List<Person> list) {
        Comparator<Person> comp2 = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.getSalary()<o2.getSalary()) return -1;
                else if(o1.getSalary()==o2.getSalary()) return 0;
                else return 1;
            }
        };
        Collections.sort(list, comp2);
        return list;
    }

    public void addNewPerson(List<Person> list) {
        int check = 0;
        do {
            System.out.println("Input information of person: ");
            System.out.print("Please input name: ");
            String name = correctString();
            System.out.print("Please input address: ");
            String address = correctAddress();
            System.out.print("Please input salary: ");
            double salary = checkInput(0);
            list.add(new Person(name, address, salary));
            check++;
        } while (check < 3);
    }

    public void display(List<Person> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Information of person you've entered:");
            System.out.println(list.get(i).toString());
            System.out.println("");
        }
    }

    public String correctString() {
        String str = null;
        String regex = "(^[a-zA-Z]+)";
        while (true) {
            try {
                str = sc.nextLine();
                if (str.matches(regex)) {
                    break;
                } else {
                    System.out.print("Enter new string: ");
                }
            } catch (Exception e) {
                System.out.print("Enter new string: ");
            }
        }
        return str;
    }

    public String correctAddress() {
        String str = null;
        String regex = "(^[a-zA-Z\\s]+)";
        while (true) {
            try {
                str = sc.nextLine();
                if (str.matches(regex)) {
                    break;
                } else {
                    System.out.print("Enter new string: ");
                }
            } catch (Exception e) {
                System.out.print("Enter new string: ");
            }
        }
        return str;
    }

    public double checkInput(double min) {
        double n;
        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n < min) {
                    System.out.println("Mark must be greater than " + min);
                    System.out.print("Retype: ");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("must be a number");
                System.out.print("Retype: ");
            }
        }
        return n;
    }
}
