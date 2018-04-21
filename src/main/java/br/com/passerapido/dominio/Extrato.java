package br.com.passerapido.dominio;

import java.util.Calendar;
import java.util.List;

import br.com.passerapido.exception.DominioException;

public class Extrato  {

	private List<TipoTransacao> tiposTransacao;
	private List<Tag> tags;
	
	private TipoTransacao tipoTransacao;
	private Tag tag;
	
	private Calendar dtInicial; 
	private Calendar dtFinal;
	private List<TransacaoExtrato> listaExtrato; 

	
	public void validate() throws DominioException {
		if (this.tag == null || !this.tag.isPreenchido()) {
			throw new DominioException("Veículo deve ser preenchida");
		}


		if (this.dtInicial == null) {
			throw new DominioException("Data Inicial deve ser preenchida");
		}

		if (this.dtFinal == null) {
			throw new DominioException("Data Final deve ser preenchida");
		}

		if (this.dtInicial.compareTo(this.dtFinal) > 0) {
			throw new DominioException("Data Inicial deve ser menor que Data Final");
		}

		
	}
	
	public Extrato(String cpf) {
		Cliente cliente = Cliente.buscaPorCPF(cpf);
		
		this.tags = Tag.buscaPorIdCliente(cliente.getId());
		this.tiposTransacao = TipoTransacao.buscaTodos();
	}

	public Extrato() {

	}
	
	public void listar() {
		this.listaExtrato = (TransacaoExtrato.listaPorData(this));
	}
	
	
	
	public List<TipoTransacao> getTiposTransacao() {
		return tiposTransacao;
	}

	public List<Tag> getTags() {
		return tags;
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

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<TransacaoExtrato> getListaExtrato() {
		return listaExtrato;
	}

	

	
}
