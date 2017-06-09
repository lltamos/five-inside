package com.alqsoft.service.soldier;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.good.Good;

/**
 * 朱军
 * @author zj
 *
 */
public interface SoldierService extends BaseService<Attachment>{
	
	/**
	 * 上传图片到阿里云并保存图片路径到系统附件表
	 * 
	 * @param urlfile
	 * @param backUrl
	 * @param module
	 * @param extendFile
	 * @param request
	 * @return
	 */
	Result uploadImg(MultipartFile urlfile, Object[] backUrl, String module, String[] extendFile,
			HttpServletRequest request);

}
