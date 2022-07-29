package Pages;

import ClientSide.DataHandler;
import Listeners.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentPage extends MainPage{
    public JTable studyStatus;
    public JLabel showStatus;

    public JMenuItem studyEvidenceReq;
    public JMenuItem minorReq;
    public JMenuItem leaveReq;
    public JMenuItem recommendReq;
    public JMenuItem temporaryGrades;
    public JMenuItem eduStatus;
    public JMenuItem userProfileMenu;

    public StudentPage() {
        super();
        initTable();
        initMenubar();
        addMoreListeners();
    }




    private void initTable() {
        showStatus = new JLabel("education status");
        showStatus.setBounds(200,170,250,30);
        this.add(showStatus);
        String educationStatus = DataHandler.getInstance().getEducationStatus();
        String supervisorName = DataHandler.getInstance().getSupervisorName();
        String signupPermit = "NAMALOOM";
        if (DataHandler.getInstance().isSignUpPermit()) {
            signupPermit = "TRUE";
        } else {
            signupPermit = "FALSE";
        }
        String signupTime = DataHandler.getInstance().getSignupTime();

        String data[][] = {
                {"EDU STATUS :", educationStatus},
                {"SUPERVISOR :",supervisorName},
                {"SIGNUP PERMIT :",signupPermit},
                {"SIGNUP TIME :",signupTime}
        };

        String column[] = {" EDU STATUS "," _ "};
        studyStatus = new JTable(data,column);
        studyStatus.setBounds(200,200,500,70);
        JScrollPane jScrollPane = new JScrollPane(studyStatus);
        this.add(studyStatus);
    }
    private void initMenubar() {
        jMenuBar = new JMenuBar();
        jMenuBar.setBounds(80,0,720,30);
        eduStatus = new JMenuItem("EDU STATUS");
        temporaryGrades = new JMenuItem("TEMPORARY GRADES LIST");
        userProfileMenu = new JMenuItem("PROFILE");
        leaveReq = new JMenuItem("LEAVE REQ");
        minorReq = new JMenuItem("MINOR");
        studyEvidenceReq = new JMenuItem("STUDY EVIDENCE");
        recommendReq = new JMenuItem("RECOMMEND REQ");
        requests.add(leaveReq);
        requests.add(recommendReq);
        requests.add(minorReq);
        requests.add(studyEvidenceReq);
        gradeService.add(temporaryGrades);
        gradeService.add(eduStatus);
        userProfile.add(userProfileMenu);
    }

    private void addMoreListeners() {
        StudentRecReqListener studentRecReqListener = new StudentRecReqListener();
        recommendReq.addActionListener(studentRecReqListener);
        studyEvidenceReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.STUDYEVIDENCEREQPAGE);
            }
        });
        MinorReqListener minorReqListener = new MinorReqListener();
        minorReq.addActionListener(minorReqListener);
        LeaveReqListener leaveReqListener = new LeaveReqListener();
        leaveReq.addActionListener(leaveReqListener);
        TemporaryGradesListener temporaryGradesListener = new TemporaryGradesListener();
        temporaryGrades.addActionListener(temporaryGradesListener);
        StudentEduStatusListener studentEduStatusListener = new StudentEduStatusListener();
        eduStatus.addActionListener(studentEduStatusListener);

        userProfileMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.STUDENT_PROFILE_PAGE);
            }
        });
    }
}
