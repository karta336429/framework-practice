package org.tutorial.service;

import java.time.LocalDate;
import java.util.List;

import org.tutorial.model.Entity.EmpDO;

public interface EmpService {

    EmpDO addEmp(EmpDO empDO);

    EmpDO updateEmp(EmpDO empDO);

    void deleteEmp(Integer empno);

    EmpDO getOneEmp(Integer empno);

    List<EmpDO> getAll();

}
