package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class insertStudent {
    @FXML TextField name;
    @FXML TextField username;
    @FXML TextField major;
    @FXML TextField intake;
    @FXML TextField score;
    private String tusername;
    public void getuserName(String tusername) {
        this.tusername = tusername;
        System.out.println("fa");
    }

    public void insert(ActionEvent event) {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";

            Connection connection = DriverManager.getConnection(url, "sa", "123");
            Statement statement = connection.createStatement();
            String sql = "SELECT username FROM Student WHERE username = '" + username.getText() + "'";
            System.out.println(sql);
            ResultSet result = statement.executeQuery(sql);
            boolean check = false;
            while (result.next()) {
                if (result.getString("username").equals(username.getText())) {
                    check = true;
                }
            }
            if (!check) {
                String sql1 = "INSERT INTO Student VALUES ('" + name.getText() + "', '', '', '', '" + username.getText() + "', '', '', '" + major.getText() + "', '', " + intake.getText() + ",1)";
                System.out.println(sql1);
                statement.executeUpdate(sql1);
            }

            String sql2 = "INSERT INTO Score VALUES ('" + username.getText() + "','" + tusername + "', " + score.getText() + ")";
            System.out.println(sql2);
            statement.executeUpdate(sql2);


            connection.close();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Error");
            alert.setHeaderText("Failed to insert new student");
            alert.setContentText("This username has already existed in the database. You can only modify students' score");

            alert.showAndWait();

        }
    }

    public void cancel(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

}
