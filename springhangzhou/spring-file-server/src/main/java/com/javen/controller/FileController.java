package com.javen.controller;

import com.alibaba.fastjson.JSONObject;
import com.javen.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
//@ResponseBody
public class FileController {

    @Autowired
    private FileService<JSONObject> fileService;

    @ResponseBody
    @RequestMapping("file")
    public String getFileInfo(String name, HttpServletRequest request, HttpServletResponse response) {
        JSONObject fileinfo = fileService.download();
        System.out.println("name == " + name);
        System.out.println(fileinfo.toJSONString());

        try {
            String url = "E:\\Project\\springhangzhou\\spring-file-server\\src\\main\\resources\\1438.pdf";
            File file = new File(url);
            String l = request.getRealPath("/") + "/" + url;
            String filename = file.getName();
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // 设置response的Header
            System.out.println("报文返回的长度是： " + fileinfo.toString().getBytes().length);
            System.out.println("图片返回的长度是： " + file.length());
            System.out.println("Content-Length 是：  " + (file.length() + fileinfo.toString().getBytes().length));
            response.addHeader("Content-Length", ""+(file.length() + fileinfo.toString().getBytes().length));
            response.addHeader("File-Length", "" + file.length());
            response.addHeader("Msg-Length", "" + fileinfo.toString().getBytes().length);

            //response.setContentType("application/pdf");

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

            toClient.write(buffer);
            toClient.write(fileinfo.toString().getBytes());

            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return fileinfo.toString();
    }
}
