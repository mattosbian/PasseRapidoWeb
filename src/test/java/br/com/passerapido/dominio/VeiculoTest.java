package br.com.passerapido.dominio;

import org.junit.Before;
import org.junit.Test;

import br.com.passerapido.exception.DominioException;

public class VeiculoTest {
	Veiculo veiculo;

	@Before
	public void setUp() {
		veiculo = new Veiculo();
		
		veiculo.setCdVeiculo(1);
		veiculo.setIdCliente(1);
		
	}
	
	@Test(expected=DominioException.class)
	public void cdVeiculoDeveSerPreenchido() throws DominioException {
		veiculo.setCdVeiculo(0);
		veiculo.validate();
	}
	
	@Test(expected=DominioException.class)
	public void cdVeiculoNaoPodeSerNull() throws DominioException {
		veiculo.setCdVeiculo(null);
		veiculo.validate();
	}

	@Test(expected=DominioException.class)
	public void idClienteDeveSerPreenchido() throws DominioException {
		veiculo.setIdCliente(0);
		veiculo.validate();
	}
	
	@Test(expected=DominioException.class)
	public void idClienteNaoPodeSerNull() throws DominioException {
		veiculo.setIdCliente(null);
		veiculo.validate();
	}

}
