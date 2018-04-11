package sample.Services;

import sample.Database.DBConnection;
import sample.Entities.Commentary;
import sample.Entities.Event;
import sample.IServices.ICommentaryService;
import sample.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentaryService implements ICommentaryService {
    Connection conn;
    @Override
    public List<Commentary> ListCommentaries(Event e) {
        conn = DBConnection.getInstance().getCon();

        ArrayList<Commentary> commentaries = new ArrayList<Commentary>();
        try {
            String query = "select * from `commentary` WHERE commentedevent_id = ? ORDER BY CreationDate ASC ;";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1,String.valueOf(Main.actualevent.getId()));

            ResultSet rest = stm.executeQuery();
            while(rest.next()){
                Commentary c=new Commentary();

                c.setCommentator_id(rest.getInt(("commentator_id")));
                c.setCommentedevent_id(rest.getInt("commentedevent_id"));
                c.setContent(rest.getString("content"));
                c.setCreationDate(rest.getDate("CreationDate"));
                c.setId(rest.getInt("id"));


                commentaries.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentaries ;


    }


    @Override
    public void comment(Commentary c) {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "insert into `commentary`(`commentator_id`,`commentedevent_id` ,`content`,`CreationDate`) VALUES(?,?,?,?) ;";

            PreparedStatement st = conn.prepareStatement(query);


            st.setString(1,String.valueOf(Main.actualuser.getId()) );
            st.setString(2, String.valueOf(c.getCommentedevent_id()));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();
            st.setString(3,c.getContent());
            st.setString(4,  dateFormat.format(date));
            System.out.println("Comment added !");
            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void deletecommentbyevent() {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "delete from commentary where commentedevent_id =?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1,String.valueOf(Main.actualevent.getId()));
            st.execute();
            System.out.println("Comments deleted !");

        } catch (SQLException ex) {
            Logger.getLogger(CommentaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletecomment(int id) {
        conn = DBConnection.getInstance().getCon();

        try {
            String query = "delete from commentary where id =?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1,String.valueOf(id));
            st.execute();
            System.out.println("Comment deleted !");

        } catch (SQLException ex) {
            Logger.getLogger(CommentaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
