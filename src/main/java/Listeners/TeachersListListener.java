package Listeners;

import ClientSide.ClientReqType;
import Pages.GuiController;
import Pages.PanelType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TeachersListListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        List<String> orders = new ArrayList<>();
        orders.add(ClientReqType.GETTEACHERSLIST.toString());
        GuiController.getInstance().getClient().getClientSender().sendMessage(orders);
        GuiController.getInstance().changePanelTo(PanelType.TEACHERSLISTPAGE);
    }
}
