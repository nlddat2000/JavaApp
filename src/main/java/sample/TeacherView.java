package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

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
    TableColumn<StudentInfo, Integer> accessroleColumn;
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
    TextField accessrole;
    int index = -1;
    private String currentUserName;

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
        accessroleColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, Integer>("Accessrole"));

        tableOfStudent.setItems(getStudents());
    }

    //Show table
    public void initialize() {
        updateTable();
    }

    //Get data from SQLServer
    public static ObservableList<StudentInfo> getStudents() {
        ObservableList<StudentInfo> students = FXCollections.observableArrayList();

        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url, "sa", "123");

            String sql = "SELECT * FROM Student";
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
                int Accessrole = result.getInt("Access_role");

                students.add(new StudentInfo(Name, Gender, BirthDay, Address, Username, Email, PassWord, Major, PlaceOfBirth, Intake, Accessrole));

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
                String sql0 = "DELETE FROM Score WHERE  Username = ('" + studentSelected.get(i).getUsername() + "');";
                String sql = "DELETE FROM Student WHERE  Username = ('" + studentSelected.get(i).getUsername() + "');";
                Statement statement = connection.createStatement();
                System.out.println(sql0+"\n"+sql);
                statement.executeUpdate(sql0);
                statement.executeUpdate(sql);

                connection.close();

                allStudent.remove(studentSelected.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        StudentInfo student = new StudentInfo();
        student.setName(name.getText());
        student.setGender(gender.getText());
        student.setBirthday(birthday.getText());
        student.setAddress(address.getText());
        student.setUsername(username.getText());
        student.setEmail(email.getText());
        student.setPassword(password.getText());
        student.setMajor(major.getText());
        student.setPlaceOfBirth(placeOfBirth.getText());
        student.setIntake(Integer.parseInt(intake.getText()));
        student.setAccessrole(Integer.parseInt(accessrole.getText()));

        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";

            Connection connection = DriverManager.getConnection(url, "sa", "123");

            String sql = "INSERT INTO Student VALUES ('" + student.getName() + "', '" + student.getGender() + "', '" + student.getBirthday() + "', '" + student.getAddress() + "', '" + student.getUsername() + "', '" + student.getEmail() + "', '" + student.getPassword() + "', '" + student.getMajor() + "', '" + student.getPlaceOfBirth() + "', " + student.getIntake() + "," + student.getAccessrole() + ")";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            tableOfStudent.getItems().add(student);
            connection.close();
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Update Error");
            alert.setHeaderText("Failed to update");
            alert.setContentText("Cannot modify username");

            alert.showAndWait();

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
        accessrole.setText(accessroleColumn.getCellData(index).toString());

    }


    public void modify() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info;";
            Connection connection = DriverManager.getConnection(url, "sa", "123");

            if (username.getText().equals(currentUserName)) {
                String new_name = name.getText();
                String new_gender = gender.getText();
                String new_birthday = birthday.getText();
                String new_address = address.getText();
                String new_username = username.getText();
                String new_major = major.getText();
                String new_placeOfBirth = placeOfBirth.getText();
                String new_intake = intake.getText();
                String new_accessrole = accessrole.getText();
                String sql = "UPDATE Student SET Name = '" + new_name + "', Gender = '" + new_gender + "', Birthday = '" + new_birthday + "', Address = '" + new_address + "', Major = '" + new_major + "', PlaceOfBirth = '" + new_placeOfBirth + "', Intake = '" + new_intake +  "', access_role = '" + new_accessrole + "'  WHERE Username = " + "'" + new_username + "'";
                System.out.println(sql);
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);

                updateTable();
            } else {
                System.out.println("Cannot modify username");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Update Error");
                alert.setHeaderText("Failed to update");
                alert.setContentText("Cannot modify username");

                alert.showAndWait();
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void score(ActionEvent event) {
        System.out.println("deja vu");
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreWindow.fxml"));
//            root = FXMLLoader.load(getClass().getResource("ScoreWindow.fxml"));
            root = loader.load();
            Stage window = new Stage();
            window.setScene(new Scene(root));
            ScoreWindowController controller = loader.getController();
            controller.setUserName(currentUserName);

            window.setTitle("Modify personal infomation");
            window.show();

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
        String new_accessrole = accessrole.getText();
        String sql = "SELECT * FROM Student WHERE ";

        int i = 0;

        if (name.getText().equals("") == false) {
            sql += "Name = '" + new_name + "' AND ";
            i = 1;
        }

        if (gender.getText().equals("") == false) {
            sql += "Gender = '" + new_gender + "' AND ";
            i = 1;
        }

        if (birthday.getText().equals("") == false) {
            sql += "Birthday = '" + new_birthday + "' AND ";
            i = 1;
        }

        if (address.getText().equals("") == false) {
            sql += "Address = '" + new_address + "' AND ";
            i = 1;
        }

        if (username.getText().equals("") == false) {
            sql += "Username = '" + new_username + "' AND ";
            i = 1;
        }

        if (email.getText().equals("") == false) {
            sql += "Email = '" + new_email + "' AND ";
            i = 1;
        }

        if (password.getText().equals("") == false) {
            sql += "Password = '" + new_password + "' AND ";
            i = 1;
        }

        if (major.getText().equals("") == false) {
            sql += "Major = '" + new_major + "' AND ";
            i = 1;
        }

        if (placeOfBirth.getText().equals("") == false) {
            sql += "PlaceOfBirth = '" + new_placeOfBirth + "' AND ";
            i = 1;
        }

        if (intake.getText().equals("") == false) {
            sql += "Intake = '" + new_intake + "' AND ";
            i = 1;
        }
        if (accessrole.getText().equals("") == false) {
            sql += "Accessrole = '" + new_accessrole + "' AND ";
            i = 1;
        }
        if (i == 1) {
            sql = sql.substring(0, sql.length() - 5);
            sql += ";";

            ObservableList<StudentInfo> students = FXCollections.observableArrayList();

            System.out.println(sql);

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
                    int Accessrole = result.getInt("Accessrole");
                    students.add(new StudentInfo(Name, Gender, BirthDay, Address, Username, Email, PassWord, Major, PlaceOfBirth, Intake, Accessrole));
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
}

