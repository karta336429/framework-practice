package org.tutorial.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.tutorial.dao.EmpDAO;
import org.tutorial.dao.impl.EmpDAOImpl;
import org.tutorial.model.EmpDO;
import org.tutorial.service.EmpService;

public class EmpServiceImpl implements EmpService {

    private EmpDAO dao;

    public EmpServiceImpl() {
        dao = new EmpDAOImpl();
    }

    public EmpDO addEmp(EmpDO empDO) {
        dao.insert(empDO);
        return empDO;
    }

    public EmpDO updateEmp(EmpDO empDO) {
        dao.update(empDO);
        return dao.findByPrimaryKey(empDO.getEmpno());
    }

    public void deleteEmp(Integer empno) {
        dao.delete(empno);
    }

    public EmpDO getOneEmp(Integer empno) {
        return dao.findByPrimaryKey(empno);
    }

    public List<EmpDO> getAll() {
        return dao.getAll();
    }

}
