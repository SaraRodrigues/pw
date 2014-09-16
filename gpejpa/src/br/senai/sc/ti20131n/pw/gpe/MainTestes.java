package br.senai.sc.ti20131n.pw.gpe;

import javax.persistence.EntityManager;

import br.senai.sc.ti20131n.pw.gpe.entity.Canal;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class MainTestes {

	private static EntityManager entityManager;

	public static void main(String[] args) {
		JpaUtil.iniciarPersistenceUnit();

		entityManager = JpaUtil.getEntityManager();
		
		inserir();
		editar();
		
		entityManager.close();		
		JpaUtil.fecharPersistenceUnit();
	}

	private static void editar() {
		entityManager.getTransaction().begin();
		Canal canalEditado = entityManager.find(Canal.class, 1L);
		canalEditado.setNome("Frozen");
		canalEditado.setNumero(42);
		
		entityManager.merge(canalEditado);
		entityManager.getTransaction().commit();
		
	}

	private static void inserir() {
		Canal canal = new Canal();
		canal.setNome("Cool");
		canal.setClassificacao(0);
		canal.setNumero(42);
		canal.setGenero("Comédia");
		
		entityManager.getTransaction().begin();
		entityManager.persist(canal);
		entityManager.getTransaction().commit();
	}

}
