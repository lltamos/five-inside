package com.alqsoft.controller.admin.after.attachment;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.utils.DoubleUtils;
import org.alqframework.utils.UniqueUtils;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.init.InitParamPc;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.utils.UpLoadUtils;


@RequestMapping("admin/after/attachment")
@Controller
public class AttachmentController {

	@Autowired
	private InitParamPc initParam;
	@Autowired
	private AttachmentService attachmentService;
	
	/**
	 * 接受ckeditor图片
	 * 
	 * @param model
	 * @param upload
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("import-attachment-ckeditor.do")
	public String importAttachmentOfCkeditor(Model model,
			@RequestParam("upload") MultipartFile upload,
			HttpServletRequest request) {
		String fileName = null;
		Attachment attachment = null;
		String sysFileName = "";
		String module = "ckeditor";
		String imageServerUrl = initParam.getProperties().getProperty(
				"img_server");
		// 判断是否为空
		if (upload.isEmpty()) {
			return "上传文件失败";
		} else {
			// 建立唯一地址
			// 获取服务器文件路径
			String basePath = SpringMVCUtils.getRequest()
					.getRealPath("/upload");
			String path = null;
			// 得到文件名字
			fileName = upload.getOriginalFilename();
			sysFileName = UniqueUtils.getOrder() + "."
					+ StringUtils.substringAfterLast(fileName, ".");
			String realPath = basePath + "/" + module ;
			boolean isFile = StringUtils.endsWithAny(
					StringUtils.lowerCase(fileName), new String[] { ".png",
							".jpg", ".jpeg", ".bmp", ".gif" });
			if (isFile) {
				path = realPath + "/"+ sysFileName;
			} 	else {
				return "上传文件格式错误";
			}
			// 将文件保存到指定文件下面
			try {
				File f = new File(realPath);
				if (!f.exists()) {
					f.mkdir();
				}
				upload.transferTo(new File(path));
				attachment = new Attachment();
				attachment.setMemory(DoubleUtils.div(upload.getSize(),
						1024000.0, 2));
				attachment.setName(fileName);
				attachment.setAddress("upload/" + module + "/" + sysFileName);
				attachmentService.saveAndModify(attachment);
				UpLoadUtils.alyUpload(module, sysFileName, path,
						InitParamPc.getInitParam());
			} catch (Exception e) {
				e.printStackTrace();
				return "上传失败";
			}
		}
		// 设置headers参数
		String result = "";
		// 设置返回“图像”选项卡
		String callback = SpringMVCUtils.getRequest().getParameter(
				"CKEditorFuncNum");
		result = "<script type=\"text/javascript\">";
		String parentWindow = "var parentWindow = (window.parent == window)?window.opener : window.parent;";
		result = result + parentWindow;
		result = result + "parentWindow.CKEDITOR.tools.callFunction("
				+ callback + ",'" + imageServerUrl + "/upload/" + module + "/"
				+ sysFileName + "','')";
		result = result + "</script>";
		model.addAttribute("result", result);
		return "ckeditor/ckeditor-fileupload";
	}
}
