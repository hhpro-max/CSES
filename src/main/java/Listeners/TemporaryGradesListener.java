package Listeners;

import ClientSide.DataHandler;
import Pages.GuiController;
import Pages.PanelType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemporaryGradesListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DataHandler.getInstance().updateTemporaryGradesList();
        GuiController.getInstance().changePanelTo(PanelType.TEMPORARYGRADELISTPAGE);
    }
}
