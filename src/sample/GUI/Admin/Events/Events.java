package sample.GUI.Admin.Events;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.Entities.Event;
import sample.Services.EventService;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Events implements Initializable {
    @FXML
    private AnchorPane content;

    @FXML
    private Label error;
    @FXML
    private TableView<Event> eventstable;
    @FXML
    private TableColumn<Event, String> name;

    @FXML
    private TableColumn<Event, String> capacity;

    @FXML
    private TableColumn<Event, String> participants;

    @FXML
    private TableColumn<Event, String> cost;

    @FXML
    private TableColumn<Event, Date> begins;

    @FXML
    private TableColumn<Event, Date> ends;

    @FXML
    private TableColumn<Event, Boolean> status;

    @FXML
    private TableColumn<Event, Boolean> approve;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        begins.setCellValueFactory(new PropertyValueFactory<>("begin"));
        ends.setCellValueFactory(new PropertyValueFactory<>("end"));

        approve.setCellFactory(new Callback<TableColumn<Event, Boolean>, TableCell<Event, Boolean>>() {


            @Override
            public TableCell<Event, Boolean> call(TableColumn<Event, Boolean> param) {

                return new ButtonCell();
            }
        });
        status.setCellFactory(new Callback<TableColumn<Event, Boolean>, TableCell<Event, Boolean>>() {


            @Override
            public TableCell<Event, Boolean> call(TableColumn<Event, Boolean> param) {

                return new StatusCell();
            }
        });

        eventstable.setItems(getEvents());

    }

    private ObservableList<Event> getEvents() {
        EventService es = new EventService();
        ObservableList<Event> allevents = FXCollections.observableArrayList();
        allevents.addAll(es.allEvents());
        return allevents;
    }

    private class ButtonCell extends TableCell<Event, Boolean> {
        final Button cellButton = new Button("Validate");

        public ButtonCell() {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    EventService es = new EventService();
/*
                    Event e =eventstable.getSelectionModel().getSelectedItem();
*/
                    int selectdIndex = getTableRow().getIndex();
                    Event e = eventstable.getItems().get(selectdIndex);
                    es.approveEvent(e.getId());
                    eventstable.setItems(getEvents());
/*
                    cellButton.setVisible(false);
*/


                    error.setText("Name edited successfully.");
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
                int selectdIndex = getTableRow().getIndex();
                Event e = eventstable.getItems().get(selectdIndex);
                if (e.getValidated() == 1) {
                    cellButton.setVisible(false);
                }

                setGraphic(cellButton);
            }
        }

    }

    private class StatusCell extends TableCell<Event, Boolean> {
        final Label cellabel = new Label();

        public StatusCell() {
cellabel.setStyle("-fx-text-fill: white");
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                int selectdIndex = getTableRow().getIndex();
                Event e = eventstable.getItems().get(selectdIndex);
                if (e.getValidated() == 1) {
                    Date now=new Date();
                    if((now.after(e.getBegin()))&&(now.before(e.getEnd())));
                    {cellabel.setText("ongoing");}
                    if(now.after(e.getEnd())){cellabel.setText("event ended");}
                    else{
                        cellabel.setText("upcoming");
                    }
                } else {
                    cellabel.setText("not validated");
                }

                setGraphic(cellabel);
            }
        }

    }
}
