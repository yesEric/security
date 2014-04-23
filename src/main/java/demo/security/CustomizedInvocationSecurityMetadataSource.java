package demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import demo.dao.CommonDao;

/**
 * 安全资源（URL）和角色映射关系处理器
 * 
 * @author Watson Xu
 * @since 1.0.7 <p>2013-7-9 下午3:25:09</p>
 */
public class CustomizedInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private boolean rejectPublicInvocations = false;
	private CommonDao dao;

	private static Map<String, Integer> resources = new HashMap<String, Integer>();
	
	public CustomizedInvocationSecurityMetadataSource(CommonDao dao) {
		this.dao = dao;
		loadSecurityMetadataSource();
	}
	
	// According to a URL, Find out permission configuration of this URL.
	// 根据URL来查找所有能够访问该资源的角色。
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// guess object is a URL.
		//@3
		String url = ((FilterInvocation)object).getRequestUrl();
		
		if(resources.isEmpty()) return null;
		Integer resourceId = resources.get(url);
		if(rejectPublicInvocations && resourceId == null) {
			throw new IllegalArgumentException("Secure object invocation " + object +
                    " was denied as public invocations are not allowed via this interceptor. ");//请求不存在
		}
		return getRolesByResouceId(resourceId);
	}

	private Collection<ConfigAttribute> getRolesByResouceId(Integer resourceId) {
		List<String>  roles = dao.getRoleByResourceId(resourceId);
		
		Collection<ConfigAttribute> atts = null;
		if(roles != null) {
			atts = new ArrayList<ConfigAttribute>();
			for (String role : roles) {
				atts.add(new SecurityConfig(role));
			}
		}
		
		return atts;
	}
	
	//加载所有安全资源（URL）
	private void loadSecurityMetadataSource() {
		List<Map<String, Object>> resourceDtos = dao.getAllResource();
		if(resourceDtos != null) {
			resources.clear();
			for (Map<String, Object> dto : resourceDtos) {
				resources.put(dto.get("url").toString(), Integer.parseInt(dto.get("id").toString())); 
			}
		}
	}
	
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public void setDao(CommonDao dao) {
		this.dao = dao;
	}

	public void setRejectPublicInvocations(boolean rejectPublicInvocations) {
		this.rejectPublicInvocations = rejectPublicInvocations;
	}

}