package com.multithreading.chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 显式为任务创建线程
 * 这种无限创建线程的方式在高负荷下会显示出问题
 */
public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connect = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    //do sth...
                }
            };

            new Thread(task).start();
        }
    }
}
