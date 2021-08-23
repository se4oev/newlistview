package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Sample.fxml")));
//        primaryStage.setTitle("List View Super New Project");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListViewExample.fxml")));
        primaryStage.setTitle("List View Super New Project");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}