package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreWindowController implements  Initializable{
    @FXML
        Label NameLabel;
    @FXML
        Label MathLabel;
    @FXML
        Label LawLabel;
    @FXML
        Label ProgramingExerciseLabel;
    @FXML
        Label PhysicsLabel;
    @FXML
        Label GPALabel;
    private String userName;


    public void setUserName(String userName) {
        this.userName = userName;
        System.out.println(userName);
        NameLabel.setText(userName);
        getValue(userName);
    }

    public  void getValue(String Username) {
        try{
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";
            Connection connection = DriverManager.getConnection(url, "sa", "123");
            String sql = "select score,course from score join teacher on teacher.tusername = score.tusername where score.username =  '" + Username + "';" ;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            float s = 0;
            int i = 0;
            while (result.next()) {
                s = s + result.getFloat("score");
                String Subject = result.getString("course");
                //System.out.println(String.valueOf(result.getFloat("score")));
                ArrayList<Float> myList = new ArrayList<Float>();
                if (Subject.equals("Math")) {
                    i += 1;
                    MathLabel.setText(String.valueOf(result.getFloat("score")));
                    myList.add(result.getFloat("score"));
                }

                if (Subject.equals("Physics")) {
                    i += 1;
                    PhysicsLabel.setText(String.valueOf(result.getFloat("score")));
                    myList.add(result.getFloat("score"));
                }

                if (Subject.equals("Law")) {
                    i += 1;
                    LawLabel.setText(String.valueOf(result.getFloat("score")));
                    myList.add(result.getFloat("score"));
                }
                if (Subject.equals("Programming_Exercise")) {
                    i += 1;
                    ProgramingExerciseLabel.setText(String.valueOf(result.getFloat("score")));
                    myList.add(result.getFloat("score"));
                }


                GPALabel.setText(String.format("%.2f", s/i));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void cancel(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
