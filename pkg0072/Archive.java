/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0072;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author songm
 */
public class Archive {
        public static void loadFile(ArrayList<Account> list) {
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
                    Account add_data = new Account(acc, pass, name, phone, email, address, date);
                    list.add(add_data);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Cannot load file.");
        }
    }//end of load file

    public static void writeFile(Account newAcc) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true));
            writer.newLine();
            writer.write(newAcc.toString());
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Cannot write file.");
        }
    }

    public static void addNewAccount_withData(ArrayList<Account> list) {
        File f = new File("data.txt");
        if (f.exists()) {
            loadFile(list);
            Functions.addNewAccount(list);
        } else {
            Functions.addNewAccount(list);
        }
    }
}
