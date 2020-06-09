/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    private String acc;
    private String pass;
    private String name;
    private int phone;
    private String email;
    private String address;
    private Date dob;

    public Account(String acc, String pass, String name, int phone, String email, String address, Date dob) {
        this.acc = acc;
        this.pass = pass;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(dob);
        return date;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return (acc + "|" + pass + "|" + name + "|" + phone + "|" + email + "|" + address + "|" + getDob());
    }

}
