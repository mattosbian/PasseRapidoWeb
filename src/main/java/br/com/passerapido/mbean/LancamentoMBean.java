package br.com.passerapido.mbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.passerapido.dominio.Lancamento;
import br.com.passerapido.dominio.Login;
import br.com.passerapido.dominio.Tag;
import br.com.passerapido.dominio.TipoTransacao;
import br.com.passerapido.dominio.Transacao;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.JsfUtil;

@ManagedBean
@ViewScoped
public class LancamentoMBean {


	Lancamento lancamento;
	
	private List<Tag> tags;
	private List<TipoTransacao> tipos;
	
	private Tag tag;
	private Transacao transacao;
	
	public LancamentoMBean() {
		
		Login login = Login.getUsuarioLogado();
		
		lancamento = new Lancamento(login);
		tags = lancamento.getTags();
		setTipos(lancamento.getTipos());

		tag = new Tag();
		transacao = new Transacao();

	}

	private void reinicio() {
		this.transacao.setVlTransacao(0);
		this.transacao.setTipoTransacao(null);
	}
	
	public void salvar()  {

		transacao.setTag(this.tag);
		lancamento.setTransacao(transacao);

		try {
			lancamento.salvar();
			JsfUtil.addMensagem("Lançamento realizado com sucesso");
			reinicio();

		} catch (DominioException e) {
			JsfUtil.addMensagemDeErro(e.getMessage());
			return;
		}
		
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

	public List<TipoTransacao> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoTransacao> tipos) {
		this.tipos = tipos;
	}

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}

	
}
