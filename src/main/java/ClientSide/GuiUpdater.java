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
        JPanel jPanel = GuiController.getInstance().getUserCurrentPanel();

        if (jPanel instanceof ChatPage){
            ((ChatPage) jPanel).initTable();
            ((ChatPage) jPanel).initChatRoom();
        }else if (jPanel instanceof NewChatPage){
            ((NewChatPage) jPanel).initTable();
        }else if (jPanel instanceof StudentPage){
            ((StudentPage) jPanel).initCWMyLesson();
            ((StudentPage) jPanel).jMenuBar.repaint();
            ((StudentPage) jPanel).jMenuBar.revalidate();
        }else if (jPanel instanceof TeacherPage){
            ((TeacherPage) jPanel).initCWMyLesson();
            ((TeacherPage) jPanel).jMenuBar.repaint();
            ((TeacherPage) jPanel).jMenuBar.revalidate();
        }



        jPanel.repaint();
        jPanel.revalidate();
    }
}
