package org.tutorial.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.Entity.EmpDO;

@Repository
public class EmpDAOImpl implements EmpDAO {
	
	// 加上此annotation，由 Spring 注入 EntityManager
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public void insert(EmpDO empDO) {
		entityManager.persist(empDO);
	}
	
	@Override
	public void update(EmpDO empDO) {
		entityManager.merge(empDO);
	}
	
	@Override
	public void delete(Integer empno) {
		EmpDO empDO = entityManager.find(EmpDO.class, empno);
		entityManager.remove(empDO);
		/*** 
		 確認此段程式碼的執行狀況
		 依我自己理解為：因透過 find 找回來的資料存在 empDO 物件內，empDO 物件目前處於persistent狀態
		 在執行remove方法時，還沒對資料庫做刪除，而是等 Spring 管理的 entityManager 物件關閉時，才進行刪除！
		 ***/ 
	}
	
	@Override
	public EmpDO findByPrimaryKey(Integer empno) {
		return entityManager.find(EmpDO.class, empno);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EmpDO> getAll(){
		// Named Query
		Query query = entityManager.createNamedQuery("emp.all");
		// JPQL
//		Query query = entityManager.createQuery("SELECT emp FROM EmpDO emp");
		// NativeSQL
//		Query query = entityManager.createNativeQuery("SELECT * DROM EMP2");
		return query.getResultList();
	}
}
