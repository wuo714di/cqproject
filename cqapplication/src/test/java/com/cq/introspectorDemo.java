package com.cq;

import com.cq.BO.UserBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * @ClassName : introspectorDemo
 * @Description : Introspector内省类使用
 * @Author : WXD
 * @Date: 2020-09-25 11:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class introspectorDemo {

    /**
     * Introspector内省工具类
     * 用于获取javaBean的一些gettter。setter属性
     *
     */
    @Test
    public void test() {
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(UserBO.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (!"class".equals(propertyDescriptor.getName())) {
                System.out.println(propertyDescriptor.getName());
                System.out.println(propertyDescriptor.getWriteMethod().getName());
                System.out.println(propertyDescriptor.getReadMethod().getName());
                System.out.println("=======================");
            }
        }

    }
}
