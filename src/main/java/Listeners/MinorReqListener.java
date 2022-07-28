package Listeners;

import ClientSide.DataHandler;
import Pages.GuiController;
import Pages.PanelType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinorReqListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DataHandler.getInstance().updateMinorReqList();
        GuiController.getInstance().changePanelTo(PanelType.MINORREQPAGE);
    }
}
