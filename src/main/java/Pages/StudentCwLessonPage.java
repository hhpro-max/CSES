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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentCwLessonPage extends JPanel {
    String lessonName;
    String lessonId;
    String teacherId;
    JScrollPane subjectPanel;
    List<List<String>> eduSubject;
    JScrollPane hmScrollPane;
    JTable hmTable;
    String[] columns = {"START TIME","FINISH TIME","FULL SCORE TIME","HM NAME","EXPLANATION","FILE"};
    String[][] rows;
    //
    JTable checkHm;
    JScrollPane checkHmSp;
    String[] columns2 = {"TEACHER_ID","UPLOADED_FILE","UPLOAD_TIME","SCORE"};
    String[][] rows2;

    public StudentCwLessonPage(String lessonName){
        this.lessonName = lessonName;
        eduSubject = new ArrayList<>();
        initPanel();
        initComps();
        initCwEduSubjects();
        initHMTable();
        initCheckHmTable();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        for (List<String> i:
                DataHandler.getInstance().getUserLessons()) {
            if (i.get(1).equals(lessonName)){
                lessonId = i.get(0);
                teacherId = i.get(3);
            }
        }
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
        hmTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame jFrame = new JFrame();
                jFrame.setVisible(true);
                jFrame.setLayout(null);
                jFrame.setResizable(true);
                jFrame.setSize(new Dimension(800,400));
                JLabel JFLabel = new JLabel("UPLOAD FILE FOR / "+hmTable.getValueAt(hmTable.getSelectedRow(),3)+" /  ?");
                JFLabel.setBounds(10,50,600,50);
                jFrame.add(JFLabel);
                JButton JFYesBut = new JButton("YES");
                JFYesBut.setBounds(200,100,150,30);
                jFrame.add(JFYesBut);
                JFYesBut.addActionListener(new ActionListener() {
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
                                req.add(ClientReqType.SEND_MESSAGE.toString());
                                req.add(lessonId);
                                req.add(fileName);
                                req.add(Arrays.toString(fileContentBytes));
                                GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                            }catch (IOException exception){
                                exception.printStackTrace();
                            }
                        }
                        jFrame.dispose();
                    }
                });
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
                data.get(data.size() - 1).add(i.get(2));
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
}
