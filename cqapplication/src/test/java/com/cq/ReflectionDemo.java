package com.cq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author changqing
 * @date 2020-07-27 16:12
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ReflectionDemo {
    /**
     * java 的反射机制来获取类的所有属性和方法
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
    //    Student student = new Student();
        /**
         * 已经生成了class文件
         */
        // Class aClass = student.getClass();
        /**
         *需要导入类包，依赖太强，不导包就抛编译错误
         */
        //  Class studentClass = Student.class;
        Class clazz = Class.forName("com.cq.dto.Student");
        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。
        System.out.println("con = " + con);

        //调用构造方法
        Object obj = con.newInstance();
        //	System.out.println("obj = " + obj);
        //	Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');

    }

    /**
     * corePoolSize
     */
    public void test2() {
        /**
         * 创建线程池的两种方式
         * ExecutorService是一个接口，提供了操作线程的一些方法
         * ThreadPoolExecutor实现了vExecutorService，所以也有ExecutorService的方法
         */
        //单个线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //缓存线程池
        Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });
        //固定线程数的线程池
        ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        //执行定时任务类似的周期行任务的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        //ThreadPoolExecutor implements AbstractExecutorService implements ExecutorService
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 2000, TimeUnit.SECONDS, new LinkedBlockingQueue(5));
        threadPoolExecutor.shutdown();
        boolean shutdown = threadPoolExecutor.isShutdown();

    }

    /**
     * Volatile只能保证可见性，不能保证原子性，
     * 因此设计到变量运算的，还是要使用到Synchronized或是lock
     */
    private volatile Integer couunt = 0;

    @Test
    public void test3() throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger();
        if (couunt < 10) {
            System.out.println(15 >> 1);
            Thread.sleep(3000);
            couunt++;
            //实现递归，自己调自己
            this.test3();
        }

    }

    /**
     *
     * 给出一个Integer数组，找出数组和为最大值的子数组(子数组长度不限)
     * 思想：结果是[4, -1, 2, 1]
     * 1. 默认第一个元素作为一个组数组，为原始值
     * 2. 遍历数组元素，往后累加，获取最大值后，获取其对应的开始和结束的值
     * 总结，一定要分析好题目，理好逻辑
     */
    @Test
    public void test4() {
        Integer[] integers = new Integer[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int max = 0;
        int start=0;
        int end=0;

        for (int i = 0; i < integers.length; i++) {
            int sum=0;
            for (int j = i; j < integers.length; j++) {
             sum+=integers[j];
                if (sum > max) {
                    max = sum;
                    start=i;
                    end=j;
                }
            }
        }
        List<Integer> list=new ArrayList<>();
        for (int i = start; i <=end; i++) {
            list.add(integers[i]);
        }
        System.out.println(list.toString());
    }

    /**
     * 计算符合条件的子数组的和
     */
    private void countSonList(List list) {

    }


    /**
     * 每三个小朋友一组，依次翻跃4个障碍，要求组内所有小朋友都翻越其中一个障碍后，才能开始翻阅下一个障碍物。
     * 要求输出结果类似下方，时间精确到s并保留三位小数，请写出伪代码。
     * 2019-12-25 15:24:54 小明, 翻越了第0个障碍物, 使用了 1.494s
     * 2019-12-25 15:24:54 乔丹, 翻越了第0个障碍物, 使用了 1.755s
     * 2019-12-25 15:24:55 小乖, 翻越了第0个障碍物, 使用了 2.129s
     */

  /*
   @Test
  public void test5() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // ReflectionDemo.get(1,"小明");
        List<String> list = new ArrayList<>();
        list.add("小明");
        list.add("乔丹");
        list.add("小怪");
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (String s : list) {
                        get( i, s);
                    }

                }
            });
        }

    }

    public static void get(Integer count, String name) {
        Date date = new Date("yyyy-MM-dd HH:mm:ss");
         System.out.println(String.join(date.toString(),name,", 翻越了第",count.toString(),"个障碍物, 使用了"+ workTimes()));
    }

    *//**
     * 模拟翻越的时间
     * @return
     *//*
    public static String workTimes() {
        // ReflectionDemo.get(1,"小明");
        long l = System.currentTimeMillis();
        try {
            Thread.sleep(1004);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        System.out.println(timeInMillis - l);
        Long longs = timeInMillis - l;
        BigDecimal bigDecimal = new BigDecimal(longs);
        BigDecimal bigDecimal1 = new BigDecimal(1000);
        BigDecimal multiply = bigDecimal.divide(bigDecimal1, 3, BigDecimal.ROUND_HALF_DOWN);

        System.out.println(multiply.toString());
        return multiply.toString();
    }*/

    /**
     * 服务器的线程数量：
     * CPU是密集型的话，cpu的核心数+1
     * cpu是IO密集型的话：：8/（1-0.9） = 80 个线程数
     */
    @Test
    public void test5(){
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU的核心线程数:"+i);

    }
}
