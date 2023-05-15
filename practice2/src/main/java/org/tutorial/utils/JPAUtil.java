package org.tutorial.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {

	public static final EntityManagerFactory emf;

	static {
		// 對應到META-INF內的persistence.xml設定檔的name
		emf = Persistence.createEntityManagerFactory("jpa-pu");
	}

	private JPAUtil() {
	}
	
	// 直接取得唯一一個EntityManagerFctory
	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

}
