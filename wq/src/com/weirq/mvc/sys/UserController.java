package com.weirq.mvc.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weirq.mvc.BaseController;
import com.weirq.util.Json;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@ResponseBody
	@RequestMapping("/reg")
	public Json reg(String userName,String pwd,String email) throws Exception {
		Json json = new Json();
		if(db.checkUsername(userName)){
			json.setMsg("该用户名已存在，请换一个");
			return json;
		}
		if (db.checkEmail(email)) {
			json.setMsg("该邮箱已存在");
			return json;
		}
		try {
			long id = db.getGid();
			//创建用户
			db.add("user_id", userName, "id", "id", id);
			db.add("id_user", id, "user", "name", userName);
			db.add("id_user", id, "user", "pwd", pwd);
			db.add("id_user", id, "user", "email", email);
			db.add("email_user", email, "user", "userid", id);
			//在hdfs中创建用户根目录
			db.add("hdfs", id, "dir", "name", userName);
			db.add("hdfs", id, "dir", "type", "D");//D表示是目录，F表示是文件
			db.add("hdfs_name", userName, "id", "id", id);
			hdfsDB.mkdir(userName);
			
			json.setSuccess(true);
			json.setMsg("用户注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("用户注册失败");
		}
		return json;
	}
}
