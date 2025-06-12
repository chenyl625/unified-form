package com.jfbrother.baseserver.enums;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * 常用路径枚举类
 */
public enum PathEnum {
    /**
     * 用户头像文件夹
     */
    USER_PORTRAIT("portrait"),
    /**
     * 错误文件存放路径
     */
    ERROR_FILE("errorFile"),
    /**
     * 压缩包存放处
     */
    COMPRESSED_FILE("compressed");
    /**
     * 文件存储的基础路径
     */
    private String basePath;

    /**
     * 上传路径
     */
    @Value("${system.file.upload}")
    private String uploadPath;

    PathEnum(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public String toString() {
        return uploadPath + File.separator + this.getBasePath();
    }

    public String toString(String uploadPath) {
        return uploadPath + File.separator + this.getBasePath();
    }
}
