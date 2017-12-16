package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainWindowController {

    @FXML private AnchorPane content;

    public MainWindowController() {

    }

    @FXML
    protected void initialize() {}

    @FXML
    private void onServerClicked() throws IOException{
        this.content.getChildren().clear();
        FXMLLoader serverLoader = new FXMLLoader(getClass().getResource("/view/ServerView.fxml"));
        serverLoader.setController(new ServerController(this));
        Node serverView = serverLoader.load();
        this.content.getChildren().add(serverView);
    }

    @FXML
    private void onClientClicked() throws IOException{
        this.content.getChildren().clear();
        FXMLLoader clientLoader = new FXMLLoader(getClass().getResource("/view/ClientView.fxml"));
        clientLoader.setController(new ClientController(this));
        Node serverView = clientLoader.load();
        this.content.getChildren().add(serverView);
    }
}
