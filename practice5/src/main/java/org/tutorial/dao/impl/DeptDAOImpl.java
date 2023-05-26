package org.tutorial.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.Entity.DeptDO;
import org.tutorial.model.Entity.EmpDO;

@Repository
public class DeptDAOImpl implements DeptDAO {

    private static final String WILD_CARD = "%";

	// 加上此annotation，由 Spring 注入 EntityManager
    @PersistenceContext
    protected EntityManager entityManager;
    
    public DeptDAOImpl() {
    }

    @Override
    @Transactional
    public void insert(DeptDO deptDO) {
        entityManager.persist(deptDO);
    }

    @Override
    public void update(DeptDO deptDO) {
        DeptDO deptDOFromDB = entityManager.find(DeptDO.class, deptDO.getDeptno());
        if (deptDOFromDB != null) {
            deptDOFromDB.setDeptno(deptDO.getDeptno());
            deptDOFromDB.setDname(deptDO.getDname());
            deptDOFromDB.setLoc(deptDO.getLoc());
        }
    }

    @Override
    public void delete(Integer deptno) {
        DeptDO deptDO = entityManager.find(DeptDO.class, deptno);
        entityManager.remove(deptDO);
    }

    @Override
    public DeptDO findByPrimaryKey(Integer deptno) {
        return entityManager.find(DeptDO.class, deptno);
    }

	@Override
	@SuppressWarnings("unchecked")
	 public List<DeptDO> getAll() {
        //Name Query
        Query query = entityManager.createNamedQuery("dept.all");
        //JPQL Query
//        Query query = entityManager.createQuery("SELECT dept FROM DeptDO dept");
        //Native Query
//        Query query = entityManager.createNativeQuery("SELECT * FROM dept2", DeptDO.class);
        return query.getResultList();
    }

	@Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        // FETCH: 一次查出一方及多方，而非預設的 Lazy Loading（先查一方，等到要使用到多方的屬性時，才再發送 sql 至資料庫中查詢多方）
        TypedQuery<DeptDO> query =
                entityManager.createQuery("SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno", DeptDO.class);
        query.setParameter("deptno", deptno);
        DeptDO deptDO = query.getSingleResult();
        return deptDO.getEmpDOs();
    }
	
    @Override
    public List<DeptDO> findByCriteria(DeptDO deptDO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptDO> criteriaQuery = criteriaBuilder.createQuery(DeptDO.class);
        Root<DeptDO> column = criteriaQuery.from(DeptDO.class);

        List<Predicate> predicates = new ArrayList<>();
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
