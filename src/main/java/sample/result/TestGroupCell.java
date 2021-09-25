package sample.result;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.entity.TestResult;

/**
 * Created by karpenko on 23.09.2021
 * Description:
 */
public class TestGroupCell extends AbstractResultCell<TestResult> {

    @FXML
    AnchorPane rootPane;
    @FXML
    Label lblText;

    @Override
    protected void updateItem(TestResult item, boolean b) {
        super.updateItem(item, b);
    }
}
