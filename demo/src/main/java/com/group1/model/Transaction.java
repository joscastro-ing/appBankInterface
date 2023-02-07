package com.group1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transaction {
    public int id;
    public static int count= 113;
    private Account accountOrigen;
    private Account accountDestino;
    private double amount;
    private Date date;
    private String concept;

    public Transaction(){}

    public Transaction(Account accountOrigen, Account accountDestino, double amount) {
        this.accountOrigen = accountOrigen;
        this.accountDestino = accountDestino;
        this.amount = amount;
        this.date = new Date();
        this.id= count++;
    }
    public Transaction(Account accountOrigen, Account accountDestino, double amount, String concept) {
        this.accountOrigen = accountOrigen;
        this.accountDestino = accountDestino;
        this.amount = amount;
        this.date = new Date();
        this.concept = concept;
        this.id= count++;
    }
    public static Transaction makeTransaction(Account accountOrigen, Account accountDestino, double amount, String concept){
        if(validateTransaction(accountOrigen, amount)){
            Transaction trans = new Transaction(accountOrigen, accountDestino, amount, concept);
            accountOrigen.setBalance(accountOrigen.getBalance()-amount);
            accountDestino.setBalance(accountDestino.getBalance()+amount);
            accountOrigen.setMovements(trans);
            return trans;
        }else{
            System.out.println("Invalid Transaction");
            return null;
        }
    }
    public static Transaction makeTransaction(Account accountOrigen, Account accountDestino, double amount){
        if(validateTransaction(accountOrigen, amount)){
            Transaction trans = new Transaction(accountOrigen, accountDestino, amount);
            accountOrigen.setBalance(accountOrigen.getBalance()-amount);
            accountDestino.setBalance(accountDestino.getBalance()+amount);
            accountOrigen.setMovements(trans);
            return trans;
        }else{
            System.out.println("Invalid Transaction");
            return null;
        }
    }
    public static boolean validateTransaction(Account account, double amount){
        if(account.getBalance()<amount){
            return false;
        } else {
            return true;
        }
    }


    @Override
    public String toString() {
        return "\nTransaction: " + getId() +
                "\nDate: " + getDate()+
                "\nSource Account: " + accountOrigen.getAccountNumber() +
                "\nDestination Account: " + accountDestino.getAccountNumber() +
                "\namount: " + amount + "\n" +
                ((concept == null)? "":(concept.isEmpty())? "": "concepto:" + concept);
    }
}
