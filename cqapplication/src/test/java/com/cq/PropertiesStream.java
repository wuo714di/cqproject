package com.cq;

import com.cq.BO.UserBO;
import com.cq.util.FileFilterUtil;
import com.sun.org.apache.xpath.internal.operations.String;
import jdk.nashorn.internal.ir.CallNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PropertiesStream {
    private static Properties properties = null;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src\\main\\resources\\application.properties")));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }


    }

    //将文件用留的方式加载进来
    @Test
    public void test1() {
        String session_store = (String) properties.get("session_store");
        System.out.println(session_store);
    }

    /**
     * 判断空的几种方式
     * Optional容易中的数据如果为NULL，返回的null,
     * 有数据的话，返回值
     */
    @Test
    public void test2() {
        UserBO userBO = new UserBO("zhangsan", 22);
        UserBO userBO1 = Optional.ofNullable(userBO).get();/*.orElse(new UserBO().builder().name("zhangsan").age(11));*/
        log.info("userBO数据：{}", userBO1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("创建一个线程");
            }
        }).start();

        //thread的内部枚举类

    }

    /**
     * 多线程更改共享变量
     * <p>
     * synchronize使用synchronized锁到一个对象，保证的操作的线程安全
     */
    private static int count = 0;
    //volatile能够保证多线程对变量的可见性和顺序性
    // ，不能保证操作的原子性
    // private static volatile int count =0;

    private static Object object = new Object();

    @Test
    public void thread() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (object) {
                        count++;
                    }

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (object) {
                        count--;
                    }

                }
            }
        });


        t1.start();
        t2.start();
        /**
         *  join是指调用该方法的线程进入阻塞状态，等待某线程执行完成后恢复运行
         *  能够保证所有的线程都能够执行完
         */
        t1.join();
        t2.join();

        log.info("两个线程执行完后count:{}", count);


    }

    /**
     * object.wait();阻塞线程，需要用nofity()来唤醒
     * 两者用法都是要在synchronized中操作
     */
    @Test
    public void test4() {

        new Thread(
                () -> {
                    synchronized (object) {
                        log.info("thread1开始执行");
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        log.info("thread1开始执行核心代码;");


                    }
                }).start();


        new Thread(
                () -> {
                    synchronized (object) {
                        log.info("thread2开始执行");
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        log.info("thread2开始执行核心代码;");


                    }
                }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (object) {
            //只是随机唤醒一个线程
            //object.notify();
            object.notifyAll();

        }

    }

    /**
     * volatile能够保证主线程修改aboolean的参数后，
     * 对thread线程可见。
     */
    private static volatile boolean aBoolean = true;

    @Test
    public void test5() throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (aBoolean) {
                    log.info("count:{}");

                }
            }
        }).start();
        Thread.sleep(2000);
        aBoolean = false;
    }

    private final static ReentrantLock reentrantlock = new ReentrantLock();

    @Test
    public void reentrantLockDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    /**
     * File只是对文件路径的封装的对象。
     * 文件或目录不存在时， isDirectory() 或 isFile()  返回false
     */
    @Test
    public void ioTest() {
        //绝对路径,文件要带上格式
        File file = new File("D:\\soft\\EVrecord\\data\\111");
        File MM = new File("D:/MM.md");
        boolean file1 = MM.isFile();
        boolean directory = MM.isDirectory();
        log.info("是否是文件：{}，是否是目录：{}", file1, directory);
        //     filter = new FilenameFilter();

        /**
         * 下面是文件过滤的用法
         */
        FileFilterUtil fileFilterUtil = new FileFilterUtil(".*md$");
        File files = new File("D:/");
        java.lang.String[] list = files.list();
        for (java.lang.String s : list) {
            log.info("获取D盘下的文件：{}", s);
        }
        java.lang.String[] list1 = files.list(fileFilterUtil);

        for (java.lang.String s : list1) {

            log.info("d:盘下的过去的md的文件是：{}", s);
        }
    }

    /**
     * 输入流
     */
    @Test
    public void inputStreamTest() throws IOException {
        File mm = new File("D:/MM.md");
        FileInputStream fileInputStream = new FileInputStream(mm);
        /**
         * 这种方式减少IO操作的次数，推荐使用
         */
        byte[] bytes = new byte[16];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {

            System.out.println(new java.lang.String(bytes, 0, len));
        }


    }

    /**
     * 输出流
     */
    @Test
    public void outPutStream() throws IOException {
        File file = new File("D:/MM2.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);

        fileOutputStream.write("我是一只小鸭子，咿呀咿呀幺111".getBytes());
        fileOutputStream.close();

    }

    /**
     * 字节流
     * 利用文件节点流实现文件的复制
     */
    @Test
    public void test6() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("D:/MM2.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream("D:/MM3.txt", true);
        byte[] bytes = new byte[8];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {
            System.out.println(new java.lang.String(bytes, 0, len));
            fileOutputStream.write(bytes, 0, len);

        }
        fileInputStream.close();
        fileOutputStream.close();

    }

    /**
     * 利用字节缓存流来读取的写入数据贼快
     *
     * 下面是使用缓冲流的标准写法
     */

    @Test
    public void bufferTest() {
        long l = System.currentTimeMillis();
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {


            bufferedInputStream = new BufferedInputStream(new FileInputStream("D:/soft/EVrecord/data/20200916调试同步.mp4"));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:/soft/EVrecord/data/复制.mp4"));
            byte[] bytes = new byte[8 * 1024 * 1024];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes);
            }
        } catch (Exception e) {
            log.error("{}",e);
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    log.error("{}",e);
                }
            }
            ;
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    log.error("{}",e);
                }
            }
        }


        //使用缓存字节流复制视频成功:耗时：94ms
        log.info("使用缓存字节流复制视频成功:耗时：{}", System.currentTimeMillis() - l);
       // HashMap map=new ConcurrentHashMap();
    }


    @Test
    public void test8(){
        System.out.println(1 << 30);

    }
}
