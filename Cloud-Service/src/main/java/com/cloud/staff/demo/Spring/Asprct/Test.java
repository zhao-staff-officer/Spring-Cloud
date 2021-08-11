package com.cloud.staff.demo.Spring.Asprct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/test")
@Slf4j
public class Test {

    @RequestMapping("/aop1")
    @TestAnnotation(name = "aop切面")
    public void test1(HttpServletRequest request) {
        System.out.println("aop测试");
        try {
            InputStream is = request.getInputStream();

            //将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                log.info(sb.toString());
            } catch (IOException e) {
                log.error("weChatUnifiedPayCallBack 读取回调数据出错", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("weChatUnifiedPayCallBack 关闭输入流出错", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/aop2")
    @TestAnnotation(name = "aop切面")
    public void tes2(RequestParamDto requestParamDto) {
        System.out.println("aop测试");
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            InputStream is = request.getInputStream();

            //将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                log.info(sb.toString());
            } catch (IOException e) {
                log.error("weChatUnifiedPayCallBack 读取回调数据出错", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("weChatUnifiedPayCallBack 关闭输入流出错", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/aop3")
    @TestAnnotation(name = "aop切面")
    public void tes3(@RequestBody RequestParamDto requestParamDto) {
        System.out.println("aop测试");
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            InputStream is = request.getInputStream();

            //将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                log.info(sb.toString());
            } catch (IOException e) {
                log.error("weChatUnifiedPayCallBack 读取回调数据出错", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("weChatUnifiedPayCallBack 关闭输入流出错", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
