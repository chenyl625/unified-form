package com.jfbrother.baseserver.utils;

import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.FileTypeEnum;
import com.jfbrother.baseserver.enums.PathEnum;
import com.jfbrother.baseserver.model.FileModel;
import com.jfbrother.baseserver.utils.encoder.Base64Encoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件操作工具类
 */
@Component
@Slf4j
public class FileUtils extends DecompressFile {

    /**
     * 上传路径
     */
    @Value("${system.file.upload}")
    private String uploadPath;

    private static FileUtils fileUtils;

    /**
     * 初始化静态参数
     */
    @PostConstruct
    public void init() {
        fileUtils = this;
        fileUtils.uploadPath = this.uploadPath;
    }

    /**
     * 快速获取文件路径，如果没有文件所在的文件夹路径，则自动生成
     *
     * @param path 文件所在路径
     * @param name 文件名
     * @return
     */
    public static File makeFile(String path, String name) {
        createFolder(path);

        return new File(path, name);
    }

    /**
     * 通过PathEnum构造一个file对象，但并不判断文件是否存在
     *
     * @param pathEnum
     * @param name
     * @return
     */
    public static File makeFile(PathEnum pathEnum, String name) {
        return makeFile(pathEnum.toString(fileUtils.uploadPath), name);
    }

