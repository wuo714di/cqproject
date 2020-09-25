package com.cq.dao;


import com.cq.entity.AccountEntity;

import com.cq.myBatisUtils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author changqing
 * @date 2020-07-14 13:50
 * @description:
 */
@Mapper
public interface AccountDao extends BaseDao<AccountEntity> {

}
