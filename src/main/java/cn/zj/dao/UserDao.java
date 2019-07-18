package cn.zj.dao;

import cn.zj.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaojie
 * @date 2019\7\17 0017 - 14:01
 */
public class UserDao {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;


    public Connection getConnection(){

        // 数据库驱动类名的字符串
        String driver = "com.mysql.jdbc.Driver";
        // 数据库连接串
        String url = "jdbc:mysql:///myshopping";
        // 用户名
        String username = "root";
        // 密码
        String password = "199715";


        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(driver);
            // 2、获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            //3.返回连接
            if(conn!=null) {
                return conn;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获得所有用户
    public List<User> getAllUser() throws SQLException {

        List<User> users = new ArrayList();

        conn = getConnection();
        //定义操作的SQL语句
        String sql = "select * from user";
        //获取数据库操作对象
        stmt = conn.prepareStatement(sql);
        //执行数据库操作
        rs = stmt.executeQuery();
        //获取并操作结果集
        while (rs.next()) {

            User user = new User();

            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setBirthday(rs.getString("birthday"));
            user.setUsermoney(rs.getInt("usermoney"));
            user.setVipid(rs.getString("vipid"));
            user.setViplevel(rs.getInt("viplevel"));

            users.add(user);
        }

        return users;
    }

    //根据会员卡号查询用户
    public User getUserVipId(String VipId) throws SQLException {

        conn = getConnection();

        //定义操作的SQL语句
        String sql = "select * from user where vipid =?";

        //获取数据库操作对象
        stmt = conn.prepareStatement(sql);

        stmt.setString(1,VipId);
        //执行数据库操作
        rs = stmt.executeQuery();
        //获取并操作结果集

        User user = null;

        while (rs.next()) {

            user = new User();

            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setBirthday(rs.getString("birthday"));
            user.setUsermoney(rs.getInt("usermoney"));
            user.setVipid(rs.getString("vipid"));
            user.setViplevel(rs.getInt("viplevel"));

        }
        return user;
    }

    //根据用户名和密码进行用户登录
    public User getUser(String username,String password) throws SQLException {

        conn = getConnection();
        //定义操作的SQL语句
        String sql = "select * from user where username =? and password =?";
        //获取数据库操作对象
        stmt = conn.prepareStatement(sql);
        stmt.setString(1,username);
        stmt.setString(2,password);
        //执行数据库操作
        rs = stmt.executeQuery();
        //获取并操作结果集

        User user = null;
        while(rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setBirthday(rs.getString("birthday"));
            user.setUsermoney(rs.getInt("usermoney"));
            user.setVipid(rs.getString("vipid"));
            user.setViplevel(rs.getInt("viplevel"));
        }

        return user;
    }

    //添加用户
    public Integer SaveUser(User user) throws SQLException {

        conn = getConnection();
        //定义操作的SQL语句
        String sql = "insert into user(username,password,birthday,vipid,usermoney,viplevel) value(?,?,?,?,?,?)";
        //获取数据库操作对象
        stmt = conn.prepareStatement(sql);
        stmt.setString(1,user.getUsername());
        stmt.setString(2,user.getPassword());
        stmt.setString(3,user.getBirthday());
        stmt.setString(4,user.getVipid());
        stmt.setInt(5,user.getUsermoney());
        stmt.setInt(6,user.getViplevel());

        //执行数据库操作
        int flag = stmt.executeUpdate();
        //获取并操作结果集
        return flag;
    }

    //修改用户信息
    public void UpdateUser(User user) throws SQLException {

        conn = getConnection();
        //定义操作的SQL语句
        String sql = "update user set username =? ,password = ? ,birthday =? ,usermoney = ? ,viplevel = ? where vipid = ?";
        //获取数据库操作对象
        stmt = conn.prepareStatement(sql);
        stmt.setString(1,user.getUsername());
        stmt.setString(2,user.getPassword());
        stmt.setString(3,user.getBirthday());
        stmt.setString(6,user.getVipid());
        stmt.setInt(4,user.getUsermoney());
        stmt.setInt(5,user.getViplevel());

        //执行数据库操作
        int flag = stmt.executeUpdate();

    }


}
