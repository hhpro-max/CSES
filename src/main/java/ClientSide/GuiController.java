package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiController {
    Client client;
    public JOptionPane jOptionPane;
    public  JFrame frame;

    public  ArrayList<JPanel> jPanels = new ArrayList<>();


    public static GuiController guiController;

    private GuiController() {
        jOptionPane = new JOptionPane();
    }

    public static GuiController getInstance() {
        if (guiController == null) {
            guiController = new GuiController();
        }
        return guiController;
    }

    public void resetJPanels() {
        for (JPanel i :
                jPanels) {
            i.setVisible(false);
        }
        jPanels.clear();

    }
    public void changePanelTo(JPanel jPanel){
        //todo
    }
    public void exitButton(){
        resetJPanels();
        LoginPage loginPage = new LoginPage();
        frame.add(loginPage);
        updateFrame();
    }

    public void addJPanel(JPanel jPanel) {
        jPanels.add(jPanel);
    }


    public  JFrame getFrame() {
        return frame;
    }

    public  void setFrame(JFrame frame) {
        this.frame = frame;
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
