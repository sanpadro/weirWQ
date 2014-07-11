package com.weirq.mvc.cloud;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.weirq.mvc.BaseController;
import com.weirq.util.Json;
import com.weirq.vo.HdfsVo;

@Controller
@RequestMapping("/cloud")
public class CloudController extends BaseController{

	@ResponseBody
	@RequestMapping("/list")
	public List<HdfsVo> list(HttpSession session,Model model) throws Exception {
		String name = (String) session.getAttribute("username");
		return hdfsDB.queryAll(name);
	}
	/**
	 * 创建目录
	 * @param mkdir
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/mkdir")
	public Json mkdir(String mkdir,Long parentId,HttpSession session) {
		Json json = new Json();
		String name = (String) session.getAttribute("username");
		if (name==null) {
			json.setMsg("用户已注销，请重新登陆");
			return json;
		}
		try {
			String dir = null;
			if (parentId!=null) {
				
			}else {
				dir = name+"/"+mkdir;
			}
			//在该用户下创建目录
			hdfsDB.mkdir(dir);
			long id = db.getGid();
			//保存目录信息
			db.add("hdfs", id, "dir", "name", mkdir);
			db.add("hdfs", id, "dir", "type", "D");
			db.add("hdfs_name", mkdir, "id", "id", id);
			long pid = db.getIdByDirName(name);
			//保存目录关联信息
			db.add("hdfs_cid", pid, "cid", mkdir, id);
			json.setMsg("创建成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("创建失败");
		}
		return json;
	}
	
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			
			
		}
		return null;
	}
}
