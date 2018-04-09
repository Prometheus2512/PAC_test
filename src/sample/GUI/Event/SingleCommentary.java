package sample.GUI.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Entities.Commentary;
import sample.Entities.Event;
import sample.Entities.User;
import sample.Main;
import sample.Services.CommentaryService;
import sample.Services.UserService;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class SingleCommentary implements Initializable { @FXML
private Text content;

    @FXML
    private Label date;
    @FXML
    private Label x;
    @FXML
    private AnchorPane deleteask;
    Commentary commentary;
    @FXML
    private AnchorPane deleted;
    @FXML
    private Label username;
    public void setContent(Commentary c) {
        this.content.setText(c.getContent());
        UserService us = new UserService();
        User u=us.showUser(String.valueOf(c.getCommentator_id()));
        this.username.setText(u.getUsername());
        Calendar cal=Calendar.getInstance();
        cal.setTime(c.getCreationDate());
        date.setText(cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+1+"/"+cal.get(Calendar.YEAR)+" on "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
        commentary=c;
        if(c.getCommentator_id()== Main.actualuser.getId())
        {x.setVisible(true);}
        else
        {x.setVisible(false);}
    }
        @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    @FXML
    void askfordelete(MouseEvent event) {
        deleteask.setVisible(true);

    }
    @FXML
    void no(ActionEvent event) {
        deleteask.setVisible(false );
    }
    @FXML
    void yes(ActionEvent event) {
        CommentaryService cs=new CommentaryService();
        cs.deletecomment(commentary.getId());
        deleted.setVisible(true);
        deleteask.setVisible(false);
/*
        sample.GUI.Event.Amasinglelady.getController().updatevbox();
*/
    }
}
