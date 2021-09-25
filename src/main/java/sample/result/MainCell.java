package sample.result;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sample.entity.ResultType;
import sample.entity.TestResult;

import java.io.IOException;

/**
 * Created by karpenko on 25.09.2021
 * Description:
 */
public class MainCell extends AnchorPane {

    public MainCell() {

    }

    public void fillData(TestResult item) {
        if (item != null) {
            ResultType resultType = item.getResultType();
            AbstractResultCell<TestResult> cell = createCell(resultType);
            cell.updateItem(item, false);
            cell.fillData(item);
        }
    }

    private AbstractResultCell<TestResult> createCell(ResultType resultType) {
        if (resultType == ResultType.COUNTER) {
            return createCounterCell();
        }
        if (resultType == ResultType.TEXT) {
            return createTextCell();
        }
        if (resultType == ResultType.LIST) {
            return createListCell();
        }
        if (resultType == ResultType.NUM) {
            return createNumCell();
        }
        if (resultType == ResultType.FIX_LIST) {
            return createFixCell();
        }
        if (resultType == ResultType.TEST_GROUP) {
            return createTestGroup();
        }
        return null;
    }

    private AbstractResultCell<TestResult> createListCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListResultCell.fxml"));
        AnchorPane pane = new AnchorPane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(pane);
        return loader.getController();
    }

    private AbstractResultCell<TestResult> createNumCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NumResultCell.fxml"));
        AnchorPane pane = new AnchorPane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(pane);
        return loader.getController();
    }

    private AbstractResultCell<TestResult> createFixCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FixResultCell.fxml"));
        AnchorPane pane = new AnchorPane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(pane);
        return loader.getController();
    }

    private AbstractResultCell<TestResult> createTestGroup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestGroupCell.fxml"));
        AnchorPane pane = new AnchorPane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(pane);
        return loader.getController();
    }

    private AbstractResultCell<TestResult> createTextCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TextResultCell.fxml"));
        AnchorPane pane = new AnchorPane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(pane);
        return loader.getController();
    }

    private AbstractResultCell<TestResult> createCounterCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CounterResultCell.fxml"));
        AnchorPane pane = new AnchorPane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(pane);
        return loader.getController();
    }
}
