package sample.GUI.Main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton login;
    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label close;
    @FXML
    private JFXDrawer logindrawer;
    @FXML
    private AnchorPane content;

    public void setContent(Node view) {
       this.content.getChildren().clear();
       this.content.getChildren().add(view);//run
    }

    @FXML
    void logoff(javafx.event.ActionEvent event) {
        Main.actualuser=null;
        Stage stage = (Stage) hamburger.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
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
    private AnchorPane hoverme;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            setContent(FXMLLoader.load(getClass().getResource("../Home/Home.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox box = null;
        try {


            box = FXMLLoader.load(getClass().getClassLoader().getResource("sample/GUI/Main/drawercontent.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        drawer.setSidePane(box);
/*
        logindrawer.setSidePane(pane);
*/
        HamburgerBackArrowBasicTransition burgerTask2=new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask2.setRate(-1);
        hoverme.addEventHandler(MouseEvent.MOUSE_ENTERED,(e) -> {
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();
            drawer.open();

        });
        drawer.addEventHandler(MouseEvent.MOUSE_EXITED,(e) -> {
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();
            drawer.close();


        });
        close.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {
            System.exit(0);


        });
        login.setStyle("-fx-cursor: hand");
        login.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {
           logindrawer.open();


        });

    }
}
