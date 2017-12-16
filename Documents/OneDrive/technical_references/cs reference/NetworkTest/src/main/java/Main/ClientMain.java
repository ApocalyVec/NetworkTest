package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.TestClient;

import java.io.IOException;

public class ClientMain extends Application{

    TestClient

    @Override
    public void start(Stage serverStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("view/ClientView.fxml"));
        serverStage.setTitle("Server Window");

        Scene mainScene = new Scene(root, 600, 500);

        serverStage.setScene(mainScene);
        serverStage.show();
    }

    public static void main(String [] args) {
        launch(args);

    }
}
