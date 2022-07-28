package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class StudyEvidenceReqPage extends JPanel {
    JTextArea jTextArea;
    String matnGovahi;
    JButton jButton;
    Date date;

    public StudyEvidenceReqPage() {
        initPanel();
        initComps();
        align();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }

    private void initPanel() {
        this.setBounds(0, 30, ClientConfig.mainFrameWidth, ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void initComps() {
        date = new Date();
        jButton = new JButton("NAMAYESH DARKHAST");
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();
        String tarikhSodor = year + "/" + month + "/" + day;
        String matnGovahi1 = "GOVAHI MISHAVAD KE AGHA/KHANOM % BE SHOMARE DANESHJOOEE * MASHGHOOL BE TAHSIL DAR RESHTE $ \n DARDANESHGAH SANATI SHARIF AST TARIKH SODOR GOVAHI : =";
        String matngovahi2 = matnGovahi1.replace("%", DataHandler.getInstance().getFullName());
        String matngovai3 = matngovahi2.replace("*", String.valueOf(DataHandler.getInstance().getId()));
        String matngovvahi4 = matngovai3.replace("$", DataHandler.getInstance().getCollege());
        matnGovahi = matngovvahi4.replace("=", tarikhSodor);
    }
    public void align(){
        jButton.setBounds(10, 50, 200, 30);
        this.add(jButton);
    }
    public void addListener(){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea = new JTextArea(matnGovahi);
                jTextArea.setBounds(20, 100, 700, 200);
                add(jTextArea);
                repaint();
                revalidate();
            }
        });
    }

}
