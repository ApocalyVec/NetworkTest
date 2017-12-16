package CS;

import Controller.ServerController;
import javafx.util.Duration;
import sun.awt.windows.ThemeReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TestServer extends Thread{
    private ServerSocket serverSocket;
    ServerController parent;


    public TestServer(int port, ServerController parent) throws IOException{
        this.parent = parent;
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(Integer.MAX_VALUE);
    }

    public void run() {
        parent.setMessage(("Waiting for client on port " +
                serverSocket.getLocalPort() + "..."));
        while(true) {
            try{
                Socket server = serverSocket.accept();

                parent.setMessage("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                parent.setMessage(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nMessage reveived");
                server.close();
            }catch (SocketTimeoutException e) {
                System.out.println("Socket Timed out!");
                break;
            }catch (IOException e) {
                e.printStackTrace();
                parent.setMessage(e.getMessage());
            }
        }
    }
}
