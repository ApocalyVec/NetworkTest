package Main;

import CS.TestServer;
import Controller.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerMain extends Application {


    @Override
    public void start(Stage serverStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("view/ServerView.fxml"));
        serverStage.setTitle("Server Window");

        Scene mainScene = new Scene(root, 600, 500);

        serverStage.setScene(mainScene);
        serverStage.show();
    }

    public static void main(String [] args) {
        launch(args);

        int port = Integer.parseInt(args[0]);
        try{
            Thread t = new TestServer(port);
            t.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
