package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TeacherRecReqPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JComboBox<String> jComboBox;
    JTextField jTextField;
    JLabel jLabel;
    JButton jButton;
    List<List<String>> data = new ArrayList<>();
    String column[] = {"ID", "NAME", "VAZIAT"};
    JOptionPane jOptionPane;

    public TeacherRecReqPage() {
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
        jComboBox = new JComboBox<>();
        jComboBox.addItem("SAVED");
        jComboBox.addItem("REJECTED");
        jComboBox.addItem("ACCEPTED");
        data = DataHandler.getInstance().getRecommendsList();
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
        jScrollPane.setBounds(10, 100, 600, 600);
        this.add(jScrollPane);
    }

    public void addListener() {
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!jTextField.getText().isEmpty()) {
                    String id = jTextField.getText();
                    String vaziat = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                    try {
                        Integer.parseInt(id);
                    } catch (Exception exception) {
                        jOptionPane.showMessageDialog(null, "ONLY INTEGER FORM IS ACCEPTABLE!");
                        return;
                    }
                    List<String> req = new ArrayList<>();

                    req.add(ClientReqType.SET_REC_RESULT.toString());
                    req.add(id);
                    req.add(vaziat);
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                    if (!(jScrollPane == null)) {
                        remove(jScrollPane);
                    }
                    repaint();
                    revalidate();
                    DataHandler.getInstance().updateRecommendsList();
                    data = DataHandler.getInstance().getRecommendsList();
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
