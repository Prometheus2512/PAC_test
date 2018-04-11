package sample.GUI.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.GUI.Main.Controller;
import sample.Main;
import sample.Services.UserService;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private Label or;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton facebook;

    @FXML
    private JFXButton login;

    @FXML
    private Label erroru;

    @FXML
    private Label errorp;

    @FXML
    private Label wrong;
    @FXML
    private Label register;

    @FXML
    private JFXProgressBar progress;
    private static sample.GUI.Main.Controller Controller ;

    public static Controller getController() {
        return Controller;
    }

    public static void setController(Controller controller) {
        Controller = controller;
    }

    UserService us=new UserService();
    @FXML
    void close(javafx.scene.input.MouseEvent event) {
        System.exit(0);


    }
    @FXML
    void registeraction(javafx.scene.input.MouseEvent event) {

        Stage stage = (Stage) facebook.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
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
    void loginAction(javafx.event.ActionEvent event) {
        if (username.getText().trim().isEmpty() && password.getText().trim().isEmpty()) {
            errorp.setVisible(true);
            erroru.setVisible(true);

        }
        else if (username.getText().trim().isEmpty() ) {
            errorp.setVisible(false);

            erroru.setVisible(true);

        }
        else if (password.getText().trim().isEmpty())
        {errorp.setVisible(true);
            erroru.setVisible(false);

        }
        else{ errorp.setVisible(false);
            erroru.setVisible(false);
            password.setVisible(false);
            username.setVisible(false);
            or.setVisible(false);
            progress.setVisible(true);
            login.setVisible(false);
            facebook.setVisible(false);
            String pwd = password.getText();
         String uname=username.getText();
            Main.actualuser=us.Login(uname,pwd);
            if(Main.actualuser!=null)
            {
/*
                System.out.println(Main.actualuser.getRole());
*/
                progress.setVisible(false);
                wrong.setStyle("-fx-text-fill: green");
                wrong.setText("Welcome "+Main.actualuser.getUsername());
                wrong.setVisible(true);
                Stage stage2 = (Stage) facebook.getScene().getWindow();
                // do what you have to do
                stage2.close();
                try{ if(Main.actualuser.getRole().equals("ROLE_USER")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/GUI/Main/sample.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    LoginController.Controller = fxmlLoader.<sample.GUI.Main.Controller>getController();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("ABC");
                    stage.setScene(new Scene(root1));

                    stage.show();
                }
                else if(Main.actualuser.getRole().equals("ROLE_ADMIN"))
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/GUI/Admin/Home/Home.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
/*
                    LoginController.Controller = fxmlLoader.<sample.GUI.Main.Controller>getController();
*/
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("ABC");
                    stage.setScene(new Scene(root1));

                    stage.show();
                }


                }catch(IOException e) {
                    e.printStackTrace();
                }





            }
            else
            {
                password.setVisible(true);
                username.setVisible(true);
                progress.setVisible(false);
                login.setVisible(true);
                facebook.setVisible(true);
                wrong.setVisible(true);
                or.setVisible(true);

            }
        }



    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        register.setStyle("-fx-text-fill: white");

    }



}
