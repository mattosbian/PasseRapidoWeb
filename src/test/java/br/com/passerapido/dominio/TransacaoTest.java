package br.com.passerapido.dominio;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.passerapido.entity.TbTipoTransacao;
import br.com.passerapido.exception.DominioException;

public class TransacaoTest {

	private Transacao tran;
	private Tag tag;
	private TipoTransacao tipo;
	private TbTipoTransacao tbTipo;
	
	@Before
	public void setUp() {


		tag = new Tag();
		try {
			tag.creditaSaldo(100);
		} catch (DominioException e) {
			e.printStackTrace();
		}

		tbTipo = new TbTipoTransacao();
		tbTipo.setCdTipoTransacao(1);
		tbTipo.setNmTipoTransacao("debito");
		tbTipo.setFlCredito(0);
		
		tipo = new TipoTransacao(tbTipo);
		
		tran = new Transacao();
//		tran.setDtHoraTransacao(Calendar.getInstance());
//		tran.setDtTransacao(Calendar.getInstance());
		tran.setTag(tag);
		tran.setVlTransacao(100);
		tran.setTipoTransacao(tipo);
	}
	
//	@Test(expected=DominioException.class)
//	public void DataDeveSerPreenchida() throws DominioException {
//		tran.setDtTransacao(null);
//		tran.validate();
//	}
//
//	@Test(expected=DominioException.class)
//	public void HoraDeveSerPreenchida() throws DominioException {
//		tran.setDtHoraTransacao(null);
//		tran.validate();
//	}

	@Test(expected=DominioException.class)
	public void DataHoraNaoFutura() throws DominioException {
		tran.getDtHoraTransacao().add(Calendar.MINUTE, 1);
		tran.validate();
	}

	@Test(expected=DominioException.class)
	public void ValorMaiorQueZero() throws DominioException {
		tran.setVlTransacao(101);
		tran.validate();
	}

}
