package Pages;

import Listeners.ExitListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class EduAdminMainPage extends ChatPage{
    public Timer timer;
    public JLabel showTime;
    public JButton exit;
    public EduAdminMainPage(){
        super();
        showTime = new JLabel();
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTime.setText(new Date().toString());
            }
        });
        timer.start();
        exit = new JButton("EXIT");
        showTime.setBounds(0,35,200,30);
        this.add(showTime);
        exit.setBounds(0,0,80,30);
        this.add(exit);
        ExitListener exitListener = new ExitListener();
        exit.addActionListener(exitListener);
    }
}
