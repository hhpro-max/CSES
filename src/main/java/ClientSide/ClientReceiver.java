package ClientSide;

import Pages.ConnectionStatusPage;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

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
            try {
                String input = in.nextLine();


                //todo delete sout
                System.out.println(input);

                List<String> serverOrder = castToList(input);


                DataHandler.getInstance().analyzeOrder(serverOrder);
            }catch (NoSuchElementException noSuchElementException){
                noSuchElementException.printStackTrace();
                JOptionPane.showMessageDialog(null,"CONNECTION LOST!");
                ConnectionStatusPage.isConnected = false;
                break;
            }

        }
    }

    private ArrayList<String> castToList(String msg) {
        String r1 = msg.replace("[","");
        String r2 = r1.replace("]","");
        return new ArrayList<String>(Arrays.asList(r2.split(", ")));
    }

}
