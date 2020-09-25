package com.cq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author changqing
 * @date 2020-07-02 11:41
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OriDemo {
    /**
     * DTO:UserDTO(name=null, age=null)========>BO:UserBO(name=null, age=null)
     * 说明转换器将空对象转化不会报异常
     */
   /* @Test
    public void test1() {
        UserDTO userDTO = new UserDTO();
        UserBO map = OrikaMapperUtils.map(userDTO, UserBO.class);
        
        log.info("DTO:{}========>BO:{}", userDTO, map);
    }

    @Test
    public void test2() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFlowerName("zhangsan");
        String userName = userDTO.getUserName();
        log.info("测试protected:{}", userName);
    }*/

    /**
     * 做stream流的过滤的校验
     * 过滤是空的数据：[UserDTO(name=null, age=null, FlowerName=null)]
     * filter中的条件是返回的数据条件
 /*    *//*
    @Test
    public void test() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFlowerName("zhangsan");
        UserDTO userDTO1 = new UserDTO();
        userDTO.setName("zhangsan");
        ArrayList<UserDTO> list = new ArrayList<>();
        list.add(userDTO);
        list.add(userDTO1);
        List<UserDTO> collect = list.stream().filter(l -> StringUtils.isNotEmpty(l.getFlowerName())).collect(Collectors.toList());
        log.info("过滤是空的数据：{}", collect);

    }*/

    @Test
    public void test3() {
        int i = Runtime.getRuntime().availableProcessors();
        //获取系统的线程数
        System.out.println(i);
        TestPoolDestroy();
    }

    /**
     * 通过闭锁来实现线程的停止
     */
    private static void TestPoolDestroy() {
        ExecutorService batchTaskPool = Executors.newFixedThreadPool(3);
        final CountDownLatch latch = new CountDownLatch(3);// 闭锁
        for (int i = 0; i < 3; i++) {
            batchTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "进入run");
                        Thread.sleep(5 * 1000);
                        System.out.println(Thread.currentThread().getName() + "退出run");
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            latch.await();// 闭锁产生同步效果
            System.out.println("三个都执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(10);
        list.add(2);
        list.add(7);
        list.add(5);
        list.add(3);
        //升序排序[1, 2, 3, 3, 5, 7, 10]

        List<Integer> collect = list.stream().sorted().collect(Collectors.toList());
        System.out.println(collect);
    }

   /* @Test
    public void test5() {
      *//*
      他们两个都是通过Synchronized修饰的，因此都是线程安全的。
      ConcurrentHashMap;
        Hashtable*//*
        UserDTO userDTO = new UserDTO();
        userDTO.setIsPublicStatus(true);
        System.out.println(userDTO);
    }
*/
    @Test
    public void test6() {
        Integer a = 10;
        try {
            Integer i = a / 0;
        }catch (Exception e){
            log.info("info=====>{}",e.getMessage());
            log.error("info=====>{}",e.getMessage());
        }


    }
    @Test
    public void test7(){
        byte a=1;
        //byte b =a+1; a+1默认会自动提升表达式的类型为int，因此编译报错
        a +=1;
        int c;
        Integer d;

    }
    @Test
    public void test8(){
        //缓存线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        Object o = new Object();

        executorService.execute(new Runnable() {
            @Override
            public void run() {



            }
        });

    }

    /**
     * Segment
     */
    @Test
    public void test9(){
        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

    }

    /**
     * ：：语法的使用。
     *
     */
    @Test
    public void test10(){
        String join = StringUtils.join("111", "222", "222");
    }

}
