package ClientSide;

import Pages.ChatPage;
import Pages.GuiController;
import Pages.NewChatPage;

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
        }



        jPanel.repaint();
        jPanel.revalidate();
    }
}
