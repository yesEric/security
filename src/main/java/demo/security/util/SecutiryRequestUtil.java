package demo.security.util;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest工具类
 * 
 * @author Watson Xu
 * @since 1.0.0 <p>2013-7-16 上午11:11:44</p>
 */
public class SecutiryRequestUtil {
	/**
	 * 取得用户的ip地址
	 * 
	 * @param request
	 * @return ip地址，没得到是 null
	 */
	public static String getRequestIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-client-ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		// 多级反向代理
		if (null != ip && !"".equals(ip.trim())) {
			StringTokenizer st = new StringTokenizer(ip, ",");
			if (st.countTokens() > 1) {
				return st.nextToken();
			}
		}
		return ip;

	}

}
