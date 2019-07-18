package cn.zj.service;

import cn.zj.dao.UserDao;
import cn.zj.pojo.User;
import cn.zj.web.WelcomePage;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhaojie
 * @date 2019\7\17 0017 - 11:17
 */
public class ServiceController {

    UserDao userDao = new UserDao();

    Scanner scanner = new Scanner(System.in);

    //欢迎页控制层
    public String WelcomeController(String choice) throws SQLException {

        String flag = "0";//控制标志变量

        if(choice.equals("1")){

            //登录操作
            int i = 3;  //拥有三次登录的机会

            while(i>0) {
                System.out.println("请输入管理员账号:");
                String username = scanner.next();
                System.out.println("请输入管理员密码:");
                String password = scanner.next();

                User user = userDao.getUser(username, password);

                if (user != null) {
                    flag = "1";
                    return flag;
                }else{
                    i--;
                    System.out.println("密码错误，你还有："+i+"次机会！");
                }
            }
        }else if (choice.equals("2")){

            System.out.println("     谢谢使用O(∩_∩)O哈哈~");
            //退出系统
            System.exit(0);

        }else{

            //错误操作
            System.out.println("您输入的操作有误，请重新选择");
            System.out.println();
            System.out.println();

            return flag;
        }

        return flag;
    }

