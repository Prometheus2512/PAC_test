package sample.Services;

import sample.Database.DBConnection;
import sample.Entities.Event;
import sample.IServices.IEventService;
import sample.IServices.IUserService;
import sample.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventService implements IEventService{
    Connection conn;
    @Override
    public void addEvent(Event E) {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "insert into `event`(`hostid_id`,`Name` ,`BeginningDate`,`EndingDate`,`address` , `posx` , `posy` , `capacity` , `views`, `cost`, `validated`, `brochure`, `Description`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ;";

            PreparedStatement st = conn.prepareStatement(query);


            st.setString(1,String.valueOf(Main.actualuser.getId()) );
            st.setString(2, E.getName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            st.setString(3, dateFormat.format(E.getBegin()));
            st.setString(4, dateFormat.format(E.getEnd()));
            st.setString(5, E.getAddress());
            st.setString(6, String.valueOf(34.885931));
            st.setString(7, String.valueOf(9.84375));
            st.setString(8, String.valueOf(E.getCapacity()));
            st.setString(9, String.valueOf(0));
            st.setString(10, String.valueOf(E.getCost()));

            st.setString(11, String.valueOf(0));
            st.setString(12, "ea754675d709f02abb15b3fb84f1213a.jpeg");
            st.setString(13,E.getDescription());

System.out.println("Event added !");
            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void deleteEvent(int id) {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "delete from event where id =?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1,String.valueOf(id));
            st.execute();
            System.out.println("Event Deleted ");

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateEvent(Event E) {

    }

    @Override
    public void approveEvent(int id) {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "update `event` set PawsAndClaws.event.validated =? WHERE PawsAndClaws.event.id=?;";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1,String.valueOf(1));
            st.setString(2,String.valueOf(id));


            st.execute();
            System.out.println("Event approved ");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
