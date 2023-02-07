package com.group1.controller;

import com.group1.HelloApplication;
import com.group1.model.Account;
import com.group1.model.CurrentAccount;
import com.group1.model.SavingAccount;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateNewAccountController {
    @FXML
    private TextField initialAmount;
    @FXML
    private ComboBox typeOfAccount;

    @FXML
    public void showList(Event event){
        HelloApplication.listCombo(typeOfAccount, SignUpController.listaccounts);
    }

    @FXML
    public void cancelCreateAccount(Event event){
        HomeController.loadStage("Account.fxml", event);

    }
    @FXML
    public void createNew(ActionEvent event){
        Account newAccount = null;
        if(typeOfAccount.getSelectionModel().getSelectedItem() == "CurrentAccount"){
            newAccount = CurrentAccount.createAccount(Double.parseDouble(initialAmount.getText()), HomeController.userActive);
        }else{
            newAccount = SavingAccount.createAccount(Double.parseDouble(initialAmount.getText()), HomeController.userActive);
        }
        Alert alert = new Alert(Alert.AlertType.NONE, "You have created a new Account" + "\n " + newAccount.toString() , ButtonType.OK);
        alert.show();

    }
}
