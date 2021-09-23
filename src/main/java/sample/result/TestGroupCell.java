package sample.result;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.entity.TestITestResult;

/**
 * Created by karpenko on 23.09.2021
 * Description:
 */
public class TestGroupCell extends AbstractResultCell {

    @FXML
    AnchorPane rootPane;
    @FXML
    Label lblText;

    @Override
    protected void fillData(TestITestResult result) {
        lblText.setText(result.getText());
    }
}
