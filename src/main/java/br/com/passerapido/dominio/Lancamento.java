package br.com.passerapido.dominio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.passerapido.entity.TbSaldoDiario;
import br.com.passerapido.entity.TbTag;
import br.com.passerapido.entity.TbTransacao;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Lancamento {
	
	private List<Tag> tags;
	private List<TipoTransacao> tipos;
	
	private Transacao transacao;
	
	public Lancamento(Login login) {
		if (login != null) {
			carregaDados(login.getCpf());
		}
		
		carregaCombo();
	}

	private void carregaCombo() {
		this.tipos = TipoTransacao.buscaTodosNaoSistema();
	}

	
	private void carregaDados(String cpf) {
		Cliente cliente = Cliente.buscaPorCPF(cpf);
		
		this.tags = Tag.buscaPorIdClienteAtivas(cliente.getId());
	}
	

	public void salvar() throws DominioException  {

		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			transacao.validate();
			transacao.atualizaSaldo();

			TbTransacao tbTran = transacao.toEntity();
			TbTag tbTag = transacao.getTag().toEntity();
			TbSaldoDiario tbSaldo = transacao.getSaldoDiario().toEntity();

			em.getTransaction().begin();

			em.persist(tbTran);
			tbTag = em.merge(tbTag);

			if (transacao.isSaldoDiarioNovo()) {
				em.persist(tbSaldo);
			} else {
				tbSaldo = em.merge(tbSaldo);
			}

			em.getTransaction().commit();

		
		} catch (DominioException e) {
			throw new DominioException(e.getMessage(), e);
		} catch (PersistenceException e) {
           em.getTransaction().rollback();             
		}	
		
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<TipoTransacao> getTipos() {
		return tipos;
	}

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}


}
