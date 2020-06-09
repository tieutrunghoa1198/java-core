/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.util.*;

/**
 *
 * @author songm
 */
public class Check {
    //check tài khoản

    public boolean account(List<Account> listAcc, String acc) {
        for (int i = 0; i < listAcc.size(); i++) {
            if (acc.equals(listAcc.get(i).getAcc())) {
                return true;
            }
        }
        return false;
    }

    //check phone
    public boolean phoneNumber(List<Account> listPhone, int phone) {
        for (int i = 0; i < listPhone.size(); i++) {
            if (phone == listPhone.get(i).getPhone()) {
                return true;
            }
        }
        return false;
    }

    //check email 
    public boolean email(List<Account> listEmail, String email) {
        for (int i = 0; i < listEmail.size(); i++) {
            if (email.equalsIgnoreCase(listEmail.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    //check mật khẩu và tài khoản khớp hay không 
    public boolean pass(Account acc, String pass) {
        if (acc.getPass().equals(pass)) {
            return true;
        } else {
            return false;
        }
    }
}
