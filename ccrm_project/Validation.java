/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccrm_project;

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

    public double inputTime(double min, double max) {
        double n;
        while (true) {
            try {
                n = Double.parseDouble(sc.nextLine());
                if (n < min) {
                    throw new Exception();
                } else if (n > max) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Range[" + min + "-" + max + "]");
                System.out.println("Retype time: ");
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
