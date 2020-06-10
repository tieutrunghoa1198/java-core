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
public class GUI {

    Scanner sc = new Scanner(System.in);

    void mainMenu() {
        boolean flag = true;
        int choice;
        TPBank program = new TPBank();
        Validation validte = new Validation();

        //initialize bundle 
        ResourceBundle bundle = setLanguage("vi");

        while (flag) {
            System.out.println("=====Login Program=====");
            System.out.println("Tien Phong Bank");
            System.out.println("1. English");
            System.out.println("2. Tieng Viet");
            System.out.println("3. Captcha");
            System.out.println("4. Exit");

            System.out.print(bundle.getString("choose_option"));
            choice = validte.checkOption(1, 4, bundle);
            switch (choice) {
                case 1:
                    bundle = setLanguage("en");
                    program.login(bundle);
                    break;
                case 2:
                    bundle = setLanguage("vi");
                    program.login(bundle);
                    break;
                case 3:
                    System.out.println("\n" + program.captchaGenerator() + "\n");
                    break;
                case 4:
                    flag = false;
                    break;
            }
        }
    }

    ResourceBundle setLanguage(String lang) {
        String baseName = "language.Bundle";
        Locale currentLocale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, currentLocale);
        return bundle;
    }
}