    //主菜单控制层
    public Integer MeunController(String choice) throws SQLException, InterruptedException {

        Integer flag = 0;//控制标志变量

        if(choice.equals("1")){
            //1.客户信息管理
            System.out.println();
            System.out.println();
            System.out.println("          欢迎进入客户信息管理系统       ");
            System.out.println("***********************************************");
            System.out.println();
            System.out.println("            1.查询所有会员信息");
            System.out.println();
            System.out.println("            2.添加会员");
            System.out.println();
            System.out.println("            3.修改会员信息");
            System.out.println();
            System.out.println("            4.查询会员信息");
            System.out.println();
            System.out.println("            5.返回上一级菜单");
            System.out.println();
            System.out.print("  请输入你的操作:");
            String userchoice = scanner.next();
            System.out.println();
            System.out.println();
            new UserListService().choiceController(userchoice);

        }else if (choice.equals("2")){

            //2.购物结算
            System.out.println();
            System.out.println();
            System.out.println("           欢迎进入购物结算积分系统      ");
            System.out.println("*****************************************");
            System.out.println();

            System.out.print("  请输入会员卡号：");
            String vipid = scanner.next();

            System.out.print("  请输入消费金额：");
            int money = scanner.nextInt();

            System.out.println("*****************************************");
            System.out.println("1.满50 元，加2元换购百事可乐一瓶");
            System.out.println("2.满100元，加3元换购500ml可乐一瓶");
            System.out.println("3.满100元，加10元换购5斤面粉");
            System.out.println("4.满200元，加10元换购苏泊尔炒菜锅一个");
            System.out.println("5.满200元，加20元换购欧莱雅爽肤水一瓶");
            System.out.println("0.不换购");
            System.out.print("请选择：");

            String changechoice = scanner.next();

            if(changechoice.equals("1")&&money<50||
                    changechoice.equals("2")&&money<100||
                    changechoice.equals("3")&&money<100||
                    changechoice.equals("4")&&money<200||
                    changechoice.equals("5")&&money<200){
                System.out.println("该会员消费金额不符合换购条件");
            }else {

                //处理换购逻辑
                switch (changechoice) {
                    case "1":
                        money += 2;
                        System.out.println("本次消费总金额:" + money);
                        System.out.println("成功换购:百事可乐一瓶");
                        break;
                    case "2":
                        money += 3;
                        System.out.println("本次消费总金额:" + money);
                        System.out.println("成功换购:500ml可乐一瓶");
                        break;
                    case "3":
                        money += 10;
                        System.out.println("本次消费总金额:" + money);
                        System.out.println("成功换购:5斤面粉");
                        break;
                    case "4":
                        money += 10;
                        System.out.println("本次消费总金额:" + money);
                        System.out.println("成功换购:苏泊尔炒菜锅一个");
                        break;
                    case "5":
                        money += 20;
                        System.out.println("本次消费总金额:" + money);
                        System.out.println("成功换购:欧莱雅爽肤水一瓶");
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("输入有误");
                }


                //更新用户积分
                User userVipId = userDao.getUserVipId(vipid);
                User user = copyObject(userVipId);
                user.setUsermoney(user.getUsermoney()+money);
                userDao.UpdateUser(user);
                System.out.println();
                System.out.println("会员:"+user.getUsername()+"当前积分为："+user.getUsermoney());

            }
        }else if(choice.equals("3")){
            //3.真情回馈
            System.out.println();
            System.out.println();
            System.out.println("           欢迎进入真情大回馈       ");
            System.out.println("*****************************************");
            System.out.println();
            System.out.println("            1.幸运大放送");
            System.out.println();
            System.out.println("            2.幸运抽奖");
            System.out.println();
            System.out.println("            3.生日问候");
            System.out.println();
            System.out.print("  请输入你的操作:");
            String funchoice = scanner.next();

            System.out.println("请输入会员卡号:");
            String vipmember = scanner.next();

            User userVipId = userDao.getUserVipId(vipmember);
            System.out.println();

            if(funchoice.equals("1")){
                //幸运大放送
                System.out.println("用户"+userVipId.getUsername()+" 当前的积分为："+userVipId.getUsermoney()+" 卡类型："+userVipId.getViplevelDetail());
                System.out.println();
                System.out.println("             兑换中......");
                Thread.sleep(2000);

                if(userVipId.getViplevel().equals(0)){

                    if(userVipId.getUsermoney() <= 3000){
                        System.out.println("不好意思，当前的积分不足，不能回馈。。。");
                    }else{
                        System.out.println();
                        System.out.println("回馈积分:500分！");

                        User user = copyObject(userVipId);

                        user.setUsermoney(user.getUsermoney()+500);

                        userDao.UpdateUser(user);

                        User userVipId1 = userDao.getUserVipId(userVipId.getVipid());
                        System.out.println();
                        System.out.println("当前会员的积分为： "+userVipId1.getUsermoney());

                    }

                }else if(userVipId.getViplevel().equals(1)){
                    if(userVipId.getUsermoney() <= 1000){

                        System.out.println("不好意思，当前的积分不足，不能回馈。。。");

                    }else{
                        System.out.println();
                        System.out.println("回馈积分:500分！");

                        User user = copyObject(userVipId);

                        user.setUsermoney(user.getUsermoney()+500);

                        userDao.UpdateUser(user);

                        User userVipId1 = userDao.getUserVipId(userVipId.getVipid());
                        System.out.println();
                        System.out.println("当前会员的积分为： "+userVipId1.getUsermoney());


                    }
                }


            }else if(funchoice.equals("2")){
                //幸运抽奖
                List<Integer> list = new ArrayList<Integer>();
                boolean awardflag = false;
                int i=5;
                while(i>=0){
                    int code = (int)(Math.random()*(999999-100000+1))+100000;
                    list.add(code);
                    i--;
                }

                System.out.println("本次的抽奖号码为：");

                for(Integer num : list){

                    System.out.print(num + "  ");
                    if(num.equals(userVipId.getVipid())){
                        awardflag = true;
                    }
                }


                if(awardflag){
                    System.out.println();
                    System.out.println("恭喜，该会员是本日的幸运会员！！！  赠送1000积分");

                    User user = copyObject(userVipId);

                    user.setUsermoney(user.getUsermoney()+1000);

                    userDao.UpdateUser(user);

                    User userVipId1 = userDao.getUserVipId(userVipId.getVipid());
                    System.out.println();
                    System.out.println();
                    System.out.println("当前会员的积分为： "+userVipId1.getUsermoney());

                }else {
                    System.out.println();
                    System.out.println("不好意思，该会员不是今日的幸运会员。。。。");
                }


            }else if(funchoice.equals("3")){
                //生日问候

                //对比会员成日是否是当天
                long time = System.currentTimeMillis();
                Date date = new Date(time);
                String ma = "MM/dd";
                SimpleDateFormat format = new SimpleDateFormat(ma);
                String nowdate = format.format(date);

                if(nowdate.equals(userVipId.getBirthday())){

                    System.out.println("今天是该会员的生日，奖励积分1000！");

                    User user = copyObject(userVipId);

                    user.setUsermoney(user.getUsermoney()+1000);

                    userDao.UpdateUser(user);

                    User userVipId1 = userDao.getUserVipId(userVipId.getVipid());
                    System.out.println();
                    System.out.println("当前会员的积分为： "+userVipId1.getUsermoney());
                }else{

                    System.out.println("今天不是该会员的生日。。。");
                }
            }
            System.out.println();

        }else if(choice.equals("4")){
            //4注销操作
            new WelcomePage().welcome();

        }else {
            //错误操作
            System.out.println("您输入的操作有误，请重新选择");
            System.out.println();
            System.out.println();
            new WelcomePage().mainMeun();
        }

        System.out.println("是否返回主菜单(y/n):");
        String returnchoice = scanner.next();

        //处理菜单返回操作
        if(returnchoice.equals("y")||returnchoice.equals("Y")){
            new WelcomePage().mainMeun();
        }else if(returnchoice.equals("n")){
            System.out.println("系统将在3秒钟内退出。。。");
            Thread.sleep(3000);
            System.exit(0);
        }else{
            System.out.println("输入有误，系统将返回主菜单");
            new WelcomePage().mainMeun();
        }


        return flag;
    }


    //对象更新工具类
    public User copyObject(User user){

        User u = new User();

        u.setId(user.getId());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setVipid(user.getVipid());
        u.setViplevel(user.getViplevel());
        u.setBirthday(user.getBirthday());
        u.setUsermoney(user.getUsermoney());

        return u;
    }

}
