package org.tutorial.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tutorial.model.Entity.DeptDO;
import org.tutorial.model.Entity.EmpDO;
import org.tutorial.service.DeptService;

@RunWith(SpringJUnit4ClassRunner.class)	
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DeptServiceTest {

	@Autowired
	private DeptService service;

	@Test
	public void testGetAll() {
		List<DeptDO> list = service.getAll();
		assertNotNull(list);
		if (list != null) {
			for (DeptDO deptDO : list) {
				System.out.print(deptDO.getDeptno() + ", ");
				System.out.print(deptDO.getDname() + ", ");
				System.out.print(deptDO.getLoc() + ", ");
				System.out.println();
			}
		}
	}

	@Test
	public void testGetOneDept() {
		DeptDO deptDO = service.getOneDept(20);
		assertEquals(new Integer(20), deptDO.getDeptno());
		assertEquals("研發部", deptDO.getDname());
		assertEquals("臺灣新竹", deptDO.getLoc());
	}

	@Test
	public void testUpdate() {
		DeptDO deptDO = new DeptDO();
		deptDO.setDeptno(10);
		deptDO.setDname("財務部2");
		deptDO.setLoc("臺灣台北2");
		service.update(deptDO);
		assertTrue(true);
	}
	
	@Test
	public void testGetEmpsByDeptno() {
		List<EmpDO> list = service.getEmpsByDeptno(10);
		for (EmpDO empDO : list) {
			System.out.println(empDO.getEmpno() + ", ");
			System.out.print(empDO.getEname() + ", ");
			System.out.print(empDO.getJob() + ", ");
			System.out.print(empDO.getHiredate() + ", ");
			System.out.print(empDO.getSal() + ", ");
			System.out.print(empDO.getComm() + ", ");
			System.out.print(empDO.getDeptDO().getDeptno());
			System.out.println();
		}
	}
	
	@Test
	public void testDeleteDept() {
		service.deleteDept(30);
		assertTrue(true);
	}
}
