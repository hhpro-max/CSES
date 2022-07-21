package ClientSide;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{
    Socket socket;
    private static String token;
    int port = ClientConfig.port;
    String host = ClientConfig.host;


    public void init() throws IOException {
        socket = new Socket(host,port);
        ClientSender clientSender = new ClientSender(this);
        ClientReceiver clientReceiver = new ClientReceiver(this);
        new Thread(clientReceiver).start();
        new Thread(clientSender).start();
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

    @Override
    public void run() {
        try {
            init();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("SERVER IS OFF!");
        }
    }
}
