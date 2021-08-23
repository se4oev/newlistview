package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;


public class Sample {

    @FXML
    private AnchorPane listViewContainer;

    @FXML
    void initialize() {
        ListView<TestResult> listView = new ListView<>();
    }
}
