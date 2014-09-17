package br.senai.sc.ti20131n.pw.gpe;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.gpe.entity.Canal;
import br.senai.sc.ti20131n.pw.gpe.entity.Programa;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class MainTestes {

	private static EntityManager entityManager;

		public static void main(String[] args) {
		JpaUtil.iniciarPersistenceUnit();

		entityManager = JpaUtil.getEntityManager();

		inserir();
		editar();
		excluir();
		listar();

		entityManager.close();	
		JpaUtil.fecharPersistenceUnit();
		}

			
		

		private static void listar() {
		Query query = entityManager.createQuery("From Canal" , Canal.class);
		List<Canal> canais = query.getResultList();

		for (Canal canal : canais){
		System.out.print("Numero:"  + canal.getNumero());
		System.out.println("-Nome:" + Canal.getNome());
		}

		}

		private  static void excluir(){
		entityManager.getTransaction().begin();
		Canal canal = entityManager.find(Canal.class, 1L);
		entityManager.remove(canal);
		entityManager.getTransaction().commit();
		}


		private static void editar() {
		entityManager.getTransaction().begin();
		Canal canalEditado = entityManager.find(Canal.class, 1L);
		canalEditado.setNome("Frozen");
		canalEditado.setNumero(42);

		entityManager.merge(canalEditado);
		entityManager.getTransaction().commit();

		}

		private static void inserirPrograma() {
		Canal canal = new Canal();
		canal.setNome("Globo");
		canal.setClassificacao(0);
		canal.setNumero(13);
		canal.setGenero("Comédia");
		
		Programa programa = new Programa();
		programa.setCanal(canal);
		programa.setClassificacao(0);
		programa.setNome("TV GLOBINHO");
		programa.setDescricao("Programa infantil");
		Calendar inicio = Calendar.getInstance();
		inicio.set(2014,Calendar.SEPTEMBER,16,10,0,0);
		Calendar fim = Calendar.getInstance();
		fim.set(2014,Calendar.SEPTEMBER,16,11,59,59);
		
		programa.setInicio (inicio.getTime());
		programa.setFim (fim.getTime());
		

		entityManager.getTransaction().begin();
		programa.setCanal(entityManager.find(Canal.class,7L));
		entityManager.persist(programa);
		entityManager.getTransaction().commit();
		
	
		
	
		}
}
