package com.group1.controller;

import com.group1.HelloApplication;
import com.group1.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class HomeController {

    public static User userActive;
    public static  boolean valid = true;
    @FXML
    private TextField userLogIn;
    @FXML
    private TextField passwordLogIn;
    @FXML
    private Button buttonLogIn;
    @FXML
    private void eventKey(KeyEvent event){

        userLogIn.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().equals(" ")) {
                change.setText("");
            }
            return change;
        }));

        passwordLogIn.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().equals(" ")) {
                change.setText("");
            }
            return change;
        }));

    }

    @FXML
    private void eventAction(ActionEvent event){
        if(userLogIn.getText().isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION, "User cannot be empty");
            alert.setTitle("ERROR");
            alert.show();
        }else if(passwordLogIn.getText().isEmpty() || passwordLogIn.getText().length()<8){
            Alert alert = new Alert(AlertType.INFORMATION, "Password cannot be empty and it must be at least 8 characters");
            alert.setTitle("ERROR");
            alert.show();
        }else if(SignUpController.users.isEmpty() ||  SignUpController.users == null){
            Alert alert = new Alert(AlertType.INFORMATION, "No user found");
            alert.setTitle("ERROR");
            alert.show();
        }else{
            out:
            for(User user: SignUpController.users){
                if((user.getEmail().equals(userLogIn.getText())) && (user.getPassword().equals(passwordLogIn.getText()))){
                    userActive = user;
                    loadStage("Account.fxml", event);
                    valid = false;
                    break out;
                }else{
                    valid = true;
                }
            }
            if(valid){
                Alert alert = new Alert(AlertType.INFORMATION, "User or password is incorrect");
                alert.setTitle("ERROR");
                alert.show();
            }
        }
    }
    @FXML
    private void signUpClick(MouseEvent click){
        loadStage("SignUp.fxml", click);
    }

    public static void loadStage(String url, Event event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(url));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Platform.exit();
                }
            });
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}