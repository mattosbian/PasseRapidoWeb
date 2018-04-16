package br.com.passerapido.mbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.passerapido.dominio.Cliente;
import br.com.passerapido.dominio.Login;
import br.com.passerapido.dominio.Tag;

@ManagedBean
@ViewScoped
public class WelcomeMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Tag> tags;
	private Tag tag;
	
	public WelcomeMBean() {
		//FacesContext context = FacesContext.getCurrentInstance();
		//Login login = (Login) context.getExternalContext().getSessionMap().get("cpfLogado");
		Login login = Login.getUsuarioLogado();
		
		Cliente cliente = Cliente.buscaPorCPF(login.getCpf());
		
		this.setTags(Tag.buscaPorIdCliente(cliente.getId()));
		
		tag = new Tag();
	}

	
	public String logoff() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.getExternalContext().getSessionMap().remove("cpfLogado");
		Login.removeUsuarioLogado();
		
		return "login?faces-redirect=true";
	}

	
	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	
}
