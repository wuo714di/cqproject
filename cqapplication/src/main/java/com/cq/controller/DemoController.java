package com.cq.controller;

import com.cq.entity.AccountEntity;
import com.cq.loginAuth.AuthContext;
import com.cq.loginAuth.AuthEmum;
import com.cq.loginAuth.LoginRequired;
import com.cq.service.AccountService;
import com.cq.util.OrikaMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : DemoController
 * @Description : 测试使用
 * @Author : WXD
 * @Date: 2020-09-21 15:38
 */
@RestController
@Slf4j
public class DemoController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/demo")
   // @LoginRequired(description = "测试接口", auth = /*"1111"*/AuthContext.GLOBAL)
    public void demo() {
   /* AccountEntity accountEntity = new AccountEntity();
    User map = OrikaMapperUtils.map(accountEntity, User.class);
*/
        //  List<AccountEntity> accountEntities = accountService.selectAccount();

        log.info("111111");
    }
    @GetMapping(value = "/demo1")
    @LoginRequired(description = "测试接口", auth = "1111")
    public void demo1() {
        log.info("111111");


    }
}
