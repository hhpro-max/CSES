package Pages;



import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.ClientSender;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChatPage extends JPanel {
    JTable jTable;
    JScrollPane chatBox;
    String id;
    JScrollPane chatPanel;
    JTextField jTextField;
    JButton sendMessage;
    JButton sendFile;
    File file;
    String[] columns = {"id","name","last message"};
    String[][] rows;
    List<List<String>> mainData;
    List<List<String>> chats;
    public ChatPage(){
        DataHandler.getInstance().updateChats();
        mainData = new ArrayList<>();
        chats = new ArrayList<>();
        initPanel();
        initTable();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void initTable(){
        jTextField = new JTextField();
        sendMessage = new JButton("SEND MESSAGE");
        sendFile = new JButton("SEND FILE");
        if (chatBox != null){
            this.remove(chatBox);
        }
        mainData.clear();
        for (List<String> i:
             DataHandler.getInstance().getChats()) {
            if (!isContains(mainData,i)){
                mainData.add(i);
            }
        }
        rows = mainData.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(rows,columns);
        chatBox = new JScrollPane(jTable);
        chatBox.setBounds(10,70,400,600);
        this.add(chatBox);
        repaint();
        revalidate();
    }
    public void addListener(){
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = (String) jTable.getValueAt(jTable.getSelectedRow(),0);
                initChatRoom(id);
            }
        });
        sendFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    try {
                        file = jFileChooser.getSelectedFile();
                        FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                        String fileName = file.getName();
                        byte[] fileContentBytes = new byte[(int) file.length()];

                        fileInputStream.read(fileContentBytes);

                        List<String> req = new ArrayList<>();
                        req.add(ClientReqType.SEND_MESSAGE.toString());
                        req.add(id);
                        req.add(fileName);
                        req.add(Arrays.toString(fileContentBytes));
                        GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                    }catch (IOException exception){
                        exception.printStackTrace();
                    }
                }
            }
        });
        sendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextField.getText().isEmpty()){
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.SEND_MESSAGE.toString());
                    req.add(id);
                    req.add(jTextField.getText()+".msg");
                    req.add("--");
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                }else {
                    JOptionPane.showMessageDialog(null,"REALLY? YOU WANA SEND EMPTY TEXT?");
                }
            }
        });
    }
    public void initChatRoom(String id){
        chats.clear();
        if (chatPanel != null){
            this.remove(chatPanel);
        }


        for (List<String> i:
             DataHandler.getInstance().getChats()) {
            if (i.get(0).equals(id)){
                chats.add(i);
            }
        }
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        Collections.reverse(chats);
        for (List<String> i:
             chats) {
            JLabel jLabel = new JLabel(i.get(2));
            jLabel.setVisible(true);
            jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int i = jLabel.getText().lastIndexOf('.');
                    if (i > 0){
                        JFrame jFrame = new JFrame();
                        jFrame.setVisible(true);
                        jFrame.setLayout(null);
                        jFrame.setResizable(true);
                        jFrame.setSize(new Dimension(800,400));
                        JLabel JFLabel = new JLabel("ARE YOU SURE YOU WANT TO DOWNLOAD / "+jLabel.getText()+" / FILE ?");
                        JFLabel.setBounds(10,50,600,50);
                        jFrame.add(JFLabel);
                        JButton JFYesBut = new JButton("YES");
                        JFYesBut.setBounds(200,100,150,30);
                        jFrame.add(JFYesBut);
                        JButton JFNoBut = new JButton("NO");
                        JFNoBut.setBounds(400,100,150,30);
                        jFrame.add(JFNoBut);
                        JFNoBut.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e1) {
                                jFrame.dispose();
                            }
                        });
                        JFYesBut.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                List<String> req = new ArrayList<>();
                                req.add(ClientReqType.DOWNLOAD_FILE.toString());
                                String fileName = jLabel.getText().replaceAll(" ","").split(":")[1];
                                req.add(fileName);
                                GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                                jFrame.dispose();
                            }
                        });
                        jFrame.repaint();
                        jFrame.revalidate();
                    }else {
                        JOptionPane.showMessageDialog(null,"this is not downloadable file!");
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            jPanel.add(jLabel);
        }
        chatPanel = new JScrollPane(jPanel);
        chatPanel.setBounds(500,70,600,600);
        this.add(chatPanel);
        //
        jTextField.setBounds(500,700,300,30);
        this.add(jTextField);
        sendMessage.setBounds(850,700,200,30);
        this.add(sendMessage);
        sendFile.setBounds(1100,700,200,30);
        this.add(sendFile);
        repaint();
        revalidate();
    }
    public boolean isContains(List<List<String>> i,List<String> j){
        for (List<String> k :i){
            if (k.get(0).equals(j.get(0))){
                return true;
            }
        }
        return false;
    }
}
