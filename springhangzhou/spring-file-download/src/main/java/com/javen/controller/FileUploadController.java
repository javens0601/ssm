package com.javen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * 文件上传/下载实现
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping("/download")
    @ResponseBody
    public  String downLoad(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //获取当前项目目录下的下载文件
        String realPath = request.getServletContext().getRealPath("/file/yumeiren.jpg");
        //根据文件路径封装file 对象
        File file = new File(realPath);
        //根据file对象获取文件名
        String fileName = file.getName();
        //设置响应头
        //response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //根据文件路径，封装文件输入流
        FileInputStream inputStream = new FileInputStream(realPath);
        int len = 0;
        //声明一个1kB 的字节缓冲区
        byte[] buff = new byte[1024];

        //获取输出流
        OutputStream outputStream = response.getOutputStream();
        while ((len = inputStream.read(buff)) > 0) {
            outputStream.write(buff, 0, len);
        }
        inputStream.close();
        System.out.println("file download !!!");
        return "success";
    }

    @RequestMapping("upload")
    @ResponseBody
    public String fileUpload(String desc, MultipartFile multipartFile) throws IOException{
        System.out.println(desc);
        System.out.println(multipartFile.getOriginalFilename());
        String path = "D:\\" + multipartFile.getOriginalFilename();
        File file = new File(path);
        multipartFile.transferTo(file);
        return "success";
    }
}
