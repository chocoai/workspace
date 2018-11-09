package com.whty.mxbj.base.service;

public class BaseService {

//	protected Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	protected MemcachedClient memcachedClient;
//
//	protected boolean checkToken(String account, String token) {
//		boolean flag = false;
//
//		try {
//			String key = account + WebConstants.TOKEN_KEY;
//			String userToken = memcachedClient.get(key);
//
//			if (userToken != null && userToken.equals(token)) {
//				flag = true;
//				memcachedClient.set(key, 360000, token);
//			}
//		} catch (TimeoutException | InterruptedException | MemcachedException e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}
//
//	protected boolean checkVerification(String account, String verification) {
//		boolean flag = false;
//
//		try {
//			String key = account + WebConstants.TOKEN_KEY;
//			String userVerification = memcachedClient.get(key);
//
//			if (userVerification != null && userVerification.equals(verification)) {
//				flag = true;
//			}
//		} catch (TimeoutException | InterruptedException | MemcachedException e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}
}
