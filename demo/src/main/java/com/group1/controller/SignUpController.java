package com.group1.controller;

import com.group1.HelloApplication;
import com.group1.model.Account;
import com.group1.model.CurrentAccount;
import com.group1.model.SavingAccount;
import com.group1.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SignUpController {
    public static ArrayList<User> users= new ArrayList<>();
    @FXML
    private TextField nameSignUp;
    @FXML
    private TextField lastnameSignUp;
    @FXML
    private TextField passwordSignUp;
    @FXML
    private TextField emailSignUp;
    @FXML
    private TextField cellSignUp;
    @FXML
    private TextField amount;
    @FXML
    private Button buttonSignUp;
    @FXML
    private ComboBox typeAccount;

    public static ObservableList<String> listaccounts = FXCollections.observableArrayList("CurrentAccount", "SavingAccount");

    @FXML
    public void createList(Event event){
        HelloApplication.listCombo(typeAccount, listaccounts);
    }

    @FXML
    private void actionSignUp(ActionEvent event){
        //Validar email, validar amount (redondear a dos decimales)
        if(nameSignUp.getText().isEmpty() || lastnameSignUp.getText().isEmpty()
                || passwordSignUp.getText().isEmpty()
                || emailSignUp.getText().isEmpty()
                || cellSignUp.getText().isEmpty()
                || amount.getText().isEmpty()
        ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You must to fill all fields");
            alert.show();
        }else if(passwordSignUp.getText().length()<8){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password cannot be empty and it must be at least 8 characters");
            alert.show();
        } else if(Double.parseDouble(amount.getText()) < 25){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Amount have to be greater than or equal to $25");
            alert.show();
        }else if(!emailSignUp.getText().contains("@") || !emailSignUp.getText().contains(".com")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Insert a correct email direction");
            alert.show();
        }
        else{
            Account account = null;
            User user = new User(nameSignUp.getText(),
                    lastnameSignUp.getText(),
                    emailSignUp.getText(),
                    cellSignUp.getText(),
                    passwordSignUp.getText()
            );
            users.add(user);
            if(typeAccount.getSelectionModel().getSelectedItem() == "CurrentAccount"){
                account = CurrentAccount.createAccount(Double.parseDouble(amount.getText()), user);
            }else{
                account = SavingAccount.createAccount(Double.parseDouble(amount.getText()), user);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, user.toString() + "\n" + user.getAccounts().get(0).toString());
            alert.show();
            alert.setOnCloseRequest(ev ->{
                HomeController.loadStage("Home.fxml", event);
            });
        }
    }

    @FXML
    private void passwordKey(KeyEvent event){
        passwordSignUp.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().equals(" ")) {
                change.setText("");
            }
            return change;
        }));
    }
    @FXML
    private void amountKey(KeyEvent event){
        amount.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().contains(".")) {
                change.setText("");
            }else if(!Character.isDigit(event.getCharacter().charAt(0))){
                change.setText("");
            }
            return change;
        }));
    }

    @FXML
    private void signInClick(MouseEvent click){
        HomeController.loadStage("Home.fxml", click);
    }

}
