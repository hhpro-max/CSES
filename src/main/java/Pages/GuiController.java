package Pages;

import ClientSide.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiController {
    Client client;
    public JOptionPane jOptionPane;
    public JFrame frame;
    public JPanel userMainPanel;
    public JPanel userCurrentPanel;

    public ArrayList<JPanel> jPanels = new ArrayList<>();


    public static GuiController guiController;

    private GuiController() {
        jOptionPane = new JOptionPane();

    }

    public static GuiController getInstance() {
        if (guiController == null) {
            guiController = new GuiController();
        }
        return guiController;
    }


    public void resetJPanels() {
        for (JPanel i :
                jPanels) {
            i.setVisible(false);
        }
        jPanels.clear();

    }

    public void changePanelTo(PanelType panelType) {
        resetJPanels();
        switch (panelType) {
            case EDU_MANAGER_MAIN_PAGE:
                EduManagerPage eduManagerPage = new EduManagerPage();
                userCurrentPanel = eduManagerPage;
                userMainPanel = eduManagerPage;
                frame.add(eduManagerPage);
                break;
            case EDU_ASSISTANT_MAIN_PAGE:
                EduAssistantPage eduAssistantPage = new EduAssistantPage();
                userCurrentPanel = eduAssistantPage;
                userMainPanel = eduAssistantPage;
                frame.add(eduAssistantPage);
                break;
            case DOCTOR_STUDENT_MAIN_PAGE:
                DoctorStudentPage doctorStudentPage = new DoctorStudentPage();
                userCurrentPanel = doctorStudentPage;
                userMainPanel = doctorStudentPage;
                frame.add(doctorStudentPage);
                break;
            case SENIOR_STUDENT_MAIN_PAGE:
                SeniorStudentPage seniorStudentPage = new SeniorStudentPage();
                userCurrentPanel = seniorStudentPage;
                userMainPanel = seniorStudentPage;
                frame.add(seniorStudentPage);
                break;
            case STUDENTMAINPAGE:
                StudentPage normalStudentPage = new StudentPage();
                userMainPanel = normalStudentPage;
                userCurrentPanel = normalStudentPage;
                frame.add(normalStudentPage);
                break;
            case TEACHERMAINPAGE:
                TeacherPage teacherPage = new TeacherPage();
                userMainPanel = teacherPage;
                userCurrentPanel = teacherPage;
                frame.add(teacherPage);
                break;
            case DEFENCE_TIME_PAGE:
                DefenceTimePage defenceTimePage = new DefenceTimePage();
                userCurrentPanel = defenceTimePage;
                frame.add(defenceTimePage, 1);
                break;
            case LESSONSLISTPAGE:
                LessonsListPage lessonsListPage = new LessonsListPage();
                userCurrentPanel = lessonsListPage;
                frame.add(lessonsListPage, 1);
                break;
            case TEACHERSLISTPAGE:
                TeachersListPage teachersListPage = new TeachersListPage();
                userCurrentPanel = teachersListPage;
                frame.add(teachersListPage, 1);
                break;
            case WEEKLYPLANPAGE:
                WeeklyPlanPage weeklyPlanPage = new WeeklyPlanPage();
                userCurrentPanel = weeklyPlanPage;
                frame.add(weeklyPlanPage, 1);
                break;
            case EXAMSLISTPAGE:
                ExamsListPage examsListPage = new ExamsListPage();
                userCurrentPanel = examsListPage;
                frame.add(examsListPage, 1);
                break;
            case STUDENTRECREQPAGE:
                StudentRecReqPage studentRecReqPage = new StudentRecReqPage();
                userCurrentPanel = studentRecReqPage;
                frame.add(studentRecReqPage, 1);
                break;
            case STUDYEVIDENCEREQPAGE:
                StudyEvidenceReqPage studyEvidenceReqPage = new StudyEvidenceReqPage();
                userCurrentPanel = studyEvidenceReqPage;
                frame.add(studyEvidenceReqPage, 1);
                break;
            case MINORREQPAGE:
                MinorReqPage minorReqPage = new MinorReqPage();
                userCurrentPanel = minorReqPage;
                frame.add(minorReqPage, 1);
                break;
            case LEAVEREQPAGE:
                LeaveReqPage leaveReqPage = new LeaveReqPage();
                userCurrentPanel = leaveReqPage;
                frame.add(leaveReqPage, 1);
                break;
            case TEMPORARYGRADELISTPAGE:
                TemporaryGradesPage temporaryGradesPage = new TemporaryGradesPage();
                userCurrentPanel = temporaryGradesPage;
                frame.add(temporaryGradesPage, 1);
                break;
            case STUDENT_EDU_STATUS_PAGE:
                StudentEduStatusPage studentEduStatusPage = new StudentEduStatusPage();
                userCurrentPanel = studentEduStatusPage;
                frame.add(studentEduStatusPage, 1);
                break;
            case STUDENT_PROFILE_PAGE:
                StudentProfilePage studentProfilePage = new StudentProfilePage();
                userCurrentPanel = studentProfilePage;
                frame.add(studentProfilePage, 1);
                break;
            case TEACHER_REC_REQ_PAGE:
                TeacherRecReqPage teacherRecReqPage = new TeacherRecReqPage();
                userCurrentPanel = teacherRecReqPage;
                frame.add(teacherRecReqPage, 1);
                break;
            case TEACHER_TEMPORARY_GRADES_PAGE:
                TeacherTemporaryGradesPage teacherTemporaryGradesPage = new TeacherTemporaryGradesPage();
                userCurrentPanel = teacherTemporaryGradesPage;
                frame.add(teacherTemporaryGradesPage, 1);
                break;
            case TEACHER_PROFILE_PAGE:
                TeacherProfilePage teacherProfilePage = new TeacherProfilePage();
                userCurrentPanel = teacherProfilePage;
                frame.add(teacherProfilePage, 1);
                break;
            case DORMITORY_REQ_PAGE:
                DormitoryReqPage dormitoryReqPage = new DormitoryReqPage();
                userCurrentPanel = dormitoryReqPage;
                frame.add(dormitoryReqPage, 1);
                break;
            case ADD_LESSON_PAGE:
                AddLessonPage addLessonPage = new AddLessonPage();
                userCurrentPanel = addLessonPage;
                frame.add(addLessonPage, 1);
                break;
            case ADD_STUDENT_PAGE:
                AddStudentPage addStudentPage = new AddStudentPage();
                userCurrentPanel = addStudentPage;
                frame.add(addStudentPage, 1);
                break;
            case ADD_TEACHER_PAGE:
                AddTeacherPage addTeacherPage = new AddTeacherPage();
                userCurrentPanel = addTeacherPage;
                frame.add(addTeacherPage, 1);
                break;
            case CHECK_MINOR_PAGE:
                CheckMinorPage checkMinorPage = new CheckMinorPage();
                userCurrentPanel = checkMinorPage;
                frame.add(checkMinorPage, 1);
                break;
            case CHECK_LEAVE_REQ_PAGE:
                CheckLeaveReqPage checkLeaveReqPage = new CheckLeaveReqPage();
                userCurrentPanel = checkLeaveReqPage;
                frame.add(checkLeaveReqPage, 1);
                break;
            case CHECK_TEMPORARY_GRADES_LIST:
                CheckTemporaryGradesPage checkTemporaryGradesPage = new CheckTemporaryGradesPage();
                userCurrentPanel = checkTemporaryGradesPage;
                frame.add(checkTemporaryGradesPage, 1);
                break;
            case SET_CHOOSE_TIME:
                SetChooseTime setChooseTime = new SetChooseTime();
                userCurrentPanel = setChooseTime;
                frame.add(setChooseTime,1);
                break;
            case TAKE_LESSON_PAGE:
                TakeLessonPage takeLessonPage = new TakeLessonPage();
                userCurrentPanel = takeLessonPage;
                frame.add(takeLessonPage,1);
                break;
            case REQ_MESSAGES_PAGE:
                ReqMessagePage reqMessagePage = new ReqMessagePage();
                userCurrentPanel = reqMessagePage;
                frame.add(reqMessagePage,1);
                break;
            case CHAT_PAGE:
                ChatPage chatPanel = new ChatPage();
                userCurrentPanel = chatPanel;
                frame.add(chatPanel,1);
                break;
            case NEW_CHAT_PAGE:
                NewChatPage newChatPage = new NewChatPage();
                userCurrentPanel = newChatPage;
                frame.add(newChatPage,1);
                break;

        }

        updateFrame();
    }

    public void exitButton() {
        resetJPanels();
        userMainPanel.setVisible(false);
        LoginPage loginPage = new LoginPage();
        frame.add(loginPage);
        updateFrame();
    }

    public void goToMainPanel() {
        resetJPanels();
        getFrame().add(getUserMainPanel(), 1);
        updateFrame();
    }

    public void addJPanel(JPanel jPanel) {
        jPanels.add(jPanel);
    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void updateFrame() {
        frame.repaint();
        frame.revalidate();
        if (!(userMainPanel == null)) {
            userMainPanel.repaint();
            userMainPanel.revalidate();
        }
        if (!(userCurrentPanel == null)) {
            userCurrentPanel.repaint();
            userCurrentPanel.revalidate();
        }


    }

    public JOptionPane getjOptionPane() {
        return jOptionPane;
    }

    public void setjOptionPane(JOptionPane jOptionPane) {
        this.jOptionPane = jOptionPane;
    }

    public JPanel getUserMainPanel() {
        return userMainPanel;
    }

    public void setUserMainPanel(JPanel userMainPanel) {
        this.userMainPanel = userMainPanel;
    }

    public JPanel getUserCurrentPanel() {
        return userCurrentPanel;
    }

}
