package demo.security.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全用户白名单持有者类
 * 
 * @author Watson Xu
 * @since 1.0.7 <p>2013-7-16 上午10:48:08</p>
 */
public class SecurityUserTrustListHolder {
private static final List<String> userTrustList = new ArrayList<String>();
	
	public void setTrustList(ArrayList<String> trustList) {
		userTrustList.clear();
		for (String s : trustList) {
			userTrustList.add(s);
		}
	}
	
	public static Boolean isTrustUser(String user) {
		return userTrustList.contains(user);
	}
}
