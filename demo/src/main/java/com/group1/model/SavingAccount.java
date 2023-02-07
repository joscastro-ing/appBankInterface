package com.group1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingAccount extends Account{


    public SavingAccount(){}
    public SavingAccount(double balance, User user) {
        super(balance, user);
        setAccountType("SavingAccount");
    }
    public static Account createAccount(double balance, User usuario){
        Account account = new CurrentAccount();
        account.generateNumAccount();
        account.setBalance(balance);
        account.setUser(usuario);
        account.setAccountType("SavingAccount");
        usuario.addAccount(account);
        return account;
    }

    public String history() {
        String movements = "Tansactions: ";
        if(getMovements().isEmpty()){
            movements = "No hay ningún movimiento que mostrar";
        }else{
            for(int i=0; i<getMovements().size(); i++){
                movements = movements+ "\n" + getMovements().get(i).toString();
            }
        }
        return movements;
    }

    @Override
    public String toString() {
        return "SavingAccount" + super.toString();
    }
}
