package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;
import Listeners.ExitListener;
import Listeners.MainPanelListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MrMohseniMainPage extends JPanel {
    JMenuBar jMenuBar;
    JMenu students;
    JMenu msg;
    JMenuItem studentList;
    JMenuItem sendMsg;
    JButton exit;
    JButton mainPage;
    public MrMohseniMainPage(){
        DataHandler.getInstance().updateAllStudents();
        initPanel();
        initComps();
        align();
        addListener();
        GuiController.getInstance().updateFrame();
    }
    public void initPanel(){
        this.setBounds(0, 0, ClientConfig.mainFrameWidth, ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        mainPage = new JButton("MAIN PANEL");
        exit = new JButton("EXIT");
        jMenuBar = new JMenuBar();
        students = new JMenu("STUDENTS");
        jMenuBar.add(students);
        msg = new JMenu("MESSAGE");
        jMenuBar.add(msg);
        studentList = new JMenuItem("STUDENTS LIST");
        students.add(studentList);
        sendMsg = new JMenuItem("SEND NEW MESSAGE");
        msg.add(sendMsg);
    }
    public void align(){
        jMenuBar.setBounds(80,0,ClientConfig.mainFrameWidth - 200,30);
        this.add(jMenuBar);
        exit.setBounds(0,0,80,30);
        this.add(exit);
        mainPage.setBounds(ClientConfig.mainFrameWidth - 500,0,150,30);
        this.add(mainPage);
    }
    public void addListener(){
        studentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.SEARCH_IN_STUDENTS_PAGE);
            }
        });
        ExitListener exitListener = new ExitListener();
        exit.addActionListener(exitListener);
        MainPanelListener mainPanelListener = new MainPanelListener();
        mainPage.addActionListener(mainPanelListener);
    }
}
