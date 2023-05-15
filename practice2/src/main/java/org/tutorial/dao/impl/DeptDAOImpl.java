package org.tutorial.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.utils.JPAUtil;

public class DeptDAOImpl implements DeptDAO {

	// 透過JPA的persistence.xml設置了資料庫Driver、url、帳號及密碼，故這段已不需要
//	  String driver = "com.mysql.cj.jdbc.Driver";
//    String url = "jdbc:mysql://localhost:3306/practice?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
//    String userid = "root";
//    String passwd = "1qaz@WSX";

	// 因為使用了 JPA & Hibernate，SQL語句會自動產生，故部分SQL語句不需要再自己打
//    private static final String INSERT_STMT = "INSERT INTO dept2 (dname,loc) VALUES (?, ?)";
//    private static final String GET_ALL_STMT = "SELECT deptno , dname, loc FROM dept2";
//    private static final String GET_ONE_STMT = "SELECT deptno , dname, loc FROM dept2 where deptno = ?";
//    private static final String GET_Emps_ByDeptno_STMT = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";
//
//    private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno = ?";
//    private static final String DELETE_DEPT = "DELETE FROM dept2 where deptno = ?";
//
//    private static final String UPDATE = "UPDATE dept2 set dname=?, loc=? where deptno = ?";

	private static final String WILD_CARD = "%";

	@Override
	public void insert(DeptDO deptDO) {
		// 取得一個 EntityManager 物件
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		// 取得一個 EntityTransaction 物件，用於交易控制
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			// 利用 persist() 將 deptDO 物件新增至資料庫
			entityManager.persist(deptDO);
			transaction.commit();
			entityManager.close();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(DeptDO deptDO) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		// 透過到資料庫查詢該筆資料，先放入 deptDOformDB 物件內，但如果find方法查找的表沒有主鍵(PK)，回傳值會為 null
		DeptDO deptDOfromDB = entityManager.find(DeptDO.class, deptDO.getDeptno());
		if (deptDOfromDB != null) {
			EntityTransaction transaction = entityManager.getTransaction();
			try {
				transaction.begin();
				deptDOfromDB.setDeptno(deptDO.getDeptno());
				deptDOfromDB.setDname(deptDO.getDname());
				deptDOfromDB.setLoc(deptDO.getLoc());
				/*
				 因為 64 行有透過 find 方法做查詢且不為 null (若有資料但沒主鍵一樣會是null)，所以有無使用 merge 方法
				 都可以將同一筆資料修改後的內容，在交易 commit 或是 entity 物件關閉時，對資料庫做修改
				 機制是因為有透過 find 方法，將查詢回來的 deptDOFromDB 物件變為 persistent 狀態s
				 不過還是建議寫上 merge 方法，更好的利用JPA框架的機制來處理物件的更新
				*/
				entityManager.merge(deptDOfromDB);
				transaction.commit();
				entityManager.close();
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			}
		} else {
			System.out.println("查無資料，無法進行修改！");
		}
	}

	@Override
	public void delete(Integer deptno) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		// 先找找看有沒有這筆資料並做流程控制
		DeptDO deptDOfromDB = entityManager.find(DeptDO.class, deptno);
		if (deptDOfromDB != null) {
			EntityTransaction transaction = entityManager.getTransaction();
			try {
				transaction.begin();
				entityManager.remove(deptDOfromDB);
				transaction.commit();
				entityManager.close();
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			}
		} else {
			System.out.println("無此資料，無法進行刪除！");
		}
	}

	public DeptDO findByPrimaryKey(Integer deptno) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		DeptDO deptdo = entityManager.find(DeptDO.class, deptno);
		return deptdo;
	}

	@Override
	// 這是一段警告註解，告訴編譯器程式碼中可能會出現未檢查的轉型，但轉型被確認是安全的
	// 若沒有加此段annotation，在呼叫getResultList()方法時會出現型別轉換警告 ( Type safety )
	@SuppressWarnings("unchecked")
	public List<DeptDO> getAll() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		// Named Query
//		Query query = entityManager.createNamedQuery("dept.all");
		// JPQL
		Query query = entityManager.createQuery("SELECT dept FROM DeptDO dept");
		// Native Query
//		Query query = entityManager.createNativeQuery("SELECT * FROM DEPT2");
		List<DeptDO> list = query.getResultList();
		return list;
	}

	@Override
	public List<EmpDO> getEmpsByDeptno(Integer deptno) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		// FETCH: 一次查出一方及多方，而非預設的 Lazy Loading（先查一方，等到要使用到多方的屬性時，才再發送 sql 至資料庫中查詢多方）
		TypedQuery<DeptDO> query = entityManager.createQuery(
				"SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno", DeptDO.class);
		query.setParameter("deptno", deptno);
		DeptDO deptDO = query.getSingleResult();
		return deptDO.getEmpDOs();
	}

	@Override
	public List<DeptDO> findByCriteria(DeptDO deptDO) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeptDO> criteriaQuery = criteriaBuilder.createQuery(DeptDO.class);
		Root<DeptDO> column = criteriaQuery.from(DeptDO.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (deptDO.getDeptno() != null) {
			predicates.add(criteriaBuilder.equal(column.get("deptno"), deptDO.getDeptno()));
		}
		if (StringUtils.isNoneBlank(deptDO.getDname())) {
			predicates.add(criteriaBuilder.like(column.get("dname"), WILD_CARD + deptDO.getDname() + WILD_CARD));
		}

		if (StringUtils.isNoneBlank(deptDO.getLoc())) {
			predicates.add(criteriaBuilder.like(column.get("loc"), WILD_CARD + deptDO.getLoc() + WILD_CARD));
		}

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		TypedQuery<DeptDO> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

}
