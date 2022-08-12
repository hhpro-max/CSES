package ClientSide;

import Pages.GuiController;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{
    Socket socket;
    private static String token;
    int port = ClientConfig.port;
    String host = ClientConfig.host;
    ClientSender clientSender;
    ClientReceiver clientReceiver;
    public boolean isConnected;

    public void init() throws IOException {
        socket = new Socket(host,port);
        clientSender = new ClientSender(this);
        clientReceiver = new ClientReceiver(this);
        new Thread(clientReceiver).start();
        new Thread(clientSender).start();
        isConnected = true;
        GuiController.getInstance().setClient(this);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Client.token = token;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ClientSender getClientSender() {
        return clientSender;
    }

    public void setClientSender(ClientSender clientSender) {
        this.clientSender = clientSender;
    }

    public ClientReceiver getClientReceiver() {
        return clientReceiver;
    }

    public void setClientReceiver(ClientReceiver clientReceiver) {
        this.clientReceiver = clientReceiver;
    }

    @Override
    public void run() {
        try {
            init();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("SERVER IS OFF!");
        }
        //todo retry to connect to server
    }
}
