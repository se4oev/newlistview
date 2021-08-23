package sample.result;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CodesetPopupGraphic extends HBox {
    private final Label label;

    public CodesetPopupGraphic(String text) {
        label = new Label(text);
        label.setWrapText(true);
        this.getChildren().add(label);
    }

    public String getText() {
        return label.getText();
    }
}
