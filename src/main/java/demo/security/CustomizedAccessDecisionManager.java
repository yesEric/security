package demo.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 安全资源（URL）权限决策器
 * 
 * @author Watson Xu
 * @since 1.0.7 <p>2013-7-10 下午4:08:42</p>
 */
public class CustomizedAccessDecisionManager implements AccessDecisionManager {

	private boolean allowIfAllAbstainDecisions = false;
	
    public boolean isAllowIfAllAbstainDecisions() {
        return allowIfAllAbstainDecisions;
    }

    public void setAllowIfAllAbstainDecisions(boolean allowIfAllAbstainDecisions) {
        this.allowIfAllAbstainDecisions = allowIfAllAbstainDecisions;
    }

    protected final void checkAllowIfAllAbstainDecisions() {
        if (!this.isAllowIfAllAbstainDecisions()) {
            throw new AccessDeniedException("Access is denied");
        }
    }
    
	//In this method, need to compare authentication with configAttributes.
	// 1, A object is a URL, a filter was find permission configuration by this URL, and pass to here.
	// 2, Check authentication has attribute in permission configuration (configAttributes)
	// 3, If not match corresponding authentication, throw a AccessDeniedException.
	
	//这里的三个参数可以片面的理解为： 用户登录后的凭证(其中包含了用户名和角色的对应关系)、请求的URL、请求的URL对应角色(这些角色可以访问这些URL)
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		//@4
		if(configAttributes == null){
			return;
		}
		
		Iterator<ConfigAttribute> ite=configAttributes.iterator();
		while(ite.hasNext()){
			ConfigAttribute ca=ite.next();
			String needRole=((SecurityConfig)ca).getAttribute();
			for(GrantedAuthority ga:authentication.getAuthorities()){
				if(needRole.equals(ga.getAuthority())){  //ga is user's role.
					return;
				}
			}
		}
		
		checkAllowIfAllAbstainDecisions();
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}


}
