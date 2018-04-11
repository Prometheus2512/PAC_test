package sample.GUI.Event;

import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLTextAreaElement;
import sample.Main;
import sample.Services.EventService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.UUID;

public class Addform implements Initializable {
    @FXML
    private JFXButton previous;
    @FXML
    private TextField address;

    @FXML
    private Label erroraddress;

    @FXML
    private Label errormap;

    @FXML
    private JFXButton next;
    @FXML
    private AnchorPane secondone;

    @FXML
    private JFXButton next2;
    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField cost;

    @FXML
    private JFXTextField capacity;

    @FXML
    private JFXDatePicker starting;

    @FXML
    private JFXButton done;

    @FXML
    private WebView map;
    @FXML
    private AnchorPane firstone;
    @FXML
    private JFXDatePicker ending;
    @FXML
    private JFXButton previous2;

    @FXML
    private JFXTimePicker stime;

    @FXML
    private JFXTimePicker etime;

    @FXML
    private Label errorname;
    Boolean mapclicked = false;
    @FXML
    private Label errorcost;

    @FXML
    private Label errorcapacity;

    @FXML
    private Label errorimage;

    @FXML
    private Label errordescription;
    @FXML
    private Label errorbegindate;
    @FXML
    private Label errorbegintime;

    @FXML
    private Label errorendtime;

    @FXML
    private Label errorenddate;


    @FXML
    private Label filename;
    FileChooser fc = new FileChooser();
    File f = null;

    @FXML
    void nextaction(ActionEvent event) {
        next.setVisible(false);
        firstone.setVisible(false);
        previous.setVisible(true);
        next2.setVisible(true);
        secondone.setVisible(true);
    }

    @FXML
    void nextaction2(ActionEvent event) {
        map.setVisible(true);
        secondone.setVisible(false);
        next2.setVisible(false);
        address.setVisible(true);
        done.setVisible(true);
        previous2.setVisible(true);
        firstone.setVisible(false);
    }

    @FXML
    void previousaction(ActionEvent event) {
        next.setVisible(true);
        firstone.setVisible(true);
        secondone.setVisible(false);
        previous.setVisible(false);
        next2.setVisible(false);
    }

    @FXML
    void previousaction2(ActionEvent event) {
        done.setVisible(false);
        map.setVisible(false);
        address.setVisible(false);
        secondone.setVisible(true);
        previous.setVisible(true);
        next2.setVisible(true);
        previous2.setVisible(false);
        firstone.setVisible(false);
    }

