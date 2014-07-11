package com.weirq.mvc.login;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weirq.db.HbaseDB;
import com.weirq.mvc.BaseController;
import com.weirq.util.Json;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

//	HbaseDB db;
//	public LoginController() {
//		db = new HbaseDB();
//	}
	@ResponseBody
	@RequestMapping("login")
	public Json login(String userName,String pwd,HttpSession session) throws Exception {
		long userId = db.checkUser(userName, pwd);
		Json json = new Json();
		if (userId>0) {
			json.setSuccess(true);
			session.setAttribute("userid", userId);
			session.setAttribute("username", db.getUserNameById(userId));
		}else {
			json.setMsg("用户名或密码不正确");
		}
		return json;
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		if (session!=null) {
			session.invalidate();
		}
		return "redirect:index.jsp";
	}
}
