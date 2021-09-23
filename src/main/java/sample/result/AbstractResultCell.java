package sample.result;

import javafx.scene.control.ListCell;
import sample.entity.TestITestResult;

public abstract class AbstractResultCell extends ListCell<TestITestResult> {

    public void setData(TestITestResult result) {
        setItem(result);
        this.fillData(result);
    }

    protected abstract void fillData(TestITestResult result);

}
