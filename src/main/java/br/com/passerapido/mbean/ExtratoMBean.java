package br.com.passerapido.mbean;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.passerapido.dominio.Extrato;
import br.com.passerapido.dominio.Login;
import br.com.passerapido.dominio.Tag;
import br.com.passerapido.dominio.TipoTransacao;
import br.com.passerapido.dominio.TransacaoExtrato;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.JsfUtil;

@ManagedBean
@ViewScoped
public class ExtratoMBean{

	private List<Tag> tags;
	private List<TipoTransacao> tiposTransacao;
	private List<TransacaoExtrato> listaExtrato;
	
	private Tag tag;
	private TipoTransacao tipoTransacao;
	private Extrato extrato;

	private Calendar dtInicial;
	private Calendar dtFinal;
	
	private Integer periodoSelecionado;
	
	public ExtratoMBean() {
		
		Login login = Login.getUsuarioLogado();

		setExtrato(new Extrato(login.getCpf()));
		
		setTags(getExtrato().getTags());
		setTiposTransacao(getExtrato().getTiposTransacao());
		
		this.dtInicial = Calendar.getInstance();
		this.dtFinal = Calendar.getInstance();
		
	}

	public void listar() {
		extrato.setTag(this.tag);
		extrato.setTipoTransacao(this.tipoTransacao);
		
		if (this.periodoSelecionado == 99) {
			extrato.setDtInicial(this.dtInicial);
			extrato.setDtFinal(this.dtFinal);
		} else {
			Calendar hoje = Calendar.getInstance();
			Calendar dtInicial = Calendar.getInstance();
			
			dtInicial.add(Calendar.DAY_OF_MONTH, -this.periodoSelecionado);
			
			extrato.setDtFinal(hoje);
			extrato.setDtInicial(dtInicial);
		}

		try {
			extrato.validate();
		} catch (DominioException e) {
			JsfUtil.addMensagemDeErro(e.getMessage());
			return;
		}
		
		extrato.listar();
		if (extrato.getListaExtrato().size() == 0) {
			JsfUtil.addMensagem("Não retornou registros");
		} 
		setListaExtrato(extrato.getListaExtrato());

	}
	
	
	public boolean desabilitaData() {
		if (this.periodoSelecionado == null) {
			return true;
		}
		return this.periodoSelecionado != 99;
	}

	
	
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public List<TipoTransacao> getTiposTransacao() {
		return tiposTransacao;
	}

	public void setTiposTransacao(List<TipoTransacao> tiposTransacao) {
		this.tiposTransacao = tiposTransacao;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Integer getPeriodoSelecionado() {
		return periodoSelecionado;
	}

	public void setPeriodoSelecionado(Integer periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}

	public Extrato getExtrato() {
		return extrato;
	}

	public void setExtrato(Extrato extrato) {
		this.extrato = extrato;
	}

	public List<TransacaoExtrato> getListaExtrato() {
		return listaExtrato;
	}

	public void setListaExtrato(List<TransacaoExtrato> listaExtrato) {
		this.listaExtrato = listaExtrato;
	}

	public Calendar getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Calendar dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Calendar getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Calendar dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String tagAtiva() {
		if (this.tag == null) {
			return null;
		}
		
		if (this.tag.isAtivo()) {
			return "Ativada";
		} else {
			return "Desativada";
		}
	}
	
}
