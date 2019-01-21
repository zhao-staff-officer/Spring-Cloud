package com.cloud.staff.fastdfs.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.staff.fastdfs.basic.FastDFSClient;

@RestController
@RequestMapping("/fdfs")
public class FastDFSController {
	@Autowired
    private FastDFSClient fdfsClient;

    /**
     * 文件上传
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    public Map<String,Object> upload(MultipartFile file) throws Exception{
        
        String url = fdfsClient.uploadFile(file);
        
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "上传成功");
        result.put("url", url);
        
        return result;
    }
    
    /**
     * 文件下载
     * @param fileUrl
     * @param response
     * @throws Exception
     */
    @RequestMapping("/download")
    public void  download(String fileUrl, HttpServletResponse response) throws Exception{
        
        byte[] data = fdfsClient.downloadFile(fileUrl);
        
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("test.jpg", "UTF-8"));
        
        // 写出
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.write(data, outputStream);
    }
    
    @RequestMapping("/delete")
    public boolean delet(String fileUrl) {
    	return fdfsClient.deleteFile(fileUrl);
    }

}
