package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WeeklyPlanPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JButton namyesh;
    ArrayList<String> colomns;
    ArrayList<ArrayList<String>> data;
    String data1[][];
    String colomns1[] = {"SHANBE","YEKSHANBE","DOSHANBE","SESHANBE","CHARSHANBE","PANJSHANBE","JOME"};

    public WeeklyPlanPage(){
        initPanel();
        initComps();
        align();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }

    private void initPanel() {
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        colomns = new ArrayList<>();
        data = new ArrayList<>();
        namyesh = new JButton("namayesh");

    }
    public Integer getLessonTime(String time){
        switch (time) {
            case "9":
                return 0;
            case "10.5":
                return 1;
            case "12":
                return 2;
            case "1.5":
                return 3;
            case "3":
                return 4;
            case "4.5":
                return 5;
            case "6":
                return 6;
            case "7.5":
                return 7;
        }
        return 0;
    }
    public void align(){
        namyesh.setBounds(10,70,150,30);
        this.add(namyesh);
    }
    public void addListener(){
        namyesh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (WeekDays i:
                        WeekDays.values()) {
                    colomns.add(i.name());
                }
                for (int q = 0;q < 8;q++){
                    data.add(new ArrayList<>());
                    for (int w = 0; w < colomns.size();w++){
                        data.get(q).add(null);
                    }
                }
                jTable = null;
                jScrollPane = null;
                for (List<String> i:
                        DataHandler.getInstance().getUserLessons()) {
                    List<Integer> lessonDays = new ArrayList<>();
                    String[] days = i.get(8).split(" ");
                    try {
                        lessonDays.add(Integer.parseInt(days[0]));
                        lessonDays.add(Integer.parseInt(days[1]));
                        lessonDays.add(Integer.parseInt(days[2]));
                    }catch (Exception e2){
                        e2.printStackTrace();
                    }
                    int rh = 0;
                    int sd = 0;
                    for (Integer j:
                            lessonDays) {
                        rh = j;
                        sd = getLessonTime(i.get(9));
                        data.get(sd).set(rh,i.get(1));
                    }
                }
                data1 = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

                jTable = new JTable(data1,colomns1);
                jScrollPane = new JScrollPane(jTable);
                jScrollPane.setBounds(50,150,700,200);
                add(jScrollPane);
                repaint();
                revalidate();
            }
        });
    }
}
