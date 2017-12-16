import sun.awt.windows.ThemeReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TestServer extends Thread{
    private ServerSocket serverSocket;

    public TestServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000000000);
    }

    public void run() {
        while(true) {
            try{
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "…");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Than you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
                server.close();
            }catch (SocketTimeoutException e) {
                System.out.println("Socket Timed out!");
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String [] args) {
        int port = Integer.parseInt(args[0]);
        try{
            Thread t = new TestServer(port);
            t.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
