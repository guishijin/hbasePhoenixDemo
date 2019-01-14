package com.hbase.phoenix.jdbc.client.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        //---------------------------------------------------------------------
        // 解决用户问题
        System.setProperty("HADOOP_USER_NAME", "alphasta");
        //---------------------------------------------------------------------

        SpringApplication.run(DemoApplication.class, args);

        test();
    }

    /**
     * 测试phone连接
     */
    private static void test() {
        try {
            //org.apache.phoenix.jdbc.PhoenixDriver driver;
            String phoenixJDBCUrl = "jdbc:phoenix:10.1.100.102:2181";
            Connection conn = DriverManager.getConnection(phoenixJDBCUrl);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from GSJ.USER");
            while (rs.next()) {
                System.out.println("\r\n============================================\r\n");
                String v1 = rs.getString("ID");
                System.out.println(v1);
                System.out.println("\r\n============================================\r\n");
                String v2 = rs.getString("NAME");
                System.out.println(v2);
                System.out.println("\r\n============================================\r\n");
                String v3 = rs.getString("PASSWORD");
                System.out.println(v3);
                System.out.println("\r\n============================================\r\n");
            }
            stat.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

