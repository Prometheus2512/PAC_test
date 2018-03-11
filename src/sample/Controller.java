package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.*;
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
    private AnchorPane hoverme;
    @Override
    public void initialize(URL location, ResourceBundle resources) {




        VBox box = null;
        VBox pane=null;
        try {
            box = FXMLLoader.load(getClass().getResource("drawercontent.fxml"));
            pane = FXMLLoader.load(getClass().getResource("login.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        drawer.setSidePane(box);
        logindrawer.setSidePane(pane);
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
