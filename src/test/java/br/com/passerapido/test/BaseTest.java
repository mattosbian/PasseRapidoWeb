package br.com.passerapido.test;

import br.com.passerapido.listener.ApplicationListener;
import br.com.passerapido.util.EntityManagerUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;

import static org.mockito.Mockito.when;

public class BaseTest {


    protected static EntityManagerFactory emf;

    @Mock
    private ServletContext servletContext;

    @BeforeClass
    public static void setUpClass(){
        emf = Persistence.createEntityManagerFactory("PasseRapidoLocal");
    }

    protected void criarEntityManager() {
        MockitoAnnotations.initMocks(this);

        when(servletContext.getAttribute(ApplicationListener.EMF)).thenReturn(emf);

        EntityManagerUtil.createEntityManager(servletContext);
    }

    protected void  closeEntityManager(){
        EntityManagerUtil.closeEntityManager();
    }


    @AfterClass
    public static void closeClass(){
        if (emf!= null){
            emf.close();
        }
    }

}
