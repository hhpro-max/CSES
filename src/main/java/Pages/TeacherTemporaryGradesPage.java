package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TeacherTemporaryGradesPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JButton jButton;
    JButton jButton1;
    JButton jButton2;
    JTextField jTextField;
    JTextField jTextField1;
    JTextArea jTextArea;
    JLabel jLabel;
    JLabel jLabel1;
    JLabel jLabel2;
    String columns[] = {"ID DANESHJOO", "NAME DANESHJOO", "ID DARS", "DARS", "NOMRE", "STUDENT OBJECTION", "OBJECTION ANSWER"};
    public List<List<String>> data = new ArrayList<>();
    JOptionPane jOptionPane;
    boolean sabtnahayy = false;
    JTextArea jTextArea1;
    JScrollPane jScrollPane1;

    public TeacherTemporaryGradesPage() {
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
        jButton = new JButton("SABT PASOKH");
        jButton1 = new JButton("SABT NOMARAT");
        jButton2 = new JButton("SABT NAHAYY");
        jTextField1 = new JTextField();
        jLabel2 = new JLabel("ID DARS");
        jTextArea = new JTextArea();
        jTextField = new JTextField();
        jLabel = new JLabel("ID DANESHJOO :");
        jLabel1 = new JLabel("MATN PASOKH :");
        data = DataHandler.getInstance().getUserTemporaryGradesList();
        String data1[][] = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(data1, columns);
        jScrollPane = new JScrollPane(jTable);
        jOptionPane = new JOptionPane();
        jTextArea1 = new JTextArea();
        jScrollPane1 = new JScrollPane(jTextArea1);
    }

    public void align() {
        jLabel.setBounds(10, 50, 100, 30);
        this.add(jLabel);
        jTextField.setBounds(150, 50, 100, 30);
        this.add(jTextField);
        jLabel1.setBounds(300, 50, 100, 30);
        this.add(jLabel1);
        jTextArea.setBounds(420, 50, 300, 100);
        this.add(jTextArea);
        jButton.setBounds(500, 150, 150, 30);
        this.add(jButton);
        jScrollPane.setBounds(10, 200, 500, 300);
        this.add(jScrollPane);
        jButton1.setBounds(600, 600, 150, 30);
        this.add(jButton1);
        jButton2.setBounds(400, 600, 150, 30);
        this.add(jButton2);
        jLabel2.setBounds(10, 100, 100, 30);
        this.add(jLabel2);
        jTextField1.setBounds(150, 100, 100, 30);
        this.add(jTextField1);
        jScrollPane1.setBounds(520, 200, 260, 300);
        this.add(jScrollPane1);
    }

    public void addListener() {
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextField1.getText().isEmpty() && !jTextArea.getText().isEmpty() && !jTextField.getText().isEmpty()) {
                    String iddars = jTextField1.getText();
                    String idDaneshjoo = jTextField.getText();
                    try {
                        Integer.parseInt(iddars);
                        Integer.parseInt(idDaneshjoo);
                    } catch (Exception exception) {
                        jOptionPane.showMessageDialog(null, "ONLY INTEGER FORMAT IS ACCEPTABLE");
                        return;
                    }
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.OBJECTION.toString());
                    req.add(iddars);
                    req.add(idDaneshjoo);
                    req.add(jTextArea.getText());
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                    DataHandler.getInstance().updateTemporaryGradesList();
                } else {
                    jOptionPane.showMessageDialog(null, "HICH FIELDIO KHALI NAZAR");
                }
            }

        });


        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int row = jTable.getRowCount();
                    int column = jTable.getColumnCount();
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (int j = 0; j < row; j++) {
                        for (int i = 0; i < column; i++) {
                            arrayList.add(jTable.getValueAt(j, i).toString());
                            if (arrayList.size() == 5) {
                                String idDaneshjoo = arrayList.get(0);
                                String idDars = arrayList.get(2);
                                String nomre = arrayList.get(4);

                                try {
                                    Integer.parseInt(idDaneshjoo);
                                    Integer.parseInt(idDars);
                                    Double.parseDouble(nomre);
                                }catch (Exception exception){
                                    jOptionPane.showMessageDialog(null,"ONLY NUMERIC FORMAT IS ACCEPTABLE!");
                                    return;
                                }
                                if (Double.parseDouble(nomre) > 20 || Double.parseDouble(nomre) < 0) {
                                    jOptionPane.showMessageDialog(null, "NOMRE SABT SHODE NAMOTABAR AST");
                                    return;
                                }
                                List<String> req = new ArrayList<>();
                                req.add(ClientReqType.SET_TEMPORARY_GRADE.toString());
                                req.add(idDaneshjoo);
                                req.add(idDars);
                                req.add(nomre);
                                GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                                arrayList.clear();
                                DataHandler.getInstance().updateTemporaryGradesList();
                            }

                        }

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    jOptionPane.showMessageDialog(null, "MOSHKEL BOZORG TAR AZ IN HARFAS XD");
                }
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jOptionPane.showMessageDialog(null,"NOT INITIALIZED YET!COME BACK LATER");
            }
        });
    }
}
