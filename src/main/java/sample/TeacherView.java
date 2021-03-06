package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TeacherView {

    @FXML
    TableView<StudentInfo> tableOfStudent;
    @FXML
    TableColumn<StudentInfo, String> nameColumn;
    @FXML
    TableColumn<StudentInfo, String> genderColumn;
    @FXML
    TableColumn<StudentInfo, String> birthdayColumn;
    @FXML
    TableColumn<StudentInfo, String> addressColumn;
    @FXML
    TableColumn<StudentInfo, String> usernameColumn;
    @FXML
    TableColumn<StudentInfo, String> emailColumn;
    @FXML
    TableColumn<StudentInfo, String> passwordColumn;
    @FXML
    TableColumn<StudentInfo, String> majorColumn;
    @FXML
    TableColumn<StudentInfo, String> placeOfBirthColumn;
    @FXML
    TableColumn<StudentInfo, Integer> intakeColumn;
    @FXML
    TableColumn<StudentInfo, Float> scoreColumn;
    @FXML
    TextField name;
    @FXML
    TextField gender;
    @FXML
    TextField birthday;
    @FXML
    TextField address;
    @FXML
    TextField username;
    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    TextField major;
    @FXML
    TextField placeOfBirth;
    @FXML
    TextField intake;
    @FXML
    TextField score;

    @FXML Button insrt;
    @FXML Button mod;
    @FXML Button del;
    @FXML Button scr;
    @FXML Button rload;
    @FXML Button chrt;
    @FXML Button clr;
    @FXML Button ext;
    @FXML Button srch;
    @FXML Button lgt;

    int index = -1;
    private String currentUserName;
    private static String tusername;
    public static int[] rangeScore = {0, 0, 0, 0};

    public void disableButton(boolean b) {
        insrt.setDisable(b);
        mod.setDisable(b);
        del.setDisable(b);
        scr.setDisable(b);
        rload.setDisable(b);
        chrt.setDisable(b);
        clr.setDisable(b);
        ext.setDisable(b);
        srch.setDisable(b);
        lgt.setDisable(b);
    }

    public void updateTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("Name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("Gender"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("Birthday"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("Address"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("Username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("Password"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("Major"));
        placeOfBirthColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("PlaceOfBirth"));
        intakeColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, Integer>("Intake"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, Float>("Score"));
        tableOfStudent.setItems(getStudents());
    }

    //Get data from SQLServer
    public static ObservableList<StudentInfo> getStudents() {
        ObservableList<StudentInfo> students = FXCollections.observableArrayList();

        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url, "sa", "123");

            String sql = "SELECT * FROM student join score on student.username = score.username where score.tusername = '" + tusername + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);


            while (result.next()) {
                String Name = result.getString("Name");
                String Gender = result.getString("Gender");
                String BirthDay = result.getString("BirthDay");
                String Address = result.getString("Address");
                String Username = result.getString("Username");
                String Email = result.getString("Email");
                String PassWord = result.getString("Password");
                String Major = result.getString("Major");
                String PlaceOfBirth = result.getString("PlaceOfBirth");
                int Intake = result.getInt("Intake");
                Float Score = result.getFloat("Score");

                if (Score == 5) rangeScore[0]++;
                if (Score >=1 && Score <=2) rangeScore[1]++;
                if (Score >2 && Score <=3) rangeScore[2]++;
                if (Score >3 && Score <=4) rangeScore[3]++;

                students.add(new StudentInfo(Name, Gender, BirthDay, Address, Username, Email, PassWord, Major, PlaceOfBirth, Intake, Score));
            }
            connection.close();

            return students;
        } catch (SQLException e) {
            e.printStackTrace();

            return students;
        } catch (Exception e1) {
            e1.printStackTrace();

            return students;
        }
    }

    //Delete row
    public void delete() {

        ObservableList<StudentInfo> studentSelected, allStudent;
        allStudent = tableOfStudent.getItems();
        studentSelected = tableOfStudent.getSelectionModel().getSelectedItems();
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";

            Connection connection = DriverManager.getConnection(url, "sa", "123");
            for (int i = 0; i < studentSelected.size(); i++) {
                String sql0 = "DELETE FROM Score WHERE  Username = ('" + studentSelected.get(i).getUsername() + "') " + " AND tusername =  '" + tusername + "'";
                //String sql = "DELETE FROM Student WHERE  Username = ('" + studentSelected.get(i).getUsername() + "');";
                Statement statement = connection.createStatement();
                if (!confirmation("delete")) return;
                statement.executeUpdate(sql0);
                //statement.executeUpdate(sql);

                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateTable();
    }

    public void insert() {
        StudentInfo student = new StudentInfo();
//        if (!birthday.getText().equals("")) {
//            Checkdate(birthday.getText());
//        }
//        if (!score.getText().equals("")) {
//            if (!isScore(score.getText())) return;
//        }
//        if (!intake.getText().equals("")) {
//            checkInt(intake.getText());
//        }
//        if (!checkGender(gender.getText())) return;
//
//        student.setName(name.getText());
//        student.setGender(gender.getText());
//        student.setBirthday(birthday.getText());
//        student.setAddress(address.getText());
//        student.setUsername(username.getText());
//        student.setEmail(email.getText());
//        student.setPassword(password.getText());
//        student.setMajor(major.getText());
//        student.setPlaceOfBirth(placeOfBirth.getText());
//        student.setIntake(Integer.parseInt(intake.getText()));
//        student.setScore(Float.parseFloat(score.getText()));
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("insertStudent.fxml"));
//            root = FXMLLoader.load(getClass().getResource("ScoreWindow.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            insertStudent controller = loader.getController();
            controller.getuserName(tusername);
            window.initStyle(StageStyle.UNDECORATED);
            disableButton(true);
            window.showAndWait();
            disableButton(false);
            updateTable();
        } catch (IOException ioException) {
            ioException.printStackTrace();
    }

    }

    public void getSelected(MouseEvent event) {
        index = tableOfStudent.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        currentUserName = usernameColumn.getCellData(index).toString();

        name.setText(nameColumn.getCellData(index).toString());
        gender.setText(genderColumn.getCellData(index).toString());
        birthday.setText(birthdayColumn.getCellData(index).toString());
        address.setText(addressColumn.getCellData(index).toString());
        username.setText(usernameColumn.getCellData(index).toString());
        email.setText(emailColumn.getCellData(index).toString());
        password.setText(passwordColumn.getCellData(index).toString());
        major.setText(majorColumn.getCellData(index).toString());
        placeOfBirth.setText(placeOfBirthColumn.getCellData(index).toString());
        intake.setText(intakeColumn.getCellData(index).toString());
        score.setText(scoreColumn.getCellData(index).toString());

    }


    public void modify() {
        if (username.getText().equals("")) return;
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyScore.fxml"));
//            root = FXMLLoader.load(getClass().getResource("ScoreWindow.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            ModifyScore controller = loader.getController();
            controller.getuserName(username.getText(),tusername);
            window.initStyle(StageStyle.UNDECORATED);
            disableButton(true);
            window.showAndWait();
            disableButton(false);
            updateTable();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void score(ActionEvent event) {
        if (username.getText().equals("")) return;
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreWindow.fxml"));
//            root = FXMLLoader.load(getClass().getResource("ScoreWindow.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            ScoreWindowController controller = loader.getController();
            controller.setUserName(currentUserName);
            window.centerOnScreen();
            window.initStyle(StageStyle.UNDECORATED);
            disableButton(true);
            window.showAndWait();
            disableButton(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void search() {
        String new_name = name.getText();
        String new_gender = gender.getText();
        String new_birthday = birthday.getText();
        String new_address = address.getText();
        String new_username = username.getText();
        String new_email = email.getText();
        String new_password = password.getText();
        String new_major = major.getText();
        String new_placeOfBirth = placeOfBirth.getText();
        String new_intake = intake.getText();
        String new_score = score.getText();
        String sql = "SELECT * FROM student join score on (student.username = score.username) WHERE score.tusername = '" + tusername + "'";
        int i = 0;

        if (!name.getText().equals("")) {
            sql += " AND Student.Name = '" + new_name + "' ";
            i = 1;
        }

        if (!gender.getText().equals("")) {
            if (!checkGender(gender.getText())) return;
            sql += "AND Student.Gender = '" + new_gender + "' ";
            i = 1;
        }

        if (!birthday.getText().equals("")) {
            Checkdate(birthday.getText());
            sql += "AND Student.Birthday = '" + new_birthday + "' ";
            i = 1;
        }

        if (!address.getText().equals("")) {
            sql += "AND Student.Address = '" + new_address + "' ";
            i = 1;
        }

        if (!username.getText().equals("")) {
            sql += "AND Student.Username = '" + new_username + "' ";
            i = 1;
        }

        if (!email.getText().equals("")) {
            sql += "AND Student.Email = '" + new_email + "' ";
            i = 1;
        }

        if (!password.getText().equals("")) {
            sql += "AND Student.Password = '" + new_password + "' ";
            i = 1;
        }

        if (!major.getText().equals("")) {
            sql += "AND Student.Major = '" + new_major + "' ";
            i = 1;
        }

        if (!placeOfBirth.getText().equals("")) {
            sql += "AND Student.PlaceOfBirth = '" + new_placeOfBirth + "' ";
            i = 1;
        }

        if (!intake.getText().equals("")) {
            checkInt(intake.getText());
            sql += "AND Student.Intake = " + new_intake;
            i = 1;
        }
        if (!score.getText().equals("")) {
            if (!isScore(score.getText())) return;
            sql += "AND Score.score = " + new_score ;
            i = 1;

        }
        if (i == 1) {
            sql += ";";

            ObservableList<StudentInfo> students = FXCollections.observableArrayList();


            try {
                String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";
                Connection connection = DriverManager.getConnection(url, "sa", "123");

                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);


                while (result.next()) {
                    String Name = result.getString("Name");
                    String Gender = result.getString("Gender");
                    String BirthDay = result.getString("BirthDay");
                    String Address = result.getString("Address");
                    String Username = result.getString("Username");
                    String Email = result.getString("Email");
                    String PassWord = result.getString("Password");
                    String Major = result.getString("Major");
                    String PlaceOfBirth = result.getString("PlaceOfBirth");
                    int Intake = result.getInt("Intake");
                    Float score = result.getFloat("score");
                    students.add(new StudentInfo(Name, Gender, BirthDay, Address, Username, Email, PassWord, Major, PlaceOfBirth, Intake, score));
                }

                tableOfStudent.setItems(students);

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            updateTable();
        }
    }
    public void Logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setTitle("Login");
        window.show();

    }
    public void changePass(ActionEvent event) throws IOException {
        GridPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePass.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            //ScoreWindowController controller = loader.getController();
            window.setTitle("Change Password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Graph(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Graph.fxml"));
//            root = FXMLLoader.load(getClass().getResource("ScoreWindow.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            Graph controller = loader.getController();
            controller.setRangeScore(rangeScore, window);
            window.centerOnScreen();
            window.setTitle("Score chart");
            disableButton(true);
            window.showAndWait();
            disableButton(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Blank(ActionEvent event){
        name.setText("");
        gender.setText("");
        birthday.setText("");
        address.setText("");
        username.setText("");
        email.setText("");
        password.setText("");
        major.setText("");
        intake.setText("");
        score.setText("");
        placeOfBirth.setText("");
    }

    public void setStatus(String tusername) {
        this.tusername = tusername;
        updateTable();
        //getStudentInfo(username);
    }
    public void Checkdate(String ngay){
        try {
            Date date = new SimpleDateFormat("YYYY-MM-DD").parse(ngay);
        } catch (ParseException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot execute function");
            alert.setContentText("Please insert the right format for date (YYYY-MM-DD)");
            alert.showAndWait();
        }

    }
    public  boolean isScore(String i) {
        try {
            float f = Float.parseFloat(i);
            if ((f > 4 || f < 1) && (f != 5)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Cannot execute function");
                alert.setContentText("Please insert score in range [1..4] or 5");
                alert.showAndWait();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot execute function");
            alert.setContentText("Please insert the right format for score (float)");
            alert.showAndWait();
        }
        return true;
    }
    public void checkInt(String i) {
        try {
            int d = Integer.parseInt(i);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot execute function");
            alert.setContentText("Please insert the right format for year (integer)");
            alert.showAndWait();
        }
    }

    public boolean checkGender(String g) {
        if (!g.equals("male") && !g.equals("female") && !g.equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot execute function");
            alert.setContentText("Please insert the right format for gender (male/female)");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public boolean confirmation(String s){
        Alert alert = new Alert(AlertType.CONFIRMATION, "Confirm " + s + "? (old data will be lost after " + s + ")", ButtonType.YES, ButtonType.NO);
        disableButton(true);
        alert.showAndWait();
        disableButton(false);
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
    }
    public void exit() {
        System.exit(0);
    }
}

