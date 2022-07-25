package Pages;

import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TeachersListPage extends JPanel {

    JButton namayesh;
    JTable jTable;
    JScrollPane jScrollPane;

    public TeachersListPage(){
        initPanel();
        initComps();
        align();
        addListener();

        GuiController.getInstance().addJPanel(this);
    }


    private void initPanel() {
        this.setBounds(0,30,800,770);
        this.setVisible(true);
        this.setLayout(null);
    }
    private void initComps() {
        namayesh=new JButton("SHOW");
        jTable = null;
        jScrollPane = null;
    }

    public void align(){
        namayesh.setBounds(30,30,100,30);
        this.add(namayesh);
    }
    public void addListener(){
        namayesh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(jScrollPane==null)){
                    remove(jScrollPane);
                }
                repaint();
                revalidate();
                List<List<String>> etelat = DataHandler.getInstance().getTeachers();

                String colomns[] = {"ID","NAME","EMAIL","DANESHKADE","SHOMARETAMAS","DARAJE OSTADI"};
                String rows[][] = etelat.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

                jTable = new JTable(rows,colomns);
                jScrollPane = new JScrollPane(jTable);
                jScrollPane.setBounds(50,200,600,400);
                jScrollPane.repaint();
                jScrollPane.revalidate();
                add(jScrollPane);
                repaint();
                revalidate();
            }
        });

    }




}
