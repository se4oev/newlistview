package sample.result;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import sample.TestResult;
import sample.TestResultLoader;
import sample.ValueType;

/**
 *
 * @author hemant-pc
 */
public class ListViewExample implements Initializable {

    @FXML
    private ListView listView;

    @FXML
    Pane pane;

    public ListViewExample() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();

        pane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F5) {
                refreshAll();
            }
        });

        listView.addEventFilter(IResultEvents.RESULT_CHANGED, e -> saveResult(e.getData()));
        listView.addEventFilter(IResultEvents.NEXT_CELL, e -> nextCell());
    }

    private <T extends Event> void nextCell() {
        listView.getSelectionModel().selectNext();
    }

    private <T extends Event> void saveResult(TestResult t) {
        System.out.println(t.getValue());
    }

    private void loadData() {
        try {
            ObservableList<Pane> list = FXCollections.observableArrayList();
            for (int i = 1; i <= 10; i++) {
                TestResult testResult = new TestResultLoader().getTestResult();
                String className = "";
                if (testResult.getValueType() == ValueType.LIST) {
                    className = "ListItem.fxml";
                } else if (testResult.getValueType() == ValueType.FIX_LIST) {
                    className = "FixItem.fxml";
                } else if (testResult.getValueType() == ValueType.NUM) {
                    className = "NumItem.fxml";
                } else if (testResult.getValueType() == ValueType.TEXT) {
                    className = "TextItem.fxml";
                }
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(className));
                Pane listItem = fxmlLoader.load();
                AbstractItem controller = fxmlLoader.getController();
                controller.setText("List Item " + i);
                controller.setData(testResult);
                list.add(listItem);

            }
            listView.getItems().addAll(list);
        } catch (IOException ex) {
            Logger.getLogger(ListViewExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshAll() {
        listView.getItems().clear();
        loadData();
    }



}