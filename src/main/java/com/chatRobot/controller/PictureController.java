package com.chatRobot.controller;

import com.chatRobot.model.ImgEditor;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping(value = "file")
public class PictureController {

    ImgEditor imgEditor = new ImgEditor();
    public String filePathFinal = "";
    private Logger logger = LoggerFactory.getLogger(PictureController.class);

    //缩放
    @RequestMapping(value="/img/cutandscale",method= RequestMethod.POST)
    @ResponseBody
    public int cutAndscaleimg(@RequestParam("w") int w,@RequestParam("h") int h,@RequestParam("x") int x,@RequestParam("y") int y){
        imgEditor.cut(filePathFinal,filePathFinal,x,y,w,h);
        imgEditor.scale(filePathFinal,filePathFinal,110,110,false);
        return 1;
    }

    @RequestMapping(value = "/img/upload",method = RequestMethod.POST)
    @ResponseBody
    public Parameters addImage(@RequestParam("userphoto") MultipartFile file, HttpServletRequest request, HttpSession session){
        String filePath= "";
        try{
            filePath = imgEditor.uploadFile(file,request,session);
            filePathFinal = filePath;
            imgEditor.zoomImage(filePath,filePath,400,400);
        }catch (IOException e){
            e.printStackTrace();
        }
        logger.info("filePath:"+filePath);
        Parameters parameter = new Parameters();
        parameter.setFileName(imgEditor.getFileName(file,request,session));
        return parameter;
    }
}
