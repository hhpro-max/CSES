package Pages;

import ClientSide.ClientConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class DefenceTimePage extends JPanel {
    Date date;
    JButton jButton;
    JLabel jLabel;
    int day;
    int month;
    int year;

    public DefenceTimePage() {
        initPanel();
        initComps();
        align();
        addListener();
    }

    public void initPanel() {
        this.setBounds(0, 30, ClientConfig.mainFrameWidth, ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void initComps() {
        date = new Date();
        jButton = new JButton("SABT DARKHAST DEFA");
        day = date.getDate();
        month = date.getMonth() + 1;
        year = date.getYear();
        if (month > 12) {
            month = 0;
            year = year + 1;
        }
        jLabel = null;
    }

    public void align() {
        jButton.setBounds(10, 50, 200, 30);
        this.add(jButton);
    }

    public void addListener() {
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jLabel == null) {
                    jLabel = new JLabel("TARIKH NOBAT DEFA SHOMA : " + year + " / " + month + " / " + day);
                    jLabel.setBounds(30, 100, 400, 30);
                    add(jLabel);
                    repaint();
                    revalidate();
                }

            }
        });
    }
}

