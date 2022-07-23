package ClientSide;

import ClientSources.ImageResource;
import ClientSources.ResourceManager;
import Pages.GuiController;
import Pages.MainPage;
import Pages.PanelType;

import javax.swing.*;
import java.util.List;

public class DataHandler {
    int id;
    int nc;
    String fullName;
    String firstname;
    String lastname;
    String relation;
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
    public void analyzeOrder(List<String> orders){
        if (orders.get(0).equals(ClientReqType.LOGIN.toString())){
            DataHandler.getInstance().checkLoginRes(orders);
        }
    }
    public void checkLoginRes(List<String> orders){
        if (orders.get(1).equals(ServerRespondType.SUCCESSFUL.toString())){
            GuiController.getInstance().jOptionPane.showMessageDialog(null,"YOU HAD LOGEDIN SUCCESSFULY");
            this.id =Integer.parseInt(orders.get(2));
            this.nc = Integer.parseInt(orders.get(3));
            this.firstname = orders.get(4);
            this.lastname = orders.get(5);
            this.fullName = firstname + " " + lastname;
            this.relation = orders.get(6);
            this.email = orders.get(7);
            this.phoneNumber = orders.get(8);
            this.college = orders.get(9);
            this.lastLoginTime = orders.get(10);
            //todo change this
            GuiController.getInstance().changePanelTo(PanelType.MAINPAGE);
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
