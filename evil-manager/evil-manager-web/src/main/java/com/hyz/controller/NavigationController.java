package com.hyz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*Create at 2017年9月9日 下午5:52:48
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-manager-web
*
*Description:  资源跳转控制器
*        
*/

@Controller
public class NavigationController {
	
	/**
	 * 
	 * <p>MethodName: RegisterPage</p>
	 * <p>Description:注册页面 </p>
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年9月9日
	 * @version 1.0
	 * Create At 2017年9月9日 下午5:56:10
	 */
	@RequestMapping("/register")
	public String RegisterPage() {
		return "register/register";
	}
	
	/**
	 * 
	 * <p>MethodName: index</p>
	 * <p>Description: 首页面</p>
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年9月9日
	 * @version 1.0
	 * Create At 2017年9月9日 下午6:02:32
	 */
	@RequestMapping(path= {"/","/index"})
	public String index() {
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login/login";
	}
}


