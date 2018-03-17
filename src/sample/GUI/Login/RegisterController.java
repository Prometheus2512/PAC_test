package sample.GUI.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Entities.User;
import sample.Main;
import sample.Services.UserService;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Label close;



    @FXML
    void close(javafx.scene.input.MouseEvent event) {
        System.exit(0);


    }

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton register;


    @FXML
    private Label erroru;

    @FXML
    private Label errorp;

    @FXML
    private Label wrong;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField address;

    @FXML
    void showlogin(MouseEvent event) {
        Stage stage = (Stage) wrong.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setTitle("ABC");
            stage2.setScene(new Scene(root2));

            stage2.show();

        }catch(IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void RegisterAction(ActionEvent event) {

        UserService us = new UserService();
        User user=new User();



        if (username.getText().trim().isEmpty()
                || phone.getText().trim().isEmpty()
                || firstname.getText().trim().isEmpty()
                || lastname.getText().trim().isEmpty()
                || address.getText().trim().isEmpty()                || phone.getText().trim().isEmpty()
                || email.getText().trim().isEmpty()
                || password.getText().trim().isEmpty()
                || password1.getText().trim().isEmpty()
                ) {

            wrong.setVisible(true);

        }
        else if(!password1.getText().equals(password.getText()))
        {errorp.setVisible(true);}
        else {
            user.setUsername(username.getText());
        user.setPhonenumber(phone.getText());
        user.setFirstname(firstname.getText());
        user.setLastname(lastname.getText());
        user.setAddress(address.getText());
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        user.setRole("ROLE_USER");
            us.addUser(user);
            Stage stage2 = (Stage) password.getScene().getWindow();
            // do what you have to do
            stage2.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/GUI/Login/Login.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));

                stage.show();
                Main.actualuser=us.Login(username.getText(),password.getText());


            }catch(IOException e) {
                e.printStackTrace();
            }

        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
