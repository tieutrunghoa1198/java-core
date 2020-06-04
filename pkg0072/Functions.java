/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0072;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 *
 * @author songm
 */
public class Functions {

    static Scanner sc = new Scanner(System.in);

    public static void addNewAccount(ArrayList<Account> listAccount) {
        String acc, pass, name, email, address, encryptPass = null;
        int phone;
        //enter tài khoản 
        while (true) {
            System.out.println("Account");
            acc = Validate.userName();
            if (!Check.account(listAccount, acc)) {
                break;
            } else {
                System.out.println("Account already exist!! Retype.");
            }
        }
        // enter mật khẩu 
        System.out.println("Password:");
        pass = Validate.pass();
        try {
            encryptPass = MD5_Encryption.encrypt(pass);
        } catch (Exception e) {
            System.out.println("Error");
        }
        // enter tên nhân vật 
        System.out.println("Name:");
        name = Validate.name();
        // enter số điện thoại
        while (true) {
            System.out.println("Phone: ");
            phone = Validate.phoneNum();
            if (!Check.phoneNumber(listAccount, phone)) {
                break;
            } else {
                System.out.println("Phone number already exist!! Retype.");
            }
        }
        //enter email
        while (true) {
            System.out.println("Email: ");
            email = Validate.email();
            if (listAccount.isEmpty()) {
                break;
            }
            if (!Check.email(listAccount, email)) {
                break;
            } else {
                System.out.println("Email already exist!! Retype.");
            }
        }
        //enter  địa chỉ nhà 
        System.out.println("Address:");
        address = Validate.address();
        System.out.println("Date: ");
        Date date = Validate.date();
        //add new account
        if (CRUD.createAcc(listAccount, acc, encryptPass, name, phone, email, address, date)) {
            System.out.println("Create account successfully!");
        } else {
            System.out.println("Cannot create account. Try again.");
        }
    }

    //login function 
    public static void loginFunction(ArrayList<Account> list) {
        String pass, userName, encryptPass = null;
        int check = 0;
        File f = new File("data.txt");
        if (list.isEmpty()) {
            if (f.exists()) {
                Archive.loadFile(list);
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
                System.out.print("Account:");
                userName = sc.nextLine();
                if (Check.account(list, userName)) {
                    break;
                } else {
                    System.out.println("Account does not exist!!");
                }
            }
            //confirm password
            while (true) {
                System.out.print("Password: ");
                pass = sc.nextLine();
                try {
                    encryptPass = MD5_Encryption.encrypt(pass);
                } catch (Exception e) {
                    System.out.println("error");
                }
                if (check < 4) {
                    if (Check.pass(userName, encryptPass, list)) {
                        System.out.println("Login successfully!");
                        changePass(list, userName);
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

    public static void changePass(ArrayList<Account> list, String acc) {
        if (askUser(list, acc)) {
            while (true) {
                System.out.println("Enter old password: ");
                String oldPass = Validate.pass();
                String encryptOldPass = null;
                try {
                    encryptOldPass = MD5_Encryption.encrypt(oldPass);
                } catch (Exception e) {
                    System.out.println("error3");
                }
                if (Check.pass(acc, encryptOldPass, list)) {
                    System.out.println("Enter new password: ");
                    String newPass;
                    String encryptNewPass = null;
                    String encryptConfirm = null;
                    //enter first password
                    while (true) {
                        newPass = Validate.pass();
                        try {
                            encryptNewPass = MD5_Encryption.encrypt(newPass);
                        } catch (Exception e) {
                            System.out.println("Error4");
                        }
                        if (!encryptOldPass.equals(encryptNewPass)) {
                            break;
                        } else {
                            System.out.println("Old password must be different than new password!");
                        }
                    }
                    //enter the second pass 
                    while (true) {
                        System.out.println("Confirm password: ");
                        String confirm = sc.nextLine();
                        try {
                            encryptConfirm = MD5_Encryption.encrypt(confirm);
                        } catch (Exception e) {
                            System.out.println("Error5");
                        }
                        if (encryptConfirm.equals(encryptNewPass)) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getAcc().equals(acc)) {
                                    list.get(i).setPass(encryptNewPass);
                                    System.out.println("Change password successfully!");
                                    break;//break change password
                                }
                            }
                            break;//break confirm pass
                        } else {
                            System.out.println("Password invalid! Retype.");
                        }
                    }
                    break;
                    //break enter old pass
                } else {
                    System.out.println("Retype!");
                }
            }
//            try {
//                FileOutputStream f = new FileOutputStream("data.txt");
//            } catch (Exception e) {
//                System.out.println(e);
//            }
            for (int i = 0; i < list.size(); i++) {
                Archive.writeFile(list.get(i));
            }
        } else {
            System.out.println("Exit!!");
        }
    }

    //ask user 
    public static boolean askUser(ArrayList<Account> list, String acc) {
        for (int i = 0; i < list.size(); i++) {
            if (acc.equals(list.get(i).getAcc())) {
                System.out.println("Hello " + list.get(i).getName() + ", do you want to change password now? Y/n:");
            } else {
                System.out.println("Cannot get username after login.");
            }
        }
        while (true) {
            String option = sc.nextLine();
            if (option.equalsIgnoreCase("Y")) {
                return true;
            } else if (option.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.print("Retype: ");
            }
        }
    }
}
