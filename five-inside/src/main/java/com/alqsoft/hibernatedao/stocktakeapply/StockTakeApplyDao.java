package com.alqsoft.hibernatedao.stocktakeapply;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.stocktakeapply.StockTakeApply;

/**
 * 
* @ClassName: StockTakeApplyDao 
* @Description: 盘存申请记录Dao
* @author 腾卉 
* @date 2017年5月8日 下午1:59:28 
*
 */
public interface StockTakeApplyDao extends BaseDao<StockTakeApply>{

	/**
	 * 
	* @Title: updateUserGradeById 
	* @Description: 更改盘存审批状态 
	* @return void    返回类型 
	* @throws
	 */
	@Modifying
	@Query( value="update stocktakeapply set states=:states where id=:id",nativeQuery=true )
	public void updateStates(@Param("states")Integer States,@Param("id") Long id);
	
}
