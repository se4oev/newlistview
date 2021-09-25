package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.entity.TestResult;
import sample.result.IResultEvents;
import sample.result.MainCell;
import sample.result.TestResultCellFactory;
import sample.utils.TestResultLoader;

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
        MainCell cell = new MainCell();
        cell.fillData(item);
        return cell;
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
