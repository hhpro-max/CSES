package Pages;

import ClientSide.DataHandler;

import javax.swing.*;
import java.util.ArrayList;

public class TeacherProfilePage extends ProfilePage{
    JLabel shomareOtagh1;
    JLabel shomareOtagh2;
    JLabel darakeOstadi1;
    JLabel darakeOstadi2;

    public TeacherProfilePage(){
        super();
        initMoreComps();
        alignMoreComps();

        GuiController.getInstance().addJPanel(this);
    }
    public void initMoreComps(){
        shomareOtagh1 = new JLabel("SHOMARE OTAGH :");
        shomareOtagh2 = new JLabel(DataHandler.getInstance().getRoomNumber());
        darakeOstadi1 = new JLabel("DARAJE OSTADI :");
        darakeOstadi2 =new JLabel(DataHandler.getInstance().getTeacherLevel());
    }
    public void alignMoreComps(){
        shomareOtagh1.setBounds(500,300,200,30);
        shomareOtagh2.setBounds(650,300,200,30);
        this.add(shomareOtagh1);
        this.add(shomareOtagh2);
        darakeOstadi1.setBounds(500,400,200,30);
        darakeOstadi2.setBounds(650,400,200,30);
        this.add(darakeOstadi1);
        this.add(darakeOstadi2);
    }

}
