package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.entity.ResultType;
import sample.entity.TestResult;
import sample.result.IResultEvents;
import sample.result.TestResultCellFactory;
import sample.utils.TestResultLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPane implements Initializable {

    @FXML
    private ListView<TestResult> resultList;

    @FXML
    Pane pane;

    public MainPane() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        resultList.setCellFactory((TestResultCellFactory<TestResult>) this::initCell);

        loadData();
    }

    private Node initCell(TestResult item) {
        ResultType resultType = item.getResultType();
        String className = "";
        if (resultType == ResultType.LIST) {
            className = "result/ListItem.fxml";
        } else if (resultType == ResultType.FIX_LIST) {
            className = "result/FixItem.fxml";
        } else if (resultType == ResultType.NUM) {
            className = "result/NumItem.fxml";
        } else if (resultType == ResultType.TEXT) {
            className = "result/TextItem.fxml";
        } else if (resultType == ResultType.TEST_GROUP) {
            className = "result/TestGroup.fxml";
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(className));
        Pane pane = new Pane();
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    private void selectItem(ListView<TestResult> listView, boolean b) {
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

    private <T extends Event> void saveResult(TestResult t) {
        System.out.println(t.getValue());
    }

    private void loadData() {
        ObservableList<TestResult> list = FXCollections.observableArrayList();
        for (int i = 1; i <= 20; i++) {
            TestResult testResult = new TestResultLoader().getTestResult();
            list.add(testResult);
        }
        resultList.setItems(list);
    }

    private void refreshAll() {
        resultList.getItems().clear();
        loadData();
    }



}
