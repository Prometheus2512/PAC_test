package sample.GUI.Event;

import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.Entities.Event;
import sample.Entities.Reservation;
import sample.GUI.Admin.Events.Events;
import sample.Main;
import sample.Services.EventService;
import sample.Services.ReservationService;

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
    double total;

    @FXML
    private VBox eventlist;

    @FXML
    private JFXButton btnmyevents;

    @FXML
    private Label totalcost;

    @FXML
    private JFXButton btnupevents;

    @FXML
    private JFXButton btnnew;

    @FXML
    private AnchorPane haja2;
    @FXML
    private Label day;
    @FXML
    private Label error;

    @FXML
    private TableView<Reservation> reservationstable;

    @FXML
    private TableColumn<Reservation, Boolean> theevent;

    @FXML
    private TableColumn<Reservation, Boolean> cost;

    @FXML
    private TableColumn<Reservation, Boolean> from;

    @FXML
    private TableColumn<Reservation, Boolean> to;

    @FXML
    private TableColumn<Reservation, Boolean> unbook;
    @FXML
    private Label month;
    private static sample.GUI.Event.EventController Controller;

    @FXML
    void ShowMyEvents(ActionEvent event) {
        reservationstable.setVisible(true);
        totalcost.setVisible(true);
        AllEvents.setVisible(false);
        btnmyevents.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnupevents.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnnew.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        total=0;
theevent.setCellFactory(new Callback<TableColumn<Reservation, Boolean>, TableCell<Reservation, Boolean>>() {


    @Override
    public TableCell<Reservation, Boolean> call(TableColumn<Reservation, Boolean> param) {

        return new StatusCell();
    }
});
        cost.setCellFactory(new Callback<TableColumn<Reservation, Boolean>, TableCell<Reservation, Boolean>>() {


            @Override
            public TableCell<Reservation, Boolean> call(TableColumn<Reservation, Boolean> param) {

                return new CostCell();
            }
        });
        from.setCellFactory(new Callback<TableColumn<Reservation, Boolean>, TableCell<Reservation, Boolean>>() {


            @Override
            public TableCell<Reservation, Boolean> call(TableColumn<Reservation, Boolean> param) {

                return new BeginCell();
            }
        });
        to.setCellFactory(new Callback<TableColumn<Reservation, Boolean>, TableCell<Reservation, Boolean>>() {


            @Override
            public TableCell<Reservation, Boolean> call(TableColumn<Reservation, Boolean> param) {

                return new EndCell();
            }
        });
        unbook.setCellFactory(new Callback<TableColumn<Reservation, Boolean>, TableCell<Reservation, Boolean>>() {


            @Override
            public TableCell<Reservation, Boolean> call(TableColumn<Reservation, Boolean> param) {

                return new ButtonCell();
            }
        });
        ReservationService rs=new ReservationService();
reservationstable.setItems(getReservations());

    }

    @FXML
    void ShowNewEvent(ActionEvent event) {
        AllEvents.setVisible(false);
        btnnew.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnmyevents.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnupevents.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");


    }
    private class StatusCell extends TableCell<Reservation, Boolean> {
        final Label cellabel = new Label();

        public StatusCell() {
            cellabel.setStyle("-fx-text-fill: black");
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                int selectdIndex = getTableRow().getIndex();
                Reservation e = reservationstable.getItems().get(selectdIndex);
                EventService es = new EventService();
                Event ev = es.showEvent(e.getEvent());
                cellabel.setText(ev.getName());

                setGraphic(cellabel);
            }
        }
    }
    private class CostCell extends TableCell<Reservation, Boolean> {
        final Label cellabel = new Label();

        public CostCell() {
            cellabel.setStyle("-fx-text-fill: black");
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                int selectdIndex = getTableRow().getIndex();
                Reservation e = reservationstable.getItems().get(selectdIndex);
                EventService es = new EventService();
                Event ev = es.showEvent(e.getEvent());
                cellabel.setText(String.valueOf((long)ev.getCost()));
                total=total+ev.getCost();

                setGraphic(cellabel);
            }
        }
    }
    private class BeginCell extends TableCell<Reservation, Boolean> {
        final Label cellabel = new Label();

        public BeginCell() {
            cellabel.setStyle("-fx-text-fill: black");
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                int selectdIndex = getTableRow().getIndex();
                Reservation e = reservationstable.getItems().get(selectdIndex);
                EventService es = new EventService();
                Event ev = es.showEvent(e.getEvent());
                cellabel.setText(String.valueOf(ev.getBegin()));

                setGraphic(cellabel);
            }
        }
    }
    private class EndCell extends TableCell<Reservation, Boolean> {
        final Label cellabel = new Label();

        public EndCell() {
            cellabel.setStyle("-fx-text-fill: black");
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                int selectdIndex = getTableRow().getIndex();
                Reservation e = reservationstable.getItems().get(selectdIndex);
                EventService es = new EventService();
                Event ev = es.showEvent(e.getEvent());
                cellabel.setText(String.valueOf(ev.getEnd()));

                setGraphic(cellabel);
            }
        }
    }
    private class ButtonCell extends TableCell<Reservation, Boolean> {
        final Button cellButton = new Button("Validate");
        final Label  cellLabel=new Label("1");

        public ButtonCell() {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
/*
                    Event e =eventstable.getSelectionModel().getSelectedItem();
*/                  ReservationService rs=new ReservationService();
                    EventService es=new EventService();
                    int selectdIndex = getTableRow().getIndex();
                    Reservation e = reservationstable.getItems().get(selectdIndex);
                    Event ev=es.showEvent(e.getEvent());
                    rs.cancel(e.getEvent());
/*
                    reservationstable.getItems().remove(selectdIndex);
*/
/*
                    reservationstable.setItems(getReservations());
*/
                    cellButton.setVisible(false);
                    cellLabel.setText("deleted");
                    cellLabel.setVisible(true);
/*
                    cellButton.setVisible(false);
*/


                    error.setText("Reservation edited successfully.");
                    error.setStyle("-fx-text-fill: darkgreen");
                    error.setVisible(true);
                    FadeTransition ft = new FadeTransition(Duration.millis(5000), error);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.0);
                    ft.play();

                }
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                if(!cellLabel.getText().equals("1"))
                {                setGraphic(cellLabel);
                }
                else
                {                setGraphic(cellButton);
                }
            }
        }

    }
    private ObservableList<Reservation> getReservations() {
        ReservationService rs=new ReservationService();
        ObservableList<Reservation> allreservations = FXCollections.observableArrayList();
        allreservations.addAll(rs.myreservations());
        return allreservations;
    }

        @FXML
    void ShowUpEvents(ActionEvent event) {
            reservationstable.setVisible(false);
            error.setVisible(false);
            totalcost.setVisible(false);
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
        s.setWidth(700);
        s.setHeight(437);
        s.setScene(new Scene(root));
        s.show();


    }
public void update()
{        Date today = new Date();
        EventService es=new EventService();
    List<Event> le = es.allEvents();
    for (Event e : le) {
        if (today.before(e.getEnd())) {
            Main.actualevent = e;
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

        }
    }
}
public void reload()
{
eventlist.getChildren().clear();
update();
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
      update();

    }
}
