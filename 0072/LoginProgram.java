/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.*;
import java.util.*;

/**
 *
 * @author songm
 */
public class LoginProgram {

    Scanner sc = new Scanner(System.in);

    void mainMenu() {
        boolean isLoop = true;
        Validation validate = new Validation();
        LoginProgram program = new LoginProgram();
        List<Account> list = new ArrayList<>();
        program.loadData(list);

        while (isLoop) {
            System.out.println("============ Login Program =========");
            System.out.println(
                    "1. Add User" + "\n"
                    + "2. Login" + "\n"
                    + "3. Exit" + "\n"
            );
            System.out.print("Enter option: ");
            int option = validate.inputRange(1, 3);
            System.out.println();
            switch (option) {
                case 1:
                    Account acc = program.addUser(list);
                    if (acc == null) {
                        System.out.println("New account can not be created.");
                    } else {
                        System.out.println("Create successfully!");
                        list.add(acc);
                    }
                    break;
                case 2:
                    program.login(list);
                    System.out.println();
                    break;
                case 3:
                    System.out.println(" -> System exit.");
                    isLoop = false;
                    break;
            }
        }
    }

    Account addUser(List<Account> list) {
        Validation validate = new Validation();
        Check check = new Check();
        MD5 md5 = new MD5();
        String md5Pass = null;

        System.out.println("---------- Add User --------");

        System.out.print("Enter Account: ");
        String userName = validate.userName();
        if (check.account(list, userName)) {
            System.out.println("Account exist.");
            return null;
        }

        System.out.print("Enter Password: ");
        String pass = validate.pass();
        try {
            md5Pass = md5.encrypt(pass);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        System.out.print("Enter Name: ");
        String name = validate.name();

        System.out.print("Enter Phone: ");
        int phone = validate.phoneNum();
        if (check.phoneNumber(list, phone)) {
            System.out.println("Phone number exist.");
            return null;
        }

        System.out.print("Enter Email: ");
        String email = validate.email();
        if (check.email(list, email)) {
            System.out.println("Email exist.");
            return null;
        }

        System.out.print("Enter Address: ");
        String address = validate.address();

        System.out.print("Enter DOB: ");
        Date date = validate.date();

        Account acc = new Account(userName, md5Pass, name, phone, email, address, date);
        return acc;
    }

    void login(List<Account> list) {

        LoginProgram program = new LoginProgram();
        Check check = new Check();
        MD5 md5 = new MD5();

        System.out.print("Enter username: ");
        String userName = sc.nextLine();
        Account acc = program.search(list, userName);
        if (acc == null) {
            System.out.println("Username is not exist.");
            return;
        }

        System.out.print("Enter password: ");
        String encryptPass = null, pass = sc.nextLine();
        try {
            encryptPass = md5.encrypt(pass);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        boolean authrorize = check.pass(acc, encryptPass);

        if (authrorize) {
            System.out.println("Login successfully!");
            if (program.askUser(acc)) {
                program.changePass(acc);
            } else {
                System.out.println("No password is changed.");
            }
        } else {
            System.out.println("Wrong password.");
            System.out.println("Try again later.");
        }
    }

    boolean confirmPass(String pass, String confirmPass) {
        if (pass.equals(confirmPass)) {
            return true;
        } else {
            return false;
        }
    }

    void changePass(Account acc) {
        Validation validate = new Validation();
        MD5 md5 = new MD5();
        Check check = new Check();
        LoginProgram program = new LoginProgram();

        System.out.print("Old password: ");
        String oldPass = validate.pass();
        String encryptOldPass = null;
        try {
            encryptOldPass = md5.encrypt(oldPass);
        } catch (Exception e) {
            System.out.println("error3");
        }

        if (check.pass(acc, encryptOldPass)) {
            String newPass, confirmPass;
            System.out.print("Enter new password: ");
            newPass = validate.pass();
            System.out.print("Confirm password: ");
            confirmPass = validate.pass();

            if (program.confirmPass(newPass, confirmPass)) {
                String finalPass = null;
                try {
                    finalPass = md5.encrypt(newPass);
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                    System.out.println(e);
                }
                acc.setPass(finalPass);
            } else {
                System.out.println("Password is not match.");
            }

        } else {
            System.out.println("Wrong password.");
        }
    }

    boolean askUser(Account acc) {
        int flag = 0;
        System.out.println("Hello " + acc.getName() + ", do you want to change password now? Y/N:");
        while (flag < 4) {
            String option = sc.nextLine();
            if (option.equalsIgnoreCase("Y")) {
                return true;
            } else if (option.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.print("Retype: ");
                flag++;
            }
        }
        return false;
    }

    Account search(List<Account> list, String userName) {
        Account acc;
        for (int i = 0; i < list.size(); i++) {
            acc = list.get(i);
            if (acc.getAcc().equals(userName)) {
                return acc;
            }
        }
        return null;
    }

    public void loadData(List<Account> list) {
        String acc, pass, email, name, address;
        Date date;
        int phone;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tknz = new StringTokenizer(line, "|");
                while (tknz.hasMoreTokens()) {
                    acc = tknz.nextToken();
                    pass = tknz.nextToken();
                    name = tknz.nextToken();
                    phone = Integer.parseInt(tknz.nextToken());
                    email = tknz.nextToken();
                    address = tknz.nextToken();
                    date = sdf.parse(tknz.nextToken());
                    Account account = new Account(acc, pass, name, phone, email, address, date);
                    list.add(account);
                }
            }
        } catch (IOException | NumberFormatException | ParseException e) {
            System.out.println(e);
            System.out.println("Cannot load file.");
        }
    }
}
