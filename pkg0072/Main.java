/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0072;
import java.util.*;
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
        boolean flag = true;
        int choice;
        ArrayList<Account> listAccount = new ArrayList<>();
        System.out.println("========== Login Program ==========");
        while (flag != false) {
            System.out.println("1. Add user");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            choice = Validate.number(1, 3);
            switch (choice) {
                case 1:
                    Archive.addNewAccount_withData(listAccount);
                    break;
                case 2:
                    Functions.loginFunction(listAccount);
                    break;
                case 3:
                    flag = false;
                    break;
            }
        }
    }
}
