package com.recruit.web.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 作者：qiwj
 * 时间：2019/12/10
 */
public class FileUpload {

    public final static String IMG_PATH_PREFIX = "static/images/upload";
    @Value("${file.path}")
    private String path;

    public static File getImgDirFile() {
        String os = System.getProperty("os.name");
        //文件保存路径
        String filePath="";
        if(os.toLowerCase().startsWith("win")){
            //windows下的路径
            filePath ="d:/pictureUpload/images/upload/";
        }else {
            //linux下的路径
            filePath="/usr/pictureUpload/images/upload/";
        }

       // String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX);
        String fileDirPath = new String(filePath);
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static Object headImg(@RequestParam(value = "file", required = false) MultipartFile file,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filepath = "";
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        String originalName = "";
        try {
            if (file != null) {
                originalName = file.getOriginalFilename();
                filepath = getImgDirFile().getAbsolutePath() + File.separator + originalName;
                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", map2);
        map2.put("src", "../images/upload/" + originalName);
        return map;
    }

}
