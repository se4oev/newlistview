package sample.result;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import sample.TestResult;
import sample.common.AppUtils;
import sample.common.BaseEvent;
import sample.images.AppImages;

public class ListItem extends AbstractItem {

    @FXML private AnchorPane rootPane;
    @FXML private ImageView validationImage;
    @FXML private ImageView reportImage;
    @FXML private Label textLabel;
    @FXML private Label executorLabel;
    @FXML private Label unitsLabel;
    @FXML private Label normLabel;
    @FXML private TextArea valueField;
    @FXML private Button btnTemplate;
    @FXML private TextArea noteField;
    @FXML private ImageView pathologyImage;

    private CodesetPopupField codesetPopupField;

    @FXML
    void initialize() {

    }

    protected void fillData(TestResult result) {
        valueField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    onEnter();
                    keyEvent.consume();
                }
            }
        });
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
        codesetPopupField = new CodesetPopupField(valueField, btnTemplate, result.getTemplateList(), this, result);

//        cellSetting(result);
        Platform.runLater(this::updateHeight);
    }

    private void onEnter() {
        if (hasChanged()) {
            fireEvent(new BaseEvent<>(this, IResultEvents.RESULT_CHANGED, getItem()));
            fireEvent(new BaseEvent<>(this, IResultEvents.NEXT_CELL));
        } else {
            fireEvent(new BaseEvent<>(this, IResultEvents.NEXT_CELL));
            System.out.println(this.getParent());
        }
    }

    private boolean hasChanged() {
        if (getItem().getValue().equals(valueField.getText())) {
            return false;
        } else {
            return true;
        }
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