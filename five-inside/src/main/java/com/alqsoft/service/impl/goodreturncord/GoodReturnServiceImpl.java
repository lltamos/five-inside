package com.alqsoft.service.impl.goodreturncord;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.goodsreturnrecord.GoodsReturnRecord;
import com.alqsoft.hibernatedao.goodsreturnrecord.GoodsReturnRecordDao;
import com.alqsoft.service.goodreturnrecord.GoodReturnService;
import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月11日 下午6:21:29 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class GoodReturnServiceImpl implements GoodReturnService {

	@Resource
	private GoodsReturnRecordDao goodReturnRecordDao;

	@Override
	public void findReturnRecordList(ArgsBean bean) {
		PageRequest pageRequest = buildPageRequest(bean.getPage(), bean.getRows());

		Specification<GoodsReturnRecord> spec = new Specification<GoodsReturnRecord>() {

			public Predicate toPredicate(Root<GoodsReturnRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return query.where(cb.equal(root.get("OrderGoodsId").as(Long.class), bean.getSearchStr()))
						.getRestriction();
			}
		};
		goodReturnRecordDao.findAll(spec, pageRequest);
	}

	@Override
	public void findGoodReturnList(ArgsBean bean) {

	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize, null);
	}

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GoodsReturnRecord get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public GoodsReturnRecord saveAndModify(GoodsReturnRecord arg0) {
		return goodReturnRecordDao.save(arg0);
	}

	@Override
	public int getGoodsReturnRecordByOrderNo(String order_no) {
		return goodReturnRecordDao.getGoodsReturnRecordByOrderNo(order_no);
	}

}
