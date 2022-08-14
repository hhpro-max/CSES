package Listeners;



import ClientSide.Client;
import Pages.GuiController;
import Pages.LoginPage;
import ClientSide.ClientReqType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginListener implements ActionListener {
    String username;
    String password;
    String captcha1;
    String captcha2;

    JOptionPane jOptionPane = new JOptionPane();
    @Override
    public void actionPerformed(ActionEvent e) {
        username = LoginPage.usernameField.getText();
        password = LoginPage.passwordField.getText();
        captcha1 = LoginPage.capchafield.getText();
        captcha2 = LoginPage.thiscapcha.getName();

        if (captcha1.equals(captcha2)){
            if (!username.isEmpty() && !password.isEmpty()){
                List<String> loginOrder = new ArrayList<>();
                loginOrder.add(ClientReqType.LOGIN.toString());
                loginOrder.add(username);
                loginOrder.add(password);
                GuiController.getInstance().getClient().getClientSender().sendMessage(loginOrder);
            }else {
                jOptionPane.showMessageDialog(null,"MAKE SURE THAT YOU DIDNT LEFT ANY FIELD BLANK!");
            }
        }else {
            jOptionPane.showMessageDialog(null,"CAPTCHA IS WRONG!");
        }
    }
}
