package Pages;

import ClientSide.ClientConfig;
import ClientSources.ImageResource;
import ClientSources.ResourceManager;
import Listeners.LoginListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class LoginPage extends JPanel{
    public static int capchacounter = 0;
    public static JLabel thiscapcha;
    ArrayList<JLabel> capchas = new ArrayList<>();
    public JLabel capcha1;
    public JLabel capcha2;
    public JLabel capcha3;
    public JLabel capcha4;
    public JLabel capcha5;
    public JLabel capcha6;

    int width = ClientConfig.mainFrameWidth;
    int height = ClientConfig.mainFrameHeight;

    public JButton logIn;
    public JButton refreshcapcha;
    public static JTextField capchafield;
    public static JPasswordField passwordField;
    public static JTextField usernameField;

    public JLabel nameLabel;
    public JLabel passLabel;

    public Timer timer;
    public JLabel showTime;

    public JOptionPane jOptionPane;

    public LoginListener loginListener;

    public LoginPage(){
        initPanel();
        initCom();
        align();
        addListener();
        GuiController.getInstance().jPanels.add(this);
    }
    public void initPanel() {

        this.setBounds(0, 0, width, height);
        this.setVisible(true);
        this.setLayout(null);

    }
    public void initCom() {

        logIn = new JButton("LOGIN");
        passwordField = new JPasswordField();
        usernameField = new JTextField();
        nameLabel = new JLabel("USERNAME  :");
        passLabel = new JLabel("PASSWORD  :");

        refreshcapcha = new JButton("NEXT");
        capchafield = new JTextField();


        //timer
        showTime = new JLabel();

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTime.setText(new Date().toString());
            }
        });
        timer.start();


        capcha1 = new JLabel();
        ImageIcon imageIcon = new ImageIcon(ResourceManager.get(ImageResource.CAPCHA1));
        imageIcon.getImage().flush();
        capcha1.setIcon(imageIcon);
        capcha1.setName("3a4d");

        capcha2 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(ResourceManager.get(ImageResource.CAPCHA2));
        imageIcon1.getImage().flush();
        capcha2.setIcon(imageIcon1);
        capcha2.setName("xoe");

        capcha3 = new JLabel();
        ImageIcon imageIcon2 = new ImageIcon(ResourceManager.get(ImageResource.CAPCHA3));
        imageIcon2.getImage().flush();
        capcha3.setIcon(imageIcon2);
        capcha3.setName("pls");

        capcha4 = new JLabel();
        ImageIcon imageIcon3 = new ImageIcon(ResourceManager.get(ImageResource.CAPCHA4));
        imageIcon3.getImage().flush();
        capcha4.setIcon(imageIcon3);
        capcha4.setName("help");

        capcha5 = new JLabel();
        ImageIcon imageIcon4 = new ImageIcon(ResourceManager.get(ImageResource.CAPCHA5));
        imageIcon4.getImage().flush();
        capcha5.setIcon(imageIcon4);
        capcha5.setName("me");

        capcha6 = new JLabel();
        ImageIcon imageIcon5 = new ImageIcon(ResourceManager.get(ImageResource.CAPCHA6));
        imageIcon5.getImage().flush();
        capcha6.setIcon(imageIcon5);
        capcha6.setName("sos");

        capchas.add(capcha1);
        capchas.add(capcha2);
        capchas.add(capcha3);
        capchas.add(capcha4);
        capchas.add(capcha5);
        capchas.add(capcha6);



    }
    public void align() {


        nameLabel.setBounds(100, 250, 90, 30);
        this.add(nameLabel);
        usernameField.setBounds(230, 250, 150, 30);
        this.add(usernameField);
        passLabel.setBounds(100, 350, 90, 30);
        this.add(passLabel);
        passwordField.setBounds(230, 350, 150, 30);
        this.add(passwordField);
        logIn.setBounds(550, 650, 150, 60);
        this.add(logIn);

        thiscapcha = new JLabel();
        thiscapcha.setIcon(capchas.get(0).getIcon());
        thiscapcha.setName(capchas.get(0).getName());
        thiscapcha.setBounds(200, 420, 100, 80);
        this.add(thiscapcha);
        refreshcapcha.setBounds(240, 510, 80, 50);
        this.add(refreshcapcha);
        capchafield.setBounds(200, 590, 100, 30);
        this.add(capchafield);
        jOptionPane = new JOptionPane();
        this.add(jOptionPane);
        //timer
        showTime.setBounds(100, 200, 200, 30);
        this.add(showTime);


    }

    public void addListener(){
        refreshcapcha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capchacounter++;

                capcharesetcheck();

                thiscapcha.setIcon(capchas.get(capchacounter).getIcon());
                thiscapcha.setName(capchas.get(capchacounter).getName());
                update();
                GuiController.getInstance().updateFrame();

            }
        });
        loginListener = new LoginListener();
        logIn.addActionListener(loginListener);


    }
    void capcharesetcheck() {
        if (capchacounter > 5) {
            capchacounter = 0;
        }
    }

    public void update() {

        this.repaint();
        this.revalidate();

    }

}
