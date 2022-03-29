package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.sql.Timestamp;


public class ReceiptController {

    @FXML
    private Label lbldatetime;

    @FXML
    private Label lblfname;

    @FXML
    private Label lblsname;

    @FXML
    private Label lblage;

    @FXML
    private Label lblcity;

    @FXML
    private Label lblvac;

    @FXML
    private Label lblNIC;

    @FXML
    private Button butclose;

    @FXML
    private Label lblbooth;

    @FXML
    void close(ActionEvent event) {
        Stage stage= (Stage) butclose.getScene().getWindow();
        stage.close();

    }

    public void display(String firstName1, String surName1, String age1, String city1, String NIC1, String vacRequested1, String booth){
          lblfname.setText(firstName1);
          lblsname.setText(surName1);
          lblage.setText(age1);
          lblcity.setText(city1);
          lblNIC.setText(NIC1);
          lblvac.setText(vacRequested1);
          lblbooth.setText(booth);

          Date date= new Date();
          long time = date.getTime();

          Timestamp ts = new Timestamp(time);
          lbldatetime.setText(String.valueOf(ts));


    }


}
