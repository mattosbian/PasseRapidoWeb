package br.com.passerapido.dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.TbTransacao;
import br.com.passerapido.util.EntityManagerUtil;

public class TransacaoExtrato {

	private TipoTransacao tipoTransacao;
	private String dtTransacao; 
	private double vlCredito;
	private double vlDebito;
	private double vlSaldo;

	
	public static List<TransacaoExtrato> listaPorData(Extrato extrato) {

		EntityManager em = EntityManagerUtil.getEntityManager();
		
		TypedQuery<TbTransacao> query;
		
		if (extrato.getTipoTransacao().isPreenchido()) {
			query = em.createNamedQuery(TbTransacao.POR_TAG_DATA_TIPO, TbTransacao.class);
			query.setParameter("cdTipoTransacao", extrato.getTipoTransacao().getCdTipoTransacao());
		} else {
			query = em.createNamedQuery(TbTransacao.POR_TAG_DATA, TbTransacao.class);
		}
		
		query.setParameter("cdTag", extrato.getTag().getCdTag());
		query.setParameter("dtInicial", extrato.getDtInicial());
		query.setParameter("dtFinal", extrato.getDtFinal());

		List<TbTransacao> resultList = query.getResultList();

		
		if (resultList == null) {
			return null;
		}

		ArrayList<TransacaoExtrato> lista = new ArrayList<TransacaoExtrato>();

		SaldoDiario saldoDiario = null;
		
		for (TbTransacao tb : resultList) {
			TransacaoExtrato transacaoExtrato = new TransacaoExtrato(tb);
			
			if (saldoDiario == null || !saldoDiario.getDtSaldo().equals(tb.getDtTransacao())) {
				saldoDiario = SaldoDiario.buscaPorTagData(extrato.getTag(),tb.getDtTransacao());
			}

			transacaoExtrato.setVlSaldo(saldoDiario.getVlSaldo());
			
			lista.add(transacaoExtrato);
		}
		
		return lista;
	}
	
	
	public TransacaoExtrato(TbTransacao tbT) {
		this.tipoTransacao = new TipoTransacao(tbT.getTipoTransacao());
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.dtTransacao = df.format(tbT.getDtTransacao().getTime());
		
		if (this.tipoTransacao.isCredito()) {
			this.vlCredito = tbT.getVlTransacao();
		} else {
			this.vlDebito = tbT.getVlTransacao();
		}
	}

	
	
	
	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public String getDtTransacao() {
		return dtTransacao;
	}


	public double getVlCredito() {
		return vlCredito;
	}


	public double getVlDebito() {
		return vlDebito;
	}

	public double getVlSaldo() {
		return vlSaldo;
	}
	
	public void setVlSaldo(double vlSaldo) {
		this.vlSaldo = vlSaldo;
	}
	
}
