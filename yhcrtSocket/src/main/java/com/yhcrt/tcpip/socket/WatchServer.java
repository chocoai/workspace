package com.yhcrt.tcpip.socket;

import java.io.*;
import java.net.*;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.yhcrt.tcpip.util.Constans;

public class WatchServer {
	
	private static final Logger logger =Logger.getLogger(WatchServer.class);  
	boolean started = false;
	ServerSocket ss = null;

	public static void main(String[] args) {
		new WatchServer().start();
	}

	public void start() {
		try {
			ss = new ServerSocket(10007);
			started = true;
			logger.info("端口已开启,占用10007端口号....");
		} catch (BindException e) {
			logger.error("端口使用中....");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (started) {
				Socket s = ss.accept();
				SocketHandler handler = new SocketHandler(s);
				logger.info(s.getInetAddress().getHostAddress()+" 客户端连接成功!");
				new Thread(handler).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
				logger.error("服务端通讯断开......");
				Constans.clientMap = new HashMap<String, Socket>();
				Constans.clientKeyMap = new HashMap<String, String>();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ServerSocket getSs() {
		return ss;
	}

}
