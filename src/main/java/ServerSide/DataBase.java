package ServerSide;


import java.sql.*;

public class DataBase {
    Connection connection;
    private static DataBase dataBase;
    private DataBase(){
        try {
            connection = DriverManager.getConnection(Config.databaseUrl, Config.databaseUser, Config.databasePass);
        } catch (SQLException e) {
            e.printStackTrace();
            //todo
        }
    }
    public static DataBase getInstance(){
        if (dataBase == null){
            dataBase = new DataBase();
        }
        return dataBase;
    }
    synchronized void checkLogin(ClientHandler clientHandler, String username, String password){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sut_members where id = ? and password =?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                clientHandler.sendMessage("YOU HAD LOGED IN SUCCESSFULLY");
                //todo for next
            }else {
                clientHandler.sendMessage("USERNAME OR PASSWORD IS WRONG!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //todo
        }

    }
}
