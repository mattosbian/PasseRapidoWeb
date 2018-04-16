package br.com.passerapido.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.entity.TbEstado;
import br.com.passerapido.entity.TbEstadoCivil;
import br.com.passerapido.entity.TbGenero;
import br.com.passerapido.entity.TbTag;
import br.com.passerapido.exception.CadastroException;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Lancamento {
	
	private List<Tag> tags;
	
	private Tag tag;
	
	private void inicio() {
		this.tags = new ArrayList<Tag>();
		this.tag = new Tag();
	}
	
	public Lancamento(Login login) {
		
		if (login == null) {
			inicio();
		} else {
			carregaDados(login.getCpf());
		}
		
	}

	private void carregaDados(String cpf) {
		Cliente cliente = Cliente.buscaPorCPF(cpf);
		
		this.tags = Tag.buscaPorIdCliente(cliente.getId());
	}
	

	public void recarregar(Integer valor) throws DominioException  {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			this.tag.carregar(valor);
			
			System.out.println("Lancamento saldoAtualizado :" + this.tag.getVlSaldo());
			//endereco.validate();

			TbTag tbTag= getTag().toEntity();
			
			//TbContaBancaria tbContaBancaria = conta.toEntity();
			
			em.getTransaction().begin();
			
			tbTag = em.merge(tbTag);

			em.getTransaction().commit();
		
		} catch (DominioException e) {
			System.out.println("lancamento - erro");
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

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
