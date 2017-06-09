package com.alqsoft.hibernatedao.user;

import javax.persistence.LockModeType;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.user.Users;

public interface UsersDao extends BaseDao<Users>{
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select u from Users as u where u.id=:id")
	public Users getUsersByIdRowLock(@Param("id")long id);
	
	@Modifying  
	@Query("update Users as u set u.fanxAccount=:fanxAccount where u.id=:id")
	public void updateUserFanxAccountById(@Param("fanxAccount") Double fanxAccount,@Param("id") long id);
	
	@Modifying
	@Query("update Users as u set u.bdtjrId=:BdtjrId where u.id=:id")
	public void updateUserBdtjrIdById(@Param("BdtjrId")long BdtjrId,@Param("id") long id);

	@Modifying
	@Query("update Users as u set u.goodBalance=:goodbl where u.id=:id")
	public void updateUserGoodBlById(@Param("goodbl")double goodbl, @Param("id")long id);

	@Modifying
	@Query("update Users as u set u.grade=:grade where u.id=:id")
	public void updateUserGradeById(@Param("grade")long grade,@Param("id") long id);
	
	@Modifying
	@Query("update Users as u set u.orderCenterAccount=:summoney where u.id=:id")
	public void updateUserOrderCenterAccountByRecharge(@Param("summoney")Double summoney,@Param("id") long id);
	
}
