package Pages;

import ClientSide.Client;
import ClientSide.ClientConfig;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    int width = ClientConfig.mainFrameWidth;
    int height = ClientConfig.mainFrameHeight;
    LoginPage loginPage;
    public MainFrame(){

        initFrame();
        loginPage = new LoginPage();
        this.add(loginPage);
        update();

        GuiController.getInstance().setFrame(this);
    }


    public void initFrame(){


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(true);
        this.setSize(new Dimension(width,height));



    }
    public void update(){

        this.repaint();
        this.revalidate();
    }
    public JFrame getFrame(){
        return this;
    }
}
