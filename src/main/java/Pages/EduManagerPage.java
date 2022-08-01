package Pages;

import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EduManagerPage extends EduAssistantPage {
    JTextField hazfOsdat1;
    JButton hazfOstad;
    JButton virayeshVaEzafe;
    JButton upGradeOstad;
    JOptionPane jOptionPane;

    public EduManagerPage() {
        super();
        initManagerComps();
        addMangerListener();
    }

    public void initManagerComps() {
        hazfOsdat1 = new JTextField();
        hazfOstad = new JButton("HAZF OSTAD");
        jOptionPane = new JOptionPane();
        upGradeOstad = new JButton("UGRADE TO MOAVEN");
        virayeshVaEzafe = new JButton("VIRAYESH VA EZAFE");
    }

    public void addMangerListener() {
        for (ActionListener i : teachersList.getActionListeners()) {
            teachersList.removeActionListener(i);
        }
        teachersList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataHandler.getInstance().updateTeachersList();
                GuiController.getInstance().resetJPanels();
                TeachersListPage teachersListPage = new TeachersListPage();
                hazfOsdat1.setBounds(150, 50, 150, 30);
                teachersListPage.add(hazfOsdat1);
                hazfOstad.setBounds(350, 50, 150, 30);
                teachersListPage.add(hazfOstad);
                virayeshVaEzafe.setBounds(250, 100, 200, 30);
                teachersListPage.add(virayeshVaEzafe);
                upGradeOstad.setBounds(550, 50, 200, 30);
                teachersListPage.add(upGradeOstad);
                add(teachersListPage, 1);
                repaint();
                revalidate();
            }
        });
        virayeshVaEzafe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataHandler.getInstance().updateTeachersList();
                GuiController.getInstance().changePanelTo(PanelType.ADD_TEACHER_PAGE);
            }
        });
        hazfOstad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hazfOsdat1.getText().isEmpty()) {
                    jOptionPane.showMessageDialog(null, "ID RA VARED KONID");
                } else {
                    String idOstad = hazfOsdat1.getText();
                    try {
                        Integer.parseInt(idOstad);
                    } catch (Exception exception) {
                        jOptionPane.showMessageDialog(null, "ID RA dorost VARED KONID");
                        return;
                    }
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.DELETE_TEACHER.toString());
                    req.add(idOstad);
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                }
            }
        });
        upGradeOstad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hazfOsdat1.getText().isEmpty()) {
                    jOptionPane.showMessageDialog(null, "ID RA VARED KONID");
                } else {
                    String idOstad = hazfOsdat1.getText();
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.UPGRADE_TO_ASSISTANCE.toString());
                    req.add(idOstad);
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                }
            }
        });
    }
}
