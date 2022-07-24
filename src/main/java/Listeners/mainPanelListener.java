package Listeners;

import Pages.GuiController;
import Pages.PanelType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainPanelListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //todo
        GuiController.getInstance().goToMainPanel();

    }
}
