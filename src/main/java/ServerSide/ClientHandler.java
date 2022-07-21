package ServerSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    Socket socket;
    PrintWriter out;
    Scanner in;
    private String token;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream());
        in =  new Scanner(socket.getInputStream());
        token = AuthToken.generateNewToken();
        //todo sendtoken for client side
    }

    @Override
    public void run() {
        while (true){
            //todo
            sendMessage("YOU ARE CONNECTED TO THE SERVER!");
            String msgFromClient = in.nextLine();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    private void checkAuthToken(String str) {
        String tokenC = str.split(":")[1];
        if (!tokenC.equals(this.token)){
            sendMessage("WRONG TOKEN!");
            try {
                this.kill();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void kill() throws IOException {
        socket.close();
    }
}
