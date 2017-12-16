package Controller;

import CS.TestServer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.io.IOException;

public class ServerController {

    private MainWindowController parent;
    @FXML
    JFXTextField tfMsg;
    @FXML
    JFXTextField tfPort;
    @FXML
    JFXButton btConnect;


    public ServerController(MainWindowController parent) {
        this.parent = parent;
    }

    @FXML
    protected void initialize() {
        btConnect.setDefaultButton(true);
        this.tfMsg.setEditable(false);
        this.tfMsg.setVisible(false);
        tfPort.requestFocus();
    }

    @FXML
    private void onConnectClicked() {
        if(tfPort.getText() == null) {
            this.tfPort.setPromptText("You must enter a port number");
        }
        try{
            Thread t = new TestServer(Integer.parseInt(tfPort.getText()), this);
            t.start();
        }catch (IOException e) {
            e.printStackTrace();
            this.tfPort.setPromptText(e.getMessage());
            return;
        }
        this.tfPort.setVisible(false);
        btConnect.setDisable(true);
        this.btConnect.setVisible(false);
        this.tfMsg.setVisible(true);
    }

    public void setMessage(String msg) {
        this.tfMsg.setText(msg);
    }
}
