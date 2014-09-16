package br.senai.sc.ti20131n.pw.introducaojpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.introducaojpa.entity.Banda;

public class BandaDao {
	
	private EntityManager entityManager;

	public BandaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void salvar(Banda banda){
		entityManager.persist(banda);
	}

	public Banda buscarPorId(Long id) {
		return entityManager.find(Banda.class, id);
	}

	public void excluirBandaPorId(Long l) {
		Banda banda = entityManager.getReference(Banda.class, l);
		entityManager.remove(banda);
	}

	public List<Banda> listarTodos() {
		Query query = entityManager
				.createQuery("From Banda",Banda.class);
		
		return query.getResultList();
	}

	public void atualizar(Banda banda) {
		entityManager.merge(banda);
	}

	public List<Banda> listarBandaPorNome(String nome) {
		
		
		Query query = entityManager
				.createQuery("From Banda b Where b.nome = ?",Banda.class);
		query.setParameter(1, nome);
		return query.getResultList();
	}
}
