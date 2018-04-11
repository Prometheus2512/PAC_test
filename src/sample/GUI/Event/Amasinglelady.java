package sample.GUI.Event;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Entities.Commentary;
import sample.Entities.Reservation;
import sample.Main;
import sample.Services.CommentaryService;
import sample.Services.EventService;
import sample.Services.ReservationService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.sql.Time;
import java.util.*;

public class Amasinglelady implements Initializable {
    FileChooser fc = new FileChooser();
    File f;
    int aux;
    @FXML
    private Label error;
    @FXML
    private Text description;
    @FXML
    private Button confirmname;
    @FXML
    private ProgressBar pb;
    @FXML
    private Button confirmcost;

    @FXML
    private Button confirmcapacity;

    @FXML
    private Label numberofpartipants;
    @FXML
    private JFXTimePicker estime;

    @FXML
    private JFXDatePicker esdate;
    @FXML
    private TextField ename;
    @FXML
    private JFXTimePicker eetime;

    @FXML
    private JFXDatePicker eedate;


    @FXML
    private Label day;
    @FXML
    private ScrollPane scrollbabyscroll;

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
    private Label slash;

    @FXML
    private Label hour1;

    @FXML
    private Label minute1;

    @FXML
    private ImageView staticmap;

    @FXML
    private Text name;
    @FXML
    private Label Cost;

    @FXML
    private Label Capacity;

    @FXML
    private AnchorPane themap;

    @FXML
    private JFXButton participate;

    @FXML
    private JFXButton unbook;

    @FXML
    private VBox commentaries;
    @FXML
    private WebView wv;
    @FXML
    private ImageView ebdescription;

    @FXML
    private ImageView ebstart;

    @FXML
    private ImageView ebend;

    @FXML
    private ImageView ebname;

    @FXML
    private ImageView ebcost;
    @FXML
    private AnchorPane estarting;

    @FXML
    private Button delete;
    @FXML
    private JFXButton unbook1;
    @FXML
    private TextField ecapacity;

    @FXML
    private ImageView ebimage;

    @FXML
    private Button ebconfirm;

    @FXML
    private Button ebundo;
    @FXML
    private Button confirmdescription;

    @FXML
    private AnchorPane eending;

    @FXML
    private ImageView ebcapacity;

    @FXML
    private JFXTextArea edescription;

    @FXML
    private AnchorPane deletepane;

    @FXML
    private Text deletetext;

    @FXML
    private Button yes;

    @FXML
    private Button no;
    @FXML
    private TextField ecost;
    @FXML
    private TextArea content;
    private static sample.GUI.Event.Amasinglelady Controller;

    public static Amasinglelady getController() {
        return Controller;
    }

    public static void setController(Amasinglelady controller) {
        Controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.actualevent.getHost() == Main.actualuser.getId()) {
            ebcapacity.setVisible(true);
            ebcost.setVisible(true);
            ebdescription.setVisible(true);
            ebname.setVisible(true);
            ebimage.setVisible(true);
            ebend.setVisible(false);
            ebstart.setVisible(false);
            unbook.setVisible(false);
            delete.setVisible(true);



        } else {
            ebcapacity.setVisible(false);
            ebcost.setVisible(false);
            ebdescription.setVisible(false);
            ebname.setVisible(false);
            ebimage.setVisible(false);
            ebend.setVisible(false);
            ebstart.setVisible(false);
            unbook.setVisible(false);
            delete.setVisible(false);
        }
        ecapacity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ecapacity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        ecost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ecost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        scrollbabyscroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.name.setText(Main.actualevent.getName());
        this.description.setText(Main.actualevent.getDescription());
        this.Capacity.setText(String.valueOf(Main.actualevent.getCapacity()));
        this.Cost.setText(Main.actualevent.getCost() + " DT");
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(Main.actualevent.getBegin());
        cal2.setTime(Main.actualevent.getEnd());
        int y = cal.get(Calendar.YEAR);
        int y2 = cal2.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int m2 = cal2.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int d2 = cal2.get(Calendar.DAY_OF_MONTH);
        int h = cal.get(Calendar.HOUR);
        int h2 = cal2.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int min2 = cal2.get(Calendar.MINUTE);
        if (h < 10) {
            hour.setText("0" + String.valueOf(h));
        } else {
            hour.setText(String.valueOf(h));
        }
        if (min < 10) {
            minute.setText("0" + String.valueOf(min));
        } else {
            minute.setText(String.valueOf(min));
        }
        if (d < 10) {
            day.setText("0" + String.valueOf(d));
        } else {
            day.setText(String.valueOf(d));
        }
        if (h2 < 10) {
            hour1.setText("0" + String.valueOf(h2));
        } else {
            hour1.setText(String.valueOf(h2));
        }
        if (min2 < 10) {
            minute1.setText("0" + String.valueOf(min2));
        } else {
            minute1.setText(String.valueOf(min2));
        }
        if (d2 < 10) {
            day1.setText("0" + String.valueOf(d2));
        } else {
            day1.setText(String.valueOf(d2));
        }

        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        month.setText(monthName[m]);
        month1.setText(monthName[m2]);

