package org.tutorial.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpDO;
import org.tutorial.utils.JPAUtil;

public class EmpDAOImpl implements EmpDAO {
	
	// 透過JPA的persistence.xml設置了資料庫Driver、url、帳號及密碼，故這段已不需要
//	String driver = "com.mysql.cj.jdbc.Driver";
//    String url = "jdbc:mysql://localhost:3306/practice?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
//    String userid = "root";
//    String passwd = "1qaz@WSX";

	// 因為使用了 JPA & Hibernate，SQL語句會自動產生，故部分SQL語句不需要再自己打
//    private static final String INSERT_STMT = "INSERT INTO emp2 (ename,job,hiredate,sal,comm,deptno) VALUES (?, ?, ?, ?, ?, ?)";
//    private static final String GET_ALL_STMT = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 order by empno";
//    private static final String GET_ONE_STMT = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 where empno = ?";
//    private static final String DELETE = "DELETE FROM emp2 where empno = ?";
//    private static final String UPDATE = "UPDATE emp2 set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=? where empno = ?";

	@Override
	public void insert(EmpDO empDO) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(empDO);
			transaction.commit();
			entityManager.close();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}


	@Override
	public void update(EmpDO empDO) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		// 先查找有沒有此筆資料，回傳null等於無此資料 ( 若有資料但沒主鍵一樣會是null )
		EmpDO empDOFromDB = entityManager.find(EmpDO.class, empDO.getEmpno());
		if (empDOFromDB != null) {
			EntityTransaction transaction = entityManager.getTransaction();
			try {
				transaction.begin();
				empDOFromDB.setEname(empDO.getEname());
				empDOFromDB.setSal(empDO.getSal());
				empDOFromDB.setComm(empDO.getComm());
				entityManager.merge(empDOFromDB);
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
	public void delete(Integer empno) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EmpDO empDO = entityManager.find(EmpDO.class, empno);
		if (empDO != null) {
			EntityTransaction transaction = entityManager.getTransaction();
			try {
				transaction.begin();
				entityManager.remove(empDO);
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

	@Override
	public EmpDO findByPrimaryKey(Integer empno) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EmpDO empDOFromDB = entityManager.find(EmpDO.class, empno);
		return empDOFromDB;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	// 這是一段警告註解，告訴編譯器程式碼中可能會出現未檢查的轉型，但轉型被確認是安全的
	// 若沒有加此段annotation，在呼叫getResultList()方法時會出現型別轉換警告 ( Type safety )
	public List<EmpDO> getAll() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		// Named Query
//		Query query = entityManager.createNamedQuery("dept.all");
		// JPQL : 直接使用實體類別寫Query
		Query query = entityManager.createQuery("SELECT emp FROM EmpDO emp");
		// Native Query : 直接寫MySQL的語法
//		Query query = entityManager.createNativeQuery("SELECT * FROM EMP2");
		// 透過此方法取得查詢回來的資料
		List<EmpDO> list = query.getResultList();
		return list;
	}

}
