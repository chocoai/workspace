package com.smart.web.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 1,统计在线人数 2,根据sessionId得到session, 解决FF中用flash进行文件上传session丢失问题。
 * 3,配合cookies可以解决多浏览器，多服务器间session共享问题
 * 
 * @author 充满智慧的威哥
 */
public class SiteSessionListener implements HttpSessionListener {

    private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    /**
     * session创建
     */
    public void sessionCreated(HttpSessionEvent sessionEvent) {
	HttpSession session = sessionEvent.getSession();
	sessions.put(session.getId(), session);
	List<Integer> articleReadList = new ArrayList<Integer>(); // 阅读文章记录
	List<Integer> articleUpList = new ArrayList<Integer>(); // 顶文章记录
	List<Integer> photoReadList = new ArrayList<Integer>(); // 阅读照片记录
	List<Integer> photoUpList = new ArrayList<Integer>(); // 顶照片记录
	session.setAttribute("articleReadList", articleReadList);
	session.setAttribute("articleUpList", articleUpList);
	session.setAttribute("photoReadList", photoReadList);
	session.setAttribute("photoUpList", photoUpList);
    }

    /**
     * session销毁
     */
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
	sessions.remove(sessionEvent.getSession().getId());
    }

    /**
     * 根据sessionId得到session
     * 
     * @param sessionID
     * @return
     */
    public static HttpSession getSession(String sessionID) {
	return sessions.get(sessionID);
    }

    /**
     * 根据sessionId销毁session
     * 
     * @param sessionID
     */
    public static void removeSession(String sessionID) {
	if (sessions.containsKey(sessionID)) {
	    sessions.remove(sessionID);
	}
    }

    /**
     * 当前在线人数
     * 
     * @return
     */
    public static int getAliveCount() {
	return sessions.size();
    }

}
