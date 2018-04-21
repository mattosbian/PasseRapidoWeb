package br.com.passerapido.dominio;

import java.io.Serializable;
import java.util.Calendar;

import br.com.passerapido.entity.TbTransacao;
import br.com.passerapido.exception.DominioException;

public class Transacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idTransacao;
	private TipoTransacao tipoTransacao;
	private Tag tag;
	private SaldoDiario saldoDiario;
	private Calendar dtTransacao; 
	private Calendar dtHoraTransacao; 
	private double vlTransacao; 

	public Transacao(TbTransacao tbT) {
		this.idTransacao = tbT.getIdTransacao();
		this.tipoTransacao = new TipoTransacao(tbT.getTipoTransacao());
		this.dtTransacao = tbT.getDtTransacao();
		this.dtHoraTransacao = tbT.getDtHoraTransacao();
		this.vlTransacao = tbT.getVlTransacao();
	}

	public TbTransacao toEntity() {
		TbTransacao tbT = new TbTransacao();

		tbT.setIdTransacao(this.idTransacao);
		tbT.setTipoTransacao(this.tipoTransacao.toEntity());
		tbT.setCdTag(this.getTag().getCdTag());
		tbT.setDtTransacao(this.dtTransacao); 
		tbT.setDtHoraTransacao(this.dtHoraTransacao); 
		tbT.setVlTransacao(this.vlTransacao);

		return tbT;
	}
	
	public Transacao() {
		dtTransacao = Calendar.getInstance(); 
		dtHoraTransacao = Calendar.getInstance();
	}

	public void atualizaSaldo() throws DominioException {
		Calendar agora = Calendar.getInstance();
		
		this.dtTransacao = agora;
		this.dtHoraTransacao = agora;
		
		if (this.tipoTransacao.isCredito()) {
			tag.creditaSaldo(this.vlTransacao);
		} else {
			tag.debitaSaldo(this.vlTransacao);
		}
			
		saldoDiario = SaldoDiario.buscaPorTagData(this.tag, this.dtTransacao);
		saldoDiario.atualizaSaldo(this.tag);
	}

	public void validate() throws DominioException {
		if (this.tag == null || !this.tag.isPreenchido()) {
			throw new DominioException("Veículo deve ser preenchido");
		}
		
		if (this.dtTransacao == null) {
			throw new DominioException("Data deve ser preenchida");
		}
		
		if (this.dtHoraTransacao == null) {
			throw new DominioException("Hora deve ser preenchida");
		}
		
		Calendar dataHora = Calendar.getInstance();
		dataHora.set(this.dtTransacao.get(Calendar.YEAR)
					, this.dtTransacao.get(Calendar.MONTH)
					, this.dtTransacao.get(Calendar.DATE)
					, this.dtHoraTransacao.get(Calendar.HOUR_OF_DAY)
					, this.dtHoraTransacao.get(Calendar.MINUTE));	
		
		if (Calendar.getInstance().before(dataHora)) {
			throw new DominioException("Não são permitidos lançamentos futuros");
		}

		if (this.tipoTransacao == null || !this.tipoTransacao.isPreenchido()) {
			throw new DominioException("Tipo de Transação deve ser preenchido");
		}

		if (this.vlTransacao <= 0) {
			throw new DominioException("Valor deve ser maior que zero(0)");
		}

		if (!this.tipoTransacao.isCredito() && this.tag.getVlSaldo() < this.vlTransacao) {
			throw new DominioException("Saldo insuficiente");
		}
		
	}
	
	
	
	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}


	public Calendar getDtTransacao() {
		return dtTransacao;
	}

	public Calendar getDtHoraTransacao() {
		return dtHoraTransacao;
	}

	public double getVlTransacao() {
		return vlTransacao;
	}

	public void setVlTransacao(double vlTransacao) {
		this.vlTransacao = vlTransacao;
	}

	public Integer getIdTransacao() {
		return idTransacao;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	
	public SaldoDiario getSaldoDiario() {
		return saldoDiario;
	}

	public void setSaldoDiario(SaldoDiario saldoDiario) {
		this.saldoDiario = saldoDiario;
	}
	
	public boolean isSaldoDiarioNovo() {
		return this.saldoDiario.isNovo();
	}
	
}
