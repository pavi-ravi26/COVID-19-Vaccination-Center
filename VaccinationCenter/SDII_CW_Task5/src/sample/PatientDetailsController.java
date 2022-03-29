package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class PatientDetailsController{

    @FXML
    private AnchorPane ancBack;

    @FXML
    private Button butSubmit;

    @FXML
    private Button butReceipt;

    @FXML
    private TextField txtfName;

    @FXML
    private TextField txtsName;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtNIC;

    @FXML
    private CheckBox chkAstra;

    @FXML
    private CheckBox chkSino;

    @FXML
    private CheckBox chkPfizer;

    @FXML
    private TextField txtBoothNo;


    @FXML
    public void getReceipt(ActionEvent actionEvent) throws IOException {
        String boothNo=txtBoothNo.getText();
        String firstname=txtfName.getText();
        String surName=txtsName.getText();
        String age=txtAge.getText();
        String city=txtCity.getText();
        String NIC=txtNIC.getText();
        String vacRequested;
        if (chkAstra.isSelected()) {
            vacRequested=chkAstra.getText();
        } else if (chkSino.isSelected()) {
            vacRequested=chkSino.getText();
        } else {
            vacRequested=chkPfizer.getText();
        }



        FXMLLoader loader=FXMLLoader.load(getClass().getResource("receipt.fxml"));
        Parent root;
        root =loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Receipt");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();

        ReceiptController receipt = loader.getController();
        receipt.display(firstname,surName,age,city,NIC,vacRequested,boothNo);





    }

}

