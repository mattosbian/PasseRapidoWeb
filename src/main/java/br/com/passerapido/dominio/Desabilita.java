package br.com.passerapido.dominio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.passerapido.entity.TbSaldoDiario;
import br.com.passerapido.entity.TbTag;
import br.com.passerapido.entity.TbTransacao;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Desabilita {
	
	private Cliente cliente;
	
	private List<Tag> tagsDe;
	
	private Tag tagDe;
	private Tag tagPara;
	
	public Desabilita(Login login) {
		if (login != null) {
			carregaDados(login.getCpf());
		}
	}

	
	private void carregaDados(String cpf) {
		this.cliente = Cliente.buscaPorCPF(cpf);
		
		this.tagsDe = Tag.buscaPorIdClienteAtivas(cliente.getId());
	}

	
	public void validate() throws DominioException {
		if (this.tagDe == null || !this.tagDe.isPreenchido()) {
			throw new DominioException("Veículo para desabilitar deve ser preenchido");
		}

		this.tagDe.validate();
		
		if (this.tagDe.getVlSaldo() > 0) {
			if (this.tagsDe.size() > 1) {
				if	(this.tagPara == null || !this.tagPara.isPreenchido()) {
					throw new DominioException("Veículo para transferir saldo deve ser preenchido");
				}
			} else {
				throw new DominioException("Não existem veículos para transferir saldo");
			}

			this.tagPara.validate();
		}
	}
	
	public void salvar() throws DominioException  {

		EntityManager em = EntityManagerUtil.getEntityManager();

		TransferenciaSaldo transfereSaldo;
		boolean transfere = false;

		tagDe.desativa();

		if (this.tagDe.getVlSaldo() > 0) { //transferir
			transfere = true;
		}

			try {

				if (!transfere) {
					em.getTransaction().begin();

					TbTag tbTagDe = tagDe.toEntity();
					tbTagDe = em.merge(tbTagDe);
					
					em.getTransaction().commit();
					
				} else {

					transfereSaldo = new TransferenciaSaldo(tagDe, tagPara);
					transfereSaldo.validate();
					transfereSaldo.atualizaSaldo();
	
					Transacao transacaoDe = transfereSaldo.getTransacaoDe();
					Transacao transacaoPara = transfereSaldo.getTransacaoPara();
				
					TbTransacao tbTranDe = transacaoDe.toEntity();
					TbTransacao tbTranPara = transacaoPara.toEntity();
	
					TbTag tbTagDe = transacaoDe.getTag().toEntity();
					TbTag tbTagPara = transacaoPara.getTag().toEntity();
					
					TbSaldoDiario tbSaldoDe = transacaoDe.getSaldoDiario().toEntity();
					TbSaldoDiario tbSaldoPara = transacaoPara.getSaldoDiario().toEntity();
	
					em.getTransaction().begin();
	
					em.persist(tbTranDe);
					em.persist(tbTranPara);
					
					tbTagDe = em.merge(tbTagDe);
					tbTagPara = em.merge(tbTagPara);
	
					if (transacaoDe.isSaldoDiarioNovo()) {
						em.persist(tbSaldoDe);
					} else {
						tbSaldoDe = em.merge(tbSaldoDe);
					}
	
					if (transacaoPara.isSaldoDiarioNovo()) {
						em.persist(tbSaldoPara);
					} else {
						tbSaldoPara = em.merge(tbSaldoPara);
					}
	
					em.getTransaction().commit();
				}
	
		} catch (DominioException e) {
			throw new DominioException(e.getMessage(), e);
		} catch (PersistenceException e) {
           em.getTransaction().rollback();             
		}	
		
	}


	public List<Tag> getTagsDe() {
		return tagsDe;
	}


	public Tag getTagDe() {
		return tagDe;
	}


	public void setTagDe(Tag tagDe) {
		this.tagDe = tagDe;
	}


	public Tag getTagPara() {
		return tagPara;
	}


	public void setTagPara(Tag tagPara) {
		this.tagPara = tagPara;
	}


}
