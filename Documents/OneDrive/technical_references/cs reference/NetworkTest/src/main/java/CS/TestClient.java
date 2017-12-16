package CS;

import Controller.ClientController;
import Controller.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.*;
import java.io.*;

public class TestClient {
    private Socket clientSocket;
    private ClientController parent;
    private String serverName;
    private int port;

    public TestClient(ClientController clientController, String serverName, int port) throws IOException{
        this.parent = clientController;
        this.serverName = serverName;
        this.port = port;
        clientSocket = new Socket(serverName, port);
        parent.setMsgFromServer("Connecting to " + serverName + " on port " + port);
        parent.setMsgFromServer(("Just connected to " + clientSocket.getRemoteSocketAddress()));
    }

    public void sendMsgToServer(String s) throws IOException{
        if(clientSocket.isClosed()) {
            clientSocket = new Socket(serverName, port);
        }
        try{
            OutputStream outToServer = clientSocket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF(s);
            InputStream inFromServer = clientSocket.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            parent.setMsgFromServer("Server says " + in.readUTF());
            clientSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
            parent.setMsgFromServer(e.getMessage());
        }
    }
}