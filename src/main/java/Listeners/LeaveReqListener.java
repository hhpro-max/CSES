package Listeners;



import ClientSide.DataHandler;
import Pages.GuiController;
import Pages.PanelType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaveReqListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DataHandler.getInstance().updateLeaveReqList();
        GuiController.getInstance().changePanelTo(PanelType.LEAVEREQPAGE);
    }
}
