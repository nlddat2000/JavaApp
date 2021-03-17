package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.*;

public class ModifyScore {
@FXML TextField score;
@FXML Label header;
    private String username;
    private String tusername;
    public void getuserName(String username, String tusername){
        this.username  = username;
        this.tusername  = tusername;
        header.setText("Update score: " + this.username);
        System.out.println(this.tusername + this.username);
    }
    public void setScore(ActionEvent event){
        try{
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";
            Connection connection = DriverManager.getConnection(url, "sa", "123");
            String sql = " UPDATE score SET score = " + Float.valueOf(score.getText()) + " WHERE username = '" + username + "' AND tusername = '" + tusername + "'";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void cancel(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

}