    /**
     * 创建文件夹
     *
     * @param path
     */
    public static void createFolder(String path) {
        File parentFile = new File(path);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    /**
     * 文件存储
     *
     * @param pathEnum 存储路径的枚举对象
     * @param file
     * @return
     * @throws IOException 存储失败，需要调用者自行处理异常
     */
    public static File save(PathEnum pathEnum, MultipartFile file) throws IOException {

        return save(pathEnum, file, null);
    }

    /**
     * 文件存储
     *
     * @param pathEnum 存储路径的枚举对象
     * @param file
     * @param suffix   自定义后缀名
     * @return
     * @throws IOException 存储失败，需要调用者自行处理异常
     */
    public static File save(PathEnum pathEnum, MultipartFile file, String suffix) throws IOException {
        //调试获取原始文件名
        log.debug(file.getOriginalFilename());
        //使用UUID作为随机文件名
        String savePath = UUID.randomUUID().toString() + (StringUtils.isEmpty(suffix) ? "" : ("." + suffix));
        //存储的真实文件完整对象
        File saveFile = makeFile(pathEnum.toString(fileUtils.uploadPath), savePath);
        //将图片存储在服务器上
        file.transferTo(saveFile);

        return saveFile;
    }

    /**
     * 存储POI文件
     *
     * @param pathEnum
     * @param file
     * @return
     * @throws IOException
     */
    public static File save(PathEnum pathEnum, Workbook file) throws IOException {
        return save(pathEnum, file, null);
    }

    /**
     * 存储POI文件
     *
     * @param pathEnum
     * @param file
     * @param suffix
     * @return
     * @throws IOException
     */
    public static File save(PathEnum pathEnum, Workbook file, String suffix) throws IOException {
        String completePath = null;
        OutputStream out = null;
        try {
            //使用UUID作为随机文件名
            String savePath = UUID.randomUUID().toString() + (StringUtils.isEmpty(suffix) ? "" : ("." + suffix));
            //存储的真实文件完整对象
            createFolder(pathEnum.toString(fileUtils.uploadPath));
            completePath = pathEnum.toString(fileUtils.uploadPath) + File.separator + savePath;

            out = new FileOutputStream(completePath);
            //将文件存储在服务器上
            file.write(out);
            out.flush();
        } catch (IOException e) {
            throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (file != null) {
                file.close();
            }
        }


        return new File(completePath);
    }

    /**
     * 根据存储路径和文件名获取磁盘上的真实文件
     *
     * @param pathEnum 存储路径枚举类
     * @param name
     * @return
     * @throws ServiceException
     */
    public static File get(PathEnum pathEnum, String name) throws ServiceException {
        File file = makeFile(pathEnum.toString(fileUtils.uploadPath), name);
        if (!file.exists()) {
            throw new ServiceException(ResultCode.NOT_FOUND, "Not found file.");
        }
        return file;
    }

    /**
     * 文件下载，文件名默认为服务器上的实际文件名
     *
     * @param file
     */
    public static void downFile(File file) {
        downFile(file, file.getName());
    }

    /**
     * 文件下载
     *
     * @param file
     * @param fileName
     */
    public static void downFile(File file, String fileName) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentLength((int) file.length());

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (NullPointerException e) {
            throw new ServiceException(ResultCode.NOT_FOUND, "Not found file!");
        } catch (FileNotFoundException e) {
            throw new ServiceException(ResultCode.NOT_FOUND, "Not found file!");
        } catch (IOException e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "IO error!");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new ServiceException(ResultCode.INTERNAL_SERVER_ERROR, "数据流关闭异常!");
            }
        }
    }

    /**
     * Workbook文件下载
     *
     * @param file
     */
    public static void downFile(Workbook file) {
        downFile(file, UUID.randomUUID().toString());
    }

    /**
     * Workbook文件下载
     *
     * @param file
     * @param fileName
     */
    public static void downFile(Workbook file, String fileName) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();

        ServletOutputStream outputStream = null;
        BufferedOutputStream bufferedOutPut = null;
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            response.setContentLength((int) file.length());

            outputStream = response.getOutputStream();
            bufferedOutPut = new BufferedOutputStream(outputStream);
            file.write(bufferedOutPut);
            bufferedOutPut.flush();
        } catch (IOException e) {
            throw new ServiceException(ResultCode.INTERNAL_SERVER_ERROR, "POI 导出失败!");
        } finally {
            try {
                if (bufferedOutPut != null) {
                    bufferedOutPut.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                throw new ServiceException(ResultCode.INTERNAL_SERVER_ERROR, "数据流关闭异常!");
            }
        }
    }

    /**
     * 文件转base64
     *
     * @param file
     * @return
     */
    public static String convertToBase64(File file) {
        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(file);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return Base64Encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 文件转base64
     *
     * @param pe   相对路径
     * @param name 文件名
     * @return
     */
    public static String convertToBase64(PathEnum pe, String name) {
        return convertToBase64(get(pe, name));
    }

    /**
     * 文件转base64
     *
     * @param path 文件路径
     * @return
     */
    public static String convertToBase64(String path) {
        return convertToBase64(new File(path));
    }

    /**
     * 获取文件真实类型
     *
     * @param file 要获取类型的文件。
     * @return 文件类型枚举。
     */
    public static FileTypeEnum getFileType(File file) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] head = new byte[4];
            if (-1 == inputStream.read(head)) {
                return FileTypeEnum.UNKNOWN;
            }
            int headHex = 0;
            for (byte b : head) {
                headHex <<= 8;
                headHex |= b;
            }
            switch (headHex) {
                case 0x504B0304:
                    return FileTypeEnum.ZIP;
                case 0x776f7264:
                    return FileTypeEnum.TAR;
                case -0x51:
                    return FileTypeEnum._7Z;
                case 0x425a6839:
                    return FileTypeEnum.BZ2;
                case -0x74f7f8:
                    return FileTypeEnum.GZ;
                case 0x52617221:
                    return FileTypeEnum.RAR;
                default:
                    return FileTypeEnum.UNKNOWN;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return FileTypeEnum.UNKNOWN;
    }

    /**
     * 将pathEnum转化为完整字符串
     *
     * @param pathEnum
     * @return
     */
    public static String pathEnumToString(PathEnum pathEnum) {
        return pathEnum.toString(fileUtils.uploadPath);
    }

    /**
     * 获取文件的md5
     *
     * @param file
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String getMd5(MultipartFile file) {
        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * file转byte
     *
     * @param file
     * @return
     */
    public static byte[] file2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * byte 转file
     *
     * @param buf
     * @param filePath
     * @param fileName
     * @return
     */
    public static File byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static void exportZip(List<FileModel> fileList) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 生成word
        response.setContentType("application/octet-stream");
        // 压缩包名
        response.setHeader("Content-Disposition", "attachment; filename=ScholarshipWord-" + System.currentTimeMillis() + ".zip");
        ZipOutputStream zos = null;

        try {
            zos = new ZipOutputStream(response.getOutputStream());
            int i=1;
            for (FileModel fileModel : fileList) {
                // 生成word
                RestTemplate restTemplate = new RestTemplate();
                MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
                String minioId = !StringUtils.isEmpty(fileModel.getMinioId())?fileModel.getMinioId():fileModel.getSavePath().split("\\+")[0];
                ResponseEntity<byte[]> res = restTemplate.getForEntity("http://zdtyc.zjbti.net.cn/onetable/" + minioId, byte[].class);
                byte[] body = res.getBody();
                String fileName=String.valueOf(i).concat(fileModel.getName());
                if(!StringUtils.isEmpty(fileModel.getKeywords())){
                    fileName=fileModel.getKeywords().concat(String.valueOf(i)).concat(".").concat(fileModel.getExt());
                }
                    zos.putNextEntry(new ZipEntry(fileName ));
                    int temp = 0;
                    // 读取内容
                    InputStream inputStream = new ByteArrayInputStream(body);
                    while ((temp = inputStream.read()) != -1) {
                        // 压缩输出
                        zos.write(temp);
                    }
                    // 关闭输入流
                    inputStream.close();
                    i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != zos) {
                    zos.flush();
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
