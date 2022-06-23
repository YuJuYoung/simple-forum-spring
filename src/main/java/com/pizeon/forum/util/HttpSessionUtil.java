package com.pizeon.forum.util;

import javax.servlet.http.HttpSession;

public class HttpSessionUtil {
	
	public static String getLoginedId(HttpSession session) {
		return (String) session.getAttribute("logined_id");
	}
	
	public static void setLoginedId(HttpSession session, String id) {
		session.setAttribute("logined_id", id);
	}
	
	public static boolean isLoginedId(HttpSession session, String id) {
		return session.getAttribute("logined_id").equals(id);
	}
	
}
