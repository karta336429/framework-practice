package dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tutorial.dao.impl.EmpDAOImpl;
import org.tutorial.model.EmpDO;

public class EmpDAOTest {

	private EmpDAOImpl dao;

	@Before
	public void setUp() {
		dao = new EmpDAOImpl();
	}

	@After
	public void tearDown() {
		dao = null;
	}

	// 新增
	@Test
	public void testInsert() {
		EmpDO empDO1 = new EmpDO();
		empDO1.setEname("王小明1");
		empDO1.setJob("manager");
		empDO1.setHiredate(LocalDate.parse(("2020-04-01")));
		empDO1.setSal(new Double(50000));
		empDO1.setComm(new Double(500));
		empDO1.setDeptno(10);
		dao.insert(empDO1);
		assertEquals("王小明1", empDO1.getEname());
	}

	// 修改
	@Test
	public void testUpdate() {
		EmpDO empDO2 = new EmpDO();
		empDO2.setEmpno(17);
		empDO2.setEname("王小明2");
		empDO2.setJob("manager2");
		empDO2.setHiredate(LocalDate.parse(("2020-04-01")));
		empDO2.setSal(new Double(20000));
		empDO2.setComm(new Double(200));
		empDO2.setDeptno(20);
		dao.update(empDO2);
		assertEquals("update complete!", new Integer(17), empDO2.getEmpno());
	}

	// 刪除
	@Test
	public void testdelete() {
		EmpDO empDO3 = new EmpDO();
		empDO3.setEmpno(17);
		dao.delete(empDO3.getEmpno());
		assertTrue(true);
		assertNotEquals(new Integer(17), empDO3.getEmpno());
	}

	@Test
	public void findByPrimaryKey() {
		EmpDO empDO4 = dao.findByPrimaryKey(1);
		assertEquals(new Integer(1), empDO4.getEmpno());
		assertEquals("king", empDO4.getEname());
		assertEquals("president", empDO4.getJob());
		assertEquals(new Integer(10), empDO4.getDeptno());
	}

	@Test
	public void getAll() {
		List<EmpDO> list = dao.getAll();
		for (EmpDO empDO : list) {
			System.out.print(empDO.getEmpno() + ",");
			System.out.print(empDO.getEname() + ",");
			System.out.print(empDO.getJob() + ",");
			System.out.print(empDO.getHiredate() + ",");
			System.out.print(empDO.getSal() + ",");
			System.out.print(empDO.getComm() + ",");
			System.out.print(empDO.getDeptno());
			System.out.println();
		}
	}
}
