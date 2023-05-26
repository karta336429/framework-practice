# Practice#5 - Thymeleaf

- 此練習為將 JSP 改寫為 HTML + Thymeleaf 及 xml 改寫為 Java 組態檔
- 先增加相關套件注入在 pom.xml
- 新增 templates 資料夾在 src/main/webapp/WEB-INF 的目錄結構下
- 改寫 JSP 檔為 HTML + Thymeleaf 放在 templates 資料夾內
- 將 mvc-config.xml ( 設定 Spring MVC ) 改寫為 WebMvcConfig.java
- 將 applicationContext.xml ( 設定 Spring ) 改寫為 ApplicationContextConfig.java
- 將 web.xml ( 設定 DispatcherServlet ) 改寫為 SpringServletContainerInitializer.java
- 統一將 Java 類別組態設定檔放在 src/main/java/org/tutorial/config 底下統一管理
- 無用的 xml 檔可移除