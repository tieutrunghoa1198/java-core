/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0072;

import java.text.*;
import java.util.*;

/**
 *
 * @author songm
 */
public class Validate {
    static Scanner sc = new Scanner(System.in);
    //check int 
    public static int number(int min, int max) {
        int n;
        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n < min) {
                    System.out.println("Range[" + min + "-" + max + "]");
                    System.out.println("Bill must be greater than " + min);
                    System.out.print("Retype: ");
                    continue;
                } else if (n > max) {
                    System.out.println("Range[" + min + "-" + max + "]");
                    System.out.println("Bill must be less than " + max);
                    System.out.println("Retype: ");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("must be a number");
                System.out.print("Retype: ");
            }
        }
        return n;
    }
    
    //check correct userName
    public static String userName() {
        String userName;
        String regex = "^[\\w_-]{5,}";
        while (true) {
            userName = sc.nextLine();
            if (userName.matches(regex)) {
                break;
            } else {
                System.out.println("You must enter least 5 characters, and no space!");
            }
        }
        return userName;
    }

    //correct password
    public static String pass() {
        String pass;
        while (true) {
            pass = sc.nextLine();
            if (pass.matches("[.[^\\s]]{6,}")) {
                break;
            } else {
                System.out.println("You must enter least 6 characters, and no space!");
            }
        }
        return pass;
    }

    //check correct name
    public static String name() {
        String name;
        String regex = "^[a-zA-Z\\s]+";
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

    //check correct phone numver 
    public static int phoneNum() {
        String phone;
        String regex = "\\d{10,11}";
        while (true) {
            phone = sc.nextLine();
            if (phone.matches(regex)) {
                break;
            } else {
                System.out.println("Wrong format! Retype.");
            }
        }
        int phoneNum = Integer.parseInt(phone);
        return phoneNum;
    }

    //check correct email
    public static String email() {
        String email;
        String regex = "^[\\w-\\d]+(\\.[\\w\\d]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,4})$";
        while (true) {
            email = sc.nextLine();
            if (email.matches(regex)) {
                break;
            } else {
                System.out.println("Wrong format!! Retype.");
            }
        }
        return email;
    }

    //check correct address
    public static String address() {
        String address;
        String regex = "^[\\w\\d\\s]+";
        while (true) {
            address = sc.nextLine();
            if (address.matches(regex)) {
                break;
            } else {
                System.out.println("Wrong format!! Retype.");
            }
        }
        return address;
    }

    //check correct date 
    public static Date date() {
        Date date = null;
        while (true) {
            String da = sc.nextLine();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                date = sdf.parse(da);
                break;
            } catch (Exception e) {
                System.out.println("Wrong");
            }
        }
        return date;
    }

}
