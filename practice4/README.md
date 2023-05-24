# Practice#4 - Thymeleaf

- 此練習為將 Servlet 改寫為 Spring MVC 架構
- 先增加相關套件注入在 pom.xml
- 新增 mvc-config.xml 在 src/main/webapp/WEB-INF 的目錄結構下
- 改寫 web.xml ( 設定DispatcherServlet )
- 新增 VO ( View Object ) ，為 View 層傳遞資料
- 針對 Spring MVC 架構需要改寫 Servlet 為 Controller
- 將 JSP 移至 webapp/WEB-INF/view 路徑下，不讓 Client 可用 URL 就找到網頁