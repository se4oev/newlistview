package sample.result;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.entity.ResultType;
import sample.entity.TestResult;
import sample.images.AppImages;

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
            createCell(resultType);
        }
    }

    private void createCell(ResultType resultType) {
        if (resultType == ResultType.COUNTER) {
            createCounterCell();
        }
        if (resultType == ResultType.TEXT) {
            createTextCell();
        }
        if (resultType == ResultType.LIST) {
            createListCell();
        }
        if (resultType == ResultType.NUM) {
            createNumCell();
        }
        if (resultType == ResultType.FIX_LIST) {
            createFixCell();
        }
        if (resultType == ResultType.TEST_GROUP) {
            createTestGroup();
        }
    }

    private void createTextCell() {
        rootPane = new AnchorPane();
        rootPane.setMinWidth(100.0);
        rootPane.setPrefWidth(410.0);
        rootPane.setMinHeight(88.0);
        rootPane.setPrefHeight(88.0);
        rootPane.getStyleClass().add("test-result-row");

        HBox mainBox = createMainBox();
        VBox validationBox = createValidationBox();
        VBox valueBox = createValueBox();
        VBox normBox = createNormBox();

        mainBox.getChildren().addAll(validationBox, valueBox, normBox);
        rootPane.getChildren().add(mainBox);
        getChildren().add(rootPane);
    }

    private VBox createNormBox() {
        VBox normBox = new VBox();
        normBox.setSpacing(2.0);
        normBox.setPadding(new Insets(0, 0, 0, 2));

        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(5.0);
        Label textLabel = new Label("Название показателя");
        Label executorLabel = new Label("[DeviceName]");
        executorLabel.setLayoutX(10.0);
        executorLabel.setLayoutY(10.0);
        headerBox.getChildren().addAll(textLabel, executorLabel);

        BorderPane borderPane = new BorderPane();
        borderPane.setLayoutX(10.0);
        borderPane.setLayoutY(10.0);
        HBox hBox = new HBox();
        hBox.setSpacing(10.0);
        hBox.setPadding(new Insets(10.0));
        BorderPane.setAlignment(hBox, Pos.CENTER);
        Label unitsLabel = new Label("ед/л");
        unitsLabel.setFont(Font.font("System Bold", 14.0));
        Label normLabel = new Label("(0.25 - 0.99)");
        normLabel.setFont(Font.font(14.0));
        normLabel.setPadding(new Insets(5.0));
        hBox.getChildren().addAll(unitsLabel, normLabel);
        borderPane.setRight(hBox);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("buttoned-text-field");
        TextArea valueField = new TextArea();
        valueField.setMinHeight(30.0);
        valueField.setMinWidth(50.0);
        valueField.setPrefHeight(30.0);
        valueField.setPrefRowCount(1);
        valueField.setText("1.65");
        valueField.setWrapText(true);
        AnchorPane.setRightAnchor(valueField, 0.0);
        AnchorPane.setLeftAnchor(valueField, 0.0);
        AnchorPane.setBottomAnchor(valueField, 0.0);
        AnchorPane.setTopAnchor(valueField, 0.0);
        anchorPane.getChildren().add(valueField);
        borderPane.setCenter(anchorPane);

        HBox noteBox = new HBox();
        TextArea noteArea = new TextArea();
        noteArea.setMinHeight(30.0);
        noteArea.setPrefHeight(30.0);
        noteArea.setPrefRowCount(1);
        noteArea.setWrapText(true);
        noteBox.getChildren().add(noteArea);

        normBox.getChildren().addAll(headerBox, borderPane, noteBox);

        return normBox;
    }

    private VBox createValueBox() {
        VBox valueBox = new VBox();

        return valueBox;
    }

    private VBox createValidationBox() {
        VBox validationBox = new VBox();
        validationBox.setAlignment(Pos.CENTER);
        validationBox.setPadding(new Insets(0, 5, 0, 5));

        AnchorPane validationPane = new AnchorPane();
        validationBox.getChildren().add(validationPane);
        ImageView validationImage = new ImageView(AppImages.getValidationImage(1));
        validationImage.setLayoutX(10.0);
        validationImage.setLayoutY(7.0);
        validationImage.setPickOnBounds(true);
        validationImage.setPreserveRatio(true);
        AnchorPane.setBottomAnchor(validationImage, 0.0);
        AnchorPane.setTopAnchor(validationImage, 0.0);
        AnchorPane.setLeftAnchor(validationImage, 0.0);
        AnchorPane.setRightAnchor(validationImage, 0.0);
        validationImage.setCursor(Cursor.HAND);

        ImageView reportImage = new ImageView(AppImages.getValidationImage(1));
        reportImage.setLayoutX(10.0);
        reportImage.setLayoutY(10.0);
        reportImage.setMouseTransparent(true);
        reportImage.setPickOnBounds(true);
        reportImage.setPreserveRatio(true);
        reportImage.setVisible(false);
        AnchorPane.setBottomAnchor(reportImage, 0.0);
        AnchorPane.setTopAnchor(reportImage, 0.0);
        AnchorPane.setLeftAnchor(reportImage, 0.0);
        AnchorPane.setRightAnchor(reportImage, 0.0);
        reportImage.setCursor(Cursor.HAND);

        validationPane.getChildren().addAll(validationImage, reportImage);

        return validationBox;
    }

    private HBox createMainBox() {
        HBox hBox = new HBox();
        AnchorPane.setTopAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setBottomAnchor(hBox, 0.0);
        hBox.setLayoutX(2.0);
        hBox.setLayoutY(2.0);
        return hBox;
    }

    private void createCounterCell() {
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
    }

    private void createTestGroup() {
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
    }

    private void createFixCell() {
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
    }

    private void createNumCell() {
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
    }

    private void createListCell() {
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
    }
}
