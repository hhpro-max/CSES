package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MinorReqPage extends JPanel {
    JButton jButton;
    JComboBox<College> jComboBox;
    JOptionPane jOptionPane;
    String coloms[] ={"Daneshkade Maghsad","Moshahede Natije"};
    List<List<String>> data = new ArrayList<>();
    JTable jTable;
    JScrollPane jScrollPane;

    public MinorReqPage(){
        initPanel();
        initComps();
        align();
        addListener();

        GuiController.getInstance().addJPanel(this);
    }

    private void initPanel() {
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        jButton = new JButton("Sabt Darkhast");
        jComboBox = new JComboBox<>(College.values());
        jOptionPane = new JOptionPane();
        jTable = null;
        jScrollPane = null;
        if (DataHandler.getInstance().getMinorReqList() != null){
            initTable();
        }
    }
    public void align(){
        jButton.setBounds(10,50,150,30);
        this.add(jButton);
        jComboBox.setBounds(200,50,150,30);
        add(jComboBox);
    }
    public void addListener(){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(jScrollPane==null)){
                    remove(jScrollPane);
                }
                repaint();
                revalidate();
                List<String> req = new ArrayList<>();
                req.add(ClientReqType.MINORREQ.toString());
                req.add(String.valueOf(jComboBox.getItemAt(jComboBox.getSelectedIndex())).toLowerCase());
                GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                initTable();
            }
        });
    }

    public void initTable() {
        data.addAll(DataHandler.getInstance().getMinorReqList());
        String data1[][]= data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(data1,coloms);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(50,200,600,400);
        jScrollPane.repaint();
        jScrollPane.revalidate();
        add(jScrollPane);
        repaint();
        revalidate();
    }
}
