package com.weirq.mvc.cloud;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.weirq.mvc.BaseController;
import com.weirq.util.BaseUtils;
import com.weirq.util.DateUtil;
import com.weirq.util.FileUtils;
import com.weirq.util.Json;
import com.weirq.util.OfficeToSwf;
import com.weirq.vo.FileSystemVo;

@Controller
@RequestMapping("/cloud")
public class CloudController extends BaseController{

	@RequestMapping("/list")
	public String list(String name,HttpSession session,Model model) throws Exception {
		if (!BaseUtils.isNotEmpty(name)) {
			name = (String) session.getAttribute("username");
			name = "/"+name;
		}
//		model.addAttribute("fs", db.getFile(name));
		model.addAttribute("fs", hdfsDB.queryAll(name));
		model.addAttribute("dir", name);
		model.addAttribute("url", BaseUtils.getUrl(name));
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
		if(!BaseUtils.isNotEmpty(mkdir)){
			json.setMsg("空值无效");
			return json;
		}
		String name = (String) session.getAttribute("username");
		if (name==null) {
			json.setMsg("用户已注销，请重新登陆");
			return json;
		}
		try {
			String dir = null;
			if (BaseUtils.isNotEmpty(dirName)) {
				dir = dirName;
			}else {
				dir = "/"+name;
			}
			//在该用户下创建目录
			hdfsDB.mkdir(dir+"/"+mkdir);
			/*long id = db.getGid();
			db.add("filesystem", id, "files", "name", mkdir);
			db.add("filesystem", id, "files", "dir", dir);
			db.add("filesystem", id, "files", "pdir", dir.substring(0, dir.lastIndexOf("/")));
			db.add("filesystem", id, "files", "type", "D");*/
			
			FileSystemVo fs = new FileSystemVo();
//			fs.setId(id);
			fs.setName(mkdir);
			fs.setType("D");
			fs.setDate(DateUtil.DateToString("yyyy-MM-dd HH:mm", new Date()));
			
			json.setObj(fs);
			json.setMsg("创建成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("创建失败");
		}
		return json;
	}
	
	/**
	 * 上传文件
	 * @param dir
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
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
		}
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fms = multipartRequest.getFileMap();
			for (Map.Entry<String, MultipartFile> entity : fms.entrySet()) {
				MultipartFile mf = entity.getValue();
				//System.out.println(mf.getSize());
				InputStream in = mf.getInputStream();
				hdfsDB.upload(in, dir+"/"+mf.getOriginalFilename());
//				long id = db.getGid();
//				db.add("filesystem", id, "files", "name", mf.getOriginalFilename());
//				db.add("filesystem", id, "files", "dir", dir);
//				db.add("filesystem", id, "files", "pdir", dir.substring(0, dir.lastIndexOf("/")));
//				db.add("filesystem", id, "files", "type", "F");
//				db.add("filesystem", id, "files", "size", BaseUtils.FormetFileSize(mf.getSize()));
				in.close();
				json.setSuccess(true);
			}
		}
		return json;
	}
	/**
	 * 删除文件及文件夹
	 * @param ids
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Json delete(String ids,String dir) throws Exception {
		Json json = new Json();
//		String[] ss = fs.getIds().split(",");
		try {
			//db.deleteRow("filesystem", ss);
			String[] ns = ids.split(",");
//			String[] ds = fs.getDirs().split(",");
			for (int i = 0; i < ns.length; i++) {
				hdfsDB.delete(dir+"/"+ns[i]);
			}
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setMsg("删除失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 重命名文件及文件夹
	 * @param dir
	 * @param name
	 * @param rename
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/rename")
	public Json rename(String dir,String name,String rename,String type) throws Exception {
		Json json = new Json();
		try {
			if(type.equals("F")){
				hdfsDB.rename(dir+"/"+name, dir+"/"+rename+name.substring(name.lastIndexOf(".")));
			}else if (type.equals("D")) {
				hdfsDB.rename(dir+"/"+name, dir+"/"+rename);
			}
			json.setSuccess(true);
			json.setMsg("重命名成功");
		} catch (Exception e) {
			json.setMsg("删除失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 查看文档
	 * @param dir
	 * @param name
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/view")
	public String view(String dir,String name,HttpServletRequest request,Model model) throws Exception {
		String local = request.getServletContext().getRealPath("/test");
		String swfFile = FileUtils.getFilePrefix(local+"\\"+name)+".swf";
		File outFile = new File(swfFile);
		if(outFile.exists()){
			model.addAttribute("local", "test/"+name.substring(0,name.lastIndexOf("."))+".swf");
			return "22";
		}
		String pdfFile = FileUtils.getFilePrefix(local+"\\"+name)+".pdf";
		File outFile01 = new File(pdfFile);
		if(!outFile01.exists()){
			hdfsDB.downLoad(dir+"/"+name, local+"\\"+name);
			OfficeToSwf.convert2PDF(local+"\\"+name);
		}else{
			OfficeToSwf.pdftoswf(pdfFile);
		}
		model.addAttribute("local", "test/"+name.substring(0,name.lastIndexOf("."))+".swf");
		return "22";
	}
	/**
	 * 分享
	 * @param dir
	 * @param names
	 * @param usernames
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/share")
	public Json share(String dir,String names,String usernames,String types,HttpSession session) throws Exception {
		Json json = new Json();
		String name = (String) session.getAttribute("username");
		if (name==null) {
			json.setMsg("用户已注销，请重新登陆");
			return json;
		}
		String[] n = names.split(",");
		String[] t = types.split(",");
		String[] u = usernames.split(",");
		for (int i = 0; i < u.length; i++) {
			try {
				db.share(dir, name, n, t, u[i]);
			} catch (Exception e) {
				json.setMsg("分享失败");
				e.printStackTrace();
				return json;
			}
		}
		json.setSuccess(true);
		return json;
	}
	@RequestMapping("/getshare")
	public String getshare(HttpSession session,Model model) throws Exception {
		String name = (String) session.getAttribute("username");
		if (name==null) {
			return null;
		}
		model.addAttribute("shares", db.getshare(name));
		return "cloud/share";
	}
	@RequestMapping("/getshareed")
	public String getshareed(HttpSession session,Model model) throws Exception {
		String name = (String) session.getAttribute("username");
		if (name==null) {
			return null;
		}
		model.addAttribute("shares", db.getshareed(name));
		return "cloud/shareed";
	}
}
