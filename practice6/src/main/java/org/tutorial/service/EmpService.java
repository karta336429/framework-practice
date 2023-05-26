package org.tutorial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tutorial.model.Entity.EmpDO;

public interface EmpService {

	EmpDO addEmp(EmpDO empDO);
	
	EmpDO updateEmp(EmpDO empDO);
	
	void deleteEmp(Integer empno);

	Optional<EmpDO> getOneEmp(Integer empno);

	List<EmpDO> getAll();
	
	Page<EmpDO> getAllpages(Pageable pageable);
	
}
