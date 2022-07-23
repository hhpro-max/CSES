package ClientSide;

import Listeners.ExitListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MainPage extends JPanel {
    int width = ClientConfig.mainFrameWidth;
    int height = ClientConfig.mainFrameHeight;

    public Timer timer;
    public JLabel showTime;
    public JButton exit;
    public JLabel lastLoginTime;
    public JLabel imageIcon;
    public JLabel name;
    public JLabel email;

    public MainPage() {
        initPanel();
        initCom();
        align();
    }

    public void initPanel() {

        this.setBounds(0, 0, width, height);
        this.setVisible(true);
        this.setLayout(null);

    }
    public void initCom(){
        showTime = new JLabel();
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTime.setText(new Date().toString());
            }
        });
        timer.start();

        exit = new JButton("EXIT");
        lastLoginTime = new JLabel(DataHandler.getInstance().getLastLoginTime());
        imageIcon = new JLabel(DataHandler.getInstance().getImageIcon());
        name = new JLabel("yourName : " + DataHandler.getInstance().getFirstname());
        email = new JLabel("yourEmail : " +DataHandler.getInstance().getEmail());



    }
    public void align(){
        showTime.setBounds(0,35,200,30);
        this.add(showTime);
        exit.setBounds(0,0,80,30);
        this.add(exit);
        imageIcon.setBounds(0,70,100,100);
        this.add(imageIcon);
        name.setBounds(0,200,200,30);
        this.add(name);
        email.setBounds(0,230,300,30);
        this.add(email);
    }
    public void addListener(){
        //
        ExitListener exitListener = new ExitListener();
        exit.addActionListener(exitListener);
        //
    }
}
