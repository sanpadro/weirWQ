package com.weirq.mvc.cloud;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.weirq.mvc.BaseController;
import com.weirq.util.BaseUtils;
import com.weirq.util.Json;
import com.weirq.vo.FileSystemVo;
import com.weirq.vo.HdfsVo;

@Controller
@RequestMapping("/cloud")
public class CloudController extends BaseController{

	@RequestMapping("/list")
	public String list(FileSystemVo fs,HttpSession session,Model model) throws Exception {
		String name = null;
		if (fs.getName()==null) {
			name = (String) session.getAttribute("username");
			name = "/"+name;
		}else{
			name = fs.getName();
		}
		model.addAttribute("fs", db.getFile(name));
		model.addAttribute("dir", name);
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
	public Json mkdir(String mkdir,String dirName,HttpSession session) {
		Json json = new Json();
		String name = (String) session.getAttribute("username");
		if (name==null) {
			json.setMsg("用户已注销，请重新登陆");
			return json;
		}
		try {
			String dir = null;
			if (!"".equals(dirName.trim())&& dirName!=null) {
				dir = "/"+dirName;
			}else {
				dir = "/"+name;
			}
			//在该用户下创建目录
			hdfsDB.mkdir(dir+"/"+mkdir);
			long id = db.getGid();
			//保存目录信息
			db.add("filesystem", id, "files", "name", mkdir);
			db.add("filesystem", id, "files", "dir", dir);
			db.add("filesystem", id, "files", "pdir", dir.substring(0, dir.lastIndexOf("/")));
			db.add("filesystem", id, "files", "type", "D");
			
			/*db.add("hdfs", id, "dir", "name", mkdir);
			db.add("hdfs", id, "dir", "type", "D");
			db.add("hdfs_name", mkdir, "id", "id", id);
			long pid = db.getIdByDirName(name);
			//保存目录关联信息
			db.add("hdfs_cid", pid, "cid", mkdir, id);*/
			json.setMsg("创建成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("创建失败");
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/upload")
	public Json upload(String dir,HttpSession session,HttpServletRequest request) throws Exception {
		Json json = new Json();
		String name = (String) session.getAttribute("username");
		if (name==null) {
			json.setMsg("用户已注销，请重新登陆");
			return json;
		}
		if(dir.equals("root")){
			dir = "/"+name;
		}else{
			dir = "/"+dir;
		}
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fms = multipartRequest.getFileMap();
			for (Map.Entry<String, MultipartFile> entity : fms.entrySet()) {
				MultipartFile mf = entity.getValue();
				System.out.println(mf.getSize());
				InputStream in = mf.getInputStream();
				hdfsDB.upload(in, dir+"/"+mf.getOriginalFilename());
				long id = db.getGid();
				db.add("filesystem", id, "files", "name", mf.getOriginalFilename());
				db.add("filesystem", id, "files", "dir", dir);
				db.add("filesystem", id, "files", "pdir", dir.substring(0, dir.lastIndexOf("/")));
				db.add("filesystem", id, "files", "type", "F");
				db.add("filesystem", id, "files", "size", BaseUtils.FormetFileSize(mf.getSize()));
				in.close();
				json.setSuccess(true);
			}
		}
		return json;
	}
	@ResponseBody
	@RequestMapping("/delete")
	public Json delete(FileSystemVo fs) throws Exception {
		Json json = new Json();
		String[] ss = fs.getIds().split(",");
		try {
			db.deleteRow("filesystem", ss);
			String[] ns = fs.getNames().split(",");
			String[] ds = fs.getDirs().split(",");
			for (int i = 0; i < ds.length; i++) {
				hdfsDB.delete(ds[i]+"/"+ns[i]);
			}
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setMsg("删除失败");
			e.printStackTrace();
		}
		return json;
	}
	
}
