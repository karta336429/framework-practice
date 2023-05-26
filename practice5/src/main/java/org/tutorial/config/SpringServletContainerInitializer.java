package org.tutorial.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


// 此類別配置及初始化 Spring 的 DispatcherServlet 
public class SpringServletContainerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 啟動時會先呼叫此方法，將 spring.profiles.active 的值設定為 dev
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("spring.profiles.active", "dev");
    }

	// 取得根配置類別
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationContextConfig.class };
	}

	// 取得 Servlet 配置類別
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ApplicationContextConfig.class };
	}
	
	// 指定 DispatcherServlet 的對應路徑，表示他將處理根目錄
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	// 此方法將請求及回應的字元編碼設定為 UTF-8
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}
}
