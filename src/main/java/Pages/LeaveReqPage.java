package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LeaveReqPage extends JPanel {
    JButton jButton;
    JOptionPane jOptionPane;
    String coloms[] ={"DARKHAST ENSERAF","MOSHADE NATIJE"};
    List<List<String>> data = new ArrayList<>();
    JTable jTable;
    JScrollPane jScrollPane;

    public LeaveReqPage(){
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
        jButton = new JButton("SABT DARKHAST ENSERAF AZ TAHSIL");
        jOptionPane = new JOptionPane();
        jTable = null;
        jScrollPane = null;
        if (DataHandler.getInstance().getLeaveReqList() != null){
            initTable();
        }
    }
    public void align(){
        jButton.setBounds(10,50,250,30);
        this.add(jButton);
    }
    public void addListener(){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataHandler.getInstance().updateLeaveReqList();
                List<String> req = new ArrayList<>();
                req.add(ClientReqType.LEAVEREQ.toString());
                req.add(String.valueOf(DataHandler.getInstance().getId()));
                GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                initTable();
            }
        });
    }

    public void initTable() {
        if (DataHandler.getInstance().getLeaveReqList() != null){
            data.addAll(DataHandler.getInstance().getLeaveReqList());
        }
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
