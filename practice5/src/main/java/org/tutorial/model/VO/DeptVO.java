package org.tutorial.model.VO;

import java.util.List;

import org.tutorial.model.Entity.EmpDO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptVO {

	private Integer deptno;
	private String dname;
	private String loc;
	private List<EmpVO> empVOs;
}
