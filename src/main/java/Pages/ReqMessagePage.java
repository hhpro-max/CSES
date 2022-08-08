package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReqMessagePage extends JPanel {
    JComboBox<Status> jComboBox;
    JTextField jTextField;
    JButton jButton;
    JTable jTable;
    JScrollPane jScrollPane;
    String[] columns = {"MESSAGE ID","SENDER ID","RECEIVER ID","MESSAGE","RESULT"};
    String[][] rows;
    public ReqMessagePage(){
        DataHandler.getInstance().updateReqMessage();
        initPanel();
        initComps();
        align();
        addListener();
        repaint();
        revalidate();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        jComboBox = new JComboBox<>(Status.values());
        jTextField = new JTextField("MESSAGE ID");
        jButton = new JButton("SET RESULT");
        rows = DataHandler.getInstance().getReqMessages().stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(rows,columns);
        jScrollPane = new JScrollPane(jTable);
    }
    public void align(){
        jTextField.setBounds(10,40,150,30);
        this.add(jTextField);
        jComboBox.setBounds(200,40,150,30);
        this.add(jComboBox);
        jButton.setBounds(400,40,150,30);
        this.add(jButton);
        jScrollPane.setBounds(50,130,900,400);
        this.add(jScrollPane);
    }
    public void addListener(){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> req = new ArrayList<>();
                req.add(ClientReqType.SET_REQ_MESSAGE_RESULT.toString());
                try {
                    Integer.parseInt(jTextField.getText());
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"ONLY INTEGER FORMAT IS ACCEPTABLE!");
                    return;
                }
                req.add(jTextField.getText());
                req.add(jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString());
                GuiController.getInstance().getClient().getClientSender().sendMessage(req);
            }
        });
    }
}
