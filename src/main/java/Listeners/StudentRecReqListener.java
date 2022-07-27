package Listeners;

import ClientSide.DataHandler;
import Pages.GuiController;
import Pages.PanelType;
import Pages.StudentRecReqPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRecReqListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DataHandler.getInstance().updateRecommendsList();
        GuiController.getInstance().changePanelTo(PanelType.STUDENTRECREQPAGE);
    }
}
