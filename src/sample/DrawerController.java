package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerController implements Initializable {
    @FXML
    private JFXButton home;

    @FXML
    private JFXButton pets;

    @FXML
    private JFXButton events;

    @FXML
    private JFXButton blog;

    @FXML
    private JFXButton services;

    @FXML
    private JFXButton products;

    @FXML
    private JFXButton about;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            home.setStyle(    "-fx-text-fill: #2EB398;");

            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");




        });
        pets.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            pets.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");




        });
        services.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            services.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");


        });
        products.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            products.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");


        });
        events.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            events.setStyle(    "-fx-text-fill: #2EB398;");

            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");


        });
        blog.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            blog.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");


        });
        about.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            about.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");


        });
    }
}
