/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author songm
 */
public class Validation {

    Scanner sc = new Scanner(System.in);

    //check int 
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
                System.out.println("Retype: ");
            }
        }
        return n;
    }

    //check correct userName
    public String userName() {
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
    public String pass() {
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
    public String name() {
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
    public int phoneNum() {
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
    public String email() {
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
    public String address() {
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
    public Date date() {
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
