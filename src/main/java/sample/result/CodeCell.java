package sample.result;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sample.entity.ResultType;
import sample.entity.TestResult;

/**
 * Created by karpenko on 25.09.2021
 * Description:
 */
public class CodeCell extends AnchorPane {

    private AnchorPane rootPane = new AnchorPane();

    public CodeCell() {

    }

    public void fillData(TestResult item) {
        if (item != null) {
            ResultType resultType = item.getResultType();
            rootPane = createCell(resultType);
        }
    }

    private AnchorPane createCell(ResultType resultType) {
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

    private AnchorPane createTestGroup() {
        rootPane.setPrefWidth(410.0);
        rootPane.setMinWidth(410.0);
        rootPane.setPrefHeight(44.0);
        rootPane.setMinHeight(44.0);
        BorderPane pane = new BorderPane();
        Label label = new Label("Группа тестов");
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        pane.setCenter(label);
        rootPane.getChildren().add(pane);
        getChildren().add(rootPane);
        return rootPane;
    }

    private AnchorPane createFixCell() {
        rootPane.setPrefWidth(410.0);
        rootPane.setMinWidth(410.0);
        rootPane.setPrefHeight(88.0);
        rootPane.setMinHeight(88.0);
        BorderPane pane = new BorderPane();
        Label label = new Label("Ячейка с фиксированным списком");
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        pane.setCenter(label);
        rootPane.getChildren().add(pane);
        getChildren().add(rootPane);
        return rootPane;
    }

    private AnchorPane createNumCell() {
        rootPane.setPrefWidth(410.0);
        rootPane.setMinWidth(410.0);
        rootPane.setPrefHeight(88.0);
        rootPane.setMinHeight(88.0);
        BorderPane pane = new BorderPane();
        Label label = new Label("Ячейка с номером");
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        pane.setCenter(label);
        rootPane.getChildren().add(pane);
        getChildren().add(rootPane);
        return rootPane;
    }

    private AnchorPane createListCell() {
        rootPane.setPrefWidth(410.0);
        rootPane.setMinWidth(410.0);
        rootPane.setPrefHeight(88.0);
        rootPane.setMinHeight(88.0);
        BorderPane pane = new BorderPane();
        Label label = new Label("Ячейка с листом");
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        pane.setCenter(label);
        rootPane.getChildren().add(pane);
        getChildren().add(rootPane);
        return rootPane;
    }

    private AnchorPane createTextCell() {
        rootPane.setPrefWidth(410.0);
        rootPane.setMinWidth(410.0);
        rootPane.setPrefHeight(88.0);
        rootPane.setMinHeight(88.0);
        BorderPane pane = new BorderPane();
        Label label = new Label("Ячейка с текстом");
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        pane.setCenter(label);
        rootPane.getChildren().add(pane);
        getChildren().add(rootPane);
        return rootPane;
    }

    private AnchorPane createCounterCell() {
        rootPane.setPrefWidth(410.0);
        rootPane.setMinWidth(410.0);
        rootPane.setPrefHeight(88.0);
        rootPane.setMinHeight(88.0);
        BorderPane pane = new BorderPane();
        Label label = new Label("Ячейка с счетчиком");
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        pane.setCenter(label);
        rootPane.getChildren().add(pane);
        getChildren().add(rootPane);
        return rootPane;
    }
}
