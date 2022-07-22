package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiController {
    Client client;

    public static JFrame frame;

    public static ArrayList<JPanel> jPanels = new ArrayList<>();


    public static GuiController guiController;

    private GuiController() {

    }

    public static GuiController getInstance() {
        if (guiController == null) {
            guiController = new GuiController();
        }
        return guiController;
    }

    public void resetJpanels() {
        for (JPanel i :
                jPanels) {
            i.setVisible(false);
        }
        jPanels.clear();

    }
    public void changePanelTo(JPanel jPanel){
        //todo
    }


    public void addJpannel(JPanel jPanel) {
        jPanels.add(jPanel);
    }


    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        GuiController.frame = frame;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void updateFrame(){
        frame.repaint();
        frame.revalidate();
    }
}
