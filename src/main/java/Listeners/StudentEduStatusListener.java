package Listeners;

import ClientSide.DataHandler;
import Pages.GuiController;
import Pages.PanelType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentEduStatusListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DataHandler.getInstance().updateUserLessons();
        GuiController.getInstance().changePanelTo(PanelType.STUDENT_EDU_STATUS_PAGE);
    }
}
