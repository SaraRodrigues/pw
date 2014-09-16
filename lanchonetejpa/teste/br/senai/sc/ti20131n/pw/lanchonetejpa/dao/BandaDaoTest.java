package br.senai.sc.ti20131n.pw.lanchonetejpa.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.introducaojpa.entity.Banda;
import br.senai.sc.ti20131n.pw.introducaojpa.util.JpaUtil;

public class BandaDaoTest {

	// Manipulação do banco para garantir o estado inicial do teste

	private void resetTableBanda() {
		String query = "truncate banda;";
		executeQuery(query);
	}

	private void insertId1() {
		String query = "INSERT INTO banda(id,nome,historia) VALUES(1,'Banda 1','History')";
		executeQuery(query);
	}

	private void insertId10() {
		String query = "INSERT INTO banda(nome,historia) VALUES('Banda 1','Historia banda 1')";

		for (int i = 2; i <= 10; i++)
			query = query.concat(",('Banda " + i + "','Historia da banda " + i
					+ "')");

		executeQuery(query);
	}

	private void executeQuery(String query) {
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.createNativeQuery(query).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}

	// fim de manipulação do banco

	@BeforeClass
	public static void init() {
		JpaUtil.initFactory();
	}

	@AfterClass
	public static void finish() {
		JpaUtil.closeFactory();
	}

	private EntityManager entityManager;
	private BandaDao dao;

	@Before
	public void begin() {
		resetTableBanda();
		entityManager = JpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		dao = new BandaDao(entityManager);
	}

	@After
	public void close() {
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManager = null;
		dao = null;
	}

	@Test
	public void testSalvar() {
		Banda banda = new Banda();
		banda.setNome("Banda ...");
		banda.setHistoria("Hitória da banda");

		dao.salvar(banda);
	}

	@Test
	public void buscarBandaPorId() {
		insertId1();

		testSalvar();
		Banda banda = dao.buscarPorId(1L);
		assertNotNull(banda);
	}

	@Test
	public void excluirABanda1() {
		insertId1();

		dao.excluirBandaPorId(1L);
		assertNull(dao.buscarPorId(1L));
	}

	@Test
	public void listarTodos() {
		insertId10();

		List<Banda> bandas = dao.listarTodos();

		assertEquals(10, bandas.size());
	}
	
	@Test
	public void listarPorNome() {
		insertId10();
		
		List<Banda> bandas = dao.listarBandaPorNome("Banda 1");
		
		assertEquals(1, bandas.size());
	}

	@Test
	public void editarBanda() {
		insertId1();

		testSalvar();
		Banda banda = new Banda();
		banda.setId(1L);
		banda.setNome("Banda nova");
		banda.setHistoria("Hitória da banda nova");
		dao.atualizar(banda);

		assertEquals("Banda nova", dao.buscarPorId(1L).getNome());
	}
}
