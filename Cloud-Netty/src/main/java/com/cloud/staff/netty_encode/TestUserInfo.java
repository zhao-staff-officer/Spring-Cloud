package com.cloud.staff.netty_encode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.setUserId(100);
        info.setUserName("welcome to netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();

        System.out.println("The jdk serializable length is:"+ b.length);
        bos.close();
        System.out.println("-----------------------------------------");
        System.out.println("The byte array serializable length is:"+info.codecC().length);

    }
}
