package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckMinorPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JComboBox<Status> jComboBox;
    JComboBox<College> jComboBox1;
    JTextField jTextField;
    JLabel jLabel;
    JButton jButton;
    List<List<String>> data = new ArrayList<>();
    String column[] = {"ID","DK.MABDA","DK.MAGHSAD" ,"VAZIAT DK.MABDA", "VAZIAT DK.MAGHSAD"};
    JOptionPane jOptionPane;

    public CheckMinorPage() {
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
        jComboBox = new JComboBox<>(Status.values());
        jComboBox1 = new JComboBox<>(College.values());
        data = DataHandler.getInstance().getMinorReqList();
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
        jComboBox.setBounds(400, 50, 150, 30);
        this.add(jComboBox);
        jComboBox1.setBounds(400, 100, 150, 30);
        this.add(jComboBox1);
        jScrollPane.setBounds(10, 200, 700, 500);
        this.add(jScrollPane);
    }

    public void addListener() {
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextField.getText().isEmpty()) {
                    String id = jTextField.getText();
                    String vaziat = jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString().toLowerCase();
                    String daneshKade = jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).toString().toLowerCase();
                    try {
                        Integer.parseInt(id);
                    }catch (Exception exception){
                        jOptionPane.showMessageDialog(null,"INTEGER FORMAT IS JUST ACCEPTABLE!");
                        return;
                    }
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.MINORREQ.toString());
                    req.add(id);
                    req.add(vaziat);
                    req.add(daneshKade);
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);

                    DataHandler.getInstance().updateMinorReqList();

                    if (!(jScrollPane == null)) {
                        remove(jScrollPane);
                    }
                    repaint();
                    revalidate();
                    data = DataHandler.getInstance().getMinorReqList();
                    String data1[][] = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
                    jTable = new JTable(data1, column);
                    jScrollPane = new JScrollPane(jTable);
                    jScrollPane.setBounds(10, 200, 700, 500);
                    add(jScrollPane);
                    jScrollPane.repaint();
                    jScrollPane.revalidate();
                    repaint();
                    revalidate();


                } else {
                    jOptionPane.showMessageDialog(null, "FIELD ID RA POR KONID");
                }
            }
        });
    }
}
