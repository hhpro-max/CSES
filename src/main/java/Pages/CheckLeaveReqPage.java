package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckLeaveReqPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JTextField jTextField;
    JLabel jLabel;
    JButton jButton;
    List<List<String>> data = new ArrayList<>();
    String column[] = {"ID", "NAME", "RESULT"};
    JOptionPane jOptionPane;

    public CheckLeaveReqPage() {
        initPanel();
        initComps();
        align();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }

    public void initPanel() {
        this.setBounds(0, 30, ClientConfig.mainFrameWidth, ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void initComps() {
        jButton = new JButton("SABT TAGHIRAT");
        jTextField = new JTextField();
        jLabel = new JLabel("ID DANESHJOOO : ");
        data = DataHandler.getInstance().getLeaveReqList();
        String data1[][] = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(data1, column);
        jScrollPane = new JScrollPane(jTable);
        jOptionPane = new JOptionPane();
    }

    public void align() {
        jButton.setBounds(600, 50, 150, 30);
        this.add(jButton);
        jLabel.setBounds(10, 50, 200, 30);
        this.add(jLabel);
        jTextField.setBounds(200, 50, 150, 30);
        this.add(jTextField);
        jScrollPane.setBounds(10, 100, 600, 600);
        this.add(jScrollPane);
    }

    public void addListener() {
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataHandler.getInstance().updateLeaveReqList();
                if (!jTextField.getText().isEmpty()) {
                    String id = jTextField.getText();
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.LEAVEREQ.toString());
                    req.add(id);
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                    if (!(jScrollPane == null)) {
                        remove(jScrollPane);
                    }
                    repaint();
                    revalidate();
                    data = DataHandler.getInstance().getLeaveReqList();
                    String data1[][] = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
                    jTable = new JTable(data1, column);
                    jScrollPane = new JScrollPane(jTable);
                    jScrollPane.setBounds(10, 100, 600, 600);
                    add(jScrollPane);

                } else {
                    jOptionPane.showMessageDialog(null, "FIELD ID RA POR KONID");
                }
            }
        });
    }
}
