package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StudentEduStatusPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    String columns[] = {"ID","DARS","TEDADVAHED","NOMRE"};
    List<List<String>> data = new ArrayList<>();

    public StudentEduStatusPage(){
        initPanel();
        initComps();
        align();

        GuiController.getInstance().addJPanel(this);
    }

    private void initPanel() {
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        int tedadvahed = 0;
        double sum = 0;
        double counter = 0;
        double average = 0;
        List<List<String>> data2 = DataHandler.getInstance().getUserLessons();
        data = new ArrayList<>();
        for (List<String> i:
             data2) {
            data.add(new ArrayList<>());
            data.get(data.size()-1).add(i.get(0));
            data.get(data.size()-1).add(i.get(1));
            data.get(data.size()-1).add(i.get(5));
            data.get(data.size()-1).add(i.get(11));
        }
        for (List<String> i:
                data2) {
            try {
                sum = sum + (Double.parseDouble(i.get(11)) * Double.parseDouble(i.get(5)));
                counter = counter + Integer.parseInt(i.get(5));
            }catch (Exception e2){

            }
            try {
                tedadvahed = tedadvahed + Integer.parseInt(i.get(5));
            }catch (Exception e1){


            }
        }
        if (!(counter == 0.0)){
            average = sum / counter;
        }
        ArrayList<String> k = new ArrayList<>();
        k.add("JAM/AVG");
        k.add("____");
        k.add(String.valueOf(tedadvahed));
        k.add(String.valueOf(average));
        data.add(k);
        String data1[][] = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        jTable = new JTable(data1,columns);
        jScrollPane = new JScrollPane(jTable);
    }
    public void align(){
        jScrollPane.setBounds(50,50,600,300);
        this.add(jScrollPane);
    }
}
