package com.alqsoft.service.impl.feedback;

import java.util.List;
import java.util.Map;

import org.alqframework.easyui.EasyUtils;
import org.alqframework.easyui.EasyuiResult;
import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.feedback.FeedBack;
import com.alqsoft.hibernatedao.feedback.FeedBackDao;
import com.alqsoft.service.feedback.FeedBackService;

/**
 * 
 * @Title: FeedBackServiceImpl.java
 * @Description: 意见反馈服务实现类
 * @author 俞旺
 * @e-mail 13606024548@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年4月22日 下午2:48:05 Copyright © 2013 厦门卓讯信息技术有限公司 All rights
 *              reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class FeedBackServiceImpl implements FeedBackService {
	@SuppressWarnings("unused")
	private static Logger logger=LoggerFactory.getLogger(FeedBackServiceImpl.class);
	@Autowired
	private FeedBackDao feedBackDao;
	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try{
			feedBackDao.delete(arg0);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	@Override
	public FeedBack get(Long arg0) {
		
		return feedBackDao.findOne(arg0);
	}

	
	@Override
	@Transactional
	public FeedBack saveAndModify(FeedBack arg0) {
		return feedBackDao.save(arg0);
	}

	
	@Override
	public EasyuiResult<List<FeedBack>> getFeedBackPage(
			Map<String, Object> map, Integer page, Integer rows) {
		Map<String, SearchFilter> filter = SearchFilter.parse(map);
		Specification<FeedBack> specification = DynamicSpecifications.bySearchFilter(filter.values(),
				FeedBack.class);
		Page<FeedBack> Page = feedBackDao.findAll(specification, new PageRequest(page - 1, rows,
				new Sort(Direction.DESC, new String[] { "createdTime" })));
		return EasyUtils.returnPage(FeedBack.class, Page);
	}
	@Override
	public Long getFeedBackNumByTypeId(Long id) {
		return feedBackDao.getFeedBackNumByTypeId(id);
	}
}
