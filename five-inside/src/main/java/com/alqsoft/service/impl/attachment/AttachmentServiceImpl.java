package com.alqsoft.service.impl.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.hibernatedao.attachment.AttachmentDao;
import com.alqsoft.service.attachment.AttachmentService;
@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public boolean delete(Long arg0) {
		attachmentDao.delete(arg0);
		return true;
	}

	@Override
	public Attachment get(Long arg0) {
		return attachmentDao.findOne(arg0);
	}

	@Override
	public Attachment saveAndModify(Attachment arg0) {
		return attachmentDao.save(arg0);
	}

}
