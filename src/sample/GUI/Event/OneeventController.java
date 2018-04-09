package sample.GUI.Event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Entities.Event;
import sample.Main;
import sample.Services.EventService;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;



public class OneeventController implements Initializable{
    @FXML
    private Label name;
    @FXML
    private AnchorPane haja;
    @FXML
    private AnchorPane fazain;
    @FXML
    private Label day;

    @FXML
    private Label month;

    @FXML
    private Label id;


    @FXML
    private javafx.scene.image.ImageView staticmap;
    @FXML
    private Text description;
    private static sample.GUI.Event.OneeventController Controller ;

    public static OneeventController getController() {
        return Controller;
    }

    public static void setController(OneeventController controller) {
        Controller = controller;
    }
    @FXML
    void hajahide(MouseEvent event) {

        haja.setStyle("-fx-background-color:gray;" +
                "            -fx-opacity: 0;");
        fazain.setStyle("-fx-background-color: gray;-fx-cursor: hand;");
    }

    @FXML
    void hajashow(MouseEvent event) {

        haja.setStyle("-fx-background-color:gray;" +
                "            -fx-opacity: 0.2;");
        fazain.setStyle("-fx-background-color: transparent;-fx-cursor: hand;");


    }

    public void setContent(Event e) {
        this.id.setText(String.valueOf(e.getId()));
      this.name.setText(e.getName());
      this.description.setText(e.getDescription());
        Calendar cal = Calendar.getInstance();
        cal.setTime(e.getBegin());
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
      
   /*   Image i=new Image("https://maps.googleapis.com/maps/api/staticmap?center="+e.getPosy()+","+e.getPosx()+"&zoom=17&size=800x800&maptype=roadmap&markers=color:red%7Clabel:S%7C"+e.getPosy()+","+e.getPosx() +
              "&key=AIzaSyAcsrLEjp-oYNRVFr10K9tNbaKSVzdsj_M");
*/
/*
        Image i=new Image("https://maps.googleapis.com/maps/api/staticmap?center="+e.getPosy()+","+e.getPosx()+"&key=AIzaSyAcsrLEjp-oYNRVFr10K9tNbaKSVzdsj_M");
*/
        File file = new File("/var/www/html/PawsAndClaws/web/bundles/uploads/brochures/"+e.getBrochure());

        Image i = new Image(file.toURI().toString(),105,105,false,false);
staticmap.setImage(i);

    }
    @FXML
    void showsinglelady(MouseEvent event) {
        EventService es=new EventService();
        String i =id.getText();
        System.out.println(i);
        Main.actualevent=es.showEvent(Integer.parseInt(i));
        Stage s=new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("amasinglelady.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.initStyle(StageStyle.UNDECORATED);
        s.setWidth(810);
        s.setHeight(700 );
        s.setScene(new Scene(root));
        s.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
