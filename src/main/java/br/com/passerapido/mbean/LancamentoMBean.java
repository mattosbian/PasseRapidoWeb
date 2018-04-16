package br.com.passerapido.mbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.passerapido.dominio.Lancamento;
import br.com.passerapido.dominio.Login;
import br.com.passerapido.dominio.Tag;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.JsfUtil;

@ManagedBean
@ViewScoped
public class LancamentoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	Lancamento lancamento;
	
	private List<Tag> tags;
	
	private Tag tag;
	
	private Integer valorRecarga; 
	
	public LancamentoMBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		Login login = (Login) context.getExternalContext().getSessionMap().get("cpfLogado");
		
		//para teste
//		Login login = new Login();
//		login.setCpf("111");
		//para teste

		lancamento = new Lancamento(login);
		tags = lancamento.getTags();
		
		this.valorRecarga = 0;
	}

	public void recarregar()  {
		System.out.println("lancamentoMBean valor:" + this.valorRecarga);

		lancamento.setTag(this.tag);
		
		try {
			this.lancamento.recarregar(this.valorRecarga);
			JsfUtil.addMensagem("Recarga realizada com sucesso");
			
			this.tag = new Tag();
			this.valorRecarga = 0;
			
		} catch (DominioException e) {
			JsfUtil.addMensagemDeErro(e.getMessage());
		}
	}
	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Tag getTag() {
		System.out.println("LancamentoMbean - getTag");
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
		System.out.println("LancamentoMbean - setTag : " + tag.getCdTag());
	}


	public Integer getValorRecarga() {
		return valorRecarga;
	}


	public void setValorRecarga(Integer valorRecarga) {
		this.valorRecarga = valorRecarga;
	}

	
}
