package br.com.passerapido.dominio;

import br.com.passerapido.entity.TbTipoTransacao;
import br.com.passerapido.exception.DominioException;

public class TransferenciaSaldo {
	
	private Transacao transacaoDe;
	private Transacao transacaoPara;
	
	private TipoTransacao tipoTransferencia;
	private TipoTransacao tipoCredito;

	public TransferenciaSaldo(Tag de, Tag para) {
		tipoTransferencia = TipoTransacao.buscaPorId(TbTipoTransacao.CD_TIPO_TRANSFERENCIA);
		tipoCredito = TipoTransacao.buscaPorId(TbTipoTransacao.CD_TIPO_CREDITO);
		
		transacaoDe = new Transacao();
		transacaoDe.setTipoTransacao(tipoTransferencia);
		transacaoDe.setTag(de);
		
		transacaoPara = new Transacao();
		transacaoPara.setTipoTransacao(tipoCredito);
		transacaoPara.setTag(para);

		transacaoDe.setVlTransacao(transacaoDe.getTag().getVlSaldo());
		transacaoPara.setVlTransacao(transacaoDe.getTag().getVlSaldo());
	}

	public void validate() throws DominioException {
		transacaoDe.validate();
		transacaoPara.validate();
	}

	public void atualizaSaldo() throws DominioException {
		
		transacaoDe.atualizaSaldo();
		transacaoPara.atualizaSaldo();
	}

	public Transacao getTransacaoDe() {
		return transacaoDe;
	}

	public Transacao getTransacaoPara() {
		return transacaoPara;
	}

}
