package Listeners;

import ClientSide.DataHandler;
import Pages.GuiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GuiController.getInstance().exitButton();
        DataHandler.getInstance().resetData();
    }
}
