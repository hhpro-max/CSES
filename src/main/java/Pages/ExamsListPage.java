package Pages;

import ClientSide.ClientConfig;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ExamsListPage extends JPanel {
    JTable jTable;
    JScrollPane jScrollPane;
    JButton namyesh;
    ArrayList<String> colomns;
    ArrayList<ArrayList<String>> data;
    String data1[][];
    String colomns1[] = {"NAMEDARS", "TARIKH EMTEHAN"};

    public ExamsListPage() {
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
        colomns = new ArrayList<>();
        data = new ArrayList<>();
        namyesh = new JButton("namayesh");
    }

    public void align() {
        namyesh.setBounds(10, 70, 150, 30);
        this.add(namyesh);
    }

    public void addListener() {
        namyesh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<List<String>> dars = DataHandler.getInstance().getUserLessons();
                int k = 0;
                for (List<String> i :
                        dars) {
                    data.add(new ArrayList<>());
                    data.get(k).add(i.get(1));
                    data.get(k).add(i.get(10));
                    k++;
                }
                jTable = null;
                jScrollPane = null;
                data1 = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
                jTable = new JTable(data1, colomns1);
                jScrollPane = new JScrollPane(jTable);
                jScrollPane.setBounds(150, 150, 500, 500);
                add(jScrollPane);
                repaint();
                revalidate();

            }
        });
    }
}
