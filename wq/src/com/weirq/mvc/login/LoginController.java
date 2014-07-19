package com.weirq.mvc.login;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weirq.mvc.BaseController;
import com.weirq.util.Json;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

	@RequestMapping("login")
	public String login(String userName,String pwd,HttpSession session) throws Exception {
		long userId = db.checkUser(userName, pwd);
		Json json = new Json();
		if (userId>0) {
			json.setSuccess(true);
			session.setAttribute("userid", userId);
			session.setAttribute("username", db.getUserNameById(userId));
		}else {
			json.setMsg("用户名或密码不正确");
		}
		return "redirect:index.jsp";
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		if (session!=null) {
			session.invalidate();
		}
		return "redirect:index.jsp";
	}
	
	@RequestMapping("init")
	public String init() throws Exception {
//		String table_gid = "gid";
//		String[] fam_gid = {"gid"};
//		db.createTable(table_gid, fam_gid);
//		
//		String table_id = "id_user";
//		String[] fam_id = {"user"};
//		db.createTable(table_id, fam_id);
//		
//		String table_user = "user_id";
//		String[] fam_user = {"id"};
//		db.createTable(table_user, fam_user);
//		
//		String table_email = "email_user";
//		String[] fam_email = {"user"};
//		db.createTable(table_email, fam_email);
//		
//		db.add(table_gid, "gid", "gid", "gid", (long)0);
//		
//		long id = db.getGid();
//		db.add("user_id", "admin", "id", "id", id);
//		db.add("id_user", id, "user", "name", "admin");
//		db.add("id_user", id, "user", "pwd", "336393");
//		db.add("id_user", id, "user", "email", "634623907@qq.com");
//		db.add("email_user", "634623907@qq.com", "user", "userid", id);
//		
//		
//		String table_emun = "emun";
//		String[] fam_emun = {"emun"};
//		db.createTable(table_emun, fam_emun);
//		
//		long id1 = db.getGid();
//		db.add(table_emun, id1, "emun", "name", "菜单管理");
//		db.add(table_emun, id1, "emun", "url", "/emun/list.do");
//		long id02 = db.getGid();
//		db.add(table_emun, id02, "emun", "name", "云盘");
//		db.add(table_emun, id02, "emun", "url", "/cloud/list.do");
//		
//		
//		String table_files = "filesystem";
//		String[] fam_file = {"files"};
//		db.createTable(table_files, fam_file);
//		
//		long id03 = db.getGid();
//		db.add(table_files, id03, "files", "name", "admin");
//		db.add(table_files, id03, "files", "dir", "admin");
//		db.add(table_files, id03, "files", "pdir", "/");
//		db.add(table_files, id03, "files", "type", "D");
		return "redirect:login.jsp";
	}
}
