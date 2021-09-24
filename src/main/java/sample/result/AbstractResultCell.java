package sample.result;

import javafx.scene.control.ListCell;

public abstract class AbstractResultCell<T> extends ListCell<T> {

    @Override
    protected void updateItem(T item, boolean b) {
        super.updateItem(item, b);
    }
}
