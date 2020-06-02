/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0057;

/**
 *
 * @author Tiêu Trung Hòa
 */
public class Account {
    private String userName;
    private String passWord;

    public Account() {
    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public String toString() {
        return (getUserName()+"|"+getPassWord());
    }
    
}
