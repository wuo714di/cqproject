package com.cq.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author changqing
 * @date 2020-07-02 11:47
 * @description:
 * 使用@Builder的时候必须构造有参和无参，否则new对象的时候容易报错。
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBO {
    private String name;
    private Integer age;
}
