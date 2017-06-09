package com.alqsoft.service.impl.soldier;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.alqframework.utils.UniqueUtils;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.init.InitParamPc;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.service.soldier.SoldierService;
import com.alqsoft.utils.UpLoadUtils;

/**
 * 朱军
 * @author zj
 *
 */
@Service
@Transactional(readOnly=true)
public class SoldierServiceImpl implements SoldierService {
	private static Logger logger = LoggerFactory.getLogger(SoldierServiceImpl.class);
	@Autowired
	private InitParamPc initParam;
	@Autowired
	private AttachmentService attachmentService;

	
	@Override
	@Transactional(readOnly=false)
	public Result uploadImg(MultipartFile urlfile, Object[] backUrl, String module,
			String[] extendFile, HttpServletRequest request) {
		String fileName = null;
		Attachment attachment = null;
		try {
			if (urlfile.isEmpty()) {
				return ResultUtils.returnError("上传文件失败");
			} else {
				String basePath = SpringMVCUtils.getRequest().getRealPath("/upload/" + module);
				File f = new File(basePath);
				if (!f.exists()) {
					f.mkdirs();
				}
				String path = null;
				fileName = urlfile.getOriginalFilename();
				boolean isFile = StringUtils.endsWithAny(StringUtils.lowerCase(fileName),
						extendFile);
				String sysFileName = UniqueUtils.getOrder() + "."
						+ StringUtils.substringAfter(fileName, ".");
				if (isFile) {
					path = basePath + "/" + sysFileName;
				} else {
					return ResultUtils.returnError("文件格式不正确,上传文件失败");
				}
				urlfile.transferTo(new File(path));
				attachment = new Attachment();
				attachment.setName(fileName);
				attachment.setAddress("/upload/" + module + "/" + sysFileName);

				UpLoadUtils.alyUpload(module, sysFileName, path, initParam);

				Attachment result = attachmentService.saveAndModify(attachment);
				if (result == null) {
					logger.error("上传图片回调方法返回数据为空");
					return ResultUtils.returnError("上传失败");
				} else {
					return ResultUtils.returnSuccess("上传成功", result);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError("上传失败");
		}
	}


	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Attachment get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Attachment saveAndModify(Attachment arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
