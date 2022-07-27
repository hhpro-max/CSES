package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LessonsListPage extends JPanel {


    JComboBox<College> daneshKadeha;
    JLabel daneshkade1;

    JCheckBox maghtaDars;
    JComboBox<LessonLevel> maghtaDarsha;
    JLabel maghtadars1;

    JCheckBox ostad;
    JTextField nameOstad;
    JLabel nameOstad1;

    JButton namayesh;

    JOptionPane jOptionPane;

    JTable dorosTable;

    JScrollPane sp;

    String ostadName;
    LessonLevel maghtaInDars;
    College daneshKadeDars;

    public LessonsListPage(){
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
    private void initComps() {
        daneshKadeha = new JComboBox<>(College.values());
        daneshkade1 = new JLabel("ENTEKHAB DANESHKADE : ");
        maghtaDarsha = new JComboBox<>(LessonLevel.values());
        maghtaDars=new JCheckBox();
        maghtadars1=new JLabel("MAGHTA DARS : ");
        ostad = new JCheckBox();
        nameOstad=new JTextField();
        nameOstad1 = new JLabel("NAME OSTAD : ");
        namayesh=new JButton("NAMAYESH");
        jOptionPane=new JOptionPane();
        dorosTable = null;
        sp=null;
        ostadName=null;
        maghtaInDars=null;
        daneshKadeDars=null;
    }

    private void align() {
        maghtaDars.setBounds(5,55,20,20);
        this.add(maghtaDars);
        maghtadars1.setBounds(30,50,100,30);
        this.add(maghtadars1);
        maghtaDarsha.setBounds(150,50,100,30);
        this.add(maghtaDarsha);

        ostad.setBounds(5,100,20,20);
        this.add(ostad);
        nameOstad1.setBounds(30,95,100,30);
        this.add(nameOstad1);
        nameOstad.setBounds(150,95,100,30);
        this.add(nameOstad);

        daneshkade1.setBounds(300,70,200,30);
        this.add(daneshkade1);
        daneshKadeha.setBounds(450,70,100,30);
        this.add(daneshKadeha);

        namayesh.setBounds(570,70,100,30);
        this.add(namayesh);

    }

    private void addListener() {
        namayesh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataHandler.getInstance().updateLessonsList();
                if (!(sp==null)){
                    remove(sp);
                }
                repaint();
                revalidate();
                daneshKadeDars = daneshKadeha.getItemAt(daneshKadeha.getSelectedIndex());
                if (maghtaDars.isSelected()){
                    maghtaInDars = maghtaDarsha.getItemAt(maghtaDarsha.getSelectedIndex());
                }else {
                    maghtaInDars=null;
                }
                if (ostad.isSelected() && !nameOstad.getText().isEmpty()){
                    ostadName = nameOstad.getText();
                }
                else {
                    ostadName=null;
                }
                int j = 0;
                if (ostadName == null && maghtaInDars == null){
                    List<List<String>> etelat = DataHandler.getInstance().getAllLessons();

                    String colomns[] = {"ID","NAME","PISHNIAZ","OSTAD","COLLEGE","VAHED","MAGHTA","ZARFIAT","DAYS","TIME","EXAMDATE"};
                    String rows[][] = etelat.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

                    dorosTable = new JTable(rows,colomns);


                }
                else if (!(ostadName == null) && maghtaInDars == null){
                    List<List<String>> etelat = new ArrayList<>();
                    for (List<String> i:
                            DataHandler.getInstance().getAllLessons()) {
                        if (i.get(4).equals(daneshKadeDars.toString()) && i.get(3).equals(ostadName)){
                            etelat.add(i);
                        }
                    }
                    String colomns[] = {"ID","NAME","PISHNIAZ","OSTAD","COLLEGE","VAHED","MAGHTA","ZARFIAT","DAYS","TIME","EXAMDATE"};
                    String rows[][] = etelat.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

                    dorosTable = new JTable(rows,colomns);

                }else if (ostadName == null && !(maghtaInDars == null)){
                    List<List<String>> etelat = new ArrayList<>();
                    for (List<String> i:
                            DataHandler.getInstance().getAllLessons()) {
                        if (i.get(4).equals(daneshKadeDars.toString()) && i.get(6).equals(maghtaInDars.toString())){
                            etelat.add(i);
                        }
                    }
                    String colomns[] = {"ID","NAME","PISHNIAZ","OSTAD","COLLEGE","VAHED","MAGHTA","ZARFIAT","DAYS","TIME","EXAMDATE"};
                    String rows[][] = etelat.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

                    dorosTable = new JTable(rows,colomns);

                }else if (!(ostadName == null) && !(maghtaInDars == null)){
                    List<List<String>> etelat = new ArrayList<>();
                    for (List<String> i:
                            DataHandler.getInstance().getAllLessons()) {
                        if (i.get(4).equals(daneshKadeDars.toString()) && i.get(6).equals(maghtaInDars.toString()) && i.get(3).equals(ostadName)){
                            etelat.add(i);
                        }
                    }
                    String colomns[] = {"ID","NAME","PISHNIAZ","OSTAD","COLLEGE","VAHED","MAGHTA","ZARFIAT","DAYS","TIME","EXAMDATE"};
                    String rows[][] = etelat.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

                    dorosTable = new JTable(rows,colomns);

                }
                sp = new JScrollPane(dorosTable);
                sp.setBounds(50,200,1400,400);
                sp.repaint();
                sp.revalidate();
                add(sp);
                repaint();
                revalidate();

            }
        });
    }
}
