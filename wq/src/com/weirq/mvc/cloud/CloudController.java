package com.weirq.mvc.cloud;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weirq.mvc.BaseController;
import com.weirq.util.Json;

@Controller
@RequestMapping("/cloud")
public class CloudController extends BaseController{

	@RequestMapping("/list")
	public String list(HttpSession session) {
		String name = (String) session.getAttribute("username");
		long id = db.getIdByDirName(name);
		return "/cloud/list";
	}
	/**
	 * 创建目录
	 * @param mkdir
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/mkdir")
	public Json mkdir(String mkdir,HttpSession session) {
		Json json = new Json();
		try {
			String name = (String) session.getAttribute("username");
			//在该用户下创建目录
			hdfsDB.mkdir(name+"/"+mkdir);
			long id = db.getGid();
			//保存目录信息
			db.add("hdfs", id, "dir", "name", mkdir);
			db.add("hdfs", id, "dir", "type", "D");
			db.add("hdfs_name", mkdir, "id", "id", id);
			long pid = db.getIdByDirName(name);
			//保存目录关联信息
			db.add("hdfs_cid", pid, "cid", "id", id);
			json.setMsg("创建成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("创建失败");
		}
		return json;
	}
}
