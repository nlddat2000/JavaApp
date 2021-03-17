package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    }

    public void insert(ActionEvent event) {
        if (!checkInfoValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Error");
            alert.setHeaderText("Failed to insert new student");
            alert.setContentText("The text fields must not be empty!");
            alert.showAndWait();
            return;
        }

        if (!checkInt(intake.getText())) return;
        if (!isScore(score.getText())) return;

        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";

            Connection connection = DriverManager.getConnection(url, "sa", "123");
            Statement statement = connection.createStatement();
            String sql = "SELECT username FROM Student WHERE username = '" + username.getText() + "'";
            ResultSet result = statement.executeQuery(sql);
            boolean check = false;
            while (result.next()) {
                if (result.getString("username").equals(username.getText())) {
                    check = true;
                }
            }
            if (!check) {
                String sql1 = "INSERT INTO Student VALUES ('" + name.getText() + "', '', '', '', '" + username.getText() + "', '', '1', '" + major.getText() + "', '', " + intake.getText() + ",1)";
                statement.executeUpdate(sql1);
            }

            String sql2 = "INSERT INTO Score VALUES ('" + username.getText() + "','" + tusername + "', " + score.getText() + ")";
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

    public boolean checkInt(String i) {
        try {
            int d = Integer.parseInt(i);
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot execute function");
            alert.setContentText("Please insert the right format for year (integer)");
            alert.showAndWait();
            return false;
        }
    }

    public  boolean isScore(String i) {
        try {
            float f = Float.parseFloat(i);
            if ((f > 4 || f < 1) && (f != 5)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Cannot execute function");
                alert.setContentText("Please insert score in range [1..4] or 5");
                alert.showAndWait();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot execute function");
            alert.setContentText("Please insert the right format for score (float)");
            alert.showAndWait();
            return false;
        }
    }

    public boolean checkInfoValid() {
        if (name.getText().equals("")) return false;
        if (username.getText().equals("")) return false;
        if (major.getText().equals("")) return false;
        if (intake.getText().equals("")) return false;
        if (score.getText().equals("")) return false;
        return true;
    }

}
