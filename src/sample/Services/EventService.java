package sample.Services;

import sample.Database.DBConnection;
import sample.Entities.Event;
import sample.IServices.IEventService;
import sample.IServices.IUserService;
import sample.Main;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventService implements IEventService{
    Connection conn;

    @Override
    public Event showEvent(int id) {
        conn = DBConnection.getInstance().getCon();


        Event e = new Event();
        try {
            String query = "select * from event where id = ?;";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, String.valueOf(id));
            ResultSet rest = stm.executeQuery();
            while(rest.next()){
                e.setId(rest.getInt("id"));
                e.setName(rest.getString("Name"));
                e.setDescription(rest.getString("Description"));
                e.setCost(rest.getDouble("cost"));
                e.setCapacity(rest.getInt("capacity"));
                e.setAddress(rest.getString("address"));
                java.util.Date date1;
                java.util.Date date2;
                Timestamp timestamp1 = rest.getTimestamp("BeginningDate");
                Timestamp timestamp2 = rest.getTimestamp("EndingDate");
                date1 = new java.util.Date(timestamp1.getTime());
                date2 = new java.util.Date(timestamp2.getTime());

                e.setBegin(date1);
                e.setEnd(date2);
                e.setHost(rest.getInt("hostid_id"));
                e.setPosx(rest.getDouble("posx"));
                e.setPosy(rest.getDouble("posy"));
                e.setViews(rest.getInt("views"));
                e.setValidated(rest.getInt("validated"));
                e.setBrochure(rest.getString("brochure"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e ;    }


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
            st.setString(12, E.getBrochure());
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
    public void updateEvent(Event u) {
      /*  try {
            String query = "update `event` set Name =? , Description =? , capacity =? , cost =? ,  BeginningDate=?, EndingDate = ? , brochure=? where id =?  ;";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, u.getLogin());
            st.setString(2, u.getPassword());
            st.setString(3, u.getEmail());
            st.setString(4, u.getRole().getName());
            st.setString(5, u.getId());
            st.setString(7, u.getId());
            st.setString(6, u.getPhoto());

            st.execute();
            System.out.println("Service.UserService.modifierUser()");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void updateEventname() {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "update `event` set Name =? where id =?  ;";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, Main.actualevent.getName());
            st.setString(2, String.valueOf(Main.actualevent.getId()));


            st.execute();
            System.out.println("Event's name changed to :"+ Main.actualevent.getName());

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateEventdescription() {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "update `event` set  Description=? where id =?  ;";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, Main.actualevent.getDescription());
            st.setString(2, String.valueOf(Main.actualevent.getId()));


            st.execute();
            System.out.println("Event's description changed to :"+ Main.actualevent.getDescription());

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateEventcost() {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "update `event` set  cost=? where id =?  ;";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, String.valueOf(Main.actualevent.getCost()));
            st.setString(2, String.valueOf(Main.actualevent.getId()));


            st.execute();
            System.out.println("Event's description changed to :"+ Main.actualevent.getCost());

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateEventcapacity() {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "update `event` set  capacity=? where id =?  ;";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, String.valueOf(Main.actualevent.getCapacity()));
            st.setString(2, String.valueOf(Main.actualevent.getId()));


            st.execute();
            System.out.println("Event's description changed to :"+ Main.actualevent.getCost());

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @Override
    public List<Event> allEvents() {
        conn = DBConnection.getInstance().getCon();

        ArrayList<Event>  events = new ArrayList<Event>();
        try {
            String query = "select * from `event` ORDER BY  BeginningDate ASC ;";
            PreparedStatement stm = conn.prepareStatement(query);

            ResultSet rest = stm.executeQuery();
            while(rest.next()){
                Event e = new Event();
               e.setId(rest.getInt("id"));
               e.setName(rest.getString("Name"));
               e.setDescription(rest.getString("Description"));
               e.setCost(rest.getDouble("cost"));
               e.setCapacity(rest.getInt("capacity"));
               e.setAddress(rest.getString("address"));
               e.setBegin(rest.getDate("BeginningDate"));
               e.setEnd(rest.getDate("EndingDate"));
               e.setHost(rest.getInt("hostid_id"));
               e.setPosx(rest.getDouble("posx"));
               e.setPosy(rest.getDouble("posy"));
               e.setViews(rest.getInt("views"));
               e.setValidated(rest.getInt("validated"));
               e.setBrochure(rest.getString("brochure"));

               events.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events ;


    }
}
