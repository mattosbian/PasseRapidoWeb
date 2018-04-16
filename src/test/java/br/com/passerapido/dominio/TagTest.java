package br.com.passerapido.dominio;

import org.junit.Before;
import org.junit.Test;

import br.com.passerapido.exception.DominioException;

public class TagTest {

	private Tag tag;
	private Veiculo veiculo;
	
	@Before
	public void setUp() {
		tag = new Tag();
		veiculo = new Veiculo();
		
		veiculo.setCdVeiculo(1);
		veiculo.setIdCliente(1);
		
		tag.setCdTag(1);
		tag.setVeiculo(veiculo);
	}
	
	@Test(expected=DominioException.class)
	public void cdTagDeveSerPreenchido() throws DominioException {
		tag.setCdTag(0);
		tag.validate();
	}

	@Test(expected=DominioException.class)
	public void cdTagNaoPodeSerNull() throws DominioException {
		tag.setCdTag(null);
		tag.validate();
	}

	@Test(expected=DominioException.class)
	public void veiculoNaoPodeSerNull() throws DominioException {
		tag.setVeiculo(null);
		tag.validate();
	}


}
