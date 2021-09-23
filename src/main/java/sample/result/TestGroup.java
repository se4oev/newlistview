package sample.result;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.TestResult;

/**
 * Created by karpenko on 23.09.2021
 * Description:
 */
public class TestGroup extends AbstractItem {

    @FXML
    AnchorPane rootPane;
    @FXML
    Label lblText;

    @Override
    protected void fillData(TestResult result) {
        lblText.setText(result.getText());
    }
}
