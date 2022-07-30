package Pages;

import ClientSide.DataHandler;

import javax.swing.*;

public class StudentProfilePage extends ProfilePage{
    JLabel moadel1;
    JLabel moadel2;
    JLabel ostadRahanama1;
    JLabel ostadRahanama2;
    JLabel salVorod1;
    JLabel salVorod2;
    JLabel maghta1;
    JLabel maghta2;
    JLabel vaziat1;
    JLabel vaziat2;

    public StudentProfilePage(){
        super();
        initMoreComps();
        alignMoreComps();
        GuiController.getInstance().addJPanel(this);
    }



    private void initMoreComps() {
        moadel1 = new JLabel("MOADEL KOL :");
        moadel2 = new JLabel(String.valueOf(DataHandler.getInstance().getAverageGrade()));
        ostadRahanama1 = new JLabel("OSTAD RAHNAMA :");
        ostadRahanama2 = new JLabel(DataHandler.getInstance().getSupervisorName());
        salVorod1 = new JLabel("SAL VOROOD :");
        salVorod2 =new JLabel(DataHandler.getInstance().getEntranceYear());
        maghta1 = new JLabel("MAGHTA :");
        maghta2 = new JLabel(DataHandler.getInstance().getEduLevel());
        vaziat1 = new JLabel("VAZIAT :");
        vaziat2 = new JLabel(DataHandler.getInstance().getEducationStatus());
    }
    private void alignMoreComps() {
        moadel1.setBounds(10,600,200,30);
        moadel2.setBounds(200,600,150,30);
        this.add(moadel1);
        this.add(moadel2);
        ostadRahanama1.setBounds(500,300,200,30);
        ostadRahanama2.setBounds(650,300,200,30);
        this.add(ostadRahanama1);
        this.add(ostadRahanama2);
        salVorod1.setBounds(500,400,200,30);
        salVorod2.setBounds(650,400,200,30);
        this.add(salVorod1);
        this.add(salVorod2);
        maghta1.setBounds(500,500,200,30);
        maghta2.setBounds(650,500,200,30);
        this.add(maghta1);
        this.add(maghta2);
        vaziat1.setBounds(500,600,200,30);
        vaziat2.setBounds(650,600,200,30);
        this.add(vaziat1);
        this.add(vaziat2);
    }
}
