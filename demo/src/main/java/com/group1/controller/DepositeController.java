package com.group1.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class DepositeController {
    @FXML
    private TextField depositAmount;

    @FXML
    public void cancelDeposit(Event event){
        ChangePage.loadStage("Account.fxml", event);
    }
    @FXML
    public void depositDone(ActionEvent event){
        double balanceOriginal = AccountController.activeAccount.getBalance();
        AccountController.activeAccount.setBalance(balanceOriginal + Double.parseDouble(depositAmount.getText()));
        Alert alert = new Alert(Alert.AlertType.NONE, "Successful" + "\n You have deposit $" + depositAmount.getText() , ButtonType.OK);
        alert.show();
    }
}
