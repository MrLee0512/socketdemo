package com.liz.socket;

import java.io.InputStream;
import java.net.Socket;

public class ClientDemo {

	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 51212;
		Socket socket = null;
		InputStream is = null;
		try {
			socket = new Socket(host, port);
			is = socket.getInputStream();
			byte[] bytes = new byte[1024];
			int len;
			while ((len = is.read(bytes)) != -1) {
				System.out.println(new String(bytes, 0, len, "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
