package br.com.passerapido.listener;

import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ApplicationListener implements ServletContextListener {
	
	


	private final Logger logger = Logger.getLogger(ApplicationListener.class.getSimpleName());
	public static final String EMF = "EMF";
	private EntityManagerFactory emf;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
        if (emf!= null) {
        	emf.close();
        }
        logger.info("F�brica de Entitymanager fechada");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		
		emf = Persistence.createEntityManagerFactory("PasseRapidoWeb");
		e.getServletContext().setAttribute(EMF, emf);
		logger.info("F�brica de Entitymanager carregada com sucesso");
		
    }

}
