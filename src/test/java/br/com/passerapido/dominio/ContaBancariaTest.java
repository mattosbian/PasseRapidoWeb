package br.com.passerapido.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContaBancariaTest {

	@Test
	public void isAlgumCampoPreenchido() {
		ContaBancaria conta = new ContaBancaria();
		assertEquals(conta.isAlgumCampoPreenchido(), false);

		conta.setNmBanco("Banco");
		assertEquals(conta.isAlgumCampoPreenchido(), true);

		conta.setNmBanco("");
		assertEquals(conta.isAlgumCampoPreenchido(), false);

		conta.setNrAgencia("Agencia");
		assertEquals(conta.isAlgumCampoPreenchido(), true);

		conta.setNrAgencia("");
		assertEquals(conta.isAlgumCampoPreenchido(), false);
		
		conta.setNrConta("Conta");
		assertEquals(conta.isAlgumCampoPreenchido(), true);

		conta.setNrConta("");
		assertEquals(conta.isAlgumCampoPreenchido(), false);
		
		conta.setNrDigito(0);
		assertEquals(conta.isAlgumCampoPreenchido(), true);

		conta.setNrDigito(null);
		assertEquals(conta.isAlgumCampoPreenchido(), false);
		
	}
}
