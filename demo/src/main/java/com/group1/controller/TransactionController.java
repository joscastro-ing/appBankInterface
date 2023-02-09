package com.group1.controller;

import com.group1.model.Account;
import com.group1.model.Mailing;
import com.group1.model.Transaction;
import com.group1.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TransactionController {
    public static boolean validTrans = true;
    @FXML
    private TextField destinationAccount;
    @FXML
    private TextField amountTrans;
    @FXML
    private TextField conceptTrans;

    @FXML
    public void returnToAccount(MouseEvent event){
        ChangePage.loadStage("Account.fxml", event);
    }

    @FXML
    public void transactionDone(ActionEvent event){
        typeTrans(amountTrans, destinationAccount, conceptTrans);
        if(validTrans){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Transaction");
            alert.show();
        }
    }

    public static void typeTrans(TextField amountTrans, TextField destinationAccount, TextField conceptTrans){
        out:
        for(User user: SignUpController.users){
            for(Account account: user.getAccounts()){
                String accountNumber = String.valueOf(account.getAccountNumber());
                if(destinationAccount.getText().equals(accountNumber)){
                    Transaction transaction = isValid(account, amountTrans, conceptTrans);
                    if(transaction == null){
                        validTrans = true;
                        Alert alert = new Alert(Alert.AlertType.ERROR, "You don't have enough money to make this transaction");
                        alert.show();
                        break out;
                    }else {
                        Mailing.sendEmail(HomeController.userActive.getEmail(), transaction.toString());
                        Alert alert = new Alert(Alert.AlertType.NONE, "Successful transaction" + "\n" + transaction.toString(), ButtonType.OK);
                        alert.show();
                        validTrans = false;
                        break out;
                    }

                } else {validTrans = true;}
            }
        }


    }
    public static Transaction isValid(Account account, TextField amountTrans, TextField conceptTrans){
        Transaction transaction = null;
        if(conceptTrans.getText().isEmpty()){
            transaction = Transaction.makeTransaction(AccountController.activeAccount, account, Double.parseDouble(amountTrans.getText()));

        }else{
            transaction = Transaction.makeTransaction(AccountController.activeAccount, account, Double.parseDouble(amountTrans.getText()), conceptTrans.getText());
        }
        return transaction;
    }

}
