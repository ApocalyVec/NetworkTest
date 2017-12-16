package Controller;

import CS.TestServer;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.io.IOException;

public class ServerController {

    private MainWindowController parent;
    @FXML
    JFXTextField tfMsg;
    @FXML
    JFXTextField tfPort;


    public ServerController(MainWindowController parent) {
        this.parent = parent;
    }

    @FXML
    protected void initialize() {
        this.tfMsg.setEditable(false);
        this.tfMsg.setDisable(true);
        tfPort.requestFocus();
    }

    @FXML
    private void onConnectClicked() {
        this.tfMsg.setDisable(false);
        if(tfPort.getText() == null) {
            this.tfPort.setPromptText("You must enter a port number");
            this.tfMsg.setDisable(true);
        }
        try{
            Thread t = new TestServer(Integer.parseInt(tfPort.getText()), this);
            t.start();
        }catch (IOException e) {
            e.printStackTrace();
            this.tfPort.setPromptText(e.getMessage());
            this.tfMsg.setDisable(true);
        }
    }

    public void setMessage(String msg) {
        this.tfMsg.setText(msg);
    }
}
