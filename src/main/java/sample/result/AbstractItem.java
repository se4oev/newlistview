package sample.result;

import javafx.scene.control.ListCell;
import sample.TestResult;

public abstract class AbstractItem extends ListCell<TestResult> {

    public void setData(TestResult result) {
        setItem(result);
        this.fillData(result);
    }

    protected abstract void fillData(TestResult result);

}
