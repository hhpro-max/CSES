package ClientSide;

import ClientSources.ImageResource;
import ClientSources.ResourceManager;
import Pages.GuiController;
import Pages.MainPage;
import Pages.PanelType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    //
    String educationStatus;
    String supervisorName;
    private String sp;
    boolean signUpPermit;
    String signupTime;

    List<List<String>> lessons;
    List<List<String>> teachers;


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
        }else if (orders.get(0).equals(ClientReqType.GETLESSONSLIST.toString())){
            initLessonsList(orders);
        }else if (orders.get(0).equals(ClientReqType.GETTEACHERSLIST.toString())){
            initTeachersList(orders);
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
            switch (this.relation){
                case "D":
                    this.educationStatus = orders.get(11);
                    this.supervisorName = orders.get(12);
                    this.sp = orders.get(13);
                    this.signupTime = orders.get(14);
                    break;
            }
            //todo change this
            GuiController.getInstance().changePanelTo(PanelType.STUDENTMAINPAGE);
        }else {
            GuiController.getInstance().jOptionPane.showMessageDialog(null,"USERNAME OR PASSWORD IS WRONG!");
        }
    }
    public void initLessonsList(List<String> orders){
        orders.remove(0);
        lessons = new ArrayList<>();
        for (String i:
             orders) {
            if (i.equals(ServerRespondType.SUCCESSFUL.toString())){
                lessons.add(new ArrayList<>());
            }else{
                lessons.get(lessons.size() - 1).add(i);
            }
        }


    }
    public void initTeachersList(List<String> orders){
        orders.remove(0);
        teachers = new ArrayList<>();
        for (String i:
                orders) {
            if (i.equals(ServerRespondType.SUCCESSFUL.toString())){
                teachers.add(new ArrayList<>());
            }else{
                teachers.get(teachers.size() - 1).add(i);
            }
        }
    }

    public ImageIcon getImageIcon(){
        if (imageIcon == null){
            ImageIcon imageIcon = new ImageIcon(ResourceManager.get(ImageResource.NULL_PROFILE));
            return imageIcon ;
        }
        return imageIcon;
    }

    public List<List<String>> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<List<String>> teachers) {
        this.teachers = teachers;
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

    public String getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public boolean isSignUpPermit() {
        return this.sp.equals("true");
    }

    public void setSignUpPermit(boolean signUpPermit) {
        this.signUpPermit = signUpPermit;
        if (signUpPermit){
            this.sp = "true";
        }else {
            this.sp = "false";
        }
    }

    public String getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(String signupTime) {
        this.signupTime = signupTime;
    }

    public List<List<String>> getLessons() {
        return lessons;
    }

    public void setLessons(List<List<String>> lessons) {
        this.lessons = lessons;
    }
}
