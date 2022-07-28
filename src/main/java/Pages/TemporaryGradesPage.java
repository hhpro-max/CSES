package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TemporaryGradesPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JButton sabtEteraz;
    JTextField jTextField;
    JTextArea jTextArea;
    JLabel jLabel;
    JLabel jLabel1;
    String columns[] = {"ID","NOMRE","MATN ETERAZ","PASOKH OSTAD"};
    public List<List<String>> data = new ArrayList<>();
    JOptionPane jOptionPane;

    public TemporaryGradesPage(){
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
        sabtEteraz = new JButton("SABT ETERAZ");
        jLabel = new JLabel("ID DARS : ");
        jLabel1 = new JLabel("MATN ETERAZ : ");
        jTextArea = new JTextArea();
        jOptionPane = new JOptionPane();
        jTextField = new JTextField();
        initTable();
    }
    public void align(){
        jLabel.setBounds(10,50,100,30);
        this.add(jLabel);
        jTextField.setBounds(150,50,100,30);
        this.add(jTextField);
        jLabel1.setBounds(300,50,100,30);
        this.add(jLabel1);
        jTextArea.setBounds(420,50,300,100);
        this.add(jTextArea);
        sabtEteraz.setBounds(500,150,150,30);
        this.add(sabtEteraz);
        jScrollPane.setBounds(10,200,500,300);
        this.add(jScrollPane);
    }
    public void addListener(){
        sabtEteraz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextField.getText().isEmpty() && !jTextArea.getText().isEmpty()) {
                    String id = jTextField.getText();
                    try {
                        Integer.parseInt(id);
                    }catch (Exception exception){
                        jOptionPane.showMessageDialog(null,"INTEGER FORMAT IS ACCEPTABLE!");
                    }
                    String objection = jTextArea.getText();
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.OBJECTION.toString());
                    req.add(id);
                    req.add(objection);
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                }
                else {
                    jOptionPane.showMessageDialog(null,"HICH FIELDIO KHALI NAZAR");
                }
            }
        });
    }
    public void initTable(){
        data = DataHandler.getInstance().getUserTemporaryGradesList();
        String data1[][] = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(data1,columns);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(10,200,500,300);
        jScrollPane.repaint();
        jScrollPane.revalidate();
        this.add(jScrollPane);
        repaint();
        revalidate();
    }

}
