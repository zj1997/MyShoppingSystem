package cn.zj.service;

import cn.zj.dao.UserDao;
import cn.zj.pojo.User;
import cn.zj.web.WelcomePage;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhaojie
 * @date 2019\7\17 0017 - 19:01
 */
public class UserListService {

    UserDao userDao = new UserDao();
    Scanner scanner = new Scanner(System.in);


    public void choiceController(String choice) throws SQLException, InterruptedException {


        if(choice.equals("1")){

            //查询所有会员信息列表

            System.out.println("我行我素购物管理系统>客户信息管理>查询所有会员信息");
            System.out.println("***********************************************");
            List<User> users = userDao.getAllUser();
            System.out.println();
            System.out.println();
            System.out.println("会员姓名 账号密码 会员生日 会员卡号 会员积分 会员等级");
            for(User user:users){
                System.out.println();
                System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getBirthday()+" "+user.getVipid()+" "+user.getUsermoney()+" "+user.getViplevelDetail());
            }


        }else if(choice.equals("2")){

            //添加用户信息

            User user = new User();

            System.out.println("我行我素购物管理系统>客户信息管理>添加新会员信息");
            System.out.println("***********************************************");
            System.out.println();

            System.out.println("请输入会员姓名：");
            String username = scanner.next();
            user.setUsername(username);

            System.out.println("请输入会员密码：");
            String password = scanner.next();
            user.setPassword(password);

            System.out.println("请输入会员生日《月/日<两位数表示>》：");
            String birthday = scanner.next();
            user.setBirthday(birthday);

            System.out.println("请输入会员等级(普通，金卡):");
            String viplevel = scanner.next();
            int level =0;
            if(viplevel.equals("普通")){
                level=0;
            }else if(viplevel.equals("金卡")){
                level=1;
            }
            user.setViplevel(level);

            int vipid = (int)(Math.random()*(999999-100000+1))+100000;
            user.setVipid(vipid+"");
            user.setUsermoney(0);

            Integer isSuccess = userDao.SaveUser(user);

            System.out.println();
            System.out.println();
            if(isSuccess!=0){
                System.out.println("已录入的会员信息是：");
                System.out.println("会员姓名 账号密码 会员生日 会员卡号 会员积分 会员等级");
                System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getBirthday()+" "+user.getVipid()+" "+user.getUsermoney()+" "+user.getViplevelDetail());
            }else {
                System.out.println("信息录入失败！！！！");

            }

        }else if(choice.equals("3")){


            //修改用户信息
            System.out.println("我行我素购物管理系统>客户信息管理>修改会员信息");
            System.out.println("***********************************************");
            System.out.println();

            System.out.println("请输入你要修改的会员卡号:");
            String vipid = scanner.next();
            User user = userDao.getUserVipId(vipid);
            System.out.println("该用户信息如下：");
            System.out.println("***********************************************************");
            System.out.println("会员姓名 账号密码 会员生日 会员卡号 会员积分 会员等级");
            System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getBirthday()+" "+user.getVipid()+" "+user.getUsermoney()+" "+user.getViplevelDetail());
            System.out.println();
            System.out.println("你可以修改的部分如下：");
            System.out.println("1.会员姓名   2.账号密码  3.会员生日  4.会员等级");
            System.out.println("***********************************************************");
            System.out.println("请输入你要修改的部分（按#结束）：");
            System.out.println();

            String column = scanner.next();

            while(!column.equals("#")){

                System.out.println("请输入新的信息：");
                String value = scanner.next();
                User updateuser = new ServiceController().copyObject(user);

                if(column.equals("1")){
                    updateuser.setUsername(value);
                    userDao.UpdateUser(updateuser);
                }else if(column.equals("2")){
                    updateuser.setPassword(value);
                    userDao.UpdateUser(updateuser);
                }else if(column.equals("3")){
                    updateuser.setBirthday(value);
                    userDao.UpdateUser(updateuser);
                }else if(column.equals("4")) {

                    //输入会员等级转化逻辑
                    int level=0;

                    if(value.equals("普通")){
                        level=0;
                    }else if(value.equals("金卡")){
                        level=1;
                    }

                    updateuser.setViplevel(level);

                    userDao.UpdateUser(updateuser);
                }else{
                    System.out.println("输入不规范，请重新输入！");
                }

                User user1 = userDao.getUserVipId(vipid);

                System.out.println("修改后的会员信息为：");
                System.out.println("***********************************************************");
                System.out.println("会员姓名 账号密码 会员生日 会员卡号 会员积分 会员等级");
                System.out.println(user1.getUsername()+" "+user1.getPassword()+" "+user1.getBirthday()+" "+user1.getVipid()+" "+user1.getUsermoney()+" "+user1.getViplevelDetail());
                System.out.println();
                System.out.println("请继续输入您要修改的选项，按#退出！");
                column = scanner.next();
            }



        }else if(choice.equals("4")) {

            System.out.println("我行我素购物管理系统>客户信息管理>查询会员信息");
            System.out.println("***********************************************");
            System.out.println();

            System.out.println("请输入你要查询的会员卡号:");
            String vipid = scanner.next();
            User user = userDao.getUserVipId(vipid);
            System.out.println("该用户信息如下：");
            System.out.println("***********************************************************");
            System.out.println("会员姓名 账号密码 会员生日 会员卡号 会员积分 会员等级");
            System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.getBirthday() + " " + user.getVipid() + " " + user.getUsermoney() + " " + user.getViplevelDetail());
            System.out.println();
        }else if(choice.equals("5")){
            new WelcomePage().mainMeun();
        }else{
            System.out.println("您输入的选项不规范，请重新输入！");
        }

        System.out.println("是否返回上一级菜单(y/n):");
        String returnchoice = scanner.next();

        //处理菜单返回操作
        if(returnchoice.equals("y")||returnchoice.equals("Y")){
            //返回上一级菜单
            new ServiceController().MeunController("1");
        }else if(returnchoice.equals("n")){
            System.out.println("系统将在3秒钟内退出。。。");
            Thread.sleep(3000);
            System.exit(0);
        }else{
            System.out.println("输入有误，系统将返回主菜单");
            //返回上一级菜单
            new ServiceController().MeunController("1");
        }



    }


}
