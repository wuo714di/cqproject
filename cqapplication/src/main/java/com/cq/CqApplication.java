package com.cq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName : CqApplication
 * @Description : 整个项目的启动类
 * @Author : WXD
 * @Date: 2020-09-21 11:44
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class CqApplication {
    public static void main(String[] args) {
        SpringApplication.run(CqApplication.class,args);
    }

}
