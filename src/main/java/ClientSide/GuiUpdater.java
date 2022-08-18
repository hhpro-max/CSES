package ClientSide;

import Pages.*;

import javax.swing.*;

public class GuiUpdater implements Runnable{
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(ClientConfig.delayTime);
                upDate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void upDate(){
        try {
            JPanel jPanel = GuiController.getInstance().getUserCurrentPanel();
            if (jPanel instanceof EduAdminMainPage){
                DataHandler.getInstance().updateChats();
                ((EduAdminMainPage) jPanel).initTable();
                ((EduAdminMainPage) jPanel).initChatRoom();
            }
            else if (jPanel instanceof ChatPage){
                DataHandler.getInstance().updateChats();
                ((ChatPage) jPanel).initTable();
                ((ChatPage) jPanel).initChatRoom();
            }else if (jPanel instanceof NewChatPage){
                DataHandler.getInstance().updateAvailablePeople();
                ((NewChatPage) jPanel).initTable();
            }else if (jPanel instanceof StudentPage){
                DataHandler.getInstance().updateUserLessons();
                DataHandler.getInstance().updateCwLessonsEduSubject();
                ((StudentPage) jPanel).initCWMyLesson();
                ((StudentPage) jPanel).jMenuBar.repaint();
                ((StudentPage) jPanel).jMenuBar.revalidate();
            }else if (jPanel instanceof TeacherPage){
                DataHandler.getInstance().updateUserLessons();
                DataHandler.getInstance().updateCwLessonsEduSubject();
                ((TeacherPage) jPanel).initCWMyLesson();
                ((TeacherPage) jPanel).jMenuBar.repaint();
                ((TeacherPage) jPanel).jMenuBar.revalidate();
            }else if (jPanel instanceof CwSchedulePage){
                DataHandler.getInstance().updateUserLessons();
                DataHandler.getInstance().updateHomeWorks();
                ((CwSchedulePage) jPanel).initTable();
            }else if (jPanel instanceof TeacherCwLessonPage){
                DataHandler.getInstance().updateUserLessons();
                DataHandler.getInstance().updateCwLessonsEduSubject();
                DataHandler.getInstance().updateHomeWorks();
                DataHandler.getInstance().updateUploadedHm();
                ((TeacherCwLessonPage) jPanel).initCwEduSubjects();
                ((TeacherCwLessonPage) jPanel).initHMTable();
                ((TeacherCwLessonPage) jPanel).initCheckHmTable();
            }else if (jPanel instanceof StudentCwLessonPage){
                DataHandler.getInstance().updateUserLessons();
                DataHandler.getInstance().updateCwLessonsEduSubject();
                DataHandler.getInstance().updateHomeWorks();
                DataHandler.getInstance().updateUploadedHm();
                ((StudentCwLessonPage) jPanel).initCwEduSubjects();
                ((StudentCwLessonPage) jPanel).initHMTable();
                ((StudentCwLessonPage) jPanel).initCheckHmTable();
            }

            jPanel.repaint();
            jPanel.revalidate();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR IN GUI UPDATER! DONT BE WORRY THIS IS NOT IMPORTANT!");
        }
    }
}
