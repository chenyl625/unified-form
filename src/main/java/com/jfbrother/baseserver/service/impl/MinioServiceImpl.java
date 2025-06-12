package com.jfbrother.baseserver.service.impl;

import com.jfbrother.baseserver.configurer.properties.SystemConfigProperties;
import com.jfbrother.baseserver.configurer.properties.system.FileProperties;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.MinioService;
import com.jfbrother.baseserver.service.SysFileService;
import com.jfbrother.baseserver.utils.StringUtils;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author chenyl
 */
@Service
@Slf4j
public class MinioServiceImpl implements MinioService {
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private SystemConfigProperties systemConfigProperties;
    @Autowired
    private SysFileService sysFileService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String presignedGetObject(String bucketName, String objectName, Integer expiry) {
        if (minioClient == null) {
            throw new ServiceException("minio链接未初始化");
        }
        FileProperties.Minio minio = systemConfigProperties.getFile().getMinio();
        //默认使用配置的
        if (StringUtils.isEmpty(bucketName)) {
            bucketName = minio.getBucketName();
        }
        try {
            String presignedGetUrl;
            if (expiry == null) {
                sysFileService.downloadByMinioId(objectName);
                presignedGetUrl = minioClient.presignedGetObject(bucketName, objectName);

            } else {
                sysFileService.downloadByMinioId(objectName);
                presignedGetUrl= minioClient.presignedGetObject(bucketName, objectName, expiry);
            }

            if(!StringUtils.isEmpty(presignedGetUrl)){
                presignedGetUrl=presignedGetUrl.replace(minio.getEndpoint()+"/","");
                presignedGetUrl=presignedGetUrl.replace(minio.getBucketName()+"/","");
                log.info("Minio授权后的url地址：{}",presignedGetUrl);
            }
            return presignedGetUrl;
        } catch (ErrorResponseException e) {
            doException(e);
        } catch (InsufficientDataException e) {
            doException(e);
        } catch (InternalException e) {
            doException(e);
        } catch (InvalidBucketNameException e) {
            doException(e);
        } catch (InvalidExpiresRangeException e) {
            doException(e);
        } catch (InvalidKeyException e) {
            doException(e);
        } catch (InvalidResponseException e) {
            doException(e);
        } catch (IOException e) {
            doException(e);
        } catch (NoSuchAlgorithmException e) {
            doException(e);
        } catch (XmlParserException e) {
            doException(e);
        }
        return null;
    }

    private void doException(Exception e) {
        e.printStackTrace();
        throw new ServiceException("链接生成失败:" + e.getMessage());
    }

    @Override
    public String presignedGetObject(String objectName, Integer expiry) {
        return presignedGetObject(null, objectName, expiry);
    }

    @Override
    public String presignedGetObject(String objectName) {
        return presignedGetObject(objectName);
    }
}
