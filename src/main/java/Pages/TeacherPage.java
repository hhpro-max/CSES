package Pages;

import Listeners.LessonsListListener;
import Listeners.TeacherRecReqListener;
import Listeners.TeacherTemporaryGradeListener;
import Listeners.TeachersListListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherPage extends MainPage{
    public JMenuItem recommendReq;
    public JMenuItem temporaryGrades;
    public JMenuItem userProfileMenu;

    public TeacherPage(){
        super();
        initMenuBar();
        addMoreListener();
    }



    private void initMenuBar() {
        jMenuBar = new JMenuBar();
        jMenuBar.setBounds(80,0,720,30);
        temporaryGrades = new JMenuItem("SCORES");
        recommendReq = new JMenuItem("RECOMMEND REQ");
        userProfileMenu = new JMenuItem("PROFILE");
        requests.add(recommendReq);
        gradeService.add(temporaryGrades);
        userProfile.add(userProfileMenu);
    }
    private void addMoreListener() {
        TeacherRecReqListener teacherRecReqListener = new TeacherRecReqListener();
        recommendReq.addActionListener(teacherRecReqListener);
        TeacherTemporaryGradeListener teacherTemporaryGradeListener = new TeacherTemporaryGradeListener();
        temporaryGrades.addActionListener(teacherTemporaryGradeListener);

        userProfileMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
