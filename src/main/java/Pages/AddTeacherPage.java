package Pages;

import ClientSide.ClientConfig;
import ClientSide.ClientReqType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddTeacherPage extends JPanel {
    JTextField id, pass, name, email,kodeMeli,phoneNumber,shOtagh;
    JLabel id1, pass1, name1, email1,chooseFIle ,aksKarbar1,kodeMeli1,phoneNumber1,shOtagh1;
    JButton fileChooser,sabtNam;
    JOptionPane jOptionPane;
    JComboBox<String> darajeOstadi;

    public AddTeacherPage(){
        initPanel();
        initComps();
        align();
        addListener();
        GuiController.getInstance().addJPanel(this);
    }
    public void initPanel(){
        this.setBounds(0,30, ClientConfig.mainFrameWidth,ClientConfig.mainFrameHeight);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void initComps(){
        id= new JTextField();
        pass=new JTextField();
        name=new JTextField();
        email=new JTextField();
        kodeMeli = new JTextField();
        phoneNumber = new JTextField();
        shOtagh = new JTextField();

        id1= new JLabel("ID :");
        pass1= new JLabel("PASSWORD :");
        name1= new JLabel("NAME :");
        email1= new JLabel("EMAIL :");
        kodeMeli1 = new JLabel("KODE MELI :");
        phoneNumber1 = new JLabel("SHOMARE TAMAS :");
        shOtagh1 = new JLabel("SHOMARE OTAGH :");

        darajeOstadi = new JComboBox<>();
        darajeOstadi.addItem("ostadtamam");
        darajeOstadi.addItem("ostadnatamam");
        chooseFIle = new JLabel("ENTEKHAB AKS KARBAR :");

        sabtNam=new JButton("SABT KARBAR");
        fileChooser = new JButton("CHOOSE FILE");
        jOptionPane = new JOptionPane();

    }
    public void align(){
        id1.setBounds(0,70,50,30);
        this.add(id1);
        id.setBounds(150,70,150,30);
        this.add(id);
        pass1.setBounds(0,120,100,30);
        this.add(pass1);
        pass.setBounds(150,120,150,30);
        this.add(pass);
        name1.setBounds(0,170,100,30);
        this.add(name1);
        name.setBounds(150,170,150,30);
        this.add(name);
        email1.setBounds(0,220,100,30);
        this.add(email1);
        email.setBounds(150,220,150,30);
        this.add(email);
        kodeMeli1.setBounds(0,270,200,30);
        this.add(kodeMeli1);
        kodeMeli.setBounds(150,270,200,30);
        this.add(kodeMeli);
        phoneNumber1.setBounds(0,320,200,30);
        this.add(phoneNumber1);
        phoneNumber.setBounds(150,320,200,30);
        this.add(phoneNumber);
        shOtagh1.setBounds(0,470,200,30);
        this.add(shOtagh1);
        shOtagh.setBounds(150,470,200,30);
        this.add(shOtagh);
        darajeOstadi.setBounds(0,420,200,30);
        this.add(darajeOstadi);



        chooseFIle.setBounds(0,370,150,30);
        this.add(chooseFIle);
        fileChooser.setBounds(150,370,150,30);
        this.add(fileChooser);



        sabtNam.setBounds(500,500,150,30);
        this.add(sabtNam);
        aksKarbar1 = null;


    }
    public void addListener(){
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == fileChooser){
                    JFileChooser fc = new JFileChooser();
                    int i = fc.showOpenDialog(GuiController.getInstance().getFrame());
                    if (i == JFileChooser.APPROVE_OPTION){
                        File f = fc.getSelectedFile();
                        String filepath = f.getPath();
                        try {
                            BufferedImage bi = ImageIO.read(new File(filepath));
                            aksKarbar1 = new JLabel(new ImageIcon(bi));
                            aksKarbar1.setBounds(350,10,400,400);
                            add(aksKarbar1);
                            repaint();
                            revalidate();

                        }catch (Exception ex){

                            jOptionPane.showMessageDialog(null,"FILE ENTEKHABI AKS BASHAD");
                        }
                    }
                }
            }
        });
        sabtNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ImageIcon aksKarbar2 = null;
                if (!(aksKarbar1 == null) && !(aksKarbar1.getIcon() == null)){
                    aksKarbar2 = (ImageIcon) aksKarbar1.getIcon();
                }

                String teacherId = id.getText();
                String nationalCode = kodeMeli.getText();
                String fullName = name.getText();
                String firstName;
                String lastName;
                try {
                    String[] n = fullName.split(" ");
                    firstName = n[0];
                    lastName = n[1];
                }catch (Exception exception){
                    jOptionPane.showMessageDialog(null,"NAME FORMAT IS : FIRSTNAME LASTNAME");
                    return;
                }
                String passWord = pass.getText();
                String relation = "O";
                String faceImage = "URFL";
                String eMail = email.getText();
                String phoneNum = phoneNumber.getText();
                String  college = "sharif";

                List<String> req = new ArrayList<>();
                req.add(ClientReqType.ADD_TEACHER.toString());
                req.add(teacherId);
                req.add(nationalCode);
                req.add(firstName);
                req.add(lastName);
                req.add(passWord);
                req.add(relation);
                req.add(faceImage);
                req.add(eMail);
                req.add(phoneNum);
                req.add(college);
                req.add("00:00:00");
                req.add(teacherId);
                req.add(darajeOstadi.getItemAt(darajeOstadi.getSelectedIndex()));
                req.add(shOtagh.getText());
                GuiController.getInstance().getClient().getClientSender().sendMessage(req);

            }
        });
    }
}
