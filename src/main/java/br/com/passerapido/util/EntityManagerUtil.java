package br.com.passerapido.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;

import br.com.passerapido.listener.ApplicationListener;

public class EntityManagerUtil {
	
	private static ThreadLocal<EntityManager> local = new ThreadLocal<EntityManager>();

	public static void createEntityManager(ServletContext servletContext) {
		EntityManagerFactory emf = (EntityManagerFactory) servletContext.getAttribute(ApplicationListener.EMF);
		local.set(emf.createEntityManager());		
	}
	
	public static EntityManager getEntityManager() {
		return local.get();
	}
	
	public static void closeEntityManager() {
		local.get().close();
	}
	

}
