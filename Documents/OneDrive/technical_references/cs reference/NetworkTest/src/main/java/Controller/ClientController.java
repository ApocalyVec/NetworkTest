package Controller;

import CS.TestClient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientController {
    private MainWindowController parent;
    @FXML private JFXButton btSend;
    @FXML private JFXButton btConnect;
    @FXML private JFXTextField tfServerName;
    @FXML private JFXTextField tfServerPort;
    @FXML private JFXTextField tfMsgFromServer;
    @FXML private JFXTextField tfMsgToServer;
    @FXML private Label lbMessage;

    private TestClient testClient;

    public ClientController(MainWindowController parent) {

    }

    @FXML
    protected void initialize() {
        this.parent = parent;
        this.tfMsgFromServer.setEditable(false);

        this.tfMsgToServer.setVisible(false);
        this.btSend.setVisible(false);
        this.lbMessage.setVisible(false);
    }

    @FXML
    private void OnConnectClicked() {
        if(tfServerName.getText() == null) {
            tfServerName.setPromptText("Server Name required");
            return;
        }
        if(tfServerPort.getText() == null) {
            tfServerPort.setPromptText("Server Port required");
            return;
        }
        try{
            this.testClient = new TestClient(this, tfServerName.getText(), Integer.parseInt(tfServerPort.getText()));
        }catch (UnknownHostException e) {
            e.printStackTrace();
            this.tfMsgFromServer.setText("Server does not exit.");
            return;
        }catch (IOException e) {
            e.printStackTrace();
            this.tfMsgFromServer.setText(e.getMessage());
            return;
        }
        tfServerName.setVisible(false);
        tfServerPort.setVisible(false);
        this.btConnect.setVisible(false);
        this.tfMsgToServer.setVisible(true);
        this.btSend.setVisible(true);
        this.lbMessage.setVisible(true);
    }

    public void setMsgFromServer(String string) {
        this.tfMsgFromServer.setText(string);
    }

    @FXML
    private void onSendClicked() {
        if(tfMsgToServer.getText() == null) {
            tfMsgToServer.setPromptText("Please enter the message");
            return;
        }
        try{
            testClient.sendMsgToServer(tfMsgToServer.getText());
        }catch (UnknownHostException e) {
            e.printStackTrace();
            this.tfMsgFromServer.setText("Server does not exit.");
            return;
        }catch (IOException e) {
            e.printStackTrace();
            this.tfMsgFromServer.setText(e.getMessage());
            return;
        }
    }
}
