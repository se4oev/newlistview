package sample;

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
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.entity.TestITestResult;
import sample.entity.ResultType;
import sample.result.AbstractResultCell;
import sample.result.IResultEvents;
import sample.utils.TestResultLoader;

public class MainPane implements Initializable {

    @FXML
    private ListView resultList;

    @FXML
    Pane pane;

    public MainPane() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();

        pane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F5) {
                refreshAll();
            }
        });

        resultList.addEventFilter(IResultEvents.RESULT_CHANGED, e -> saveResult(e.getData()));
        resultList.addEventFilter(IResultEvents.NEXT_CELL, e -> nextCell());

        pane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            e.consume();
            if (e.getCode() == KeyCode.TAB) {
                selectItem(resultList, !e.isShiftDown());
            }
        });
    }

    private void selectItem(ListView listView, boolean b) {
        if (b) {
            int oldIndex = listView.getSelectionModel().getSelectedIndex();
            int newIndex = oldIndex + 1;
            VirtualFlow flow = (VirtualFlow) listView.lookup(".virtual-flow");
            int firstCell = flow.getFirstVisibleCell().getIndex();
            int lastCell = flow.getLastVisibleCell().getIndex();
            listView.getSelectionModel().clearSelection();
            listView.getSelectionModel().select(newIndex);
            if (newIndex < firstCell || newIndex > lastCell) {
                listView.scrollTo(newIndex);
            }
        } else {
            int oldIndex = listView.getSelectionModel().getSelectedIndex();
            int newIndex = oldIndex - 1;
            VirtualFlow flow = (VirtualFlow) listView.lookup(".virtual-flow");
            int firstCell = flow.getFirstVisibleCell().getIndex();
            int lastCell = flow.getLastVisibleCell().getIndex();
            listView.getSelectionModel().clearSelection();
            listView.getSelectionModel().select(newIndex);
            if (newIndex < firstCell || newIndex > lastCell) {
                listView.scrollTo(newIndex);
            }
        }
    }

    private <T extends Event> void nextCell() {
        resultList.getSelectionModel().selectNext();
    }

    private <T extends Event> void saveResult(TestITestResult t) {
        System.out.println(t.getValue());
    }

    private void loadData() {
        try {
            ObservableList<Pane> list = FXCollections.observableArrayList();
            for (int i = 1; i <= 20; i++) {
                TestITestResult testResult = new TestResultLoader().getTestResult();
                String className = "";
                if (testResult.getResultType() == ResultType.LIST) {
                    className = "ListItem.fxml";
                } else if (testResult.getResultType() == ResultType.FIX_LIST) {
                    className = "FixItem.fxml";
                } else if (testResult.getResultType() == ResultType.NUM) {
                    className = "NumItem.fxml";
                } else if (testResult.getResultType() == ResultType.TEXT) {
                    className = "TextItem.fxml";
                } else if (testResult.getResultType() == ResultType.TEST_GROUP) {
                    className = "TestGroup.fxml";
                }
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(className));
                Pane listItem = fxmlLoader.load();
                AbstractResultCell controller = fxmlLoader.getController();
                controller.setText("List Item " + i);
                controller.setData(testResult);
                list.add(listItem);

            }
            resultList.getItems().addAll(list);
        } catch (IOException ex) {
            Logger.getLogger(MainPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshAll() {
        resultList.getItems().clear();
        loadData();
    }



}
