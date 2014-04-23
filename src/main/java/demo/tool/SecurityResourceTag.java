package demo.tool;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import demo.security.util.SecurityMetadataSourceTrustListHolder;
import demo.security.util.SecurityUserTrustListHolder;
import demo.security.util.SessionUserDetailsUtil;

public class SecurityResourceTag extends BodyTagSupport {

	private static Log log = LogFactory.getLog(SecurityResourceTag.class);

	private static final long serialVersionUID = -572028891027740273L;

	private HttpServletRequest request;
	
	private String resource = null;

	private String value = null;

	private String type = null;

	private String otherAttribute = null;

	private Locale locale = null;

	public static final String BUTTON = "button";

	public static final String ANCHOR = "a";

	public static final String SUBMIT = "submit";

	@Override
	public int doStartTag() throws JspException {
		boolean flag = urlDisplayFlag(request, resource);

		String content = null;
		if (flag) {
			if (locale != null) {
				value = ApplicationContextHolder.getApplicationContext().getMessage(value, null, locale);
			}
			if (type.equals(BUTTON)) {
				content = createButton(value);
			} else if (type.equals(ANCHOR)) {
				content = createAnchor(value);
			} else if (type.equals(SUBMIT)) {
				content = createSubmit(value);
			}
		
			content = MessageFormat.format(content, (otherAttribute == null || otherAttribute.equals("")) ? "" : otherAttribute);
	
			JspWriter out = pageContext.getOut();
	
			try {
				out.print(content);
				out.flush();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return super.doStartTag();
	}

	private String createSubmit(String value) {
		return "<input type=\"submit\" value=" + value + " {0} />";
	}

	private String createButton(String value) {
		return "<input type=\"button\" value=" + value + " {0} />";
	}

	private String createAnchor(String value) {
		return "<a {0}>" + value + "</a>";
	}

	public static boolean urlDisplayFlag(HttpServletRequest request, String url) {
		if(SecurityUserTrustListHolder.isTrustUser(SessionUserDetailsUtil.getLoginUserName())
				||SecurityMetadataSourceTrustListHolder.isTrustSecurityMetadataSource(url)
				||urlInSession(request, url)) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private static boolean urlInSession(HttpServletRequest request, String url) {
		HttpSession httpSession = request.getSession(true);
		if(httpSession != null) {
			List<String> securityResource = (List<String>)request.getSession().getAttribute("securityResource");
			for (String temp : securityResource) {
				if(temp.equalsIgnoreCase(url)) {
					return true;
				}
			}
		}
		return false;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOtherAttribute(String otherAttribute) {
		this.otherAttribute = otherAttribute;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
