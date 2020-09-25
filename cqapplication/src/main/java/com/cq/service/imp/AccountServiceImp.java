package com.cq.service.imp;


import com.cq.BO.UserBO;
import com.cq.dao.AccountDao;
import com.cq.entity.AccountEntity;
import com.cq.myBatisUtils.EntityWrapper;
import com.cq.service.AccountService;

import com.cq.util.OrikaMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author changqing
 * @date 2020-07-14 13:58
 * @description:
 */
@Service
public  class AccountServiceImp  implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NEVER )
    public List<AccountEntity> selectAccount() {

        get();
        this.get();
       // int propagationNever = TransactionDefinition.PROPAGATION_NEVER;
        EntityWrapper<AccountEntity> wrapper = new EntityWrapper<>();


        wrapper.eq("account_code","tianyalan").or().eq("account_age",1113);
        List<AccountEntity> accountEntities = accountDao.selectList(wrapper);
        System.out.println(a);
      //  OrikaMapperUtils.map(accountEntities, UserBO.class);
        return accountEntities;
    }
   public  final String a;
    public void demo(){
     //   a="111";

    }
   /* public  AccountServiceImp(){
        a= "111";
    }*/
    {
        a="222";
    }


}
