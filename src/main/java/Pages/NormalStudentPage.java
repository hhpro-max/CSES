package Pages;

import ClientSide.DataHandler;

import javax.swing.*;

public class NormalStudentPage extends MainPage{
    public JTable vaZiatAmoozeshi;
    public JLabel showVaziat;

    public JMenuItem darkhastGobahiEshteghal;
    public JMenuItem darkhastMinor;
    public JMenuItem darkhastenseraf;
    public JMenuItem darkhastTosiename;
    public JMenuItem nomaratMovaghat;
    public JMenuItem vaziatTahsily;
    public JMenuItem profileKarbar1;

    public NormalStudentPage() {
        super();
        initTable();
        initMenubar();
        addMoreListeners();
    }




    private void initTable() {
        showVaziat = new JLabel("VAZAIAT AMOOZASHI");
        showVaziat.setBounds(200,170,250,30);
        this.add(showVaziat);
        String vaziatTahsili = DataHandler.getInstance().getEducationStatus();
        String ostadRahnama = DataHandler.getInstance().getSupervisorName();
        String mojavezSabtnam = "NAMALOOM";
        if (DataHandler.getInstance().isSignUpPermit()) {
            mojavezSabtnam = "MOJAZ BE SABTNAM";
        } else {
            mojavezSabtnam = "MOJAZ NISTI";
        }
        String saatSabtnam = DataHandler.getInstance().getSignupTime();

        String data[][] = {
                {"VazitTahsili :", vaziatTahsili},
                {"Ostad Rahnama :",ostadRahnama},
                {"Mojavez sabnam :",mojavezSabtnam},
                {"Saat Sabtnam :",saatSabtnam}
        };

        String column[] = {"VAZIAT AMOOZESHI","_"};
        vaZiatAmoozeshi = new JTable(data,column);
        vaZiatAmoozeshi.setBounds(200,200,500,70);
        JScrollPane jScrollPane = new JScrollPane(vaZiatAmoozeshi);
        this.add(vaZiatAmoozeshi);
    }
    private void initMenubar() {
        jMenuBar = new JMenuBar();
        jMenuBar.setBounds(80,0,720,30);
        vaziatTahsily = new JMenuItem("VAZIAT TAHSILI");
        nomaratMovaghat = new JMenuItem("LIST NOMARAT MOVAGHAT");
        profileKarbar1 = new JMenuItem("PROFILE");
        darkhastenseraf = new JMenuItem("ENSERAF");
        darkhastMinor = new JMenuItem("MINOR");
        darkhastGobahiEshteghal = new JMenuItem("GOVAHI ESHTEGHAL");
        darkhastTosiename = new JMenuItem("DARKHAST TOSIENAME");
        requests.add(darkhastenseraf);
        requests.add(darkhastTosiename);
        requests.add(darkhastMinor);
        requests.add(darkhastGobahiEshteghal);
        gradeService.add(nomaratMovaghat);
        gradeService.add(vaziatTahsily);
        userProfile.add(profileKarbar1);
    }

    private void addMoreListeners() {

    }
}
