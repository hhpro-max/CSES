package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddLessonPage extends JPanel {
    JTextField id,name,pishniaz,ostad,tedadVahed,zarfiat,saatClass,zamanPayanterm;
    JComboBox<College> daneshkade;
    JComboBox<LessonLevel> maghtaDars;
    JComboBox<WeekDays> roozAval;
    JComboBox<WeekDays> roozDovom;
    JButton sabt;
    JLabel id1,name1,pishniaz1,ostad1,daneshkade1,tedadVahed1,maghtaDars1,zarfiat1,saatClass1,zamanPayanterm1,zamanPayanterm2;
    JOptionPane jOptionPane;

    public AddLessonPage(){
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
        id = new JTextField();
        id1 = new JLabel("ID DARS :");
        name = new JTextField();
        name1 = new JLabel("NAME DARS :");
        pishniaz = new JTextField();
        pishniaz1 = new JLabel("ID PISHNIAZ :");
        ostad = new JTextField();
        ostad1= new JLabel("ID OSTAD :");
        tedadVahed = new JTextField();
        tedadVahed1 = new JLabel("TEDAD VAHED :");
        zarfiat = new JTextField();
        zarfiat1 = new JLabel("ZARFIAT DARS :");
        daneshkade = new JComboBox<>(College.values());
        daneshkade1 = new JLabel("DANESHKADE DARS :");
        maghtaDars = new JComboBox<>(LessonLevel.values());
        maghtaDars1 = new JLabel("MAGHTA DARS :");
        sabt = new JButton("SABT");
        jOptionPane = new JOptionPane();
        saatClass = new JTextField();
        saatClass1 = new JLabel("SAAT CLASS :");
        zamanPayanterm = new JTextField();
        zamanPayanterm1 = new JLabel("TARIKH PAYANTERM :");
        zamanPayanterm2 = new JLabel("NEMONE : 1401/12/10");
        roozAval = new JComboBox<>(WeekDays.values());
        roozDovom = new JComboBox<>(WeekDays.values());
    }
    public void align(){
        id1.setBounds(0,70,100,30);
        this.add(id1);
        id.setBounds(150,70,150,30);
        this.add(id);
        pishniaz1.setBounds(0,120,100,30);
        this.add(pishniaz1);
        pishniaz.setBounds(150,120,150,30);
        this.add(pishniaz);
        name1.setBounds(0,170,100,30);
        this.add(name1);
        name.setBounds(150,170,150,30);
        this.add(name);
        ostad1.setBounds(0,220,100,30);
        this.add(ostad1);
        ostad.setBounds(150,220,150,30);
        this.add(ostad);
        tedadVahed1.setBounds(0,270,100,30);
        this.add(tedadVahed1);
        tedadVahed.setBounds(150,270,150,30);
        this.add(tedadVahed);
        zarfiat1.setBounds(0,320,100,30);
        this.add(zarfiat1);
        zarfiat.setBounds(150,320,150,30);
        this.add(zarfiat);
        daneshkade1.setBounds(0,370,150,30);
        this.add(daneshkade1);
        daneshkade.setBounds(150,370,150,30);
        this.add(daneshkade);
        maghtaDars1.setBounds(0,420,100,30);
        this.add(maghtaDars1);
        maghtaDars.setBounds(150,420,150,30);
        this.add(maghtaDars);
        roozAval.setBounds(150,470,150,30);
        this.add(roozAval);
        roozDovom.setBounds(150,520,150,30);
        this.add(roozDovom);
        saatClass1.setBounds(350,70,200,30);
        this.add(saatClass1);
        saatClass.setBounds(550,70,200,30);
        this.add(saatClass);
        zamanPayanterm1.setBounds(350,120,200,30);
        this.add(zamanPayanterm1);
        zamanPayanterm.setBounds(550,120,200,30);
        this.add(zamanPayanterm);
        zamanPayanterm2.setBounds(550,160,200,30);
        this.add(zamanPayanterm2);

        sabt.setBounds(500,500,150,30);
        this.add(sabt);



    }
    public void addListener(){
        sabt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (id.getText().isEmpty() || name.getText().isEmpty() || ostad.getText().isEmpty() || zarfiat.getText().isEmpty() || saatClass.getText().isEmpty() || zamanPayanterm.getText().isEmpty()){
                    jOptionPane.showMessageDialog(null,"FIELD HAYE ID , NAME VA OSTAD EJBARY HASTAND");
                }
                else {
                    String idDars = id.getText();
                    String nameDars = name.getText();
                    String pishNiazDars = pishniaz.getText();
                    String ostadDars = ostad.getText();
                    String firstDay = String.valueOf(roozAval.getSelectedIndex());
                    String secondDay = String.valueOf(roozDovom.getSelectedIndex());
                    String daneshKadeDars = daneshkade.getItemAt(daneshkade.getSelectedIndex()).toString().toLowerCase();
                    String tedadVahedDars = tedadVahed.getText();
                    String maghtaInDars = maghtaDars.getItemAt(maghtaDars.getSelectedIndex()).toString().toLowerCase();
                    String zarfiatDars = zarfiat.getText();
                    String classTime = saatClass.getText();
                    String examDate = zamanPayanterm.getText();
                    try{
                        Integer.parseInt(idDars);
                        Integer.parseInt(pishNiazDars);
                        Integer.parseInt(ostadDars);
                        Integer.parseInt(zarfiatDars);
                        Integer.parseInt(tedadVahedDars);
                        Integer.parseInt(classTime);
                    }catch (Exception exception){
                        jOptionPane.showMessageDialog(null,"INTEGER FORMAT IS JUST ACCEPTABLE");
                        return;
                    }
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.ADD_LESSON.toString());
                    req.add(idDars);
                    req.add(nameDars);
                    req.add(pishNiazDars);
                    req.add(ostadDars);
                    req.add(daneshKadeDars);
                    req.add(tedadVahedDars);
                    req.add(maghtaInDars);
                    req.add(zarfiatDars);
                    req.add(firstDay + " " + secondDay);
                    req.add(classTime);
                    req.add(examDate);
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);

                    jOptionPane.showMessageDialog(null,"your request has been sent to the server!");
                }
            }
        });

    }
}
