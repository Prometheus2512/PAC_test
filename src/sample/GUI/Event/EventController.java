package sample.GUI.Event;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Entities.Event;
import sample.Main;
import sample.Services.EventService;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EventController implements Initializable {
    @FXML
    private ScrollPane AllEvents;

    @FXML
    private VBox eventlist;

    @FXML
    private JFXButton btnmyevents;

    @FXML
    private JFXButton btnupevents;

    @FXML
    private JFXButton btnnew;

    @FXML
    private AnchorPane haja2;
    @FXML
    private Label day;

    @FXML
    private Label month;

    @FXML
    void ShowMyEvents(ActionEvent event) {
        AllEvents.setVisible(false);
        btnmyevents.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnupevents.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnnew.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");


    }

    @FXML
    void ShowNewEvent(ActionEvent event) {
        AllEvents.setVisible(false);
        btnnew.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnmyevents.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnupevents.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");


    }

    @FXML
    void ShowUpEvents(ActionEvent event) {
        AllEvents.setVisible(true);
        btnupevents.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnmyevents.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnnew.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");

    }


    @FXML
    void haja2show(MouseEvent event) {
        haja2.setStyle(" -fx-background-color:#2EB398;" +
                "            -fx-opacity: 1;");

    }

    @FXML
    void haja2hide(MouseEvent event) {
        haja2.setStyle(" -fx-background-color:transparent;" +
                "            -fx-opacity: 1;");

    }

    @FXML
    void addbutton(MouseEvent event) {
        Stage s=new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("addform.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.initStyle(StageStyle.UNDECORATED);
        s.setWidth(350);
        s.setHeight(550);
        s.setScene(new Scene(root));
        s.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_MONTH);

        if(d<10)
        {        day.setText("0"+String.valueOf(d));}
        else
        {            day.setText(String.valueOf(d));
        }
        String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December" };
        month.setText(monthName[m]);
        EventService es = new EventService();
        List<Event> le = es.allEvents();
        for (Event e : le) {
            Main.actualevent=e;
            try {
                FXMLLoader x = new FXMLLoader(getClass().getResource("oneevent.fxml"));
                Node pane = x.load();
                sample.GUI.Event.OneeventController c = x.getController();
                c.setContent(e);

                eventlist.getChildren().add(pane);

                /*Node pane = FXMLLoader.load(getClass().getResource("oneevent.fxml"));
                sample.GUI.Event.OneeventController.getController().setContent(e);

                eventlist.getChildren().add(pane);*/

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            System.out.println(e.getName());
        }


    }
}
