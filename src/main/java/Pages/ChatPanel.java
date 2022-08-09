package Pages;



import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChatPanel extends JPanel {
    JTable jTable;
    JScrollPane chatBox;
    File file;
    String[] columns = {"id","name","last message"};
    String[][] rows;
    List<List<String>> mainData;
    public ChatPanel(){
        DataHandler.getInstance().updateChats();
        mainData = new ArrayList<>();
        initPanel();
        initTable();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initTable(){
        if (chatBox != null){
            this.remove(chatBox);
        }
        mainData.clear();
        for (List<String> i:
             DataHandler.getInstance().getChats()) {
            if (!isContains(mainData,i)){
                mainData.add(i);
            }
        }
        rows = mainData.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(rows,columns);
        chatBox = new JScrollPane(jTable);
        chatBox.setBounds(10,70,400,600);
        this.add(chatBox);
        repaint();
        revalidate();
    }
    public boolean isContains(List<List<String>> i,List<String> j){
        for (List<String> k :i){
            if (k.get(0).equals(j.get(0))){
                return true;
            }
        }
        return false;
    }
}
