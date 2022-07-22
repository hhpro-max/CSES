package ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    int port = Config.serverPort;
    static List<ClientHandler> clients;
    public Server(){
        clients = new ArrayList<>();
        init();
    }

    private void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("SERVER IS RUNNING...");
            while (true){
                Socket socket = serverSocket.accept();
                addNewClient(socket);
                System.out.println("====> There are " + clients.size() + " clients on the server!");
            }
        }catch (Exception e){

        }
    }
    public void addNewClient(Socket socket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(socket);
        System.out.println("New connection accepted!");
        clients.add(clientHandler);
        new Thread(clientHandler).start();
    }

}

