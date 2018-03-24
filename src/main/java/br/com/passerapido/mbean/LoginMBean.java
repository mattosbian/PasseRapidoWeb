package br.com.passerapido.mbean;

import br.com.passerapido.dominio.Login;
import br.com.passerapido.exception.LoginException;
import br.com.passerapido.util.JsfUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "loginMBean")
@RequestScoped
public class LoginMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Login login;
	
	
	@PostConstruct
	private void init() {
		login = new Login();
		
	}
	
	

	
	public String realizarLogin() {
		try {
			login.entrarNoSistema();
			return "welcome?faces-redirect=true";
		} catch (LoginException e) {
			JsfUtil.addMensagem(e.getMessage());
            return "login";

		}
	}




	public Login getLogin() {
		return login;
	}




	public void setLogin(Login login) {
		this.login = login;
	}



	
	
	
}
