package ClientSide;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientSender implements Runnable{
    Socket socket;
    //Scanner sendM;
    PrintWriter out;
    DataOutputStream ds;
    ClientSender(Client client) throws IOException {
        //sendM = new Scanner(System.in);
        this.socket = client.socket;
        out = new PrintWriter(socket.getOutputStream());
        ds = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {

    }
    public void sendMessage(List<String> message){
        //
        message.add(0,DataHandler.getInstance().getToken());
        //
        out.println(message);
        out.flush();
    }

}
