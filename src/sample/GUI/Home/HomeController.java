package sample.GUI.Home;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Entities.Event;
import sample.Main;
import sample.Services.EventService;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Initializable {

    @FXML
    private ImageView eventspicture;

    @FXML
    private AnchorPane eventslayer;

    @FXML
    private Label events;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventService es=new EventService();
        List<Event> le=es.allEvents();
              int it=0;
/*
        PauseTransition wait = new PauseTransition(Duration.seconds(2));
        wait.setOnFinished((e) -> {

            if(it==le.size())
            {it=0;}
            File file = new File("/var/www/html/PawsAndClaws/web/bundles/uploads/brochures/" + le.get(it).getBrochure());

            Image i = new Image(file.toURI().toString(), 250, 250, false, false);
            eventspicture.setImage(i);

            wait.playFromStart();
        });
        wait.play();*/
        final int[] i = {0};
        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                // Here comes your void to refresh the whole application.
if(i[0] ==le.size()){
    i[0]=0;}
                File file = new File("/var/www/html/PawsAndClaws/web/bundles/uploads/brochures/" + le.get(   i[0]).getBrochure());

                Image it = new Image(file.toURI().toString(), 500, 310, false, false);
                eventspicture.setImage(it);
                i[0]++;
            }
        }, 2000, 2000);
    }

    @FXML
    void eventplus(MouseEvent event) {
        events.setStyle("-fx-text-fill: white");
        eventslayer.setVisible(true);
    }
    @FXML
    void eventminux(MouseEvent event) {
        events.setStyle("-fx-text-fill: #209a80");
        eventslayer.setVisible(false);
    }
}
