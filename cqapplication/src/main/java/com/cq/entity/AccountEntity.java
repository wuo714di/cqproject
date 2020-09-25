package com.cq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author changqing
 * @date 2020-07-14 13:48
 * @description:
 */
@Data
@TableName(value ="account" )
public class AccountEntity {
    @TableField(value = "account_code")
    private String accountCode;
    @TableField(value = "account_name")
    private String accountName;
    @TableField(value = "account_age")
    private String accountAge;


}
