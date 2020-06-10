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
public class TPBank {

    Scanner sc = new Scanner(System.in);

    ResourceBundle bundle;

    public String captchaGenerator() {
        Random random = new Random();
        StringBuffer captchaStringBuffer = new StringBuffer();
        int length = 7;
        for (int i = 0; i < length; i++) {
            int baseCharNumber = Math.abs(random.nextInt()) % 62;
            int charNumber;
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

    public String checkCaptcha(String captchaGenerator, ResourceBundle bundle) {
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

    public void login(ResourceBundle bundle) {
        Validation validate = new Validation();

        System.out.print(bundle.getString("acc_num"));
        validate.userName(bundle);

        //enter password
        System.out.print(bundle.getString("pass"));
        validate.pass(bundle);

        //generate captcha code
        String captchaGenerator = captchaGenerator();
        System.out.println(bundle.getString("captcha") + captchaGenerator);
        //enter captcha code
        System.out.print(bundle.getString("enter_captcha"));
        //check that captcha input is correct or not
        String vllg = checkCaptcha(captchaGenerator, bundle);
        System.out.println(vllg);
    }
}
