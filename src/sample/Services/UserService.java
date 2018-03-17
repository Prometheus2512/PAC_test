package sample.Services;


import org.mindrot.jbcrypt.BCrypt;
import sample.Database.DBConnection;
import sample.Entities.User;
import sample.IServices.IUserService;
import sample.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements IUserService{

    Connection conn;

    public UserService() {

        conn = DBConnection.getInstance().getCon();
    }
    @Override
    public User showUser(String id) {
        return null;
    }

    @Override
    public boolean addUser(User u) {
            try {
                String query = "insert into `user`(`username`,`password` ,`email`,`firstname`,`lastname`,`phonenumber`,`address`,`roles`,`enabled`,`username_canonical`,`email_canonical`,`last_login`  ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ;";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, u.getUsername());
                st.setString(2, this.hashPassword(u.getUsername()));

                st.setString(3, u.getEmail());
                st.setString(4, u.getFirstname());
                st.setString(5, u.getLastname());
                st.setString(6, u.getPhonenumber());
                st.setString(7, u.getAddress());
                st.setString(8, "a:0:{}");
                st.setString(9, "1");
                st.setString(10, u.getUsername());
                st.setString(11,u.getEmail());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                System.out.println(dateFormat.format(date));
                st.setString(12,dateFormat.format(date));





                st.execute();
System.out.println("User added with success !");
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

            }
return true;
    }
    public  String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(13);
        String thashed_password = BCrypt.hashpw(password_plaintext, salt);
        String hashed_password = thashed_password.substring(0, 2) + 'y' + thashed_password.substring(3);

        return(hashed_password);
    }
    @Override
    public User Login(String username, String password) {

        boolean test = Authentificate(username,password);
        if(test){
            User user = new User();

            try {
            String query = "select * from user where  (username = ? );";
            PreparedStatement stm = conn.prepareStatement(query);


            stm.setString(1, username);


            ResultSet rest = stm.executeQuery();
            while (rest.next()) {




               user.setId(rest.getInt("id"));
               user.setEmail(rest.getString("email"));
               user.setAddress(rest.getString("address"));
               user.setFirstname(rest.getString("firstname"));
               user.setLastname(rest.getString("lastname"));
               user.setPhonenumber(rest.getString("phonenumber"));
               user.setPassword(rest.getString("password"));
               String roleDB = rest.getString("roles");

                user.setUsername(username);
               if(roleDB.contains("ROLE_ADMIN"))
               { user.setRole("ROLE_ADMIN");}
               else if (roleDB.contains("ROLE_VETERINARY"))
               { user.setRole("ROLE_VETERINARY");}
               else{ user.setRole("ROLE_USER"); }

                String DBpassword = rest.getString("password");






            }
        } catch (SQLException ex) {

            System.out.println("not good at all ");
        }


        return user;}
        else {return  null;}
    }

    @Override
    public Boolean Authentificate(String username, String password) {
        Boolean u = false;
        int i=0;
        try {
            String query = "select password  from user where  (username = ? );";
            PreparedStatement stm = conn.prepareStatement(query);


            stm.setString(1, username);


            ResultSet rest = stm.executeQuery();

            while (rest.next()) {
                i++;
               String DBpassword = rest.getString("password");

                String hashedpassword = DBpassword.substring(0, 2) + 'a' + DBpassword.substring(3);
                boolean test = BCrypt.checkpw(password, hashedpassword);
                if (test) {
                    u = true;
                    System.out.println("Logged in with success");
                    Main.actualpassword=password;
                    System.out.println(Main.actualpassword);
                } else {
                    u = false;
                    System.out.println("Wrong credentials");

                }

            }
            if(i==0){System.out.println("Could not find user ");}
        } catch (SQLException ex) {

    System.out.println("not good at all ");
        }
        return u ;
    }
}
