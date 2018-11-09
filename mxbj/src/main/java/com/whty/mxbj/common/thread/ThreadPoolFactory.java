package com.whty.mxbj.common.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolFactory {
	private static ThreadPoolExecutor instance;
	private ThreadPoolFactory(){
		
	}
	
	public static synchronized ThreadPoolExecutor getInstance(){
		if(null == instance){
			instance = new ThreadPoolExecutor(10, 20, 200,
					TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
		}
		return instance;
	}
	
}
