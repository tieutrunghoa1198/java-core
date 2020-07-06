/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccrm_project;

import static ccrm_project.test.sc;
import java.text.*;
import java.util.*;

/**
 *
 * @author songm
 */
public class Validation {

    Scanner sc = new Scanner(System.in);

    public int inputRange(int min, int max) {
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
                System.out.println("Retype option: ");
            }
        }
        return n;
    }

    public String time() {
        String inputTime = "";
        String regex_one = "^[\\d]{1,2}\\.{0,1}[05]{0,1}";
        while (true) {
            inputTime = sc.nextLine();
            if (inputTime.matches(regex_one)) {
                return inputTime;
            } else {
                System.out.println("Time must be in 8.0, 8.5, 9.0, 9.5 … -> 17.5.");
                System.out.print("Retype: ");
            }
        }
    }

    public double inputTime(double min, double max) {
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

    public String inputString() {
        String name;
        String regex = "^[a-zA-Z\\s]+$";
        while (true) {
            name = sc.nextLine();
            if (name.matches(regex)) {
                break;
            } else {
                System.out.println("Wrong format! Retype.");
            }
        }
        return name;
    }

    public Date correctDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //parttern Date -> dd/MM/yyyy
            sdf.setLenient(false);
            return sdf.parse(date);
        } catch (ParseException e) {
            System.out.println("Wrong date.");
            return null;
        }
    }

    public String inputDate() {
        String val;
        while (true) {
            try {
                val = sc.nextLine();
                if (correctDate(val) == null) {
                    throw new Exception();
                } else {
                    return val;
                }
            } catch (Exception ex) {
                System.out.print("Retype date: ");
            }
        }
    }
}
