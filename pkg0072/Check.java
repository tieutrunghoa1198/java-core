/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0072;

import java.util.*;

/**
 *
 * @author songm
 */
public class Check {

    //check tài khoản
    public static boolean account(ArrayList<Account> listAcc, String acc) {
        for (int i = 0; i < listAcc.size(); i++) {
            if (acc.equals(listAcc.get(i).getAcc())) {
                return true;
            }
        }
        return false;
    }

    //check phone
    public static boolean phoneNumber(ArrayList<Account> listPhone, int phone) {
        for (int i = 0; i < listPhone.size(); i++) {
            if (phone == listPhone.get(i).getPhone()) {
                return true;
            }
        }
        return false;
    }

    //check email 
    public static boolean email(ArrayList<Account> listEmail, String email) {
        for (int i = 0; i < listEmail.size(); i++) {
            if (email.equalsIgnoreCase(listEmail.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    //check mật khẩu và tài khoản khớp hay không 
    public static boolean pass(String userName, String pass, ArrayList<Account> list) {
        for (int i = 0; i < list.size(); i++) {
            if (userName.equals(list.get(i).getAcc()) && pass.equals(list.get(i).getPass())) {
                return true;
            }
        }
        return false;
    }
}
