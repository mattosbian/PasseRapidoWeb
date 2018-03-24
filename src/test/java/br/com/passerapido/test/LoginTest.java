package br.com.passerapido.test;

import br.com.passerapido.dominio.Login;
import br.com.passerapido.exception.LoginException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends BaseTest{



    @Before
    public void setUpTest(){
        criarEntityManager();
    }


    @Test(expected = LoginException.class)
    public void entrarNoSistemaTest() throws  LoginException{

        Login login = new Login();

            login.setCpf(123456);
            login.setSenha("");
            login.entrarNoSistema();

    }


    @After
    public void closeTest(){
       closeEntityManager();
    }


}
