package sample.Services;

import sample.Database.DBConnection;
import sample.IServices.IReservationService;
import sample.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationService implements IReservationService {
   Connection conn;
    @Override
    public void book(int id) {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "insert into `reservation`(`participantid_id`,`eventid_id`) VALUES(?,?) ;";

            PreparedStatement st = conn.prepareStatement(query);


            st.setString(1,String.valueOf(Main.actualuser.getId()) );
            st.setString(2,String.valueOf(id));


            System.out.println("Booking done ");
            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void cancel(int id) {
        try {
            String query = "delete from reservation where eventid_id =? AND  participantid_id=?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1,String.valueOf(id));
            st.setString(1,String.valueOf(Main.actualuser.getId()));

            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
