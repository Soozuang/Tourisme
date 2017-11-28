package com.android.soozuang.ohmytrip.Model;

/**
 * Created by soozuang on 10/17/2017.
 */

public class User {
    private String Name;
    private String Password;

    public User(){

    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(String name, String pass){
        Name = name;
        Password = pass;
    }
}
