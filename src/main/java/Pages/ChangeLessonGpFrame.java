package Pages;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChangeLessonGpFrame extends JFrame {
    List<List<String>> lessonGp;
    String[] columns = {"ID","NAME","PISHNIAZ","OSTAD","COLLEGE","VAHED","MAGHTA","ZARFIAT","DAYS","TIME","EXAMDATE"};
    JTextField jTextField;
    JButton jButton;
    JScrollPane jScrollPane;
    JTable jTable;
    public ChangeLessonGpFrame(List<List<String>> lessons){
        this.lessonGp = lessons;
        initFrame();
        initComps();
        addListener();
        repaint();
        revalidate();
    }
    public void initFrame(){
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(true);
        this.setSize(new Dimension(600,600));
    }
    public void initComps(){

        jTextField = new JTextField("TEACHER ID");
        jTextField.setBounds(10,10,150,30);
        this.add(jTextField);
        jButton = new JButton("CHANGE GP");
        jButton.setBounds(200,10,200,30);
        this.add(jButton);
        jTable = new JTable(lessonGp.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new),columns);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(20,50,500,300);
        this.add(jScrollPane);
    }
    public void addListener(){
        //
    }
}
