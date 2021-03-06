package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentView {

    @FXML static String username;
    @FXML Label name;
    @FXML Label gender;
    @FXML Label birthday;
    @FXML Label address;
    @FXML Label uname;
    @FXML Label email;
    @FXML Label pass;
    @FXML Label major;
    @FXML Label placeofbirth;
    @FXML Label intake;

    @FXML Button view;
    @FXML Button mod;
    @FXML Button changeP;
    @FXML Button ext;
    @FXML Button lgt;

    public void setStatus(String loginUsername) {
        username = loginUsername;
        getStudentInfo(username);
    }
    public void getStudentInfo(String username){

        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";

            Connection connection = DriverManager.getConnection(url, "sa", "123");

            String sql = "SELECT * FROM Student WHERE Username = " + "'" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                name.setText(result.getString("Name"));
                gender.setText(result.getString("Gender"));
                birthday.setText(result.getString("BirthDay"));
                address.setText(result.getString("Address"));
                uname.setText(result.getString("Username"));
                email.setText(result.getString("Email"));
                pass.setText(result.getString("Password"));
                major.setText(result.getString("Major"));
                placeofbirth.setText(result.getString("PlaceOfBirth"));
                intake.setText(String.valueOf(result.getInt("Intake")));

                //students.add(new StudentInfo(Name, Gender, BirthDay, Address, Username, Email, PassWord, Major, PlaceOfBirth, Intake, Accessrole));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public void watchGPA(ActionEvent event) {
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreWindow.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            ScoreWindowController controller = loader.getController();
            controller.setUserName(username);
            window.setTitle("Score");
            window.centerOnScreen();
            disableButtons(true);
            window.showAndWait();
            disableButtons(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void modify1() {
       AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyStudent.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            ModifyStudent controller = loader.getController();
            controller.setUserName(username);
            window.setTitle("Modify personal information");
            disableButtons(true);
            window.showAndWait();
            disableButtons(false);
            setStatus(username);

        } catch (IOException e) {
        }
    }
    public void cancel(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }


    public void changePass() throws IOException {
        GridPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePass.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            //ScoreWindowController controller = loader.getController();
            window.setTitle("Change Password");
            disableButtons(true);
            window.showAndWait();
            disableButtons(false);
            setStatus(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setTitle("login");
        window.show();

    }

    public void disableButtons(boolean b) {
        view.setDisable(b);
        changeP.setDisable(b);
        mod.setDisable(b);
        ext.setDisable(b);
        lgt.setDisable(b);
    }

    public void exit() {
        System.exit(0);
    }


}
