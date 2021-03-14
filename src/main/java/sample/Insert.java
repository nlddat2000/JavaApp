package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Insert {

    @FXML TextField insertName;
    @FXML TextField insertGender;
    @FXML TextField insertBirthday;
    @FXML TextField insertAddress;
    @FXML TextField insertUsername;
    @FXML TextField insertEmail;
    @FXML PasswordField insertPassword;
    @FXML TextField insertMajor;
    @FXML TextField insertPlaceOfBirth;
    @FXML TextField insertIntake;
    @FXML Button confirmButton;
    public void confirm() {
        System.out.println("Student");
    }
}
