package Pages;

import ClientSide.DataHandler;
import Listeners.LessonsListListener;
import Listeners.TeacherRecReqListener;
import Listeners.TeacherTemporaryGradeListener;
import Listeners.TeachersListListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TeacherPage extends MainPage{
    public JMenuItem recommendReq;
    public JMenuItem temporaryGrades;
    public JMenuItem userProfileMenu;
    public JMenuItem eduSchedule;
    public JMenu cw;
    public JMenu myLessons;

    public TeacherPage(){
        super();
        DataHandler.getInstance().updateUserLessons();
        DataHandler.getInstance().updateCwLessonsEduSubject();
        DataHandler.getInstance().updateHomeWorks();
        DataHandler.getInstance().updateUploadedHm();
        initMenuBar();
        addMoreListener();
    }



    private void initMenuBar() {
        temporaryGrades = new JMenuItem("SCORES");
        recommendReq = new JMenuItem("RECOMMEND REQ");
        userProfileMenu = new JMenuItem("PROFILE");
        requests.add(recommendReq);
        gradeService.add(temporaryGrades);
        userProfile.add(userProfileMenu);
        cw = new JMenu("COURSE WARE");
        myLessons = new JMenu("MY LESSONS");
        eduSchedule = new JMenuItem("EDU SCHEDULE");
        //
        initCWMyLesson();
        //
        cw.add(myLessons);
        cw.add(eduSchedule);
        jMenuBar.add(cw);
    }
    private void addMoreListener() {
        TeacherRecReqListener teacherRecReqListener = new TeacherRecReqListener();
        recommendReq.addActionListener(teacherRecReqListener);
        TeacherTemporaryGradeListener teacherTemporaryGradeListener = new TeacherTemporaryGradeListener();
        temporaryGrades.addActionListener(teacherTemporaryGradeListener);
        userProfileMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.TEACHER_PROFILE_PAGE);
            }
        });
        eduSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.CW_SCHEDULE_PAGE);
            }
        });
    }
    public void initCWMyLesson(){
        //
        for (List<String> i:
                DataHandler.getInstance().getUserLessons()) {
            JMenuItem jMenuItem = new JMenuItem(i.get(1));
            boolean isContained = false;
            for (Component j:
                    myLessons.getMenuComponents()) {
                if (j instanceof JMenuItem){
                    if (((JMenuItem) j).getText().equals(jMenuItem.getText())){
                        isContained = true;
                    }
                }
            }
            if (!isContained){
                jMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TeacherCwLessonPage teacherCwLessonPage = new TeacherCwLessonPage(jMenuItem.getText());
                        GuiController.getInstance().userCurrentPanel = teacherCwLessonPage;
                        GuiController.getInstance().getFrame().add(teacherCwLessonPage,1);
                        repaint();
                        revalidate();
                    }
                });

                myLessons.add(jMenuItem);
            }
        }
        //
    }
}
