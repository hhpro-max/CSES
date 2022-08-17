package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CwSchedulePage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    String[] columns = {"LESSON_NAME/ID","DEAD_LINE_NAME","DATE"};
    String[][] rows;
    public CwSchedulePage(){
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
        List<List<String>> data = new ArrayList<>();
        if (jScrollPane != null){
            remove(jScrollPane);
        }
        for (List<String> j:
                DataHandler.getInstance().getHomeWorks()) {
            data.add(new ArrayList<>());
            data.get(data.size()-1).add(j.get(0));
            data.get(data.size()-1).add(j.get(4));
            data.get(data.size()- 1).add(j.get(3));
        }
        for (List<String> i:
             DataHandler.getInstance().getUserLessons()) {
            data.add(new ArrayList<>());
            data.get(data.size()-1).add(i.get(1));
            data.get(data.size()-1).add("EXAM TIME");
            data.get(data.size()- 1).add(i.get(10));
        }

        rows = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(rows,columns);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(10,100,600,400);
        this.add(jScrollPane);
    }
}
