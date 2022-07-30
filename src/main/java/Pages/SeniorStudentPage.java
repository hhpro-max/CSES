package Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeniorStudentPage extends StudentPage{
    public JMenuItem darkhastKhabGah;

    public SeniorStudentPage(){
        super();
        initMoreComps();
        addMoreListener();
    }
    public void initMoreComps(){
        darkhastKhabGah=new JMenuItem("DARKHAST KHAB GAH");
        requests.remove(minorReq);
        requests.add(darkhastKhabGah);
    }
    public void addMoreListener(){
        darkhastKhabGah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.DORMITORY_REQ_PAGE);
            }
        });
    }
}
