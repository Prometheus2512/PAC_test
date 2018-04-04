package sample.GUI.Event;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.Services.ReservationService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Amasinglelady implements Initializable {

    @FXML
    private Text description;
    @FXML
    private ProgressBar pb;

    @FXML
    private Label numberofpartipants;


    @FXML
    private Label day;

    @FXML
    private Label month;

    @FXML
    private Label hour;

    @FXML
    private Label minute;
    @FXML
    private Label day1;

    @FXML
    private Label month1;

    @FXML
    private Label hour1;

    @FXML
    private Label minute1;

    @FXML
    private ImageView staticmap;

    @FXML
    private Label name;
    @FXML
    private Label Cost;

    @FXML
    private Label Capacity;

    @FXML
    private JFXButton participate;

    @FXML
    private JFXButton unbook;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.name.setText(Main.actualevent.getName());
        this.description.setText(Main.actualevent.getDescription());
        this.Capacity.setText(String.valueOf(Main.actualevent.getCapacity()));
        this.Cost.setText(Main.actualevent.getCost()+" DT");
        Calendar cal = Calendar.getInstance();
        Calendar cal2=Calendar.getInstance();
        cal.setTime(Main.actualevent.getBegin());
        cal.setTime(Main.actualevent.getEnd());
        int y = cal.get(Calendar.YEAR);
        int y2= cal2.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int m2=cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int d2=cal.get(Calendar.DAY_OF_MONTH);
        int h= cal.get(Calendar.HOUR);
        int h2=cal.get(Calendar.HOUR);
        int min=cal.get(Calendar.MINUTE);
        int min2=cal.get(Calendar.MINUTE);
        if(h<10)
        {        hour.setText("0"+String.valueOf(h));}
        else
        {            hour.setText(String.valueOf(h));
        }
        if(min<10)
        {        minute.setText("0"+String.valueOf(min));}
        else
        {            minute.setText(String.valueOf(min));
        }
        if(d<10)
        {        day.setText("0"+String.valueOf(d));}
        else
        {            day.setText(String.valueOf(d));
        }
        if(h2<10)
        {        hour1.setText("0"+String.valueOf(h2));}
        else
        {            hour1.setText(String.valueOf(h2));
        }
        if(min2<10)
        {        minute1.setText("0"+String.valueOf(min2));}
        else
        {            minute1.setText(String.valueOf(min2));
        }
        if(d2<10)
        {        day1.setText("0"+String.valueOf(d2));}
        else
        {            day1.setText(String.valueOf(d2));
        }

        String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December" };
        month.setText(monthName[m]);
        month1.setText(monthName[m2]);

   /*   Image i=new Image("https://maps.googleapis.com/maps/api/staticmap?center="+e.getPosy()+","+e.getPosx()+"&zoom=17&size=800x800&maptype=roadmap&markers=color:red%7Clabel:S%7C"+e.getPosy()+","+e.getPosx() +
              "&key=AIzaSyAcsrLEjp-oYNRVFr10K9tNbaKSVzdsj_M");
*/
/*
        Image i=new Image("https://maps.googleapis.com/maps/api/staticmap?center="+e.getPosy()+","+e.getPosx()+"&key=AIzaSyAcsrLEjp-oYNRVFr10K9tNbaKSVzdsj_M");
*/
        File file = new File("/var/www/html/PawsAndClaws/web/bundles/uploads/brochures/"+Main.actualevent.getBrochure());

        Image i = new Image(file.toURI().toString(),250,250,false,false);
        staticmap.setImage(i);

        ReservationService rs=new ReservationService();
        if(rs.amiparticipating(Main.actualevent.getId())){participate.setVisible(false);unbook.setVisible(true);}
        else {participate.setVisible(true);unbook.setVisible(false);}
        numberofpartipants.setText(String.valueOf(rs.howmanyparticipants(Main.actualevent.getId())));

        pb.setProgress((double)rs.howmanyparticipants(Main.actualevent.getId())/(double)Main.actualevent.getCapacity());
    }
    @FXML
    void book(ActionEvent event) {
        ReservationService rs=new ReservationService();

        int number=rs.howmanyparticipants(Main.actualevent.getId());
        rs.book(Main.actualevent.getId());
        participate.setVisible(false);
        unbook.setVisible(true);
        numberofpartipants.setText(String.valueOf(rs.howmanyparticipants(Main.actualevent.getId())));
        pb.setProgress((double)rs.howmanyparticipants(Main.actualevent.getId())/(double)Main.actualevent.getCapacity());

    }

    @FXML
    void unbook(ActionEvent event) {
        ReservationService rs=new ReservationService();
        int number=rs.howmanyparticipants(Main.actualevent.getId());
        rs.cancel(Main.actualevent.getId());
        participate.setVisible(true);
        unbook.setVisible(false);
        numberofpartipants.setText(String.valueOf(rs.howmanyparticipants(Main.actualevent.getId())));
        pb.setProgress((double)rs.howmanyparticipants(Main.actualevent.getId())/(double)Main.actualevent.getCapacity());

    }
}
