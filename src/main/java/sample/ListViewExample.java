package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.scene.layout.Pane;

/**
 *
 * @author hemant-pc
 */
public class ListViewExample implements Initializable {

    @FXML
    private ListView listView;

    @FXML
    Pane pane;

    public ListViewExample() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Pane> list = FXCollections.observableArrayList();
            for (int i = 1; i <= 10; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListItem.fxml"));
                Pane listItem = fxmlLoader.load();
                ListItemController controller = fxmlLoader.getController();
                controller.setText("List Item " + i);
                list.add(listItem);
            }
            listView.getItems().addAll(list);
        } catch (IOException ex) {
            Logger.getLogger(ListViewExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
