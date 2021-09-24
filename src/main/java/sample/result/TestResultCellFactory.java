package sample.result;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Created by karpenko on 23.09.2021
 * Description:
 */
public interface TestResultCellFactory<T> extends Callback<ListView<T>, ListCell<T>> {

    default ListCell<T> call(ListView<T> param) {
        return new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(item != null ? createGraphics(item) : null);
            }
        };
    }

    Node createGraphics(T item);
}
