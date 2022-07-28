package ServerSide;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class DataBase {
    Connection connection;

    private static DataBase dataBase;

    private DataBase() {
        try {
            connection = DriverManager.getConnection(Config.databaseUrl, Config.databaseUser, Config.databasePass);
        } catch (SQLException e) {
            e.printStackTrace();
            //todo exception handle
        }
    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    synchronized void checkLogin(ClientHandler clientHandler, String username, String password) {
        List<String> respond = new ArrayList<>();
        Date date = new Date();
        int hour = date.getHours();
        int minute = date.getMinutes();
        int seconds = date.getSeconds();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sut_members where id = ? and password =?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //updating client info
                respond.add(ServerReqType.LOGIN.toString());
                respond.add(RespondType.SUCCESSFUL.toString());
                respond.add(String.valueOf(resultSet.getInt("id")));
                respond.add(String.valueOf(resultSet.getInt("nc")));
                respond.add(resultSet.getString("firstname"));
                respond.add(resultSet.getString("lastname"));
                respond.add(resultSet.getString("relation"));
                respond.add(resultSet.getString("email"));
                respond.add(resultSet.getString("phonenumber"));
                respond.add(resultSet.getString("college"));
                respond.add(hour + ":" + minute + ":" + seconds);

                clientHandler.id = resultSet.getInt("id");
                clientHandler.nc = resultSet.getInt("nc");
                clientHandler.firstname = resultSet.getString("firstname");
                clientHandler.lastname = resultSet.getString("lastname");
                clientHandler.relation = resultSet.getString("relation");
                clientHandler.email = resultSet.getString("email");
                clientHandler.phoneNumber = resultSet.getString("phonenumber");
                clientHandler.college = resultSet.getString("college");
                clientHandler.lastLoginTime = hour + ":" + minute + ":" + seconds;

                switch (clientHandler.relation) {
                    case "D":
                        preparedStatement = connection.prepareStatement("select * from students where id = ?");
                        preparedStatement.setInt(1, clientHandler.id);
                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            respond.add(resultSet.getString("educational_status"));
                            respond.add(findMemberName(resultSet.getInt("supervisor_id")));
                            respond.add(resultSet.getString("signup_permit"));
                            respond.add(resultSet.getString("signup_time"));
                        }
                        clientHandler.isStudent = true;
                        System.out.println("STUDENT LOGEDIN!");
                        break;
                    //todo complete the relations
                }

                preparedStatement = connection.prepareStatement("update sut_members set lastlogintime = ? where id = ?");
                preparedStatement.setString(1, hour + ":" + minute + ":" + seconds);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();


            } else {
                respond.add(ServerReqType.LOGIN.toString());
                respond.add(RespondType.UNSUCCESSFUL.toString());
            }
            clientHandler.sendMessage(respond.toString());
            respond.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            //todo
        }
    }

    synchronized public String findMemberName(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from sut_members where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("firstname") + " " + resultSet.getString("lastname");
        }
        return "NULL";
    }

    synchronized public void getLessonsList(ClientHandler clientHandler) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from lessons");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> respond = new ArrayList<>();
        respond.add(ServerReqType.GETLESSONSLIST.toString());
        while (resultSet.next()) {
            respond.add(RespondType.SUCCESSFUL.toString());
            respond.add(resultSet.getString("lessonid"));
            respond.add(resultSet.getString("name"));
            respond.add(resultSet.getString("prereq"));
            respond.add(resultSet.getString("teacherid"));
            respond.add(resultSet.getString("college"));
            respond.add(resultSet.getString("units"));
            respond.add(resultSet.getString("level"));
            respond.add(resultSet.getString("capacity"));
            respond.add(resultSet.getString("days"));
            respond.add(resultSet.getString("time"));
            respond.add(resultSet.getString("examdate"));

        }
        clientHandler.sendMessage(respond.toString());
        respond.clear();
    }

    synchronized public void getTeachersList(ClientHandler clientHandler) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from sut_members join teachers on sut_members.id = teachers.id");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> respond = new ArrayList<>();
        respond.add(ServerReqType.GETTEACHERSLIST.toString());
        while (resultSet.next()) {
            respond.add(RespondType.SUCCESSFUL.toString());
            respond.add(resultSet.getString("id"));
            respond.add(resultSet.getString("firstname") + " " + resultSet.getString("lastname"));
            respond.add(resultSet.getString("email"));
            respond.add(resultSet.getString("college"));
            respond.add(resultSet.getString("phonenumber"));
            respond.add(resultSet.getString("level"));
        }
        clientHandler.sendMessage(respond.toString());
        respond.clear();
    }

    synchronized public void getUserLessons(ClientHandler clientHandler) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from lessons join student_lessons on lessons.lessonid = student_lessons.lessonid where student_lessons.id = ? or lessons.teacherid = ?");
        preparedStatement.setInt(1, clientHandler.id);
        preparedStatement.setInt(2, clientHandler.id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> respond = new ArrayList<>();
        respond.add(ServerReqType.GETUSERLESSONS.toString());
        while (resultSet.next()) {
            respond.add(RespondType.SUCCESSFUL.toString());
            respond.add(resultSet.getString("lessonid"));
            respond.add(resultSet.getString("name"));
            respond.add(resultSet.getString("prereq"));
            respond.add(resultSet.getString("teacherid"));
            respond.add(resultSet.getString("college"));
            respond.add(resultSet.getString("units"));
            respond.add(resultSet.getString("level"));
            respond.add(resultSet.getString("capacity"));
            respond.add(resultSet.getString("days"));
            respond.add(resultSet.getString("time"));
            respond.add(resultSet.getString("examdate"));
        }
        clientHandler.sendMessage(respond.toString());
        respond.clear();
    }

    synchronized public void getRecommendList(ClientHandler clientHandler) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<String> respond = new ArrayList<>();
        respond.add(ServerReqType.GETRECOMMENDLIST.toString());
        if (clientHandler.isStudent) {
            preparedStatement = connection.prepareStatement("select * from recommend_req join sut_members on recommend_req.teacherid = sut_members.id where recommend_req.studentid = ?");
            preparedStatement.setInt(1, clientHandler.id);
        } else if (clientHandler.isTeacher) {
            preparedStatement = connection.prepareStatement("select * from recommend_req join sut_members on recommend_req.studentid = sut_members.id where recommend_req.teacherid = ?");
            preparedStatement.setInt(1, clientHandler.id);

        } else {
            //todo
            preparedStatement = connection.prepareStatement("");
        }
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            respond.add(RespondType.SUCCESSFUL.toString());
            respond.add(resultSet.getString("id"));
            respond.add(resultSet.getString("firstname") + " " + resultSet.getString("lastname"));
            respond.add(resultSet.getString("result"));
        }
        clientHandler.sendMessage(respond.toString());
        respond.clear();
    }

    synchronized public void setRecommendReq(ClientHandler clientHandler, String teacherID) throws SQLException {
        List<String> res = new ArrayList<>();
        res.add(ServerReqType.RECOMMENDREQ.toString());
        if (clientHandler.isStudent) {
            String check = findMemberRelation(Integer.parseInt(teacherID));
            if (check.equals("O")) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into recommend_req values (? , ? , default)");
                preparedStatement.setInt(1, clientHandler.id);
                preparedStatement.setInt(2, Integer.parseInt(teacherID));
                preparedStatement.execute();
                res.add(RespondType.SUCCESSFUL.toString());
            } else {
                res.add(RespondType.UNSUCCESSFUL.toString());
            }
            clientHandler.sendMessage(res.toString());
            getRecommendList(clientHandler);
        }

    }

    synchronized public String findMemberRelation(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select relation from sut_members where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("relation");
        }
        return "NULL";
    }

    synchronized public void setMinorReq(ClientHandler clientHandler, List<String> orders) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<String> respond = new ArrayList<>();
        respond.add(ServerReqType.MINORREQ.toString());
        if (clientHandler.isStudent) {
            preparedStatement = connection.prepareStatement("select grade_average from students where id = ?");
            preparedStatement.setInt(1, clientHandler.id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double score = resultSet.getDouble("grade_average");
                if (score >= 14.0) {
                    preparedStatement = connection.prepareStatement("insert into minor_req values (?,?,?,default)");
                    preparedStatement.setInt(1, clientHandler.id);
                    preparedStatement.setString(2, clientHandler.college);
                    preparedStatement.setString(3, orders.get(1));
                    preparedStatement.execute();
                    respond.add(RespondType.SUCCESSFUL.toString());
                } else {
                    respond.add(RespondType.UNSUCCESSFUL.toString());
                }
            } else {
                respond.add(RespondType.UNSUCCESSFUL.toString());
            }
        }
        getMinorReqList(clientHandler);
        clientHandler.sendMessage(respond.toString());
        respond.clear();
    }

    synchronized public void getMinorReqList(ClientHandler clientHandler) throws SQLException {
        List<String> res = new ArrayList<>();
        res.add(ServerReqType.MINORREQLIST.toString());
        if (clientHandler.isStudent) {
            PreparedStatement preparedStatement = connection.prepareStatement("select aim_college , result from minor_req where studentid = ?");
            preparedStatement.setInt(1, clientHandler.id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                res.add(RespondType.SUCCESSFUL.toString());
                res.add(resultSet.getString("aim_college"));
                res.add(resultSet.getString("result"));
            }
        }
        clientHandler.sendMessage(res.toString());
        res.clear();
    }

    synchronized public void setLeaveReq(ClientHandler clientHandler, List<String> order) throws SQLException {
        List<String> res = new ArrayList<>();
        res.add(ServerReqType.LEAVEREQ.toString());

        PreparedStatement preparedStatement = connection.prepareStatement("select * from leave_req where studentid = ?");
        preparedStatement.setInt(1, clientHandler.id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            res.add(RespondType.SUCCESSFUL.toString());
            preparedStatement = connection.prepareStatement("insert into leave_req values (?,default)");
            preparedStatement.setInt(1,clientHandler.id);
            preparedStatement.execute();
        } else {
            res.add(RespondType.UNSUCCESSFUL.toString());
        }
        getLeaveReqList(clientHandler);
        clientHandler.sendMessage(res.toString());
        res.clear();
    }

    synchronized public void getLeaveReqList(ClientHandler clientHandler) throws SQLException {
        List<String> res = new ArrayList<>();
        res.add(ServerReqType.LEAVEREQLIST.toString());
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        if (clientHandler.isStudent){
            preparedStatement = connection.prepareStatement("select * from leave_req where studentid = ?");
            preparedStatement.setInt(1,clientHandler.id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                res.add(resultSet.getString("result"));
            }
        }
        clientHandler.sendMessage(res.toString());
    }

    public void setObjection(ClientHandler clientHandler, List<String> order) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        if (clientHandler.isStudent){
            preparedStatement = connection.prepareStatement("update student_lessons set student_objection = ? where id = ? and lessonid = ?");
            preparedStatement.setString(1,order.get(order.size()-1));
            preparedStatement.setInt(2,clientHandler.id);
            preparedStatement.setInt(3,Integer.parseInt(order.get(1)));
            preparedStatement.executeUpdate();
        }
    }

    public void getTemporaryGradesList(ClientHandler clientHandler) throws SQLException {
        List<String> res = new ArrayList<>();
        res.add(ServerReqType.TEMPORARYGRADESLIST.toString());

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        if (clientHandler.isStudent){
            preparedStatement = connection.prepareStatement("select * from student_lessons where id = ?");
            preparedStatement.setInt(1,clientHandler.id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                res.add(RespondType.SUCCESSFUL.toString());
                res.add(resultSet.getString("lessonid"));
                res.add(resultSet.getString("score"));
                res.add(resultSet.getString("student_objection"));
                res.add(resultSet.getString("teacher_answer"));
            }
        }
        clientHandler.sendMessage(res.toString());
    }
}
