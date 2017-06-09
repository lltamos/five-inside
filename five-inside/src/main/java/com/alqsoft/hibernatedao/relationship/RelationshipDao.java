package com.alqsoft.hibernatedao.relationship;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.relationship.RelationShip;

public interface RelationshipDao extends BaseDao<RelationShip> {

	@Modifying
	@Query("delete from RelationShip where GoodID =:goodid and providerID=:providerId")
	void deleteRelationshipByParam(@Param("goodid") Long goodid, @Param("providerId") Long providerId);

	
	@Query("FROM  RelationShip where GoodID =:goodid")
	RelationShip getRelationShipByGoodID(@Param("goodid") Long goodid);

}
