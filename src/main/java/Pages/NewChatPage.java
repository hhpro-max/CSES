package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewChatPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JTextField id;
    JTextField msg;
    JButton sendMsg;
    String[][] rows;
    String[] columns = {"ID","NAME"};
    public NewChatPage(){
        DataHandler.getInstance().updateAvailablePeople();
        initPanel();
        initComps();
        align();
        initTable();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void  initComps(){
        id = new JTextField("99100,99200,99340");
        msg = new JTextField("MESSAGE");
        sendMsg = new JButton("SEND");
    }
    public void align(){
        id.setBounds(500,500,150,30);
        this.add(id);
        msg.setBounds(700,500,200,30);
        this.add(msg);
        sendMsg.setBounds(950,500,150,30);
        this.add(sendMsg);
    }
    public void initTable(){
        List<List<String>> ap = new ArrayList<>(DataHandler.getInstance().getAvailablePeople());
        if (jScrollPane != null){
            this.remove(jScrollPane);
        }
        rows = ap.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(rows,columns);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(10,100,400,600);
        this.add(jScrollPane);
        repaint();
        revalidate();
    }
    public void addListener(){
        sendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (id.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"ID FIELD CAN NOT BE EMPTY !");
                    return;
                }
                if (msg.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"AH SH** HERE WE GO AGAIN! \n EMPTY TEXT ??");
                    return;
                }
                String[] ids = id.getText().split(",");
                String message = msg.getText();
                for (String i:
                     ids) {
                    try {
                        List<String> req = new ArrayList<>();
                        req.add(ClientReqType.SEND_MESSAGE.toString());
                        req.add(String.valueOf(Integer.parseInt(i)));
                        req.add(msg.getText()+".msg");
                        req.add("--");
                        GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                    }catch (Exception e1){
                        JOptionPane.showMessageDialog(null,"ONLY INTEGER FORMAT IS ACCEPTABLE FOR ID!");
                        return;
                    }
                }
            }
        });
    }
}
