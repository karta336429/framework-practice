package org.tutorial.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.model.Entity.EmpDO;
import org.tutorial.repository.EmpRepository;
import org.tutorial.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepository repository;

	// 根據有沒有主鍵，判斷 save() 是新增還是修改
	// 有主鍵是 update，沒有主鍵是 insert
	// P.S. 主鍵定義在 Entity (@Id)
	@Override
	@Transactional
	public EmpDO addEmp(EmpDO empDO) {
		return repository.save(empDO);
	}

	@Override
	@Transactional
	public EmpDO updateEmp(EmpDO empDO) {
		return repository.save(empDO);
	}

	@Override
	@Transactional
	public void deleteEmp(Integer empno) {
		repository.deleteById(empno);
	}

	@Override
	public Optional<EmpDO> getOneEmp(Integer empno) {
		return repository.findById(empno);
	}

	@Override
	public List<EmpDO> getAll() {
		return repository.findAll();
	}

	@Override
	public Page<EmpDO> getAllpages(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
