package ClientSide;

import Pages.ChatPage;
import Pages.GuiController;

import javax.swing.*;

public class GuiUpdater implements Runnable{
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(5000);
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
        }

        jPanel.repaint();
        jPanel.revalidate();
    }
}
