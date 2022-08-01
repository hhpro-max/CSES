package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckTemporaryGradesPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JTextArea jTextArea1;
    JScrollPane jScrollPane1;
    JLabel jLabel;
    JTextField jTextField;
    JLabel jLabel1;
    JTextField jTextField1;
    JButton jButton;
    String columns[] = {"ID DANESHJOO","NAME DANESHJOO","ID OSTAD","ID DARS","DARS","NOMRE","student objection","answer"};
    List<List<String>> data = new ArrayList<>();
    JOptionPane jOptionPane;
    JTextArea kholaseDars;
    JButton namayeshKholase;
    JTextField idDars;

    public CheckTemporaryGradesPage(){
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
        jTextArea1 = new JTextArea();
        jLabel = new JLabel("ID DANESHJOO");
        jLabel1 = new JLabel("ID OSTAD");
        jTextField = new JTextField();
        jTextField1 = new JTextField();
        jButton = new JButton("NAMAYESH");
        data = DataHandler.getInstance().getUserTemporaryGradesList();
        String data1[][] = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(data1,columns);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane1 = new JScrollPane(jTextArea1);
        jOptionPane=new JOptionPane();
        namayeshKholase = new JButton("NAMAYESH KHOLASE");
        idDars = new JTextField();
        kholaseDars = null;
    }
    public void align(){
        jLabel.setBounds(10,50,100,30);
        this.add(jLabel);
        jTextField.setBounds(130,50,150,30);
        this.add(jTextField);
        jLabel1.setBounds(10,100,100,30);
        this.add(jLabel1);
        jTextField1.setBounds(130,100,150,30);
        this.add(jTextField1);
        jScrollPane1.setBounds(520,200,260,300);
        this.add(jScrollPane1);
        jScrollPane.setBounds(10,200,500,300);
        this.add(jScrollPane);
        jButton.setBounds(300,70,150,30);
        this.add(jButton);
        idDars.setBounds(10,530,150,30);
        this.add(idDars);
        namayeshKholase.setBounds(170,530,200,30);
        this.add(namayeshKholase);
    }
    public void addListener(){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(jScrollPane);
                repaint();
                revalidate();
                String idD = null;
                String idO = null;
                if (!jTextField.getText().isEmpty()){
                    idD = jTextField.getText();
                }
                if (!jTextField1.getText().isEmpty()){
                    idO = jTextField1.getText();
                }
                if (idD == null && idO == null){
                    data = DataHandler.getInstance().getUserTemporaryGradesList();
                    String data1[][] = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
                    jTable = new JTable(data1,columns);
                }else if (idD == null && !(idO == null)){
                    data = DataHandler.getInstance().getUserTemporaryGradesList();
                    List<List<String>> arrayLists = new ArrayList<>();
                    for (List<String> i:
                            data) {
                        String idOstad = i.get(2);
                        if (idOstad.equals(idO)){
                            arrayLists.add(i);
                        }
                    }
                    String data1[][] = arrayLists.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
                    jTable = new JTable(data1,columns);

                }else if (!(idD == null) && idO == null){
                    data = DataHandler.getInstance().getUserTemporaryGradesList();
                    List<List<String>> arrayLists = new ArrayList<>();
                    for (List<String> i:
                            data) {
                        String id = i.get(0);
                        if (id.equals(idD)){
                            arrayLists.add(i);
                        }
                    }
                    String data1[][] = arrayLists.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
                    jTable = new JTable(data1,columns);
                }
                else if (!(idD == null) && !(idO == null)){
                    data = DataHandler.getInstance().getUserTemporaryGradesList();
                    List<List<String>> arrayLists = new ArrayList<>();
                    for (List<String> i:
                            data) {
                        String id1 = i.get(0);
                        String id2 = i.get(2);
                        if (id1.equals(idD) && id2.equals(idO)){
                            arrayLists.add(i);
                        }
                    }
                    String data1[][] = arrayLists.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
                    jTable = new JTable(data1,columns);
                }
                jScrollPane = new JScrollPane(jTable);
                jScrollPane.setBounds(10,200,500,300);
                jScrollPane.repaint();
                jScrollPane.revalidate();
                add(jScrollPane);
                repaint();
                revalidate();
            }
        });
        namayeshKholase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!idDars.getText().isEmpty()){
                    if (!(kholaseDars == null)){
                        remove(kholaseDars);
                    }
                    repaint();
                    revalidate();
                    String matn = "MODEL KOL DARS : "+0+" TEDAD GHABOOLI HA : "+ 0 + "\n TEDAD MARDOODIA : "+ 0+" MOADEL BEDOON DARNAZAR GRFTN MARDOODI : " + 0;
                    kholaseDars = new JTextArea();
                    kholaseDars.setText(matn);
                    kholaseDars.setBounds(10,600,600,180);
                    add(kholaseDars);
                    repaint();
                    revalidate();
                }
                else {
                    jOptionPane.showMessageDialog(null,"ID DARS RA VARED KONID");

                }
            }
        });
    }
}
