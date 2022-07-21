import ServerSide.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestThis {
    public static void main(String[] args) {
        String userName = "hossein";
        String pass = "12";
        try {
            Connection connection = DriverManager.getConnection(Config.databaseUrl, Config.databaseUser, Config.databasePass);
            PreparedStatement preparedStatement = connection.prepareStatement("select username from user where password=? and username =?");
            preparedStatement.setString(1,pass);
            preparedStatement.setString(2,userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println(userName + " : you had loged in!");
                PreparedStatement  preparedStatement1 = connection.prepareStatement("update user set userid = userid+1 where username=?");
                preparedStatement1.setString(1,userName);
                preparedStatement1.executeUpdate();
                while (resultSet.next()){
                    System.out.println(resultSet.getString("userid"));
                }


            }else {
                System.out.println("pass or un is wrong!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
