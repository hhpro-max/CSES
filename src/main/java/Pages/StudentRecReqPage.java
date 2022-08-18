package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRecReqPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JTextField jTextField;
    JButton sabtDarkhast;
    String columns[] = {"ID","OSTAD","NATIJE"};
    JOptionPane jOptionPane;
    public StudentRecReqPage(){
        initPanel();
        initComps();
        align();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        if (DataHandler.getInstance().getRecommendsList() != null){
            initTable();
        }
        jTextField = new JTextField("teacher id");
        sabtDarkhast = new JButton("SABT DARKHAST");
        jTable = null;
        jScrollPane = null;
        jOptionPane = new JOptionPane();
    }
    public void align(){
        jTextField.setBounds(200,50,200,30);
        this.add(jTextField);
        sabtDarkhast.setBounds(10,50,150,30);
        this.add(sabtDarkhast);
    }
    public void addListener(){
        sabtDarkhast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextField.getText().isEmpty()){
                    try {
                        Integer.parseInt(jTextField.getText());
                        if (!(jScrollPane==null)){
                            remove(jScrollPane);
                        }
                        repaint();
                        revalidate();
                        List<String> req = new ArrayList<>();
                        req.add(ClientReqType.RECOMMENDREQ.toString());
                        req.add(jTextField.getText());
                        GuiController.getInstance().getClient().getClientSender().sendMessage(req);


                    }catch (Exception e1){
                        jOptionPane.showMessageDialog(null,"INTEGER ID ONLY ACCEPTABLE!");
                    }
                }else {
                    jOptionPane.showMessageDialog(null,"FILL IN THE BLANK WITH TEACHER ID");
                }
                initTable();

            }
        });
    }

    public void initTable() {
        String data1[][]= DataHandler.getInstance().getRecommendsList().stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(data1,columns);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(50,200,600,400);
        jScrollPane.repaint();
        jScrollPane.revalidate();
        add(jScrollPane);
        repaint();
        revalidate();
    }
}
