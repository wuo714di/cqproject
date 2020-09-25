package com.cq.service;

import com.cq.entity.AccountEntity;

import java.util.List;

/**
 * @author changqing
 * @date 2020-07-14 13:57
 * @description:
 */
public interface AccountService {
    String a="sss";

    List<AccountEntity> selectAccount();
    default void get(){
        System.out.println("ddddd" +
                "");
    }

}
