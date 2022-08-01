package Pages;

import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EduAssistantPage extends TeacherPage{
    JMenu sabtkarbar;
    JMenuItem addDaneshjoo;
    JMenuItem addOstad;
    JMenuItem checkMinor;
    JMenuItem checkEnserafi;
    JMenuItem vaziatTahsily;
    JMenuItem checkNomarat;
    JButton sabtVavirayesh, deleteDars;
    JTextField deleteDars1;
    JOptionPane jOptionPane;

    public EduAssistantPage(){
        super();
        initMoreComps();
        addMoreListener();

    }
    public void initMoreComps(){
        sabtkarbar = new JMenu("SABT KARBAR");
        addDaneshjoo = new JMenuItem("Daneshjoo");
        checkMinor = new JMenuItem("DARKHASTHAYE MINOR");
        checkEnserafi = new JMenuItem("DARKHASTHAYE ENSERAF");
        addOstad = new JMenuItem("Ostad");
        sabtVavirayesh = new JButton("SABT VA VIRAYESH");
        vaziatTahsily = new JMenuItem("VAZIAT TAHSILI");
        checkNomarat = new JMenuItem("CHECK NOMARAT");
        deleteDars = new JButton("HAZF DARS");
        deleteDars1 = new JTextField();
        jOptionPane = new JOptionPane();

        requests.add(checkMinor);
        gradeService.add(checkNomarat);
        gradeService.add(vaziatTahsily);
        requests.add(checkEnserafi);
        sabtkarbar.add(addOstad);
        sabtkarbar.add(addDaneshjoo);
        jMenuBar.add(sabtkarbar);

    }
    public void addMoreListener(){
        for (ActionListener i :
                lessonsList.getActionListeners()) {
            lessonsList.removeActionListener(i);
        }
        deleteDars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!deleteDars1.getText().isEmpty()) {
                    String id = deleteDars1.getText();
                    try {
                        Integer.parseInt(id);
                    }catch (Exception exception){
                        jOptionPane.showMessageDialog(null,"INTEGER FORMAT IS JUST ACCEPTABLE!");
                        return;
                    }
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.DELETE_LESSON.toString());
                    req.add(id);
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                } else {
                    jOptionPane.showMessageDialog(null, "ID RA VARED KONID");
                }
            }
        });
        sabtVavirayesh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.ADD_LESSON_PAGE);
            }
        });
        lessonsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().resetJPanels();
                LessonsListPage lessonsListPage = new LessonsListPage();
                deleteDars1.setBounds(50, 150, 150, 30);
                lessonsListPage.add(deleteDars1);
                deleteDars.setBounds(250, 150, 150, 30);
                lessonsListPage.add(deleteDars);
                sabtVavirayesh.setBounds(450, 150, 200, 30);
                lessonsListPage.add(sabtVavirayesh);
                add(lessonsListPage, 1);
                repaint();
                revalidate();
            }
        });
        addDaneshjoo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.ADD_STUDENT_PAGE);
            }
        });
        addOstad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiController.getInstance().changePanelTo(PanelType.ADD_TEACHER_PAGE);
            }
        });
        checkMinor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataHandler.getInstance().updateMinorReqList();
                GuiController.getInstance().changePanelTo(PanelType.CHECK_MINOR_PAGE);
            }
        });
        checkEnserafi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataHandler.getInstance().updateLeaveReqList();
                GuiController.getInstance().changePanelTo(PanelType.CHECK_LEAVE_REQ_PAGE);

            }
        });
    }
}
