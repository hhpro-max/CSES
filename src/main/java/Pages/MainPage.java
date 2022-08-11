package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;
import Listeners.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MainPage extends JPanel {
    int width = ClientConfig.mainFrameWidth;
    int height = ClientConfig.mainFrameHeight;

    public JMenuBar jMenuBar;
    public JMenu registration;
    public JMenu eduService;
    public JMenu gradeService;
    public JMenu userProfile;
    public JMenu requests;
    public JMenu chats;
    public JMenuItem chatPanel;
    public JMenuItem chatReq;
    public JButton mainPage;
    public JMenuItem lessonsList;
    public JMenuItem teachersList;
    public JMenuItem weeklyPlan;
    public JMenuItem examsList;
    public JMenuItem reqMessage;


    public Timer timer;
    public JLabel showTime;
    public JButton exit;
    public JLabel lastLoginTime; //todo align this
    public JLabel imageIcon;
    public JLabel name;
    public JLabel email;

    public MainPage() {
        initPanel();
        initCom();
        align();
        addListener();

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
        name = new JLabel("yourName : " + DataHandler.getInstance().getFullName());
        email = new JLabel("yourEmail : " +DataHandler.getInstance().getEmail());

        //menu items
        jMenuBar = new JMenuBar();
        registration = new JMenu("REGISTRATION");
        gradeService = new JMenu("GRADE SERVICE");
        eduService = new JMenu("EDU SERVICE");
        chats = new JMenu("CHATS");
        chatPanel = new JMenuItem("CHAT PANEL");
        chatReq = new JMenuItem("NEW CHAT");
        chats.add(chatPanel);
        chats.add(chatReq);
        weeklyPlan = new JMenuItem("WEEKLY PLAN");
        eduService.add(weeklyPlan);
        examsList = new JMenuItem("EXAMS");
        eduService.add(examsList);
        requests = new JMenu("REQUESTS");
        reqMessage = new JMenuItem("REQ MESSAGES");
        requests.add(reqMessage);
        eduService.add(requests);
        userProfile = new JMenu("PROFILE");
        mainPage = new JButton("MAIN PANEL");
        teachersList = new JMenuItem("TEACHERS");
        lessonsList =new JMenuItem("LESSONS");
        registration.add(lessonsList);
        registration.add(teachersList);
        jMenuBar.add(gradeService);
        jMenuBar.add(eduService);
        jMenuBar.add(userProfile);
        jMenuBar.add(registration);
        jMenuBar.add(chats);

        //
        HelpPage connectionStatusPage = new HelpPage();
        JScrollPane jScrollPane = new JScrollPane(connectionStatusPage);
        jScrollPane.setBounds(ClientConfig.mainFrameWidth - 800,ClientConfig.mainFrameHeight - 450,400,350);
        this.add(jScrollPane);
        //
    }
    public void align(){
        mainPage.setBounds(ClientConfig.mainFrameWidth - 500,0,150,30);
        this.add(mainPage);
        jMenuBar.setBounds(80,0,ClientConfig.mainFrameWidth - 200,30);
        this.add(jMenuBar);
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
        MainPanelListener mainPanelListener = new MainPanelListener();
        mainPage.addActionListener(mainPanelListener);

        //

        LessonsListListener lessonsListListener = new LessonsListListener();
        lessonsList.addActionListener(lessonsListListener);
        TeachersListListener teachersListListener = new TeachersListListener();
        teachersList.addActionListener(teachersListListener);
        WeeklyPlanListener weeklyPlanListener = new WeeklyPlanListener();
        weeklyPlan.addActionListener(weeklyPlanListener);
        ExamListListener examListListener = new ExamListListener();
        examsList.addActionListener(examListListener);
        //
        reqMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.REQ_MESSAGES_PAGE);
            }
        });
        chatPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.CHAT_PAGE);
            }
        });
        chatReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.NEW_CHAT_PAGE);
            }
        });
    }
}
