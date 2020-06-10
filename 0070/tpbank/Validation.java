/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpbank;

import java.util.*;

/**
 *
 * @author songm
 */
public class Validation {

    Scanner sc = new Scanner(System.in);

    public int checkOption(int min, int max, ResourceBundle bundle) {
        int n;

        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n < min) {
                    System.out.println(bundle.getString("range") + "[" + min + "-" + max + "]");
                    System.out.println(bundle.getString("your_choice_must_be_greater") + min);
                    System.out.print(bundle.getString("retype"));
                    continue;
                } else if (n > max) {
                    System.out.println(bundle.getString("range") + "[" + min + "-" + max + "]");
                    System.out.println(bundle.getString("your_choice_must_be_smaller") + max);
                    System.out.print(bundle.getString("retype"));
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(bundle.getString("must_be_a_number"));
                System.out.print(bundle.getString("retype"));
            }
        }
        return n;
        //end of check input
    }

    public void userName(ResourceBundle bundle) {
        String userName;
        String regex = "^\\d{10}$";
        while (true) {
            userName = sc.nextLine();
            userName = userName.trim();
            if (userName.matches(regex)) {
                break;
            } else {
                System.out.println(bundle.getString("acc_num_err"));
                System.out.println(bundle.getString("retype"));
            }
        }
    }

    public void pass(ResourceBundle bundle) {
        String pass;
        String regex = "^\\w{8,31}$";
        while (true) {
            pass = sc.nextLine();
            if (pass.matches(regex)) {
                break;
            } else {
                System.out.println(bundle.getString("pass_err"));
                System.out.println(bundle.getString("retype"));
            }
        }
    }
}
