package com.liz.socket;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo {

	public static void main(String[] args) {
		int port = 51212;
		ServerSocket serverSocket = null;
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		try {
			serverSocket = new ServerSocket(port);
			int i = 1;
			while (true) {
				Socket socket = serverSocket.accept();
				threadPool.submit(() -> {
					OutputStream os = null;
					try {
						os = socket.getOutputStream();
						while (true) {
							os.write(("你好" + LocalDateTime.now()).getBytes("UTF-8"));
							Thread.sleep(1000);
						}
					} catch (Exception e) {
					} finally {
						try {
							if (os != null) {
								os.close();
							}
							if (socket != null) {
								socket.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				System.out.println("第 " + i++ + " 个客户端接入");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
