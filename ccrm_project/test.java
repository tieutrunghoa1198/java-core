/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccrm_project;

import java.util.Scanner;

/**
 *
 * @author songm
 */
public class test {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter time: ");
        double inputTime = inputTime(8, 17.5);
        System.out.println("Entered time: " + inputTime);
    }

    public static String time() {
        String inputTime = "";
        String regex_one = "^[\\d]{1,2}\\.{0,1}[05]{0,1}";
        while (true) {
            inputTime = sc.nextLine();
            if (inputTime.matches(regex_one)) {
                return inputTime;
            } else {
                System.out.print("Time must be in 8.0, 8.5, 9.0, 9.5 … -> 17.5.");
            }
        }
    }

    public static double inputTime(double min, double max) {
        double n;
        while (true) {
            try {
                n = Double.parseDouble(time());
                if (n < min) {
                    throw new Exception();
                } else if (n > max) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Range[" + min + "-" + max + "]");
                System.out.print("Retype time: ");
            }
        }
        return n;
    }
}
