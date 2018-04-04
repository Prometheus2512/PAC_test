package sample.GUI.Main;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DrawerController implements Initializable {

    @FXML
    private JFXButton account;

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

    @FXML
    void showevent(ActionEvent event) {
        events.setStyle(    "-fx-text-fill: #2EB398;");

        home.setStyle(    "-fx-text-fill: #ababab;");
        about.setStyle(    "-fx-text-fill: #ababab;");
        blog.setStyle(    "-fx-text-fill: #ababab;");
        pets.setStyle(    "-fx-text-fill: #ababab;");
        services.setStyle(    "-fx-text-fill: #ababab;");
        products.setStyle(    "-fx-text-fill: #ababab;");
        account.setStyle(    "-fx-text-fill: #ababab;");

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/GUI/Main/sample.fxml"));
        try {
            Parent root = loader.load();
            Node pane = FXMLLoader.load(getClass().getResource("../Event/Event.fxml"));

            sample.GUI.Login.LoginController.getController().setContent(pane);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @FXML
    void showaccount(ActionEvent event) {
        account.setStyle(    "-fx-text-fill: #2EB398;");
        about.setStyle(    "-fx-text-fill: #ababab;");
        home.setStyle(    "-fx-text-fill: #ababab;");
        events.setStyle(    "-fx-text-fill: #ababab;");
        blog.setStyle(    "-fx-text-fill: #ababab;");
        pets.setStyle(    "-fx-text-fill: #ababab;");
        services.setStyle(    "-fx-text-fill: #ababab;");
        products.setStyle(    "-fx-text-fill: #ababab;");

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/GUI/Main/sample.fxml"));
        try {
            Parent root = loader.load();
            Node pane = FXMLLoader.load(getClass().getResource("../Account/Account.fxml"));

           sample.GUI.Login.LoginController.getController().setContent(pane);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    @FXML
    void showhome(ActionEvent event) {
        home.setStyle(    "-fx-text-fill: #2EB398;");

        about.setStyle(    "-fx-text-fill: #ababab;");
        events.setStyle(    "-fx-text-fill: #ababab;");
        blog.setStyle(    "-fx-text-fill: #ababab;");
        pets.setStyle(    "-fx-text-fill: #ababab;");
        services.setStyle(    "-fx-text-fill: #ababab;");
        blog.setStyle(    "-fx-text-fill: #ababab;");
        products.setStyle(    "-fx-text-fill: #ababab;");
        account.setStyle(    "-fx-text-fill: #ababab;");

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/GUI/Main/sample.fxml"));
        try {
            Parent root = loader.load();
            Node pane = FXMLLoader.load(getClass().getResource("../Home/Home.fxml"));

            sample.GUI.Login.LoginController.getController().setContent(pane);

            System.out.println("clicked");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pets.setContentDisplay(ContentDisplay.RIGHT);
        home.setStyle(    "-fx-text-fill: #2EB398;");

        about.setStyle(    "-fx-text-fill: #ababab;");
        events.setStyle(    "-fx-text-fill: #ababab;");
        blog.setStyle(    "-fx-text-fill: #ababab;");
        pets.setStyle(    "-fx-text-fill: #ababab;");
        services.setStyle(    "-fx-text-fill: #ababab;");
        blog.setStyle(    "-fx-text-fill: #ababab;");
        products.setStyle(    "-fx-text-fill: #ababab;");
        account.setStyle(    "-fx-text-fill: #ababab;");

     /*   FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/GUI/Main/sample.fxml"));
        try {
            Parent root = loader.load();
            Node pane = FXMLLoader.load(getClass().getResource("../Home/Home.fxml"));

            sample.GUI.Login.LoginController.getController().setContent(pane);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
*/

        home.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {





        });
        pets.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            pets.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");
            account.setStyle(    "-fx-text-fill: #ababab;");





        });
        services.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            services.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");
            account.setStyle(    "-fx-text-fill: #ababab;");



        });
        products.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            products.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            account.setStyle(    "-fx-text-fill: #ababab;");


        });

        blog.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            blog.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            about.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");
            account.setStyle(    "-fx-text-fill: #ababab;");



        });
        about.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {

            about.setStyle(    "-fx-text-fill: #2EB398;");
            home.setStyle(    "-fx-text-fill: #ababab;");
            events.setStyle(    "-fx-text-fill: #ababab;");
            blog.setStyle(    "-fx-text-fill: #ababab;");
            pets.setStyle(    "-fx-text-fill: #ababab;");
            services.setStyle(    "-fx-text-fill: #ababab;");
            products.setStyle(    "-fx-text-fill: #ababab;");
            account.setStyle(    "-fx-text-fill: #ababab;");



        });

    }
}
