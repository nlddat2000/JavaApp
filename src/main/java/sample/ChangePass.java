package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ChangePass {

    @FXML TextField username;
    @FXML TextField email;
    @FXML PasswordField password;
    @FXML PasswordField new_password;
    @FXML PasswordField re_new_password;
    @FXML private Button Confirm;
    public void confirm(ActionEvent event) throws IOException{
        if (new_password.getText().equals(re_new_password.getText())) {
            try {
                String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";

                Connection connection = DriverManager.getConnection(url, "sa", "123");

                String sql  = "SELECT * FROM Student";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);

                String f = "UPDATE Student Set Password = '" + new_password.getText() + "' WHERE Username = '" + username.getText() + "';";
                System.out.println(f);

                while (result.next()) {
                    String Username = result.getString("Username");
                    String Email = result.getString("Email");
                    String PassWord = result.getString("Password");

                    if (Username.equals(username.getText()) && Email.equals(email.getText()) && PassWord.equals(password.getText())) {
                        String sql1 = "UPDATE Student Set Password = '" + new_password.getText() + "' WHERE Username = '" + username.getText() + "';";
                        System.out.println(sql1);
                        statement.execute(sql1);

                        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(new Scene(root));
                        window.show();
                        break;
                    }
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(" no");
        }
    }
}