    @FXML
    void disableending(ActionEvent event) {
        if (!ending.isDisabled()) {
            ending.setDisable(true);
        } else {
            ending.setDisable(false);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = map.getEngine();

        engine.load(getClass().getClassLoader().getResource("sample/GUI/Event/mapform.html").toString());
        capacity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    capacity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        cost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void add(ActionEvent event) {
        int test = 0;
        if (description.getText().trim().isEmpty()) {
            errorname.setText("Name missing");
            errorname.setVisible(true);
        } else {
            test++;
        }
        if (name.getText().trim().isEmpty()) {
            errordescription.setText("Description missing");
            errordescription.setVisible(true);
        } else {
            test++;
        }
        if (cost.getText().trim().isEmpty()) {
            errorcost.setText("Cost missing");
            errorcost.setVisible(true);
        } else {
            test++;
        }
        if (capacity.getText().trim().isEmpty()) {
            errorcapacity.setText("Capacity missing");
            errorcapacity.setVisible(true);
        } else {
            test++;
        }
        if (address.getText().trim().isEmpty()) {
            erroraddress.setText("address missing");
            FadeTransition ft = new FadeTransition(Duration.millis(3000), erroraddress);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.play();
            erroraddress.setVisible(true);
        } else {
            test++;
        }

        if (f == null) {
            errorimage.setText("Image missing");
            errorimage.setVisible(true);
        } else {
            test++;
        }
        java.util.Date now=new java.util.Date();
        LocalDate date = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (starting.getValue() == null) {
            errorbegindate.setText("Beginning date missing");
            errorbegindate.setVisible(true);
        }
        else if(starting.getValue().isBefore(date))
        {
            errorbegindate.setText("must choose a date after today");
            errorbegindate.setVisible(true);
        }
        else {
            test++;
        }
        if(ending.isDisabled())
        {
            test++;
        }

         else if (ending.getValue() == null) {
            errorbegindate.setText("Ending date missing");
            errorbegindate.setVisible(true);
        }
        else if((starting.getValue()!=null)&&(ending.getValue().isBefore(starting.getValue())))
        {errorenddate.setText("Ending date should be after Beginning");
        errorenddate.setVisible(true);}
        else {
            test++;
        }

        if (stime.getValue() == null) {
            errorbegintime.setText("Beginning time missing");
            errorbegintime.setVisible(true);
        } else {
            test++;
        }

        if (etime.getValue() == null) {
            errorbegintime.setText("Beginning time missing");
            errorbegintime.setVisible(true);
        } else {
            test++;
        }
        if (!mapclicked) {
            errormap.setText("please point at the event's location");
            FadeTransition ft = new FadeTransition(Duration.millis(3000), errormap);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.play();
            errormap.setVisible(true);
        } else {
            test++;
        }

        System.out.print(test);
        if (test == 11) {
            sample.Entities.Event ev = new sample.Entities.Event();

            EventService es = new EventService();
            Element inputField = map.getEngine().getDocument().getElementById("lng");
            HTMLTextAreaElement x = (HTMLTextAreaElement) inputField;
            String lng = x.getValue();
            Element inputField2 = map.getEngine().getDocument().getElementById("lat");
            HTMLTextAreaElement x2 = (HTMLTextAreaElement) inputField2;
            String lat = x2.getValue();

            String uuu = UUID.randomUUID().toString();
            Path from = Paths.get(f.toURI());
            Path to = Paths.get("/var/www/html/PawsAndClaws/web/bundles/uploads/brochures/" + uuu + ".jpeg");
            CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
            };
            try {
                Files.copy(from, to, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (ending.isDisabled()) {
                Date bd = Date.valueOf(starting.getValue());
                Calendar cal = Calendar.getInstance();
                cal.setTime(bd);
                Time st = Time.valueOf(stime.getValue());
                cal.set(Calendar.HOUR_OF_DAY, st.getHours());
                cal.set(Calendar.MINUTE, st.getMinutes());
                cal.set(Calendar.SECOND, st.getSeconds());
                ev.setBegin(cal.getTime());
                st = Time.valueOf(etime.getValue());
                cal.set(Calendar.HOUR_OF_DAY, st.getHours());
                cal.set(Calendar.MINUTE, st.getMinutes());
                cal.set(Calendar.SECOND, st.getSeconds());
                ev.setEnd(cal.getTime());

            } else {
                Date bd = Date.valueOf(starting.getValue());
                Date ed = Date.valueOf(ending.getValue());

                Calendar cal = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();

                cal.setTime(bd);
                cal2.setTime(ed);
                Time st = Time.valueOf(stime.getValue());
                Time et = Time.valueOf(etime.getValue());
                cal.set(Calendar.HOUR_OF_DAY, st.getHours());
                cal.set(Calendar.MINUTE, st.getMinutes());
                cal.set(Calendar.SECOND, st.getSeconds());
                cal2.set(Calendar.HOUR_OF_DAY, et.getHours());
                cal2.set(Calendar.MINUTE, et.getMinutes());
                cal2.set(Calendar.SECOND, et.getSeconds());
                ev.setBegin(cal.getTime());
                ev.setEnd(cal2.getTime());
            }

            ev.setBrochure(uuu + ".jpeg");
            ev.setPosx(Double.parseDouble(lng));
            ev.setPosy(Double.parseDouble(lat));
            ev.setName(name.getText());
            ev.setDescription(description.getText());
            ev.setCapacity(Integer.parseInt(capacity.getText()));
            ev.setCost(Double.parseDouble(cost.getText()));
            ev.setAddress(address.getText());
            es.addEvent(ev);
            Stage st = (Stage) errormap.getScene().getWindow();
            st.close();
            Main.controller.reload();
        }
    }

    @FXML
    void uploadimage(ActionEvent event) {

        fc.setTitle("Choose a picture for your event");
        File selectedFile = fc.showOpenDialog(null);
        f = selectedFile;
        if (selectedFile != null) {


            //the code to copy the selected file goes here//
            filename.setVisible(true);
            filename.setText(selectedFile.getName());

        } else {
            /*file1.setText("no file attached");*/
        }
    }

    @FXML
    void mapclicked(MouseEvent event) {
        mapclicked = true;
    }
}
