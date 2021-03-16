package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Login {
    @FXML
    private TextField loginUsername;
    @FXML private PasswordField loginPassword;
    @FXML public Button loginButton;
    @FXML public Button changePasswordButton;
    public void login(ActionEvent event ) throws IOException {

        int i = 0;

        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";

            Connection connection = DriverManager.getConnection(url, "sa", "123");

            String sql  = "SELECT * FROM Student";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);


            while (result.next()) {
                String Username = result.getString("Username");
                String PassWord = result.getString("Password");

                if ((Username.equals(loginUsername.getText())) && PassWord.equals(loginPassword.getText())) {
                    i = 1;
                    break;
                }
            }

            String sql1  = "SELECT * FROM Teacher";
            Statement statement1 = connection.createStatement();
            ResultSet result1 = statement1.executeQuery(sql1);


            while (result1.next()) {
                if (i == 1) {break;}
                String Username1 = result1.getString("tusername");
                String PassWord1 = result1.getString("tpassword");

                if ((Username1.equals(loginUsername.getText())) && PassWord1.equals(loginPassword.getText())) {
                    i = 2;
                    break;
                }
            }


            connection.close();

            if (i == 1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentView.fxml"));
//                    Parent root = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
                AnchorPane root = loader.load();
                StudentView controller = loader.getController();
                controller.setStatus(loginUsername.getText());
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.setTitle("Student's view");
                window.show();
            }
            else if (i == 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherView.fxml"));
//                    Parent root = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
                BorderPane root = loader.load();
                TeacherView controller = loader.getController();
                controller.setStatus(loginUsername.getText());
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.centerOnScreen();
                window.setTitle("Teacher's view");
                window.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("CANNOT LOGIN");
                alert.setHeaderText("Failed to login");
                alert.setContentText("Wrong username or password");

                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void changePass(ActionEvent event) throws IOException {
        GridPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePass.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            window.centerOnScreen();
            //ScoreWindowController controller = loader.getController();
            window.setTitle("Change Password");
            window.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
