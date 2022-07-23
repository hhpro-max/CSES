package ServerSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
    String email;
    String phoneNumber;
    String college;
    String lastLoginTime;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream());
        in =  new Scanner(socket.getInputStream());
        token = AuthToken.generateNewToken();
        //todo sendtoken for client side
    }

    @Override
    public void run() {
        sendMessage("YOU ARE CONNECTED TO THE SERVER!");
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

    private void analyzeOrder(List<String> order) {
        if (order.get(0).equals(ServerReqType.LOGIN.toString())){
            DataBase.getInstance().checkLogin(this,order.get(1),order.get(2));
        }
    }



}
