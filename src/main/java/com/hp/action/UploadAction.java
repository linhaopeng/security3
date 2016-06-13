package com.hp.action;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSON;
import com.hp.utils.ConfigUtil;
import com.hp.utils.DateUtil;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("basePackage")
@Namespace("/upload")
@InterceptorRefs(value = { @InterceptorRef("fileUploadStack") })
@Action(value = "uploadAction")
public class UploadAction extends ActionSupport {
	// 封装上传文件域的属性
	private File file;
	private String name;
	private List<String> names;
	// 封装上传文件名的属性
	private String fileFileName;
	// 封装上传文件类型的属性
	private String fileContentType;
	// 大文件上传 分块chul
	private int chunk;
	private int chunks;
	private String fileFolder; // 前台传递过来的上次文件的目录

	public void upload() throws IOException {
		if (StringUtils.isBlank(fileFolder)) {
			fileFolder = "/temp";// 避免前台没有传递这个参数的时候会报错
		}
		String datefolder = "/" + DateUtil.dateToString(new Date(), "yyyy") + "/" + DateUtil.dateToString(new Date(), "MM") + "/" + DateUtil.dateToString(new Date(), "dd");// 日期命名的文件夹
		String webParentPath = new File(ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")).getParent();// 当前WEB环境的上层目录
		String realPath = webParentPath + ConfigUtil.get("uploadPath") + fileFolder + datefolder;// 文件上传到服务器的真实路径
		// System.out.println(realPath);
		File dir = new File(realPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String newFileName = UUID.randomUUID().toString().replace("-", "").concat(".").concat(FilenameUtils.getExtension(fileFileName));
		File target = new File(realPath, newFileName);
		FileCopyUtils.copy(file, target);
		String path = ConfigUtil.get("uploadPath") + fileFolder + datefolder;// 文件在服务器的相对路径
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("status", true);
		m.put("fileUrl", path + "/" + newFileName);
		ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(m));
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		this.chunks = chunks;
	}

	public String getFileFolder() {
		return fileFolder;
	}

	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}

}
