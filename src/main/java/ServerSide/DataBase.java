package ServerSide;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBase {
    Connection connection;
    private static DataBase dataBase;

    private DataBase() {
        try {
            connection = DriverManager.getConnection(Config.databaseUrl, Config.databaseUser, Config.databasePass);
        } catch (SQLException e) {
            e.printStackTrace();
            //todo
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
                clientHandler.email = resultSet.getString("email");
                clientHandler.phoneNumber = resultSet.getString("phonenumber");
                clientHandler.college = resultSet.getString("college");
                clientHandler.lastLoginTime = hour + ":" + minute + ":" + seconds;

                //creating respond msg




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
}
