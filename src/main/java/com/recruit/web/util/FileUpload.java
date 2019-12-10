package com.recruit.web.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


/**
 * 作者：qiwj
 * 时间：2019/12/10
 */
public class FileUpload {

    public final static String IMG_PATH_PREFIX = "static/images/upload";
    public static File getImgDirFile () {
        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX);
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static String upload(MultipartFile file, String path, HttpServletRequest request) throws Exception {
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();

        //String fileName=file.getOriginalFilename();
        // String destFileName=request.getServletPath();
        //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
        //String destFileName =path+"/static/images/upload/"  + fileName;
        String destFileName = "D:/recruit/src/main/resources/static/images/upload/" + fileName;
        //String destFileName = request.getSession().getServletContext().getRealPath("/images/upload/");
        //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
        File destFile = new File(destFileName);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        file.transferTo(destFile);
        return "../images/upload/" + fileName;
        //return destFile.getName();

    }
}
