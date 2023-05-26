package org.tutorial.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tutorial.model.Entity.DeptDO;
import org.tutorial.model.Entity.EmpDO;
import org.tutorial.model.VO.DeptVO;
import org.tutorial.model.VO.EmpVO;
import org.tutorial.service.DeptService;

@Controller
public class DeptController {

	@Autowired
	private DeptService deptService;

	@GetMapping("/dept/listAll")
	public String listAll(Model model) {
		List<DeptDO> deptDOs = deptService.getAll();
		List<DeptVO> deptVOs = transformDeptVOs(deptDOs);
		model.addAttribute("deptVOs", deptVOs);
		return "dept/listAll";
	}

	@PostMapping("/dept/listEmps_ByDeptno_A")
	public String listEmpsByDeptnoA(Model model, Integer deptno) {
		List<EmpDO> empDOs = deptService.getEmpsByDeptno(deptno);
		model.addAttribute("listEmps_ByDeptno", transformEmpVOs(empDOs));
		return "dept/listEmpsByDeptno";
	}

	@GetMapping("/dept/listEmps_ByDeptno_B/{deptno}")
	public ModelAndView listEmpsByDeptnoB(@PathVariable("deptno") Integer deptno) {
		ModelAndView modelAndView = new ModelAndView();
		List<DeptDO> deptDOs = deptService.getAll();
		List<EmpDO> empDOs = deptService.getEmpsByDeptno(deptno);
		modelAndView.addObject("deptVOs", transformDeptVOs(deptDOs));
		modelAndView.addObject("listEmps_ByDeptno", transformEmpVOs(empDOs));
		modelAndView.setViewName("dept/listAll");
		return modelAndView;
	}

	@PostMapping("/dept/getOne_For_Update_Dept")
	public String getOneForUpdate(Model model, Integer deptno) {
		DeptDO deptDO = deptService.getOneDept(deptno);
		DeptVO deptVO = transformDeptVO(deptDO);
		model.addAttribute("deptVO", deptVO);
		return "dept/update";
	}

	@PostMapping("/dept/update")
	public String update(Model model, DeptVO deptVO) {
		DeptDO deptDO = new DeptDO();
		BeanUtils.copyProperties(deptVO, deptDO);
		DeptDO updateDeptDO = deptService.update(deptDO);
		model.addAttribute("deptVO", transformDeptVO(updateDeptDO));
		return "dept/listOne";
	}

	@PostMapping("/dept/delete_Dept")
	public String deleteDept(Integer deptno) {
		deptService.deleteDept(deptno);
		return "redirect:/dept/listAll";
	}



	private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOs) {
		List<DeptVO> list = new ArrayList<>();
		for (DeptDO deptDO : deptDOs) {
			DeptVO deptVO = new DeptVO();
			deptVO.setDeptno(deptDO.getDeptno());
			deptVO.setDname(deptDO.getDname());
			deptVO.setLoc(deptDO.getLoc());
			list.add(deptVO);
		}

		List<DeptVO> collect = deptDOs.stream().map(deptDO -> {
			DeptVO deptVO = new DeptVO();
			deptVO.setDeptno(deptDO.getDeptno());
			deptVO.setDname(deptDO.getDname());
			deptVO.setLoc(deptDO.getLoc());
			return deptVO;
		})
		.collect(Collectors.toList());
		return collect;
	}

	private DeptVO transformDeptVO(DeptDO deptDO) {
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptno(deptDO.getDeptno());
		deptVO.setDname(deptDO.getDname());
		deptVO.setLoc(deptDO.getLoc());
		return deptVO;
	}

	private List<EmpVO> transformEmpVOs(List<EmpDO> empDOs) {
		return empDOs.stream().map(empDO -> {
			EmpVO empVO = new EmpVO();
			empVO.setEmpno(empDO.getEmpno());
			empVO.setEname(empDO.getEname());
			empVO.setJob(empDO.getJob());
			empVO.setHiredate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(empDO.getHiredate()));
			empVO.setSal(empDO.getSal());
			empVO.setComm(empDO.getComm());
			empVO.setDeptVO(transformDeptVO(empDO.getDeptDO()));
			return empVO;
		}).collect(Collectors.toList());
	}

}