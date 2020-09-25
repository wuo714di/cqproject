package com.cq.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @ClassName : FileFilterUtil
 * @Description : 文件过滤器
 * @Author : WXD
 * @Date: 2020-09-23 11:29
 */
public class FileFilterUtil implements FilenameFilter {
    private String suffix;

    public FileFilterUtil(String suffix) {
        this.suffix = suffix;
    }

    /**
     *  根据文件的名字正则匹配过滤文件
     * @param dir
     * @param name
     * @return
     */
    @Override
    public boolean accept(File dir, String name) {
        return name.matches(suffix);
    }
}
