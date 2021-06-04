package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundExeption, SQLExeption {
        //데이터 어딨어? => mysql
        //driver loading
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection
        Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost/jeju?" +
                                "characterEncoding=utf-8&serverTimezone=UTC"
                        ,"jeju","jejupw"
                );
        //query
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from  userinfo where id = ?"
        );
        preparedStatement.setInt(1, id);
        //실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //결과 매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과 리턴
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        //데이터 어딨어? => mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost/jeju?" +
                                "characterEncoding=utf-8&serverTimezone=UTC"
                        ,"jeju","jejupw"
                );
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo (name, password) values ( ?, ? )"
                , Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        user.setId(resultSet.getInt(1));

        preparedStatement.close();
        connection.close();
    }
}