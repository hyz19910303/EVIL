package com.hyz.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
/**
 * 拦截器
 * @author EVIL
 *
 */
public class PortalFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest req, ServletResponse response) throws Exception {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpSession session =request.getSession();
		String validateCode = (String) session.getAttribute("validateCode");
		//获取验证码
		String randomcode = request.getParameter("randomcode");
		if(validateCode!=null&& randomcode!=null && !randomcode.equals(validateCode)){
			request.setAttribute("shiroLoginFailure", "randomCodeError");
			//拒绝访问，不再校验账号和密码 
			return true; 
		}
		return super.onAccessDenied(req, response);
	}
	
	
}
