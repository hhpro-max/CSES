package Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorStudentPage extends StudentPage{
    public JMenuItem nobatDefa;

    public DoctorStudentPage(){
        super();
        initMoreComps();
        addMoreListener();
    }

    private void initMoreComps() {
        nobatDefa = new JMenuItem("DARKHAST NOBAT DEFA");
        requests.add(nobatDefa);
        requests.remove(minorReq);
        requests.remove(recommendReq);
    }
    public void addMoreListener(){
        nobatDefa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.DEFENCE_TIME_PAGE);
            }
        });
    }
}
