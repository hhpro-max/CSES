package ClientSide;

import ClientSources.ImageResource;
import ClientSources.ResourceManager;

import javax.swing.*;

public class DataHandler {
    int id;
    int nc;
    String firstname;
    String lastname;
    String email;
    String phoneNumber;
    String college;
    String lastLoginTime;
    ImageIcon imageIcon;



    private static DataHandler dataHandler;
    private DataHandler(){

    }
    public static DataHandler getInstance(){
        if (dataHandler == null){
            dataHandler = new DataHandler();
        }
        return dataHandler;
    }
    public void checkLoginRes(String res){
        if (res.equals(ServerRespondType.SUCCESSFUL.toString())){
            GuiController.getInstance().jOptionPane.showMessageDialog(null,"YOU HAD LOGEDIN SUCCESSFULY");
        }else {
            GuiController.getInstance().jOptionPane.showMessageDialog(null,"USERNAME OR PASSWORD IS WRONG!");
        }
    }

    public ImageIcon getImageIcon(){
        if (imageIcon == null){
            ImageIcon imageIcon = new ImageIcon(ResourceManager.get(ImageResource.NULL_PROFILE));
            return imageIcon ;
        }
        return imageIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNc() {
        return nc;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
