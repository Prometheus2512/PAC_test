package sample;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Database.DBConnection;
import sample.Entities.Event;
import sample.Entities.User;
import sample.Services.EventService;
import sample.Services.UserService;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.sql.Connection;

public class Main extends Application {
    public  static User actualuser=null;
    public  static String actualpassword=null;

    @Override
    public void start(Stage primaryStage) throws Exception{

/*

        actualuser=us.Login("admin","admin");
        if(actualuser!=null)
        {
        System.out.println(actualuser.getRole());}
*/


        /*UserService  us=new UserService();
        actualuser=us.Login("aaa","aaa");
        EventService es=new EventService();
        Event E=new Event();
        E.setBegin(new Date());
        E.setEnd(new Date());
        E.setName("test");
        E.setAddress("testcity");
        E.setCapacity(500);
        E.setCost(22);
        E.setDescription("testdesc");
        es.addEvent(E);*/
       /* EventService es= new EventService();
        es.approveEvent(6);*/


        Parent root = FXMLLoader.load(getClass().getResource("GUI/Login/Login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
