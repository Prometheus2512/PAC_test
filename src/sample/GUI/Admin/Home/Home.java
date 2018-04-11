package sample.GUI.Admin.Home;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    @FXML
    private AnchorPane content;

    @FXML
    private Label close;

    @FXML
    private JFXButton dashboard;

    @FXML
    private JFXButton events;

    @FXML
    private JFXButton account;

    @FXML
    private JFXButton pets;

    @FXML
    private JFXButton blog;

    @FXML
    private JFXButton services;

    @FXML
    private JFXButton products;

    @FXML
    void logoff(ActionEvent event) {
        Main.actualuser=null;
        Stage stage = (Stage) content.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/login.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage2;
            stage2 = new Stage();
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
    void showaccount(ActionEvent event) {

    }

    @FXML
    void showdashboard(ActionEvent event) {

    }

    @FXML
    void showevent(ActionEvent event) {
        events.setStyle(    "-fx-text-fill: #2EB398;");

        dashboard.setStyle(    "-fx-text-fill: #ababab;");
        blog.setStyle(    "-fx-text-fill: #ababab;");
        pets.setStyle(    "-fx-text-fill: #ababab;");
        services.setStyle(    "-fx-text-fill: #ababab;");
        products.setStyle(    "-fx-text-fill: #ababab;");
        account.setStyle(    "-fx-text-fill: #ababab;");

        try {
            Node pane = FXMLLoader.load(getClass().getResource("../Events/Events.fxml"));
            content.getChildren().add(pane);
/*
            sample.GUI.Login.LoginController.getController().setContent(pane);
*/

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
