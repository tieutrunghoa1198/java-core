/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worker;

import java.util.Scanner;

/**
 *
 * @author songm
 */
public class Validation {

    Scanner sc = new Scanner(System.in);

    public String getName() {
        String input = "";
        String regex = "^(\\w){5,}$";
        while (true) {
            input = sc.nextLine();
            if (input.matches(regex)) {
                break;
            } else {
                System.out.println("Wrong format. Please retype.");
                System.out.print("Product name: ");
            }
        }
        return input;
    }

    public String getId() {
        String input = "";
        String regex = "^W(\\d)+$";
        while (true) {
            input = sc.nextLine();
            if (input.matches(regex)) {
                break;
            } else {
                System.out.println("Wrong format. Please retype.");
                System.out.print("Product name: ");
            }
        }
        return input;
    }

    public int inputRange(int min, int max, String option) {
        int n;
        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n < min) {
                    throw new Exception();
                } else if (n > max) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Range[" + min + "-" + max + "]");
                System.out.print("Retype " + option + ": ");
            }
        }
        return n;
    }

    public double getSalary(int min) {
        double n;
        while (true) {
            try {
                n = Double.parseDouble(sc.nextLine());
                if (n < min) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println();
                System.out.print("Retype salary: ");
            }
        }
        return n;
    }

    public String getLocation() {
        String input = "";
        String regex = "^(\\w){3,}$";
        while (true) {
            input = sc.nextLine();
            if (input.matches(regex)) {
                break;
            } else {
                System.out.println("Wrong format. Please retype.");
                System.out.print("Work location: ");
            }
        }
        return input;
    }
}
