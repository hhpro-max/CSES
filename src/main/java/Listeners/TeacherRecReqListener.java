package Listeners;

import ClientSide.DataHandler;
import Pages.GuiController;
import Pages.PanelType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherRecReqListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DataHandler.getInstance().updateRecommendsList();
        GuiController.getInstance().changePanelTo(PanelType.TEACHER_REC_REQ_PAGE);
    }
}
