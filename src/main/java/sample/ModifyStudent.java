package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyStudent {
    @FXML TextField name;
    @FXML TextField Gander;
    @FXML TextField birthday;
    @FXML TextField address;
    @FXML TextField email;
    @FXML TextField placeofbirth;
    @FXML Button Confirm;
    @FXML Button Cancel;
    public void cancel(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    public void confirm(ActionEvent event) throws IOException {

    }

}
