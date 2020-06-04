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
public class CRUD {
    public static boolean createAcc(ArrayList<Account> list, String acc, String pass, String name, int phone, String email, String address, Date date){
        try {
            Account newAcc = new Account(acc, pass, name, phone, email, address, date);
            list.add(newAcc);
            Archive.writeFile(newAcc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean deleteAcc(ArrayList<Account> list){
        return true;
    }
}
