/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0057;

import java.util.*;
import java.io.*;

/**
 *
 * @author Tiêu Trung Hòa
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        Main main = new Main();
        boolean flag = true;
        List<Account> list = new ArrayList<>();
        System.out.println("========== USER MANAGEMENT SYSTEM ==========");
        System.out.println("============================================");
        do {
            System.out.println("1. Create account.");
            System.out.println("2. Login.");
            System.out.println("3. Exit. ");
            System.out.print("Select option: ");
            choice = main.checkNumber(1, 3);
            switch (choice) {
                case 1:
                    main.addNewAccount(list);
                    break;
                case 2:
                    main.loginFunction(list);
                    break;
                case 3:
                    flag = false;
                    break;
            }
        } while (flag != false);
    }

    //check int 
    public int checkNumber(int min, int max) {
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

    public String correctUserName() {
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
    public String correctPass() {
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

    //check tài khoản
    public boolean checkAcc(List<Account> listAcc, String acc) {
        for (int i = 0; i < listAcc.size(); i++) {
            if (acc.equals(listAcc.get(i).getUserName())) {
                return true;
            }
        }
        return false;
    }

    public void loadFile(List<Account> list) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tknz = new StringTokenizer(line, "|");
                while (tknz.hasMoreTokens()) {
                    String userName = tknz.nextToken();
                    String pass = tknz.nextToken();
                    list.add(new Account(userName, pass));
                }
            }

        } catch (Exception e) {
        }
    }// end of load file 

    public void writeFile(Account newAcc) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true));
            writer.write(newAcc.toString());
            writer.newLine();
            writer.close();
        } catch (Exception e) {
        }
    }
    
    //start of load file 
    public void addNewAccount(List<Account> list) {
        File f = new File("data.txt");
        if (f.exists()) {
            loadFile(list);
            addFunction(list);
        } else {
            addFunction(list);
        }
    }// end of add new account 

    public void addFunction(List<Account> list) {
        String acc, pass, encryptAcc = "";
        //enter tài khoản 
        while (true) {
            System.out.println("Account");
            acc = correctUserName();
            if (!checkAcc(list, acc)) {
                break;
            } else {
                System.out.println("Account already exist!! Retype.");
            }
        }
        // enter mật khẩu 
        System.out.println("Password:");
        pass = correctPass();
        try {
            encryptAcc = MD5_Encryption.encrypt(pass);
        } catch (Exception e) {
            System.out.println("Error1");
        }
        Account newAcc = new Account(acc, encryptAcc);
        list.add(newAcc);
        //write into file 
        writeFile(newAcc);
    }

    public boolean checkPass(String userName, String pass, List<Account> list) {
        for (int i = 0; i < list.size(); i++) {
            if (userName.equals(list.get(i).getUserName()) && pass.equals(list.get(i).getPassWord())) {
                return true;
            }
        }
        return false;
    }
    
    public void loginFunction(List<Account> list) {
        String pass, userName, encryptPass = null;
        int check = 0;
        File f = new File("data.txt");
        if (list.isEmpty()) {
            if (f.exists()) {
                loadFile(list);
                if (list.isEmpty()) {
                    System.out.println("There is no data in system! Select function 1 to add new account.");
                    return;
                }
            } else {
                System.out.println("There is no data in system! Select function 1 to add new account.");
                return;
            }
        }
        //enter username and password
        while (check == 0) {
            //check account exist
            while (true) {
                System.out.println("Account:");
                userName = correctUserName();
                if (checkAcc(list, userName)) {
                    break;
                } else {
                    System.out.println("Account does not exist!!");
                }
            }
            //confirm password
            while (true) {
                System.out.println("Password");
                pass = correctPass();
                try {
                    encryptPass = MD5_Encryption.encrypt(pass);
                } catch (Exception e) {
                    System.out.println("error1");
                }
                if (check < 4) {
                    if (checkPass(userName, encryptPass, list)) {
                        System.out.println("Login successfully!");
                        check++;
                        break;
                    } else {
                        System.out.println("Wrong username or password!! Retype.");
                        check++;
                    }
                } else {
                    System.out.println("Wrong pass too many times! System exit!");
                    break;
                }
            }// ----- end of confirm passWord
        }// ------ end of userName and passWord
    }//------ end of login function
}