   /*   Image i=new Image("https://maps.googleapis.com/maps/api/staticmap?center="+e.getPosy()+","+e.getPosx()+"&zoom=17&size=800x800&maptype=roadmap&markers=color:red%7Clabel:S%7C"+e.getPosy()+","+e.getPosx() +
              "&key=AIzaSyAcsrLEjp-oYNRVFr10K9tNbaKSVzdsj_M");
*/
/*
        Image i=new Image("https://maps.googleapis.com/maps/api/staticmap?center="+e.getPosy()+","+e.getPosx()+"&key=AIzaSyAcsrLEjp-oYNRVFr10K9tNbaKSVzdsj_M");
*/
        File file = new File("/var/www/html/PawsAndClaws/web/bundles/uploads/brochures/" + Main.actualevent.getBrochure());

        Image i = new Image(file.toURI().toString(), 250, 250, false, false);
        staticmap.setImage(i);


        WebEngine engine = wv.getEngine();
        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                        if (newValue == Worker.State.SUCCEEDED) {
                            engine.executeScript("document.addMarker(" + Main.actualevent.getPosx() + "," + Main.actualevent.getPosy() + ")");

                        }
                    }
                }
        );
        engine.load(getClass().getClassLoader().getResource("sample/GUI/Event/map.html").toString());

        ReservationService rs = new ReservationService();
        System.out.println();

        if (Main.actualevent.getHost() != Main.actualuser.getId()) {
            if ((rs.amiparticipating(Main.actualevent.getId()))) {
                participate.setVisible(false);
                unbook.setVisible(true);
            } else {
                participate.setVisible(true);
                unbook.setVisible(false);
            }
        }
        numberofpartipants.setText(String.valueOf(rs.howmanyparticipants(Main.actualevent.getId())));

        pb.setProgress((double) rs.howmanyparticipants(Main.actualevent.getId()) / (double) Main.actualevent.getCapacity());


        /*CommentaryService cs=new CommentaryService();

        List<Commentary> laliste=cs.ListCommentaries(Main.actualevent);
        for(Commentary c:laliste)
        { FXMLLoader x = new FXMLLoader(getClass().getResource("SingleCommentary.fxml"));
            try {
                Node pane = x.load();
                sample.GUI.Event.SingleCommentary cont=x.getController();
                cont.setContent(c);
                commentaries.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }

*//*
            commentaries.getChildren().add(new Label(c.getContent()));
*//*
        }*/
        updatevbox();
    }

    @FXML
    void book(ActionEvent event) {
        ReservationService rs = new ReservationService();

        int number = rs.howmanyparticipants(Main.actualevent.getId());
        rs.book(Main.actualevent.getId());
        participate.setVisible(false);
        unbook.setVisible(true);
        numberofpartipants.setText(String.valueOf(rs.howmanyparticipants(Main.actualevent.getId())));
        pb.setProgress((double) rs.howmanyparticipants(Main.actualevent.getId()) / (double) Main.actualevent.getCapacity());

    }

    @FXML
    void unbook(ActionEvent event) {
        ReservationService rs = new ReservationService();
        int number = rs.howmanyparticipants(Main.actualevent.getId());
        rs.cancel(Main.actualevent.getId());
        participate.setVisible(true);
        unbook.setVisible(false);
        numberofpartipants.setText(String.valueOf(rs.howmanyparticipants(Main.actualevent.getId())));
        pb.setProgress((double) rs.howmanyparticipants(Main.actualevent.getId()) / (double) Main.actualevent.getCapacity());

    }

    @FXML
    void addcomment(ActionEvent event) {
        Commentary c = new Commentary();
        c.setContent(content.getText());
        c.setCommentedevent_id(Main.actualevent.getId());
        c.setCommentator_id(Main.actualuser.getId());
        CommentaryService cs = new CommentaryService();
        cs.comment(c);
        FXMLLoader x = new FXMLLoader(getClass().getResource("SingleCommentary.fxml"));
        try {
            Node pane = x.load();
            sample.GUI.Event.SingleCommentary cont = x.getController();
            c.setCreationDate(new Date());
            cont.setContent(c);
            commentaries.getChildren().add(pane);
            scrollbabyscroll.setVvalue(1.0);

            content.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }

/*
            commentaries.getChildren().add(new Label(c.getContent()));
*/
        commentaries.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                scrollbabyscroll.setVvalue((Double) newValue);
            }
        });

    }

    public void updatevbox() {
        this.commentaries.getChildren().clear();
        CommentaryService cs = new CommentaryService();

        List<Commentary> laliste = cs.ListCommentaries(Main.actualevent);
        for (Commentary c : laliste) {
            FXMLLoader x = new FXMLLoader(getClass().getResource("SingleCommentary.fxml"));
            try {
                Node pane = x.load();
                sample.GUI.Event.SingleCommentary cont = x.getController();
                cont.setContent(c);
                this.commentaries.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }

/*
            commentaries.getChildren().add(new Label(c.getContent()));
*/
        }
    }

    @FXML
    void changename(MouseEvent event) {
        if (ename.isVisible()) {
            confirmname.setVisible(false);
            name.setVisible(true);
            ename.setVisible(false);
            aux--;
            if (aux == 0) {
                ebconfirm.setVisible(false);
                ebundo.setVisible(false);
            }
        } else {
            ename.setText(Main.actualevent.getName());
            name.setVisible(false);
            ename.setVisible(true);
            aux++;
            ebundo.setVisible(true);
        }
    }

    @FXML
    void changedescription(MouseEvent event) {
        if (edescription.isVisible()) {
            confirmdescription.setVisible(false);
            description.setVisible(true);
            edescription.setVisible(false);
            aux--;
            if (aux == 0) {
                ebconfirm.setVisible(false);
                ebundo.setVisible(false);
            }
        } else {
            edescription.setText(Main.actualevent.getDescription());
            description.setVisible(false);
            edescription.setVisible(true);
            aux++;
            ebundo.setVisible(true);
        }
    }

    @FXML
    void changecapacity(MouseEvent event) {
        if (ecapacity.isVisible()) {
            confirmcapacity.setVisible(false);
            numberofpartipants.setVisible(true);
            slash.setVisible(true);
            Capacity.setVisible(true);
            ecapacity.setVisible(false);
            pb.setVisible(true);
            aux--;
            if (aux == 0) {
                ebconfirm.setVisible(false);
                ebundo.setVisible(false);
            }
        } else {
            ecapacity.setText(String.valueOf(Main.actualevent.getCapacity()));
            numberofpartipants.setVisible(false);
            slash.setVisible(false);
            pb.setVisible(false);
            Capacity.setVisible(false);
            ecapacity.setVisible(true);
            aux++;
            ebundo.setVisible(true);
        }
    }

    @FXML
    void changecost(MouseEvent event) {
        if (ecost.isVisible()) {
            confirmcost.setVisible(false);
            Cost.setVisible(true);
            ecost.setVisible(false);
            aux--;
            if (aux == 0) {
                ebconfirm.setVisible(false);
                ebundo.setVisible(false);
            }

        } else {
            aux++;
            ebundo.setVisible(true);
            ecost.setText(String.valueOf(Main.actualevent.getCost()));
            Cost.setVisible(false);
            ecost.setVisible(true);
        }
    }

    @FXML
    void changeend(MouseEvent event) {
        if (eending.isVisible()) {
            eending.setVisible(false);
            aux--;
            if (aux == 0) {
                ebconfirm.setVisible(false);
                ebundo.setVisible(false);
            }
        } else {
            aux++;
            ebundo.setVisible(true);
            eending.setVisible(true);
        }
    }

    @FXML
    void changestart(MouseEvent event) {
        if (estarting.isVisible()) {
            estarting.setVisible(false);
            aux--;
            if (aux == 0) {
                ebconfirm.setVisible(false);
                ebundo.setVisible(false);
            }
        } else {
            aux++;
            ebundo.setVisible(true);
            estarting.setVisible(true);
        }
    }

    @FXML
    void changeimage(MouseEvent event) {
        fc.setTitle("Choose a picture for your event");
        File selectedFile = fc.showOpenDialog(null);
        f = selectedFile;
        if (selectedFile != null) {
            File file = new File(selectedFile.getPath());
            aux++;

            Image i = new Image(file.toURI().toString(), 250, 250, false, false);
            staticmap.setImage(i);

            ebundo.setVisible(true);
            //the code to copy the selected file goes here//
            /*filename.setVisible(true);
            filename.setText(selectedFile.getName());*/

        } else {
            /*file1.setText("no file attached");*/
        }
    }

    @FXML
    void undo(ActionEvent event) {
        confirmcost.setVisible(false);
        confirmname.setVisible(false);
        confirmdescription.setVisible(false);
        confirmcapacity.setVisible(false);
        File file = new File("/var/www/html/PawsAndClaws/web/bundles/uploads/brochures/" + Main.actualevent.getBrochure());
aux=0;
        Image i = new Image(file.toURI().toString(), 250, 250, false, false);
        staticmap.setImage(i);
        ebundo.setVisible(false);
        ebconfirm.setVisible(false);
        ecost.setVisible(false);
        ecapacity.setVisible(false);
        ename.setVisible(false);
        edescription.setVisible(false);
        eending.setVisible(false);
        estarting.setVisible(false);
        Cost.setVisible(true);
        Capacity.setVisible(true);
        slash.setVisible(true);
        pb.setVisible(true);
        numberofpartipants.setVisible(true);
        name.setVisible(true);
        description.setVisible(true);


    }
    @FXML
    void confirm(ActionEvent event) {


    }

    @FXML
    void confirmname(ActionEvent event) {
Main.actualevent.setName(ename.getText());
        EventService es=new EventService();
        String oldname=Main.actualevent.getName();
        es.updateEventname();
        Main.sendsms(oldname+"'s name has changed to "+ename.getText());
        name.setText(Main.actualevent.getName());
        ename.setVisible(false);
        name.setVisible(true);
        aux--;
        error.setText("Name edited successfully.");
        error.setStyle("-fx-text-fill: darkgreen");
        error.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(5000), error);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();

        confirmname.setVisible(false);
        if(aux==0){ebundo.setVisible(false);


        }
    }
    @FXML
    void checkname(KeyEvent event) {
if(!ename.getText().equals(Main.actualevent.getName()))
{confirmname.setVisible(true);}
else{confirmname.setVisible(false);}
    }

    @FXML
    void confirmdescription(ActionEvent event) {
confirmdescription.setVisible(false);
Main.actualevent.setDescription(edescription.getText());
EventService es=new EventService();
es.updateEventdescription();
description.setText(Main.actualevent.getDescription());
        aux--;
        confirmdescription.setVisible(false);
        edescription.setVisible(false);
        description.setText(Main.actualevent.getDescription());
        description.setVisible(true);
        error.setText("Description edited successfully.");
        error.setStyle("-fx-text-fill: darkgreen");
        error.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(5000), error);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
        if(aux==0){ebundo.setVisible(false);}
    }
    @FXML
    void checkdescription(KeyEvent event) {
        if(!edescription.getText().equals(Main.actualevent.getDescription()))
        {confirmdescription.setVisible(true);}
        else{confirmdescription.setVisible(false);}

    }
    @FXML
    void checkcapacity(KeyEvent event) {
        if(!ecapacity.getText().equals(String.valueOf(Main.actualevent.getCapacity())))
        {confirmcapacity.setVisible(true);}
        else{confirmcapacity.setVisible(false);}
    }

    @FXML
    void checkcost(KeyEvent event) {
        if(!ecost.getText().equals(String.valueOf(Main.actualevent.getCost())))
        {confirmcost.setVisible(true);}
        else{confirmcost.setVisible(false);}
    }

    @FXML
    void confirmcapacity(ActionEvent event) {
if(Integer.parseInt(ecapacity.getText())<Integer.parseInt(numberofpartipants.getText()))
{error.setText("Chosen capacity is lower than actual participants number.");
error.setStyle("-fx-text-fill: darkred");
error.setVisible(true);
    FadeTransition ft = new FadeTransition(Duration.millis(5000), error);
    ft.setFromValue(1.0);
    ft.setToValue(0.0);
    ft.play();
}
else
{
    Main.actualevent.setCapacity(Integer.parseInt(ecapacity.getText()));
    EventService es=new EventService();

    es.updateEventcapacity();
    ecapacity.setVisible(false);
    Capacity.setText(String.valueOf(Main.actualevent.getCapacity()));
    Capacity.setVisible(true);
    numberofpartipants.setVisible(true);
    slash.setVisible(true);
    pb.setProgress( Double.parseDouble(numberofpartipants.getText()) / (double) Main.actualevent.getCapacity());
pb.setVisible(true);
confirmcapacity.setVisible(false);
aux--;
    error.setText("Capacity edited successfully.");
    error.setStyle("-fx-text-fill: darkgreen");
    error.setVisible(true);
    FadeTransition ft = new FadeTransition(Duration.millis(5000), error);
    ft.setFromValue(1.0);
    ft.setToValue(0.0);
    ft.play();
if(aux==0){ebundo.setVisible(false);}
}

    }

    @FXML
    void confirmcost(ActionEvent event) {
        Main.actualevent.setCost(Double.parseDouble(ecost.getText()));
        EventService es=new EventService();

        es.updateEventcost();
        ecost.setVisible(false);
        Cost.setText(String.valueOf(Main.actualevent.getCost())+" DT");
        Cost.setVisible(true);
        error.setText("Booking cost edited successfully !");
        error.setStyle("-fx-text-fill: darkgreen");
        error.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(4000), error);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
        aux--;
        if(aux==0){ebundo.setVisible(false);}
    }
    @FXML
    void deleteevent(ActionEvent event) {
deletepane.setVisible(true);
deletetext.setText("Are you sure you want to delete "+name.getText()+" with "+numberofpartipants.getText()+" participants ?" );
    }
    @FXML
    void deleteevent1(ActionEvent event) {
        deletepane.setVisible(false);
    }
    @FXML
    void yes(ActionEvent event) {
        EventService es=new EventService();
        es.deleteEvent();
       Stage s= (Stage)yes.getScene().getWindow();
       s.close();
       Main.controller.reload();

    }
}
