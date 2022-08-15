package ServerSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import java.sql.SQLException;
import java.util.*;

public class ClientHandler implements Runnable {
    Socket socket;
    PrintWriter out;
    Scanner in;
    private String token;

    int id;
    int nc;
    String firstname;
    String lastname;
    String relation;
    String email;
    String phoneNumber;
    String college;
    String lastLoginTime;
    String studentLvl;
    String enterYear;
    int superVisorId;

    boolean isStudent = false;
    boolean isTeacher = false;
    boolean isEduAssistant = false;
    boolean isEduManager = false;
    boolean isMrMohseni = false;

    List<List<String>> allOrders;
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream());
        in =  new Scanner(socket.getInputStream());
        token = AuthToken.generateNewToken();
        allOrders = new ArrayList<>();
        //todo sendtoken for client side
    }

    @Override
    public void run() {

        while (true){
            //todo

            try {
                String msgFromClient = in.nextLine();
                List<String> msgOrder = castToList(msgFromClient);
                allOrders.add(msgOrder);
                //todo init allOrders
                analyzeOrder(msgOrder);

            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("DATABASE PROBLEM!");
                List<String> res = new ArrayList<>();
                res.add(ServerReqType.SHOW_RESULT.toString());
                res.add(RespondType.UNSUCCESSFUL.toString());
                sendMessage(res.toString());
            }catch (NoSuchElementException e){
                e.printStackTrace();
                System.out.println("USER DISCONNECTED!");
                try {
                    this.kill();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
    }



    private ArrayList<String> castToList(String msg) {
        String r1 = msg.replace("[","");
        String r2 = r1.replace("]","");
        return new ArrayList<String>(Arrays.asList(r2.split(", ")));
    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    private void checkAuthToken(String str) {
        String tokenC = str.split(":")[1];
        if (!tokenC.equals(this.token)){
            sendMessage("WRONG TOKEN!");
            try {
                this.kill();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void kill() throws IOException {
        socket.close();
        Server.clients.remove(this);
    }

    public void disconnect(){
        isStudent = false;
        isTeacher = false;
        isEduAssistant = false;
        isEduManager = false;
        isMrMohseni = false;
    }

    private void analyzeOrder(List<String> order) throws SQLException {
        //todo delete sout
        System.out.println(order.toString());

        if (order.get(0).equals(ServerReqType.LOGIN.toString())){
            DataBase.getInstance().checkLogin(this,order.get(1),order.get(2));
        }else if (order.get(0).equals(ServerReqType.GETLESSONSLIST.toString())){
            DataBase.getInstance().getLessonsList(this);
        }else if ((order.get(0).equals(ServerReqType.GETTEACHERSLIST.toString()))){
            DataBase.getInstance().getTeachersList(this);
        }else if (order.get(0).equals(ServerReqType.GETUSERLESSONS.toString())){
            DataBase.getInstance().getUserLessons(this);
        }else if (order.get(0).equals(ServerReqType.GETRECOMMENDLIST.toString())){
            DataBase.getInstance().getRecommendList(this);
        }else if (order.get(0).equals(ServerReqType.RECOMMENDREQ.toString())){
            DataBase.getInstance().setRecommendReq(this,order.get(1));
        }else if (order.get(0).equals(ServerReqType.MINORREQLIST.toString())){
            DataBase.getInstance().getMinorReqList(this);
        } else if (order.get(0).equals(ServerReqType.MINORREQ.toString())){
            DataBase.getInstance().setMinorReq(this,order);
        }else if (order.get(0).equals(ServerReqType.LEAVEREQ.toString())){
            DataBase.getInstance().setLeaveReq(this,order);
        }else if (order.get(0).equals(ServerReqType.LEAVEREQLIST.toString())){
            DataBase.getInstance().getLeaveReqList(this);
        }else if (order.get(0).equals(ServerReqType.OBJECTION.toString())){
            DataBase.getInstance().setObjection(this,order);
        }else if (order.get(0).equals(ServerReqType.TEMPORARYGRADESLIST.toString())){
            DataBase.getInstance().getTemporaryGradesList(this);
        }else if (order.get(0).equals(ServerReqType.EDIT_EMAIL.toString())){
            DataBase.getInstance().setUserEmail(this,order);
        }else if (order.get(0).equals(ServerReqType.EDIT_PHONE.toString())){
            DataBase.getInstance().setUserPhoneNumber(this,order);
        }else if (order.get(0).equals(ServerReqType.SET_REC_RESULT.toString())){
            DataBase.getInstance().setRecResult(this,order);
        }else if (order.get(0).equals(ServerReqType.SET_TEMPORARY_GRADE.toString())){
            DataBase.getInstance().setTemporaryGrades(this,order);
        }else if (order.get(0).equals(ServerReqType.DELETE_LESSON.toString())){
            DataBase.getInstance().deleteLesson(this,order);
        }else if (order.get(0).equals(ServerReqType.ADD_LESSON.toString())){
            DataBase.getInstance().addLesson(this,order);
        }else if (order.get(0).equals(ServerReqType.ADD_STUDENT.toString())){
            DataBase.getInstance().addStudent(this,order);
        }else if (order.get(0).equals(ServerReqType.ADD_TEACHER.toString())){
            DataBase.getInstance().addTeacher(this,order);
        }else if (order.get(0).equals(ServerReqType.DELETE_TEACHER.toString())){
            DataBase.getInstance().deleteTeacher(this,order);
        }else if (order.get(0).equals(ServerReqType.UPGRADE_TO_ASSISTANCE.toString())){
            DataBase.getInstance().upgradeToAssistance(this,order);
        }else if (order.get(0).equals(ServerReqType.GET_STUDENTS_LIST.toString())){
            DataBase.getInstance().getStudentsList(this);
        }else if (order.get(0).equals(ServerReqType.SET_CHOOSE_TIME.toString())){
            DataBase.getInstance().setChooseTime(this,order);
        }else if (order.get(0).equals(ServerReqType.GET_RECOMMENDED_LESSONS.toString())){
            DataBase.getInstance().getRecommendedLessonsList(this);
        }else if (order.get(0).equals(ServerReqType.TAKE_LESSON.toString())){
            DataBase.getInstance().takeLesson(this,order);
        }else if (order.get(0).equals(ServerReqType.SEND_REQ_MESSAGE.toString())){
            DataBase.getInstance().addReqMessage(this,order);
        }else if(order.get(0).equals(ServerReqType.CHANGE_LESSON_GP.toString())){
            DataBase.getInstance().changeLessonGp(this,order);
        }else if (order.get(0).equals(ServerReqType.REMOVE_TOOK_LESSON.toString())){
            DataBase.getInstance().removeTookLesson(this,order);
        }else if (order.get(0).equals(ServerReqType.GET_REQ_MESSAGES.toString())){
            DataBase.getInstance().getReqMessages(this);
        }else if (order.get(0).equals(ServerReqType.SET_REQ_MESSAGE_RESULT.toString())){
            DataBase.getInstance().setReqMessageResult(this,order);
        }else if (order.get(0).equals(ServerReqType.GET_CHATS.toString())){
            DataBase.getInstance().getChats(this);
        }else if (order.get(0).equals(ServerReqType.SEND_MESSAGE.toString())){
            DataBase.getInstance().setMessage(this,order);
        }else if (order.get(0).equals(ServerReqType.DOWNLOAD_FILE.toString())){
            DataBase.getInstance().downloadFile(this,order);
        }else if (order.get(0).equals(ServerReqType.GET_AVAILABLE_PEOPLE.toString())){
            DataBase.getInstance().getAvailablePeople(this);
        }else if (order.get(0).equals(ServerReqType.DISCONNECT.toString())){
            disconnect();
        }else if (order.get(0).equals(ServerReqType.GET_CW_EDU_SUBJECTS.toString())){
            DataBase.getInstance().getCwEduSubjects(this);
        }else if (order.get(0).equals(ServerReqType.ADD_NEW_HM.toString())){
            DataBase.getInstance().addNewHm(this,order);
        }else if (order.get(0).equals(ServerReqType.GET_HM.toString())){
            DataBase.getInstance().getHomeWorks(this);
        }
    }



}
