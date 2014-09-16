package br.senai.sc.ti20131n.pw.gpe.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory entityManagerFactory;

	public static void iniciarPersistenceUnit() {
		if (entityManagerFactory == null)
			entityManagerFactory = Persistence
					.createEntityManagerFactory("gpe_pu");
	}
	
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static void fecharPersistenceUnit() {
		entityManagerFactory.close();		
	}
}
