package Pages;

import ClientSide.ClientConfig;

import javax.swing.*;
import java.awt.*;

public class ConnectionStatusPage extends JPanel{
    JTextField msg;
    JButton sendMsg;
    public static boolean isConnected = true;
    public ConnectionStatusPage(){
        initPanel();
        initComps();
        align();
    }
    public void initPanel(){
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
    }
    public void initComps(){
        msg = new JTextField("WHAT IS YOUR PROBLEM?");
        sendMsg = new JButton("SEND");
    }
    public void align(){
        msg.setBounds(10,100,200,30);
        this.add(msg);
        sendMsg.setBounds(270,100,150,30);
        this.add(sendMsg);
    }


}
