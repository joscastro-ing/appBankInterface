package com.group1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class Account {
    private User user;
    private long accountNumber;
    private static long generator = 103304112;
    private String accountType;
    private double balance;
    private ArrayList<Transaction> movements = new ArrayList<>();

    public Account(){}

    public Account(double balance, User user){
        this.accountNumber = generator++;
        this.balance = balance;
        this.user = user;
    }


    public abstract String history();

    public void generateNumAccount (){
        setAccountNumber(generator++);
    }

   /* public void deleteAccount(){
        for (Account account1 : getUser().getAccounts()) {
            if(account1.getAccountNumber() == getAccountNumber()) {
                user.getAccounts().remove(account1);
                System.out.println("La cuenta con número: " + getAccountNumber()
                        + " ha sido eliminada con éxito");
            }else {
                System.out.println("Ninguna cuenta fue elminada");
            }
            break;
        }
    }*/
    public void setMovements(Transaction t) {
        getMovements().add(t);
    }

    @Override
    public String toString() {
        return "" +
                "\naccountnumber: " + accountNumber +
                "\nuser: " + user.getName() +
                "\nbalance: " + balance;
    }
}
