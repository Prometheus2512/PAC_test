package sample.Services;

import sample.Database.DBConnection;
import sample.Entities.Event;
import sample.Entities.Reservation;
import sample.IServices.IReservationService;
import sample.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "delete from reservation where eventid_id =? AND  participantid_id=?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1,String.valueOf(id));
            st.setString(2,String.valueOf(Main.actualuser.getId()));

            st.execute();
      System.out.println("Unbooking done ! ");
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deletebyevent() {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "delete from reservation where eventid_id =?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1,String.valueOf(Main.actualevent.getId()));

            st.execute();
            System.out.println("Reservations deleted ! ");
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<Reservation> myreservations() {

        conn = DBConnection.getInstance().getCon();

        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        try {
            String query = "select * from `reservation` WHERE participantid_id="+Main.actualuser.getId()+";";
            PreparedStatement stm = conn.prepareStatement(query);

            ResultSet rest = stm.executeQuery();
            while(rest.next()){
                Reservation e = new Reservation();
                e.setId(rest.getInt("id"));
                e.setEvent(rest.getInt("eventid_id"));

                reservations.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations ;


    }

    @Override
    public int howmanyparticipants(int id) {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "select count(*) from reservation where eventid_id = ?;";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, String.valueOf(id));

            ResultSet rest = stm.executeQuery();
            while(rest.next()){
            return rest.getInt(1);}



        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;    }

    @Override
    public boolean amiparticipating(int id) {
        conn = DBConnection.getInstance().getCon();

        Event e = new Event();
        try {
            String query = "select * from reservation where participantid_id = ? AND eventid_id = ?;";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, String.valueOf(Main.actualuser.getId()));
            stm.setString(2, String.valueOf(id));

            ResultSet rest = stm.executeQuery();
            return rest.next();




        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
