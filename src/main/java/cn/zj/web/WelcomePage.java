package cn.zj.web;

import cn.zj.service.ServiceController;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author zhaojie
 * @date 2019\7\17 0017 - 10:59
 */
public class WelcomePage {

    //业务逻辑控制类
    ServiceController serviceController = new ServiceController();

    Scanner scanner = new Scanner(System.in);

    //开始界面
    public void welcome() throws SQLException, InterruptedException {

        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("       欢迎使用我行我素购物管理系统        ");
            System.out.println();
            System.out.println();
            System.out.println("              1.登录系统");
            System.out.println();
            System.out.println();
            System.out.println("              2.退出系统");
            System.out.println();
            System.out.println("*****************************************");
            System.out.print("请选择您的操作：");
            String choice = scanner.next();
            String flag = serviceController.WelcomeController(choice);//跳转控制层
            System.out.println();
            System.out.println();

            if (flag.equals("1")) {

                mainMeun();
                return;
            }

    }

}



    //主菜单模式
    public void mainMeun() throws SQLException, InterruptedException {

        System.out.println();
        System.out.println();
        System.out.println("           欢迎使用我行我素购物管理系统        ");
        System.out.println("***********************************************");
        System.out.println();
        System.out.println("              1.客户信息管理");
        System.out.println();
        System.out.println("              2.购物结算");
        System.out.println();
        System.out.println("              3.真情回馈");
        System.out.println();
        System.out.println("              4.注销");
        System.out.println();
        System.out.println("***********************************************");
        System.out.print("请选择您的操作：");
        String choice = scanner.next();
        Integer flag = serviceController.MeunController(choice);//跳转控制层
        System.out.println();
        System.out.println();

    }






    public static void main(String[] args) throws SQLException, InterruptedException {
        new WelcomePage().welcome();


    }
}
