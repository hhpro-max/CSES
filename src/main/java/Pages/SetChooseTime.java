package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SetChooseTime extends JPanel {
    JTextField entranceYear,takingTime;
    JLabel ey,tt;
    JComboBox<LessonLevel> studentLvl;
    JButton jButton;
    List<List<String>> data;
    String[][] rows;
    String[] columns = {"ID","SIGNUP_TIME","ENTER_YEAR","EDU_LVL"};

    JTable jTable;
    JScrollPane jScrollPane;

    public SetChooseTime(){
        DataHandler.getInstance().updateStudentsList();
        initPanel();
        initComps();
        align();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0, 30, ClientConfig.mainFrameWidth, ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){

        entranceYear = new JTextField("EX : 1399");
        takingTime = new JTextField("EX : 6");
        ey = new JLabel("ENTER_YEAR : ");
        tt = new JLabel("SIGN_UP TIME : ");
        studentLvl = new JComboBox<>(LessonLevel.values());
        data = DataHandler.getInstance().getAllStudents();
        rows = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(rows,columns);
        jScrollPane = new JScrollPane(jTable);
        jButton = new JButton("SAVE");
    }
    public void align(){
        ey.setBounds(10,70,200,30);
        this.add(ey);
        entranceYear.setBounds(220,70,200,30);
        this.add(entranceYear);
        tt.setBounds(10,150,200,30);
        this.add(tt);
        takingTime.setBounds(220,150,200,30);
        this.add(takingTime);
        studentLvl.setBounds(450,100,100,30);
        this.add(studentLvl);
        jScrollPane.setBounds(150,200,600,400);
        this.add(jScrollPane);
        jButton.setBounds(560,100,150,30);
        this.add(jButton);
    }
    public void addListener(){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (takingTime.getText().isEmpty() || entranceYear.getText().isEmpty()){
                    GuiController.getInstance().getjOptionPane().showMessageDialog(null,"FILL IN THE BLANKS!");
                }else {
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.SET_CHOOSE_TIME.toString());
                    req.add(takingTime.getText());
                    req.add(entranceYear.getText());
                    req.add("D"+studentLvl.getItemAt(studentLvl.getSelectedIndex()).toString().toUpperCase());
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                }
            }
        });
    }
}
