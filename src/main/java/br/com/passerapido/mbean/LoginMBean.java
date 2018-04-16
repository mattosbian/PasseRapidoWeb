package br.com.passerapido.mbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.passerapido.dominio.Login;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.exception.LoginException;
import br.com.passerapido.util.JsfUtil;

@ManagedBean(name = "loginMBean")
@RequestScoped
public class LoginMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Login login;
	
	
	public LoginMBean() {
		login = new Login();
	}
	
	
	public String realizarLogin() throws DominioException {
		
		if (this.login.getSenha().isEmpty()) {
			JsfUtil.addMensagemDeErro("Senha deve ser preenchida");
            return "login";
		}

		try {
			login.entrarNoSistema();
			
			//putSession();
			login.setUsuarioLogado();
			
			return "welcome?faces-redirect=true";
		} catch (LoginException e) {
			JsfUtil.addMensagem(e.getMessage());
            return "login";

		}
	}


	public String novoUsuario() {

		if (login.existeCPF()) {
			JsfUtil.addMensagemDeErro("Este CPF já está cadastrado. Efetue login");
	        return "login";
	        
		} else {
			login.novo();
			//putSession();
			login.setUsuarioLogado();
			return "cadastro?faces-redirect=true";
		}
			
	}


//	private void putSession() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.getExternalContext().getSessionMap().put("cpfLogado", this.login);
//	}
	
	public Login getLogin() {
		return login;
	}




	public void setLogin(Login login) {
		this.login = login;
	}



	
	
	
}
