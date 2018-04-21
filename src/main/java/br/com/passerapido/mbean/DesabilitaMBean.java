package br.com.passerapido.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.com.passerapido.dominio.Desabilita;
import br.com.passerapido.dominio.Login;
import br.com.passerapido.dominio.Tag;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.JsfUtil;

@ManagedBean
@ViewScoped
public class DesabilitaMBean {

	private Login login;
	
	private Desabilita desabilita;
	
	private List<Tag> tagsDe;
	private List<Tag> tagsPara;

	private Tag tag;
	private Tag tagPara;
	
	public DesabilitaMBean() {
		
		login = Login.getUsuarioLogado();

		reinicio();
	}

	public void reinicio() {
		desabilita = new Desabilita(login);
		setTagsDe(desabilita.getTagsDe());
		
		tagsPara = new ArrayList<Tag>();
		setTag(new Tag());
	}
	
	public void salvar()  {
		desabilita.setTagDe(this.tag);
		desabilita.setTagPara(this.tagPara);

		try {
			desabilita.validate();
			
			desabilita.salvar();

			reinicio();

			JsfUtil.addMensagem("Veículo desabilitado com sucesso");
		} catch (DominioException e) {
			JsfUtil.addMensagemDeErro(e.getMessage());
			return;
		} catch (PersistenceException e) {
			JsfUtil.addMensagemDeErro(e.getMessage());
			return;
		}
		
	}


	public boolean desabilitaParaTransferir() {
		if (this.tag.getVlSaldo() > 0 && this.tagsDe.size() > 1) {
			return false;
		}
		return true;
	}
	
	
	public List<Tag> getTagsDe() {
		return tagsDe;
	}

	public void setTagsDe(List<Tag> tagsDe) {
		this.tagsDe = tagsDe;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;

		this.tagPara = null;
		tagsPara.clear();
		tagsPara.addAll(tagsDe);
		tagsPara.remove(tag);
	}

	public Tag getTagPara() {
		return tagPara;
	}

	public void setTagPara(Tag tagPara) {
		this.tagPara = tagPara;
	}

	public List<Tag> getTagsPara() {
		return tagsPara;
	}

	public void setTagsPara(List<Tag> tagsPara) {
		this.tagsPara = tagsPara;
	}

	

	
}
