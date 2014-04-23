package demo.security.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全资源白名单持有者类
 * 
 * @since 1.0.7 <p>2013-7-12 下午5:40:01</p>
 */
public class SecurityMetadataSourceTrustListHolder {
	private static final List<String> smsTrustList = new ArrayList<String>();
	
	public void setTrustList(ArrayList<String> trustList) {
		smsTrustList.clear();
		for (String s : trustList) {
			smsTrustList.add(s);
		}
	}
	
	public static Boolean isTrustSecurityMetadataSource(String sms) {
		return smsTrustList.contains(sms);
	}
}
