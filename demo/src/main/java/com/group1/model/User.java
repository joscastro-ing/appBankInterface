package com.group1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class User {
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String celphoneNumber;
    private ArrayList<Account> accounts = new ArrayList<>();

    public User(String name, String lastname, String email, String celphoneNumber, String password) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.celphoneNumber = celphoneNumber;
    }


    public void addAccount(Account account){
        accounts.add(account);
    }

    @Override
    public String toString() {
        return "User \n" +
                "name: " + name + '\n' +
                "lastname: " + lastname + '\n' +
                "email: " + email + '\n' +
                "celphoneNumber: " + celphoneNumber;
    }
}
