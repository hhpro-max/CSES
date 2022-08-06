package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;
import ClientSide.DataHandler;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TakeLessonPage extends JPanel {
    List<List<String>> data1;
    List<List<String>> data2;
    List<List<String>> markedLessons;
    List<List<String>> data3;
    JButton show;

    JButton mark;

    JButton takeLesson;
    JButton reqToEduAssistant;

    JButton delete;
    JButton changeGroup;

    Object[][] rows1;
    Object[][] rows2;
    Object[][] rows3;
    String[] columns1 = {"ID","NAME","PISHNIAZ","OSTAD","COLLEGE","VAHED","MAGHTA","ZARFIAT","DAYS","TIME","EXAMDATE","MARK","TAKE","REQ"};
    String[] columns2 = {"ID","NAME","PISHNIAZ","OSTAD","COLLEGE","VAHED","MAGHTA","ZARFIAT","DAYS","TIME","EXAMDATE","SCORE","MARK","DELETE","CHANGE GP"};

    JLabel t1;
    JLabel t2;
    JLabel t3;

    JTable jTable1;
    JTable jTable2;
    JTable jTable3;

    JScrollPane jScrollPane1;
    JScrollPane jScrollPane2;
    JScrollPane jScrollPane3;

    JComboBox<College> jComboBox;

    public TakeLessonPage(){
        DataHandler.getInstance().updateLessonsList();
        DataHandler.getInstance().updateUserLessons();
        DataHandler.getInstance().updateRecommendedLessonsList();
        initPanel();
        initComps();
        initFirstTable(DataHandler.getInstance().getAllLessons());
        initSecondTable(DataHandler.getInstance().getRecommendedLessonsList());
        initThirdTable(DataHandler.getInstance().getUserLessons());
        GuiController.getInstance().addJPanel(this);

    }


    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        data1 = new ArrayList<>();
        data2 = new ArrayList<>();
        data3 = new ArrayList<>();
        markedLessons = DataHandler.getInstance().getMarkedLessons();

        show = new JButton("SHOW");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<List<String>> data = new ArrayList<>();
                for (List<String> i:
                        DataHandler.getInstance().getAllLessons()) {
                    if (i.get(4).equals(jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString().toLowerCase())){
                        data.add(i);
                    }
                }
                initFirstTable(data);
            }
        });
        show.setBounds(450,20,150,30);
        this.add(show);

        mark = new JButton("UN/MARK");

        takeLesson = new JButton("TAKE");
        reqToEduAssistant = new JButton("ASK EDU_A");

        delete = new JButton("DELETE");
        changeGroup = new JButton("CHANGE GP");

        t1= new JLabel("DOROS ERAEE SHODE : ");
        t2 =new JLabel("DOROS PISHNEHADI VA NESHAN DAR SHODE : ");
        t3 = new JLabel("DOROS AKHZ SHODE : ");


        jComboBox = new JComboBox<>(College.values());
        jComboBox.setBounds(250,20,150,30);
        this.add(jComboBox);
    }
    public void initFirstTable(List<List<String>> data){
        if (jScrollPane1 != null){
            remove(jScrollPane1);
        }
        data1.clear();
        List<List<String>> removedTookLessons = new ArrayList<>(data);
        removedTookLessons.removeAll(DataHandler.getInstance().getUserLessons());

        for (List<String> i:
                removedTookLessons){
            data1.add(new ArrayList<>());
            for (String j:
                 i) {
                data1.get(data1.size()-1).add(j);
            }
            data1.get(data1.size()-1).add("MARK");
            data1.get(data1.size()-1).add("TAKE");
            data1.get(data1.size()-1).add("REQ");
        }

        rows1 = data1.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);

        //
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(rows1,columns1);
        //

        jTable1 = new JTable(dm);
        //
        jTable1.getColumn("MARK").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("MARK").setCellEditor(new ButtonEditor(new JCheckBox()));
        jTable1.getColumn("TAKE").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("TAKE").setCellEditor(new ButtonEditor(new JCheckBox()));
        jTable1.getColumn("REQ").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("REQ").setCellEditor(new ButtonEditor(new JCheckBox()));
        //
        jScrollPane1 = new JScrollPane(jTable1);
        t1.setBounds(50,25,200,30);
        this.add(t1);
        jScrollPane1.setBounds(50,50,1300,200);
        this.add(jScrollPane1);
        repaint();
        revalidate();
    }
    public void initSecondTable(List<List<String>> data){
        if (jScrollPane2 != null){
            remove(jScrollPane2);
        }
        data2.clear();
        List<List<String>> removedTookLessons = new ArrayList<>(data);
        removedTookLessons.removeAll(DataHandler.getInstance().getUserLessons());
        for (List<String> i:
                removedTookLessons){
            data2.add(new ArrayList<>());
            for (String j:
                    i) {
                data2.get(data2.size()-1).add(j);
            }
            data2.get(data2.size()-1).add("UNMARK");
            data2.get(data2.size()-1).add("TAKE");
            data2.get(data2.size()-1).add("REQ");
        }
        for (List<String> i:
             markedLessons) {
            data2.add(new ArrayList<>());
            for (String j:
                    i) {
                data2.get(data2.size()-1).add(j);
            }
            data2.get(data2.size()-1).add("UNMARK");
            data2.get(data2.size()-1).add("TAKE");
            data2.get(data2.size()-1).add("REQ");
        }

        rows2 = data2.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);

        //
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(rows2,columns1);
        //

        jTable2 = new JTable(dm);
        //
        jTable2.getColumn("MARK").setCellRenderer(new ButtonRenderer());
        jTable2.getColumn("MARK").setCellEditor(new ButtonEditor(new JCheckBox()));
        jTable2.getColumn("TAKE").setCellRenderer(new ButtonRenderer());
        jTable2.getColumn("TAKE").setCellEditor(new ButtonEditor(new JCheckBox()));
        jTable2.getColumn("REQ").setCellRenderer(new ButtonRenderer());
        jTable2.getColumn("REQ").setCellEditor(new ButtonEditor(new JCheckBox()));
        //
        jScrollPane2 = new JScrollPane(jTable2);
        t2.setBounds(50,300,300,30);
        this.add(t2);
        jScrollPane2.setBounds(50,350,1300,200);
        this.add(jScrollPane2);
        repaint();
        revalidate();

    }
    private void initThirdTable(List<List<String>> userLessons) {
        if (jScrollPane3 != null){
            remove(jScrollPane3);
        }
        data3.clear();
        for (List<String> i:
                userLessons){
            data3.add(new ArrayList<>());
            for (String j:
                    i) {
                data3.get(data3.size()-1).add(j);
            }
            data3.get(data3.size()-1).add("MARK");
            data3.get(data3.size()-1).add("REMOVE");
            data3.get(data3.size()-1).add("CHANGE GP");
        }
        rows3 = data3.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(rows3,columns2);
        jTable3 = new JTable(dm);
        //
        jTable3.getColumn("MARK").setCellRenderer(new ButtonRenderer());
        jTable3.getColumn("MARK").setCellEditor(new ButtonEditor(new JCheckBox()));
        jTable3.getColumn("DELETE").setCellRenderer(new ButtonRenderer());
        jTable3.getColumn("DELETE").setCellEditor(new ButtonEditor(new JCheckBox()));
        jTable3.getColumn("CHANGE GP").setCellRenderer(new ButtonRenderer());
        jTable3.getColumn("CHANGE GP").setCellEditor(new ButtonEditor(new JCheckBox()));
        //
        jScrollPane3 = new JScrollPane(jTable3);
        t3.setBounds(50,550,300,30);
        this.add(t3);
        jScrollPane3.setBounds(50,600,1300,150);
        this.add(jScrollPane3);
        repaint();
        revalidate();
    }
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                //
                if (jTable1.isColumnSelected(jTable1.getColumnCount()-1)){

                    JOptionPane.showMessageDialog(null , jTable1.getValueAt(jTable1.getSelectedRow(),0));
                    jTable1.clearSelection();
                }else if (jTable1.isColumnSelected(jTable1.getColumnCount()-2)){
                    List<String> req = new ArrayList<>();
                    req.add(ClientReqType.TAKE_LESSON.toString());
                    req.add((String) jTable1.getValueAt(jTable1.getSelectedRow(),0));
                    GuiController.getInstance().getClient().getClientSender().sendMessage(req);
                    jTable1.clearSelection();
                }else if (jTable1.isColumnSelected(jTable1.getColumnCount()-3)){
                    for (List<String> i:
                         data1) {
                        if (i.get(0).equals(jTable1.getValueAt(jTable1.getSelectedRow(),0))){
                            if (markedLessons.contains(i)){
                                JOptionPane.showMessageDialog(null,"YOU HAD MARKED THIS BEFORE !");
                            }else {
                                markedLessons.add(i);
                            }
                        }
                    }
                    jTable1.clearSelection();
                }
                //

                updatePanel();
            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    public void updatePanel(){
        DataHandler.getInstance().updateLessonsList();
        DataHandler.getInstance().updateUserLessons();
        DataHandler.getInstance().updateRecommendedLessonsList();
        initFirstTable(DataHandler.getInstance().getAllLessons());
        initSecondTable(DataHandler.getInstance().getRecommendedLessonsList());
        initThirdTable(DataHandler.getInstance().getUserLessons());
        repaint();
        revalidate();
    }

}
