/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundle;

import java.util.*;
import java.lang.*;

/**
 *
 * @author songm
 */
public class Function {

    public static String baseName = "Language.Bundle";
    public static Scanner sc = new Scanner(System.in);
    private static ResourceBundle bundle;

    //check option for the main class
    public static int checkOption(int min, int max, String lang) {
        int n;
        Locale locale = new Locale(lang);

        //sue resource bundle to get base name of translation file and locale 
        bundle = ResourceBundle.getBundle(baseName, locale);
        //start of check input 
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
            } catch (Exception e) {
                System.out.println(bundle.getString("must_be_a_number"));
                System.out.print(bundle.getString("retype"));
            }
        }
        return n;
        //end of check input
    }

    public static void correctUserName(Locale locale) {
        String userName;
        String regex = "\\d{10}";
        bundle = ResourceBundle.getBundle(baseName, locale);
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

    public static void correctPass(Locale locale) {
        String pass;
        String regex = "\\w{8,31}";
        bundle = ResourceBundle.getBundle(baseName, locale);
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

    public static String captchaGenerator() {
        Random random = new Random();
        StringBuffer captchaStringBuffer = new StringBuffer();
        int length = 7;
        for (int i = 0; i < length; i++) {
            int baseCharNumber = Math.abs(random.nextInt()) % 62;
            int charNumber = 0;
            //if base char number less than 26(uppercase letters) in ASCII code 
            if (baseCharNumber < 26) {
                //the number 65 is the first uppercase letter in ASCII code
                charNumber = 65 + baseCharNumber;
            } //if base char number less than 26(lowercase letters) in ASCII code 
            else if (baseCharNumber < 52) {
                //we need to minus the 26 uppercase letters 
                //the number 97 is the first lowercase letter in ASCII code
                charNumber = 97 + (baseCharNumber - 26);
            } //the last 10 digits 
            else {
                //we need to minus both 26(uppercase letters) + 26(lowercase letters) 
                //the number 48 is the first digit in ASCII code
                charNumber = 48 + (baseCharNumber - 52);
            }
            captchaStringBuffer.append((char) charNumber);
        }
        return captchaStringBuffer.toString();

    }

    public static String checkCaptcha(String captchaGenerator, Locale locale) {
        bundle = ResourceBundle.getBundle(baseName, locale);
        String captchaInput;
        while (true) {
            captchaInput = sc.nextLine();
            if (captchaInput.compareTo(captchaGenerator) == 0) {
                System.out.println(bundle.getString("correct_captcha"));
                return bundle.getString("valid_login");
            } else {
                System.out.println(bundle.getString("wrong_captcha"));
                System.out.print(bundle.getString("retype"));
            }
        }
    }

    public static void login(String lang) {
        //specify locale
        Locale locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle(Function.baseName, locale);
        //enter account number
        System.out.print(bundle.getString("acc_num"));
        Function.correctUserName(locale);
        //enter password
        System.out.print(bundle.getString("pass"));
        Function.correctPass(locale);
        //generate captcha code
        String captchaGenerator = Function.captchaGenerator();
        System.out.println(bundle.getString("captcha") + captchaGenerator);
        //enter captcha code
        System.out.print(bundle.getString("enter_captcha"));
        //check that captcha input is correct or not
        String vllg = Function.checkCaptcha(captchaGenerator, locale);
        System.out.println(vllg);
    }
}
