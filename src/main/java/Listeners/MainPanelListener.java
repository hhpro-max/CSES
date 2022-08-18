package Listeners;

import Pages.GuiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GuiController.getInstance().goToMainPanel();

    }
}
