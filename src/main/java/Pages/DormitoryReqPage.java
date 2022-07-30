package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DormitoryReqPage extends JPanel {
    //todo complete this page

    JButton jButton;
    JTable jTable;
    JScrollPane jScrollPane;
    String columns[] = {"MOJAZ BE DASHTAN KHABGAH"};
    String data[][]={{"MOJAZI"}};
    JOptionPane jOptionPane;

    public DormitoryReqPage(){
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
        jButton=new JButton("SABT DARKHAST KHANGAH");
        jTable=null;
        jScrollPane=null;
        jOptionPane = new JOptionPane();
        if (DataHandler.getInstance().getDormitoryReq().equals("TRUE")){
            jTable = new JTable(data,columns);
            jScrollPane = new JScrollPane(jTable);
            jScrollPane.setBounds(50,200,600,100);
            jScrollPane.repaint();
            jScrollPane.revalidate();
            this.add(jScrollPane);
            repaint();
            revalidate();
        }
    }
    public void align(){
        jButton.setBounds(10,50,200,30);
        this.add(jButton);
    }
    public void addListener(){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DataHandler.getInstance().getDormitoryReq().equals("TRUE")){
                    jTable = new JTable(data,columns);
                    jScrollPane = new JScrollPane(jTable);
                    jScrollPane.setBounds(50,200,600,100);
                    jScrollPane.repaint();
                    jScrollPane.revalidate();
                    add(jScrollPane);
                    repaint();
                    revalidate();
                }else {
                    jOptionPane.showMessageDialog(null,"MOJAZ NISTI OONGHADR DOKMARO BEZAN TA MOJAZ SHI=)");
                }
            }
        });
    }
}
