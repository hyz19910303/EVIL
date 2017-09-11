package com.hyz.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyz.evil.util.VeriryCodeUtil;

/**
*Create at 2017年9月11日 上午9:04:43
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-manager-web
*
*Description: 
*        
*/
@RequestMapping
@Controller()
public class VerifyCode {
	/**
	 * 
	 * <p>MethodName: getVerifyCode</p>
	 * <p>Description:验证码 </p>
	 * @param request
	 * @param response
	 * 
	 * @author EVIL
	 * @date 2017年9月11日
	 * @version 1.0
	 * Create At 2017年9月11日 上午9:32:56
	 */
	@RequestMapping("/verifyCode")
	public void getVerifyCode(HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("verifyCode");
			String create = VeriryCodeUtil.create(100, 40, "png", response.getOutputStream());
			session.setAttribute("verifyCode", create);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
