/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundle;

import java.util.*;

/**
 *
 * @author songm
 */
public class Interface {

    Scanner sc = new Scanner(System.in);
    static String currentLang = "vi";
    ResourceBundle bundle;
    
    void run() {
        boolean flag = true;
        int choice;
        Locale currentLocale;
        while (flag) {
            System.out.println("=====Login Program=====");
            System.out.println("Tien Phong Bank");
            System.out.println("1. English");
            System.out.println("2. Tieng Viet");
            System.out.println("3. Captcha");
            System.out.println("4. Exit");

            currentLocale = new Locale(currentLang);
            bundle = ResourceBundle.getBundle(Function.baseName, currentLocale);

            System.out.print(bundle.getString("choose_option"));
            choice = Function.checkOption(1, 4, currentLang);
            switch (choice) {
                case 1:
                    Interface.currentLang = "en";
                    Function.login(currentLang);
                    break;
                case 2:
                    Interface.currentLang = "vi";
                    Function.login(currentLang);
                    break;
                case 3:
                    System.out.println("\n" + Function.captchaGenerator() + "\n");
                    break;
                case 4:
                    flag = false;
                    break;
            }
        }
    }
}
