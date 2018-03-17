package sample.GUI.Account;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.mindrot.jbcrypt.BCrypt;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    /*------edit-----*/
    @FXML
    private AnchorPane cEdit;

    @FXML
    private JFXPasswordField cpassword;

    @FXML
    private JFXButton leboutonperdu1;

    @FXML
    private AnchorPane Edit;

    @FXML
    private JFXTextField ephone;

    @FXML
    private JFXTextField elastname;

    @FXML
    private JFXTextField efirstname;

    @FXML
    private JFXTextField eemail;

    @FXML
    private JFXTextField eaddress;

    @FXML
    private JFXTextField eusername;

    @FXML
    private JFXButton eedit;

    /*
    ------------------*/
    @FXML
    private AnchorPane DeleteProfile;
    @FXML
    private JFXButton leboutonperdu;


    @FXML
    private JFXTextField lechercheur;

    @FXML
    private AnchorPane Overview;
    @FXML
    private JFXButton btnOverview;

    @FXML
    private JFXButton btnedit;

    @FXML
    private JFXButton btnpassword;

    @FXML
    private JFXButton btndelete;


    @FXML
    private Label username;

    @FXML
    private Label email;

    @FXML
    private Label firstname;

    @FXML
    private Label lastname;

    @FXML
    private Label address;

    @FXML
    private Label role;

    @FXML
    private Label phone;
    String hashedpassword = Main.actualuser.getPassword().substring(0, 2) + 'a' + Main.actualuser.getPassword().substring(3);


    @Override
    public void initialize(URL location, ResourceBundle resources) {





        username.setText(Main.actualuser.getUsername());
        email.setText(Main.actualuser.getEmail());
        firstname.setText(Main.actualuser.getFirstname());
        lastname.setText(Main.actualuser.getLastname());
        address.setText(Main.actualuser.getAddress());
        role.setText(Main.actualuser.getRole());
        phone.setText(Main.actualuser.getPhonenumber());
        Overview.setVisible(true);

    }

    @FXML
    void ShowChangePassword(ActionEvent event) {
        cEdit.setVisible(false);
        Edit.setVisible(false);
        Overview.setVisible(false);
        btnpassword.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnOverview.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnedit.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btndelete.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");

    }

    @FXML
    void Checkchange(KeyEvent event) {
        if (eusername.getText().equals(Main.actualuser.getUsername()) &&
                eemail.getText().equals(Main.actualuser.getEmail())&&
                eaddress.getText().equals(Main.actualuser.getAddress()) &&
                ephone.getText().equals(Main.actualuser.getPhonenumber())&&
                elastname.getText().equals(Main.actualuser.getLastname())&&
                efirstname.getText().equals(Main.actualuser.getFirstname())
                ) {
            eedit.setDisable(true);
        } else {
            eedit.setDisable(false);
        }
    }

    @FXML
    void ShowDeleteProfile(ActionEvent event) {
        Edit.setVisible(false);
        cEdit.setVisible(false);
        Overview.setVisible(false);
        DeleteProfile.setVisible(true);
        btndelete.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnOverview.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnedit.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnpassword.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");

    }

    @FXML
    void ShowEditProfile(ActionEvent event) {
        eusername.setText(Main.actualuser.getUsername());
        efirstname.setText(Main.actualuser.getFirstname());
        elastname.setText(Main.actualuser.getLastname());
        ephone.setText(Main.actualuser.getPhonenumber());
        eaddress.setText(Main.actualuser.getAddress());
        eemail.setText(Main.actualuser.getEmail());

        cEdit.setVisible(false);
        DeleteProfile.setVisible(false);
        Edit.setVisible(true);
        Overview.setVisible(false);
        btnedit.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnOverview.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btndelete.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnpassword.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");

    }

    @FXML
    void ShowOverview(ActionEvent event) {
        DeleteProfile.setVisible(false);
        cEdit.setVisible(false);
        Edit.setVisible(false);
        Overview.setVisible(true);
        btnOverview.setStyle("-fx-border-width: 0 0 5 0; -fx-border-color: transparent transparent #2EB398  #2EB398;" +
                "    -fx-text-fill: white;");
        btnpassword.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btndelete.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnedit.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");
        btnpassword.setStyle("    -fx-border-width: 0 0 0 5; -fx-border-color: transparent transparent green  transparent;");

    }

    @FXML
    void chercheleboutonperdu(KeyEvent event) {
/*
        String hashedpassword = Main.actualuser.getPassword().substring(0, 2) + 'a' + Main.actualuser.getPassword().substring(3);
*/
       /*if(BCrypt.checkpw(lechercheur.getText(), hashedpassword))
       {leboutonperdu.setDisable(false);}*/
        if (lechercheur.getText().equals(Main.actualpassword)) {
            leboutonperdu.setDisable(false);
        }



/*
if(Main.actualuser.getPassword().equals(lechercheur.getText()))
{
*/
        /*leboutonperdu.setDisable(false);*//*

}
*/


    }
    @FXML
    void confirmpwd(KeyEvent event) {

        if(cpassword.getText().equals(Main.actualpassword))
        {leboutonperdu1.setDisable(false);}
        /*
        if(BCrypt.checkpw(cpassword.getText(), hashedpassword))
        {leboutonperdu1.setDisable(false);}*/
    }

    @FXML
    void confirmedit(ActionEvent event) {
    Edit.setVisible(false);
    cEdit.setVisible(true);
    }
}
