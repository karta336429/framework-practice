package org.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tutorial.model.Entity.DeptDO;

@Repository
public interface DeptRepository extends JpaRepository<DeptDO, Integer>{

	// 自訂查詢語句 ( JPQL ) 
	@Query(value = "SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno")
	DeptDO getOneDeptWithEmps(@Param("deptno") Integer deptno);
	
	@Query(value = "SELECT * FROM dept2 WHERE deptno = :deptno",nativeQuery = true)
	DeptDO getOneById(@Param("deptno") Integer deptno);
	
	DeptDO findDeptByDnameAndLocOrderByDeptno(@Param("dname") String dname, @Param("loc") String loc);
	
}
