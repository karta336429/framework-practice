# Practice#6 - Spring Boot & Spring Data JPA

- 此練習為將 Spring 框架改寫為 Spring Boot 及 Hibernate 改寫為 Spring Data JPA
- 先增加相關套件注入在 pom.xml ( 引入Spring Boot，Web 已包含 Spring MVC，data-jpa 就是 Spring Data JPA)
- 將 templates 資料夾 ( 存放html檔 ) 從 src/main/webapp/WEB-INF 移至 src/main/resources 的目錄結構下 ( Spring Boot 的預設路徑為此 )
- 設定 Spring Boot 設定檔 ( application.properties 或 application.yml )，並放在 src/main/resources 路徑下
- 將 css 檔放在 src/main/resources/static/css 路徑下
- 在 src/main/java 路徑下撰寫一個執行 Spring Boot 用的 java 檔 ( 此處為 TutorialApplication.java ) 
- 將 DAO 改寫為 Repository