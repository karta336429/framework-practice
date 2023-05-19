package org.tutorial.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.config.AppConfig;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.DeptService;
import org.tutorial.service.EmpService;

@WebServlet("")
public class IndexServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DeptService deptService = getDeptServiceFromSpring();
		List<DeptDO> deptDOs = deptService.getAll();
		req.setAttribute("deptDOs", deptDOs);

		EmpService empService = getEmpServiceFromSpring();
		List<EmpDO> empDOs = empService.getAll();
		req.setAttribute("empDOs", empDOs);

		RequestDispatcher successView = req.getRequestDispatcher("index.jsp");
		successView.forward(req, res);
	}

	// 將 Service 物件生成交由 Spring 管理，不用再自己 new 物件
	// 此種取得 Spring Bean 的方式為暫時測試用
	private DeptService getDeptServiceFromSpring() {

		// XML組態定義
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// JAVA類別組態定義
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();
		
		return context.getBean("deptServiceImpl", DeptService.class);
	}

	// 將 Service 物件生成交由 Spring 管理，不用再自己 new 物件
	// 此種取得 Spring Bean 的方式為暫時測試用
	private EmpService getEmpServiceFromSpring() {
		
		// XML組態定義
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// JAVA類別組態定義
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();
		
		return context.getBean("empServiceImpl", EmpService.class);
	}

}
