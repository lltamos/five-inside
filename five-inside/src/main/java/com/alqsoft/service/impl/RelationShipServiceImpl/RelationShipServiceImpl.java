package com.alqsoft.service.impl.RelationShipServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.relationship.RelationShip;
import com.alqsoft.hibernatedao.relationship.RelationshipDao;
import com.alqsoft.service.relationship.RelationShipService;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月23日 下午8:00:41 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Service
@Transactional
public class RelationShipServiceImpl implements RelationShipService {
	@Autowired
	private RelationshipDao relationshipDao;

	@Override
	public RelationShip getRelationShipByGoodID(Long id) {
		return relationshipDao.getRelationShipByGoodID(id);
	}

}
