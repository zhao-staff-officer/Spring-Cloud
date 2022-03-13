package com.cloud.staff.java_bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if(args !=null && args.length>0){
            port =Integer.valueOf(args[0]);
        }
        ServerSocket server = null;
        try{
           server = new ServerSocket(port);
           System.out.println("The server is start in port:"+port);
           Socket socket = null;
           while(true){
               socket = server.accept();
               new Thread(new Handler(socket)).start();
           }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(server !=null){
                server.close();
                server=null;
            }
        }
    }
}

class Handler implements Runnable{

    private Socket socket;
    public Handler(Socket socket){
        this.socket=socket;
    }


    @Override
    public void run() {
        BufferedReader in =null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime = null;
            String body =null;
            while(true){
                body = in.readLine();
                if(body == null){
                    break;
                }
                System.out.println("the server receive order:"+body);
                out.println("server callback data");
            }
        } catch (Exception e) {
            if(in !=null){
                try{
                    in.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
            if(out!=null){
                out.close();;
                out=null;
            }
            if(this.socket!=null){
                try{
                    this.socket.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
                this.socket=null;
            }
        }
    }
}