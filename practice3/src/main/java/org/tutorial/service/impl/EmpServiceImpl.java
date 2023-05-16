package org.tutorial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	private EmpDAO dao;

	// Setter Injection
	// 算不算是一種"方法"注入
	@Autowired
	public void setEmpDAO(EmpDAO dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public EmpDO addEmp(EmpDO empDO) {
		dao.insert(empDO);
		return empDO;
	}

	@Override
	@Transactional
	public EmpDO updateEmp(EmpDO empDO) {
		dao.update(empDO);
		return dao.findByPrimaryKey(empDO.getEmpno());
	}

	@Override
	@Transactional
	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	@Override
	public EmpDO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	@Override
	public List<EmpDO> getAll() {
		return dao.getAll();
	}

}
