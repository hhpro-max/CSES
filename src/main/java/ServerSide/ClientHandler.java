package ServerSide;

import javax.xml.crypto.Data;
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

    boolean isStudent = false;
    boolean isTeacher = false;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream());
        in =  new Scanner(socket.getInputStream());
        token = AuthToken.generateNewToken();
        //todo sendtoken for client side
    }

    @Override
    public void run() {

        while (true){
            //todo

            try {
                String msgFromClient = in.nextLine();
                List<String> msgOrder = castToList(msgFromClient);
                analyzeOrder(msgOrder);

            }catch (Exception e){
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
        }

    }



}
