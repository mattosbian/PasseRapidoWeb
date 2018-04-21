package br.com.passerapido.dominio;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.passerapido.exception.DominioException;

public class ExtratoTest {

	private Extrato extrato;

	@Before
	public void setUp() {
		extrato = new Extrato();
		
		Calendar hoje = Calendar.getInstance();
		Calendar ontem = Calendar.getInstance();
		ontem.add(Calendar.DAY_OF_MONTH, -1);

		Tag tag = new Tag();
		tag.setCdTag(1);

		TipoTransacao tipoTransacao = new TipoTransacao();
		tipoTransacao.setCdTipoTransacao(1);
		
		extrato.setDtFinal(hoje);
		extrato.setDtInicial(ontem);
		extrato.setTag(tag);
		extrato.setTipoTransacao(tipoTransacao);
	}
	
	@Test(expected=DominioException.class)
	public void TagDeveSerPreenchida() throws DominioException {
		extrato.getTag().setCdTag(null);
		extrato.validate();
	}
	
	@Test(expected=DominioException.class)
	public void TagNaoPodeSerNUll() throws DominioException {
		extrato.setTag(null);
		extrato.validate();
	}
	
	@Test(expected=DominioException.class)
	public void DataInicialNaoPodeSerNUll() throws DominioException {
		extrato.setDtInicial(null);
		extrato.validate();
	}

	@Test(expected=DominioException.class)
	public void DataFinalNaoPodeSerNUll() throws DominioException {
		extrato.setDtFinal(null);
		extrato.validate();
	}

	@Test(expected=DominioException.class)
	public void DataInicialMaiorDataFinal() throws DominioException {
		Calendar futuro = Calendar.getInstance(); 
		futuro.setTime(extrato.getDtFinal().getTime());
		futuro.add(Calendar.DAY_OF_MONTH, 1);
		
		extrato.setDtInicial(futuro);
		extrato.validate();
	}
	
	
}
