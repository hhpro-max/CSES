package ClientSide;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientReceiver implements Runnable {
    Socket socket;
    Scanner in;
    Client client;
    ClientReceiver(Client client) throws IOException {
        this.client = client;
        this.socket = client.socket;
        in = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        //todo
        //String[] token = in.nextLine().split(":");
        //Client.setToken(token[1]);
        //System.out.println( "YOUR TOKEN IS : " + Client.getToken() );
        while (true) {
            String input = in.nextLine();
            List<String> serverOrder = castToList(input);
            analyzeOrder(serverOrder);
        }
    }



    private ArrayList<String> castToList(String msg) {
        String r1 = msg.replace("[","");
        String r2 = r1.replace("]","");
        return new ArrayList<String>(Arrays.asList(r2.split(", ")));
    }
    private void analyzeOrder(List<String> order) {
        if (order.get(0).equals(ClientReqType.LOGIN.toString())){
            DataHandler.getInstance().checkLoginRes(order.get(1));
        }
    }
}
