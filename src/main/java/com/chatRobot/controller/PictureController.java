package com.chatRobot.controller;

import com.chatRobot.model.ImgEditor;
import com.chatRobot.model.Msg;
import com.chatRobot.model.POIUtil;
import com.chatRobot.model.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;


@Controller
@RequestMapping(value = "file")
public class PictureController {

    ImgEditor imgEditor = new ImgEditor();
    public String filePathFinal = "";
    private Logger logger = LoggerFactory.getLogger(PictureController.class);

    //缩放
    @RequestMapping(value = "/img/cutandscale", method = RequestMethod.POST)
    @ResponseBody
    public int cutAndscaleimg(@RequestParam("w") int w, @RequestParam("h") int h, @RequestParam("x") int x, @RequestParam("y") int y) {
        imgEditor.cut(filePathFinal, filePathFinal, x, y, w, h);
        imgEditor.scale(filePathFinal, filePathFinal, 110, 110, false);
        return 1;
    }

    @RequestMapping(value = "/img/upload", method = RequestMethod.POST)
    @ResponseBody
    public Parameters addImage(@RequestParam("userphoto") MultipartFile file, HttpServletRequest request, HttpSession session) {
        String filePath = "";
        try {
            filePath = imgEditor.uploadFile(file, request, session);
            this.filePathFinal = filePath;
            imgEditor.zoomImage(filePath, filePath, 400, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("filePath:" + filePath);
        Parameters parameter = new Parameters();
        parameter.setFileName(imgEditor.getFileName(file, request, session));
        return parameter;
    }

    @RequestMapping(value = "/imageUpLoad")
    @ResponseBody
    public Msg uploadImage(MultipartFile goodspic, HttpServletRequest request, HttpSession session) {
        /**
         * @Description: 商品图片上传处理
         */
        String filePath;
        try {
            filePath = imgEditor.uploadFile(goodspic, request, session);
            return Msg.success().add("filePath", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/fileUpLoad")
    public Msg uploadFile(MultipartFile file, HttpSession session) {
        /**
         * @Description: 商品模型文件 上传处理
         * @return: 文件存储路径
         */
        String filePath;
        try {
            filePath = imgEditor.uploadFileWithFlag(file, session);
            return Msg.success().add("filePath", filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/ExcelFileUpload")
    public Msg ExcelFileUpload(MultipartFile file) {
        // excel 文件上传
        System.out.println(file.getName());
        // excel 处理类 处理
        try {
            List<String[]> listValue = POIUtil.readExcel(file);
            System.out.println(listValue);
            // 处理结束 页面展示
            return Msg.success().add("listValue", listValue);
        } catch (IOException e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    @RequestMapping("/DownloadFile")
    public String downloadExcelFile(String fileName, HttpServletResponse response) {
        System.out.println(fileName);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try {
            String path = "E:\\test";
            InputStream inputStream = new FileInputStream(new File(path + File.separator + fileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b,0,length);
            }
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
