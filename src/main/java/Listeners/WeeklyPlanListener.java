package Listeners;

import ClientSide.ClientReqType;
import Pages.GuiController;
import Pages.PanelType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WeeklyPlanListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        List<String> req = new ArrayList<>();
        req.add(ClientReqType.GETUSERLESSONS.toString());
        GuiController.getInstance().getClient().getClientSender().sendMessage(req);
        GuiController.getInstance().changePanelTo(PanelType.WEEKLYPLANPAGE);

    }
}
