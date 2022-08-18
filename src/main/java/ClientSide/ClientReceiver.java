package ClientSide;

import Pages.GuiController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        while (true) {
            try {
                String input = in.nextLine();

                System.out.println(input);

                List<String> serverOrder = castToList(input);


                DataHandler.getInstance().analyzeOrder(serverOrder);
            }catch (NoSuchElementException noSuchElementException){
                noSuchElementException.printStackTrace();
                JOptionPane.showMessageDialog(null,"CONNECTION LOST!");
                JButton jButton = new JButton("RETRY CONNECTION");
                jButton.setBounds(10,45,150,30);
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Socket socket = new Socket(ClientConfig.host,ClientConfig.port);
                            GuiController.getInstance().getClient().setSocket(socket);
                            ClientSender clientSender = new ClientSender(GuiController.getInstance().getClient());
                            GuiController.getInstance().getClient().setClientSender(clientSender);
                            new Thread(clientSender).start();
                            ClientReceiver cr = new ClientReceiver(GuiController.getInstance().getClient());
                            GuiController.getInstance().getClient().setClientReceiver(cr);
                            new Thread(cr).start();
                            List<String> loginOrder = new ArrayList<>();
                            loginOrder.add(ClientReqType.LOGIN.toString());
                            loginOrder.add(DataHandler.getInstance().getUserName());
                            loginOrder.add(DataHandler.getInstance().getPassWord());
                            GuiController.getInstance().getClient().getClientSender().sendMessage(loginOrder);
                            GuiController.getInstance().getUserCurrentPanel().remove(jButton);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null,"SERVER IS OFF!");
                            return;
                        }
                    }
                });
                GuiController.getInstance().getUserCurrentPanel().add(jButton);
                GuiController.getInstance().getUserCurrentPanel().repaint();
                GuiController.getInstance().getUserCurrentPanel().revalidate();

                client.isConnected = false;
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
