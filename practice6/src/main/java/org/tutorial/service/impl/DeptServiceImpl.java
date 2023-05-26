package org.tutorial.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.model.Entity.DeptDO;
import org.tutorial.model.Entity.EmpDO;
import org.tutorial.repository.DeptRepository;
import org.tutorial.service.DeptService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptRepository repository;

    @Override
    public List<DeptDO> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<DeptDO> getAllpages(Pageable pageable) {
    	return repository.findAll(pageable);
    }
    
    @Override
    public Optional<DeptDO> getOneDept(Integer deptno) {
        return repository.findById(deptno);
    }

    @Override
    @Transactional
    public DeptDO update(DeptDO deptDO) {
    	return repository.save(deptDO);
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        DeptDO deptDO = repository.getOneDeptWithEmps(deptno);
        return deptDO.getEmpDOs();
    }

    @Override
    @Transactional
    public void deleteDept(Integer deptno) {
        repository.deleteById(deptno);
    }

}
