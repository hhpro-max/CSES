package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SearchInStudent extends JPanel {
    JTable jTable;
    String[] columns = {"ID","NAME","LVL"};
    String[][] rows;
    JScrollPane jScrollPane;
    JTextField jTextField;
    List<List<String>> data;
    JScrollPane jScrollPane2;
    JPanel studentInfo;
    public SearchInStudent(){
        DataHandler.getInstance().updateAllStudents();
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
        studentInfo = new JPanel();
        studentInfo.setLayout(new BoxLayout(studentInfo,BoxLayout.Y_AXIS));
        jTextField = new JTextField();
        jTextField.setBounds(10,150,200,30);
        this.add(jTextField);
    }
    public void initComps(){
        data = DataHandler.getInstance().getAllStudents();
        rows = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(rows,columns);
        jScrollPane = new JScrollPane(jTable);

    }
    public void align(){
        jScrollPane.setBounds(10,200,600,400);
        this.add(jScrollPane);
    }
    public void addListener(){
        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                initList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                initList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                initList();
            }

            public void initList(){
                data = DataHandler.getInstance().getAllStudents();
                List<List<String>> filteredList = new ArrayList<>();
                if (jScrollPane != null){
                    remove(jScrollPane);
                }
                if (jTextField.getText().isEmpty()){
                    filteredList = data;
                }else {
                    String id = jTextField.getText();
                    for (List<String> i:
                         data) {
                        if (isSimilar(i,id)){
                            filteredList.add(i);
                        }
                    }
                }
                rows = filteredList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
                jTable = new JTable(rows,columns);
                jScrollPane = new JScrollPane(jTable);
                jScrollPane.setBounds(10,200,600,400);
                add(jScrollPane);
                repaint();
                revalidate();
                addTableListener();
            }

            private boolean isSimilar(List<String> i, String id) {
                String[] studentId = i.get(0).split("");
                String[] searchedId = id.split("");
                boolean isSimilar = false;
                for (int j = 0; j < searchedId.length;j++) {
                    if (searchedId[j].equals(studentId[j])){
                        isSimilar = true;
                    }else {
                        isSimilar = false;
                        return false;
                    }
                }
                return isSimilar;
            }
        });
        addTableListener();
    }
    public void addTableListener(){
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                data = DataHandler.getInstance().getAllStudents();
                String id = (String) jTable.getValueAt(jTable.getSelectedRow(),0);
                if (jScrollPane2 != null){
                    remove(jScrollPane2);
                }
                studentInfo.removeAll();
                for (List<String> i:
                        data) {
                    if (i.get(0).equals(id)){
                        for (String j:
                                i) {
                            studentInfo.add(new JLabel(j));
                        }
                    }
                }
                jScrollPane2 = new JScrollPane(studentInfo);
                jScrollPane2.setBounds(650 , 200,400,400);
                add(jScrollPane2);
                repaint();
                revalidate();
            }
        });
    }
}
