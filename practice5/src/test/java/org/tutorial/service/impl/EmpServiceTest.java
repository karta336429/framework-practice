package org.tutorial.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.model.Entity.DeptDO;
import org.tutorial.model.Entity.EmpDO;
import org.tutorial.service.EmpService;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class EmpServiceTest {

	@Autowired
	// Field Injection
	private EmpService service;
	
	@Test
	public void testAddEmp() {
		EmpDO empDO = new EmpDO();
		empDO.setEname("王小明1");
		empDO.setJob("manager");
		empDO.setHiredate(LocalDate.parse(("2020-04-01")));
		empDO.setSal(new Double(50000));
		empDO.setComm(new Double(500));
		DeptDO deptDO = new DeptDO();
		deptDO.setDeptno(10);
		empDO.setDeptDO(deptDO);
		service.addEmp(empDO);
		assertTrue(true);
	}

	@Test
	public void testUpdateEmp() {
		EmpDO empDO = new EmpDO();
		empDO.setEmpno(2);
		empDO.setEname("王小明2");
		empDO.setJob("manager2");
		empDO.setHiredate(LocalDate.parse(("2020-04-01")));
		empDO.setSal(new Double(20000));
		empDO.setComm(new Double(200));
		DeptDO deptDO = new DeptDO();
		deptDO.setDeptno(20);
		empDO.setDeptDO(deptDO);
		EmpDO empDOFromDB = service.updateEmp(empDO);
		assertEquals("Test Update Complete!", "王小明2", empDOFromDB.getEname());
	}

	@Test
	public void testDeleteEmp() {
		service.deleteEmp(14);
		assertTrue(true);
	}

	@Test
	public void testGetOneEmp() {
		EmpDO empDO = service.getOneEmp(1);
		assertEquals(new Integer(1), empDO.getEmpno());
		assertEquals("king", empDO.getEname());
		assertEquals("president", empDO.getJob());
	}

	@Test
	public void testGetAll() {
		List<EmpDO> list = service.getAll();
		for(EmpDO empDO : list) {
			System.out.print(empDO.getEmpno() + ",");
			System.out.print(empDO.getEname() + ",");
			System.out.print(empDO.getJob() + ",");
			System.out.print(empDO.getHiredate() + ",");
			System.out.print(empDO.getSal() + ",");
			System.out.print(empDO.getComm() + ",");
			System.out.print(empDO.getDeptDO().getDeptno()+",");
			System.out.print(empDO.getDeptDO().getDname());
			System.out.println();
		}
	}
}
