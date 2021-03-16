package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyStudent {
    @FXML TextField name;
    @FXML TextField gender;
    @FXML TextField birthday;
    @FXML TextField address;
    @FXML TextField email;
    @FXML TextField placeofbirth;
    @FXML Button Confirm;
    @FXML Button Cancel;
    String userName;
    public void cancel(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
}
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void confirm(ActionEvent event) throws IOException {
        System.out.println(birthday.getText());
        if (!birthday.getText().equals("")) {
            Checkdate(birthday.getText());
        }
        if (!checkGender(gender.getText())) {return;}

        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info";
            Connection connection = DriverManager.getConnection(url, "sa", "123");
            Statement statement = connection.createStatement();
            String uname = userName;
            if (!name.getText().equals("")){
                String sql  = "UPDATE Student SET name = '" + name.getText() + "' WHERE username = '" + uname + "'";
                statement.executeUpdate(sql);
                System.out.println(name.getText());
            }
            if (!gender.getText().equals("")){
                String sql  = "UPDATE Student SET gender = '" + gender.getText() + "' WHERE username = '" + uname +"'";
                statement.executeUpdate(sql);
            }
            if (!birthday.getText().equals("")){
                Checkdate(birthday.getText());
                String sql  = "UPDATE Student SET birthday = '" + birthday.getText() + "' WHERE username = '" + uname +"'";
                statement.executeUpdate(sql);
            }
            if (!address.getText().equals("")){
                String sql  = "UPDATE Student SET address = '" + address.getText() + "' WHERE username = '" + uname +"'";
                statement.executeUpdate(sql);
            }
            if (!email.getText().equals("")){
                String sql  = "UPDATE Student SET email = '" + email.getText() + "' WHERE username = '" + uname +"'";
                statement.executeUpdate(sql);
            }
            if (!placeofbirth.getText().equals("")){
                String sql  = "UPDATE Student SET placeofbirth = '" + placeofbirth.getText() + "' WHERE username = '" + uname +"'";
                statement.executeUpdate(sql);
            }
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void Checkdate(String ngay){
        try {
            Date date = new SimpleDateFormat("YYYY-MM-DD").parse(ngay);
        } catch (ParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot execute function");
            alert.setContentText("Please insert the right format for date (YYYY-MM-DD)");
            alert.showAndWait();
        }

    }
    public boolean checkGender(String g) {
        if (!g.equals("male") && !g.equals("female") && !g.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot execute function");
            alert.setContentText("Please insert the right format for gender (male/female)");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}