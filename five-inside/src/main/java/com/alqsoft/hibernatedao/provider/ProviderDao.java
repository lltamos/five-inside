package com.alqsoft.hibernatedao.provider;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.provider.Provider;

public interface ProviderDao extends BaseDao<Provider>{

	@Query("FROM Provider where Name=:name")
	Provider getProviderByName(@Param("name")String name);

	
}
