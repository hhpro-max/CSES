package ClientSide;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
            DataHandler.getInstance().analyzeOrder(serverOrder);
            //todo delete sout
            System.out.println(serverOrder.toString());
        }
    }

    private ArrayList<String> castToList(String msg) {
        String r1 = msg.replace("[","");
        String r2 = r1.replace("]","");
        return new ArrayList<String>(Arrays.asList(r2.split(", ")));
    }

}
