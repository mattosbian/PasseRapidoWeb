package br.com.passerapido.dominio;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.PKTbSaldoDiario;
import br.com.passerapido.entity.TbSaldoDiario;
import br.com.passerapido.util.EntityManagerUtil;

public class SaldoDiario {

	private Calendar dtSaldo;
	private Integer cdTag;
	private double vlSaldo; 
	private boolean novo;

	public SaldoDiario(TbSaldoDiario tbT) {
		this.dtSaldo = tbT.getPk().getDtSaldo();
		this.cdTag   = tbT.getPk().getCdTag();
		this.vlSaldo = tbT.getVlSaldo();
		
		this.novo = false;
	}

	public TbSaldoDiario toEntity() {
		TbSaldoDiario tbT = new TbSaldoDiario();

		PKTbSaldoDiario pk = new PKTbSaldoDiario();
		pk.setCdTag(this.cdTag);
		pk.setDtSaldo(this.dtSaldo);
		
		tbT.setPk(pk);
		tbT.setVlSaldo(this.vlSaldo);

		return tbT;
	}

	public static SaldoDiario buscaPorTagData(Tag tag, Calendar dt) {
		EntityManager em = EntityManagerUtil.getEntityManager();

		TypedQuery<TbSaldoDiario> query = em.createNamedQuery(TbSaldoDiario.POR_TAG_DATA, TbSaldoDiario.class);
		query.setParameter("cdTag", tag.getCdTag());
		query.setParameter("dtSaldo", dt);

		TbSaldoDiario tbSaldo;
		
		try {
			tbSaldo = query.getSingleResult();
		} catch (NoResultException e) {
			SaldoDiario saldoDiario = new SaldoDiario();
			saldoDiario.setCdTag(tag.getCdTag());
			saldoDiario.setDtSaldo(dt);
			saldoDiario.setVlSaldo(0);
			return saldoDiario;
		}

		return new SaldoDiario(tbSaldo);
	}

	public SaldoDiario() {
		this.novo = true;
	}

	public Calendar getDtSaldo() {
		return dtSaldo;
	}

	public void setDtSaldo(Calendar dtSaldo) {
		this.dtSaldo = dtSaldo;
	}

	public Integer getCdTag() {
		return cdTag;
	}

	public void setCdTag(Integer cdTag) {
		this.cdTag = cdTag;
	}

	public double getVlSaldo() {
		return vlSaldo;
	}

	public void setVlSaldo(double vlSaldo) {
		this.vlSaldo = vlSaldo;
	}

	public void atualizaSaldo(Tag tag) {
		this.vlSaldo = tag.getVlSaldo();
	}

	public boolean isNovo() {
		return novo;
	}

}
