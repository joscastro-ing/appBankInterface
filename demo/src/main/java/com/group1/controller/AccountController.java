package com.group1.controller;

import com.group1.model.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class AccountController {
    public static Account activeAccount;
    @FXML
    private Label userNameAccount;
    @FXML
    private Label numAccount;
    @FXML
    private Label balanceAccount;
    @FXML
    private Label typeAccount;
    @FXML
    private ComboBox listAccountscb;
    @FXML
    private Button transaction;
    @FXML
    private Button deposite;
    @FXML
    private Button history;

    ObservableList<String> accountsUser = FXCollections
            .observableList(browseAccounts(HomeController.userActive.getAccounts()));
    @FXML
    public void showAccounts(Event event){
        listComboAccounts(listAccountscb, accountsUser);
    }
    @FXML
    public void updateAccount(Event event){
        for(Account accounts: HomeController.userActive.getAccounts()){
            String accountNumber = String.valueOf(accounts.getAccountNumber());
            if(listAccountscb.getSelectionModel().getSelectedItem().equals(accountNumber)){
                activeAccount = accounts;
                userNameAccount.setText(HomeController.userActive.getName());
                numAccount.setText(String.valueOf(activeAccount.getAccountNumber()));
                balanceAccount.setText("$" + String.valueOf(activeAccount.getBalance()));
                typeAccount.setText(String.valueOf(activeAccount.getAccountType()));
            }
        }
    }

    @FXML
    public void initialize(){
        userNameAccount.setText(HomeController.userActive.getName());
        numAccount.setText(String.valueOf(HomeController.userActive.getAccounts().get(0).getAccountNumber()));
        balanceAccount.setText("$" + String.valueOf(HomeController.userActive.getAccounts().get(0).getBalance()));
        typeAccount.setText(String.valueOf(HomeController.userActive.getAccounts().get(0).getAccountType()));
        activeAccount = HomeController.userActive.getAccounts().get(0);
    }
    @FXML
    public void makeTransaction(ActionEvent event){
        HomeController.loadStage("Transaction.fxml", event);
        for(Account account: HomeController.userActive.getAccounts()){
            String accountNumber = String.valueOf(account.getAccountNumber());
            if(accountNumber.equals(numAccount.getText())){
                activeAccount = account;
            }
        }
    }
    @FXML
    public void makeDeposite(ActionEvent event){
        HomeController.loadStage("Deposite.fxml", event);
    }
    @FXML
    public void showHistory(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.NONE,  AccountController.activeAccount.history(), ButtonType.OK);
        alert.show();
    }
    @FXML
    public void logOutAccount(MouseEvent event){
        HomeController.loadStage("Home.fxml", event);
    }
    @FXML
    public void createNewAccount(ActionEvent event){
        HomeController.loadStage("CreateAccount.fxml", event);
    }

    public ArrayList<String> browseAccounts(ArrayList<Account> accounts){
        ArrayList<String> browseaccounts = new ArrayList<>();
        for(Account account:accounts){
            String value= String.valueOf(account.getAccountNumber());
            browseaccounts.add(value);
        }
        return browseaccounts;
    }

    public static void listComboAccounts(ComboBox<String> cb, ObservableList<String> list){
        cb.setItems(list);
    }
}
