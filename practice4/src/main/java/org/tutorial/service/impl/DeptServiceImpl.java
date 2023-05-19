package org.tutorial.service.impl;

import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.Entity.DeptDO;
import org.tutorial.model.Entity.EmpDO;
import org.tutorial.service.DeptService;

@Service
@NoArgsConstructor
public class DeptServiceImpl implements DeptService {
	
	private DeptDAO dao;

    // Constructor Injection
    @Autowired
    public DeptServiceImpl(DeptDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<DeptDO> getAll() {
        return dao.getAll();
    }

    @Override
    public DeptDO getOneDept(Integer deptno) {
        return dao.findByPrimaryKey(deptno);
    }

    @Override
    @Transactional
    public DeptDO update(DeptDO deptDO) {
    	dao.update(deptDO);
        return dao.findByPrimaryKey(deptDO.getDeptno());
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        return dao.getEmpsByDeptno(deptno);
    }

    @Override
    @Transactional
    public void deleteDept(Integer deptno) {
        dao.delete(deptno);
    }
}
