package Pages;

import ClientSide.Client;

import javax.swing.*;
import java.util.ArrayList;

public class GuiController {
    Client client;
    public JOptionPane jOptionPane;
    public  JFrame frame;
    public JPanel userMainPanel;

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
    public void changePanelTo(PanelType panelType){
        switch (panelType){
            case STUDENTMAINPAGE  :
                resetJPanels();
                NormalStudentPage normalStudentPage = new NormalStudentPage();
                userMainPanel = normalStudentPage;
                frame.add(normalStudentPage);
                updateFrame();
                break;
        }
    }
    public void exitButton(){
        resetJPanels();
        LoginPage loginPage = new LoginPage();
        frame.add(loginPage);
        updateFrame();
    }
    public void goToMainPanel(){
        resetJPanels();
        getFrame().add(getUserMainPanel());
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

    public JPanel getUserMainPanel() {
        return userMainPanel;
    }

    public void setUserMainPanel(JPanel userMainPanel) {
        this.userMainPanel = userMainPanel;
    }
}
