package Pages;

import ClientSide.ClientConfig;

import javax.swing.*;

public class ReqMessagePage extends JPanel {


    public ReqMessagePage(){
        initPanel();
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }

}
