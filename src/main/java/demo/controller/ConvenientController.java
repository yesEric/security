package demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.dao.CommonDao;
import demo.security.util.SessionUserDetailsUtil;

/**
 * 业务控制器
 * 
 * @author Watson Xu
 * @since 1.0.0 <p>2013-7-17 上午9:56:10</p>
 */
@Controller
public class ConvenientController {
	@Autowired
	private CommonDao dao;
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		String loginName = SessionUserDetailsUtil.getLoginUserName();
		if (loginName == null) return "/login";
		List<String> securityResource = dao.getResourceByUserName(loginName);
		request.getSession().setAttribute("securityResource", securityResource);
		request.setAttribute("welcomewords", "Hello " + SessionUserDetailsUtil.getLoginUserName()+ ", "
				+ "you have roles:" + SessionUserDetailsUtil.getUserDetails().getAuthorities());
		return "/index";
	}
	
	@RequestMapping(value = "/hello.htm", method = RequestMethod.GET)
	public String hello(HttpServletRequest request, ModelMap model) {
		return "/hello";
	}
	
	@RequestMapping(value = "/super.htm", method = RequestMethod.GET)
	public String superAmin(HttpServletRequest request, ModelMap model) {
		return "/super";
	}
	
	@RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
	public String admin(HttpServletRequest request, ModelMap model) {
		return "/admin";
	}
	
	@RequestMapping(value = "/user.htm", method = RequestMethod.GET)
	public String user(HttpServletRequest request, ModelMap model) {
		return "/user";
	}
	
}
