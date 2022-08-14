package Listeners;

import ClientSide.ClientReqType;
import ClientSide.DataHandler;
import Pages.GuiController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GuiController.getInstance().exitButton();
        DataHandler.getInstance().resetData();
        List<String> req = new ArrayList<>();
        req.add(ClientReqType.DISCONNECT.toString());
        GuiController.getInstance().getClient().getClientSender().sendMessage(req);
    }
}
