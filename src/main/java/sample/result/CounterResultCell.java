package sample.result;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.common.AppUtils;
import sample.entity.TestResult;
import sample.images.AppImages;

public class CounterResultCell extends AbstractResultCell<TestResult> {

    @FXML private AnchorPane rootPane;
    @FXML private ImageView validationImage;
    @FXML private ImageView reportImage;
    @FXML private Label textLabel;
    @FXML private Label executorLabel;
    @FXML private Label unitsLabel;
    @FXML private Label normLabel;
    @FXML private TextArea valueField;
    @FXML private TextArea noteField;
    @FXML private ImageView pathologyImage;

    private TestResult result;

    @FXML
    void initialize() {

    }

    protected void fillData(TestResult result) {

        textLabel.setText(result.getText());
        normLabel.setText(result.getNormText());
        unitsLabel.setText(result.getUnits());
        noteField.setText("");
        updateStatusImage();
        executorLabel.setText(result.getExecutorText());

        setPathologyStatus(result.getPathologyStatus());
        setEditable(result.isEditable());

//        menu.setHistory(result.getHistory());

        valueField.setText(result.getValue());

//        cellSetting(result);
        Platform.runLater(this::updateHeight);
    }

    private void updateStatusImage() {
        boolean isSuppressed = getItem().getSuppressStatus() == 1;
        boolean isValidated = getItem().getValidationStatus() == 1;
        boolean isReported = getItem().getReportStatus() == 1;

        reportImage.setVisible(!isSuppressed && isReported);

        validationImage.setVisible(!isSuppressed && !isReported && (isValidated || getItem().isEditable()));
        validationImage.setImage(AppImages.getValidationImage(getItem().getValidationStatus()));
        validationImage.setMouseTransparent(!getItem().isEditable());
        Tooltip tooltip = new Tooltip("Валидировать показатель");
        Tooltip.install(validationImage, tooltip);
    }

    private void setPathologyStatus(int status) {
        pathologyImage.setImage(AppImages.createPathologyImage(status));
        Tooltip.install(pathologyImage, new Tooltip(String.valueOf(status)));
    }

    private void updateHeight() {
        updateHeight(valueField);
        updateHeight(noteField);
//        rootPane.setPrefHeight(200);
    }

    private void updateHeight(TextArea textArea) {
        String text = textArea.getText();
        double height = AppUtils.getTextHeight(text, textArea.getWidth() - 30);
        textArea.setPrefHeight(height + 14);
    }
}
