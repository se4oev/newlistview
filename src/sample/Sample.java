package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Sample {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane listViewContainer;

    @FXML
    void initialize() {
        assert listViewContainer != null : "fx:id=\"listViewContainer\" was not injected: check your FXML file 'Sample.fxml'.";

    }
}
