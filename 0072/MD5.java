/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author songm
 */
public class MD5 {

    public String encrypt(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encryptPass;
        //messageDigest có sẵn trong secutiry giúp thực hiện việc mã hóa
        MessageDigest msd = MessageDigest.getInstance("MD5");
        //chuyển chuỗi string văn bản gốc sang mảng các byte 
        byte[] passToBytes = pass.getBytes("UTF-8"); //UTF-8 giúp mã hóa được chuỗi kí tự unicode
        //messageDigest mã hóa mảng byte văn bản gốc sang mảng byte mới 
        byte[] encrypt = msd.digest(passToBytes);
        //chuyển mảng byte đã mã hóa sang chuỗi các số hệ 16 
        BigInteger bigInt = new BigInteger(1, encrypt);
        encryptPass = bigInt.toString(16);

        return encryptPass;
    }
}
