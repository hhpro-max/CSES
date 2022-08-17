package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeacherCwLessonPage extends JPanel {
    String lessonName;
    JButton addEduSubject;
    String lessonId;
    int subjectAdded = 0;
    List<List<String>> eduSubject;
    JScrollPane subjectPanel;
    //
    JTextField hmStartTime;
    JTextField hmFinishTime;
    JTextField hmPreferTime;
    JTextField hmName;
    JTextField exp;
    JButton hmFile;
    JTable hmTable;
    JScrollPane hmScrollPane;
    String[] columns = {"START TIME","FINISH TIME","FULL SCORE TIME","HM NAME","EXPLANATION","FILE"};
    String[][] rows;
    //
    JTable checkHm;
    JScrollPane checkHmSp;
    String[] columns2 = {"STUDENT_ID","UPLOADED_FILE","UPLOAD_TIME","SCORE"};
    String[][] rows2;
    public TeacherCwLessonPage(String lessonName){
        this.lessonName = lessonName;
        eduSubject = new ArrayList<>();
        initPanel();
        initComps();
        align();
        initCwEduSubjects();
        initHMTable();
        initCheckHmTable();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        addEduSubject = new JButton("ADD NEW SUBJECT");
        for (List<String> i:
                DataHandler.getInstance().getUserLessons()) {
            if (i.get(1).equals(lessonName)){
                lessonId = i.get(0);
            }
        }
        //
        hmStartTime = new JTextField("HM START TIME");
        hmFinishTime = new JTextField("HM FINISH TIME");
        hmPreferTime = new JTextField("TIME BDOON KASR NOMRE");
        hmName = new JTextField("HM NAME");
        exp = new JTextField("EXPLANATIONS");
        hmFile = new JButton("CHOOSE FILE AND UPLOAD HM");
    }
    public void align(){
        addEduSubject.setBounds(10,70,200,30);
        this.add(addEduSubject);
        //
        hmStartTime.setBounds(350,70,150,30);
        this.add(hmStartTime);
        hmFinishTime.setBounds(350,110,150,30);
        this.add(hmFinishTime);
        hmPreferTime.setBounds(350,150,150,30);
        this.add(hmPreferTime);
        hmName.setBounds(530,70,150,30);
        this.add(hmName);
        exp.setBounds(530,110,200,30);
        this.add(exp);
        hmFile.setBounds(530,150,200,30);
        this.add(hmFile);
        //
    }
    public void initCwEduSubjects(){
        eduSubject.clear();
        if (subjectPanel != null){
            this.remove(subjectPanel);
        }
        for (List<String> i:
                DataHandler.getInstance().getCwLessonsEduSubjects()) {
            if (i.get(0).equals(lessonId)){
                eduSubject.add(i);
            }
        }
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        Collections.reverse(eduSubject);
        for (List<String> i:
                eduSubject) {
            JLabel jLabel = new JLabel(i.get(1));
            jLabel.setVisible(true);
            jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int i = jLabel.getText().lastIndexOf('.');
                    if (i > 0){
                        JFrame jFrame = new JFrame();
                        jFrame.setVisible(true);
                        jFrame.setLayout(null);
                        jFrame.setResizable(true);
                        jFrame.setSize(new Dimension(800,400));
                        JLabel JFLabel = new JLabel("ARE YOU SURE YOU WANT TO DOWNLOAD / "+jLabel.getText()+" / FILE ?");
                        JFLabel.setBounds(10,50,600,50);
                        jFrame.add(JFLabel);
                        JButton JFYesBut = new JButton("YES");
                        JFYesBut.setBounds(200,100,150,30);
                        jFrame.add(JFYesBut);
                        JButton JFNoBut = new JButton("NO");
                        JFNoBut.setBounds(400,100,150,30);
                        jFrame.add(JFNoBut);
                        JFNoBut.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e1) {
                                jFrame.dispose();
                            }
                        });
                        JFYesBut.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                List<String> req = new ArrayList<>();
                                req.add(ClientReqType.DOWNLOAD_FILE.toString());
                                String fileName = jLabel.getText();
                                req.add(fileName);
                                GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                                jFrame.dispose();
                            }
                        });
                        jFrame.repaint();
                        jFrame.revalidate();
                    }else {
                        JOptionPane.showMessageDialog(null,"this is not downloadable file!");
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            jPanel.add(jLabel);
        }
        subjectPanel = new JScrollPane(jPanel);
        subjectPanel.setBounds(10,120,300,600);
        this.add(subjectPanel);
        repaint();
        revalidate();
    }
    public void initHMTable(){
        if (hmScrollPane != null){
            remove(hmScrollPane);
        }
        List<List<String>> data = new ArrayList<>();
        for (List<String> i:
             DataHandler.getInstance().getHomeWorks()) {
            if (i.get(0).equals(lessonId)){
                data.add(i);
            }
        }
        for (List<String> i:
             data) {
            i.remove(0);
        }
        rows = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        hmTable = new JTable(rows,columns);
        hmScrollPane = new JScrollPane(hmTable);
        hmScrollPane.setBounds(350,200,400,520);
        this.add(hmScrollPane);
    }
    public void initCheckHmTable(){
        if (checkHmSp != null){
            remove(checkHmSp);
        }
        List<List<String>> data = new ArrayList<>();
        for (List<String> i :
                DataHandler.getInstance().getUploadedHm()) {
            if (i.get(1).equals(lessonId)){
                data.add(new ArrayList<>());
                data.get(data.size() - 1).add(i.get(0));
                data.get(data.size() - 1).add(i.get(3));
                data.get(data.size() - 1).add(i.get(4));
                data.get(data.size() - 1).add(i.get(5));
            }
        }
        rows2 = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        checkHm = new JTable(rows2,columns2);
        checkHm.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame jFrame = new JFrame();
                jFrame.setVisible(true);
                jFrame.setLayout(null);
                jFrame.setResizable(true);
                jFrame.setSize(new Dimension(800,400));
                JLabel JFLabel = new JLabel("ARE YOU SURE YOU WANT TO DOWNLOAD / "+checkHm.getValueAt(checkHm.getSelectedRow(),1)+" / FILE ?");
                JFLabel.setBounds(10,50,600,50);
                jFrame.add(JFLabel);
                JButton JFYesBut = new JButton("YES");
                JFYesBut.setBounds(200,100,150,30);
                jFrame.add(JFYesBut);
                JButton JFNoBut = new JButton("NO");
                JFNoBut.setBounds(400,100,150,30);
                jFrame.add(JFNoBut);
                JFNoBut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        jFrame.dispose();
                    }
                });
                JFYesBut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<String> req = new ArrayList<>();
                        req.add(ClientReqType.DOWNLOAD_FILE.toString());
                        String fileName = (String) checkHm.getValueAt(checkHm.getSelectedRow(),1);
                        req.add(fileName);
                        GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                        jFrame.dispose();
                    }
                });
                //
                JLabel label2 = new JLabel("OR YOU CAN JUST SET THE SCORE FOR STUDENT "+checkHm.getValueAt(checkHm.getSelectedRow(),0)+" :");
                label2.setBounds(10,150,600,50);
                jFrame.add(label2);
                JTextField jTextField12 = new JTextField();
                jTextField12.setBounds(10,220,150,30);
                jFrame.add(jTextField12);
                JButton save = new JButton("SAVE SCORE");
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Double.parseDouble(jTextField12.getText());
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"INTEGER FORMAT IS JUST ACCEPTABLE!");
                            return;
                        }
                        List<String> req = new ArrayList<>();
                        req.add(ClientReqType.SET_HM_SCORE.toString());
                        req.add((String) checkHm.getValueAt(checkHm.getSelectedRow(),0));
                        req.add(lessonId);
                        req.add(jTextField12.getText());
                        GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                        jFrame.dispose();
                    }
                });
                save.setBounds(200,220,150,30);
                jFrame.add(save);
                //
                jFrame.repaint();
                jFrame.revalidate();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        checkHmSp = new JScrollPane(checkHm);
        checkHmSp.setBounds(800,200,400,520);
        this.add(checkHmSp);
    }
    public void addListener(){
        addEduSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame();
                jFrame.setVisible(true);
                jFrame.setLayout(null);
                jFrame.setResizable(true);
                jFrame.setSize(new Dimension(500,500));
                JButton addNewFile = new JButton("ADD NEW FILE");
                JButton addNewText = new JButton("ADD NEW TEXT");
                JTextField text = new JTextField("TEXT");
                addNewFile.setBounds(10,50,150,30);
                jFrame.add(addNewFile);
                addNewText.setBounds(200,50,150,30);
                jFrame.add(addNewText);
                text.setBounds(100,100,200,30);
                jFrame.add(text);
                addNewFile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser jFileChooser = new JFileChooser();
                        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                            try {
                                if (subjectAdded<6) {
                                    File file = jFileChooser.getSelectedFile();
                                    FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                                    String fileName = file.getName();
                                    byte[] fileContentBytes = new byte[(int) file.length()];
                                    fileInputStream.read(fileContentBytes);
                                    List<String> req = new ArrayList<>();
                                    req.add(ClientReqType.SEND_MESSAGE.toString());
                                    req.add(lessonId);
                                    req.add(fileName);
                                    req.add(Arrays.toString(fileContentBytes));
                                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                                    subjectAdded++;
                                }else {
                                    JOptionPane.showMessageDialog(null,"YOU HAD ADDED 5 SUBJECTS THAT'S ENOUGH FOR TODAY ! XD");
                                }
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                addNewText.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!text.getText().isEmpty()){
                            List<String> req = new ArrayList<>();
                            req.add(ClientReqType.SEND_MESSAGE.toString());
                            req.add(lessonId);
                            req.add(text.getText()+".msg");
                            req.add("--");
                            GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                        }else {
                            JOptionPane.showMessageDialog(null,"REALLY? YOU WANA SEND EMPTY TEXT?");
                        }
                    }
                });
                jFrame.repaint();
                jFrame.revalidate();
            }
        });
        hmFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    try {
                        File file = jFileChooser.getSelectedFile();
                        FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                        String fileName = file.getName();
                        byte[] fileContentBytes = new byte[(int) file.length()];
                        fileInputStream.read(fileContentBytes);
                        List<String> req = new ArrayList<>();
                        req.add(ClientReqType.ADD_NEW_HM.toString());
                        req.add(lessonId);
                        req.add(hmStartTime.getText());
                        req.add(hmFinishTime.getText());
                        req.add(hmPreferTime.getText());
                        req.add(hmName.getText());
                        req.add(exp.getText());
                        req.add(fileName);
                        GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                        List<String> req1 = new ArrayList<>();
                        req1.add(ClientReqType.SEND_MESSAGE.toString());
                        req1.add(lessonId);
                        req1.add("HW"+fileName);
                        req1.add(Arrays.toString(fileContentBytes));
                        GuiController.getInstance().getClient().getClientSender().sendMessage(req1);
                    }catch (IOException exception){
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

}
