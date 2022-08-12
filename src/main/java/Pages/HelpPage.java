package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HelpPage extends JPanel implements Runnable{
    JTextField msg;
    JButton sendMsg;
    List<String> savedMsg;
    public HelpPage(){
        savedMsg = new ArrayList<>();
        initPanel();
        initComps();
        align();
        addListener();
    }
    public void initPanel(){
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
    }
    public void initComps(){
        msg = new JTextField("HOW CAN I HELP YOU?");
        sendMsg = new JButton("SEND");
    }
    public void align(){
        msg.setBounds(10,100,200,30);
        this.add(msg);
        sendMsg.setBounds(270,100,150,30);
        this.add(sendMsg);
    }
    public void addListener(){
        sendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GuiController.getInstance().getClient().isConnected){
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.SEND_MESSAGE.toString());
                    req.add(ClientConfig.eduAdminId);
                    req.add(msg.getText()+".msg");
                    req.add("--");
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                }else {
                    savedMsg.add(msg.getText()+".msg");
                }
            }
        });
    }

    @Override
    public void run() {
        while (true){
            if (!savedMsg.isEmpty() && GuiController.getInstance().getClient().isConnected){
                for (String i:
                     savedMsg) {
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.SEND_MESSAGE.toString());
                    req.add(ClientConfig.eduAdminId);
                    req.add(i);
                    req.add("--");
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                }
            }
        }
    }
}
