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
    public TeacherCwLessonPage(String lessonName){
        this.lessonName = lessonName;
        eduSubject = new ArrayList<>();
        initPanel();
        initComps();
        align();
        initCwEduSubjects();
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
    }
    public void align(){
        addEduSubject.setBounds(10,70,200,30);
        this.add(addEduSubject);
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
    }

}
