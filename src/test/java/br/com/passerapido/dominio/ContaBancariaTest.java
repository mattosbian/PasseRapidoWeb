package br.com.passerapido.dominio;

import org.junit.Test;

import br.com.passerapido.exception.DominioException;

public class ContaBancariaTest {

	@Test(expected=DominioException.class)
	public void IdClienteDveSerPreenchido() throws DominioException {
		ContaBancaria conta = new ContaBancaria();
		conta.validate();
	}
}
